package com.hn.multitenancy.service.config;

import com.baomidou.mybatisplus.core.MybatisConfiguration;
import com.baomidou.mybatisplus.core.MybatisXMLLanguageDriver;
import com.baomidou.mybatisplus.core.config.GlobalConfig;
import com.baomidou.mybatisplus.core.parser.ISqlParser;
import com.baomidou.mybatisplus.core.parser.ISqlParserFilter;
import com.baomidou.mybatisplus.core.parser.SqlParserHelper;
import com.baomidou.mybatisplus.extension.parsers.BlockAttackSqlParser;
import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import com.baomidou.mybatisplus.extension.plugins.tenant.TenantSqlParser;
import com.baomidou.mybatisplus.extension.spring.MybatisSqlSessionFactoryBean;
import com.hn.multitenancy.service.bean.RoutingDataSource;
import com.hn.multitenancy.service.enums.DBTypeEnum;
import com.hn.multitenancy.service.handler.MyTenantHandler;
import com.hn.multitenancy.service.handler.MyTenantSchemaHandler;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.type.JdbcType;
import org.mybatis.spring.transaction.SpringManagedTransactionFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@EnableTransactionManagement
@Configuration
public class MybatisPlusConfig {

    @Resource
    private MyTenantHandler myTenantHandler;

    @Resource
    private MyTenantSchemaHandler myTenantSchemaHandler;

    /**
     * 分页插件
     */
    @Bean
    public PaginationInterceptor paginationInterceptor() {
        PaginationInterceptor paginationInterceptor = new PaginationInterceptor();
        List<ISqlParser> sqlParserList = new ArrayList<>();
        // 攻击 SQL 阻断解析器、加入解析链
        sqlParserList.add(new BlockAttackSqlParser());

        // 多租户拦截

        // 同一个数据源,同一个schema
        TenantSqlParser tenantSqlParser = new TenantSqlParser();
        tenantSqlParser.setTenantHandler(myTenantHandler);
        sqlParserList.add(tenantSqlParser);

        // 同一个数据源,不同schema
//        TenantSchemaSqlParser tenantSchemaSqlParser = new TenantSchemaSqlParser();
//        MyTenantSchemaSqlParser myTenantSchemaSqlParser = new MyTenantSchemaSqlParser();
//        myTenantSchemaSqlParser.setMyTenantSchemaHandler(myTenantSchemaHandler);
//        sqlParserList.add(myTenantSchemaSqlParser);

        // 动态表名解析器, 3.2.0版本以上才有
//        DynamicTableNameParser dynamicTableNameParser = new DynamicTableNameParser();
//        Map<String, ITableNameHandler> tableNameHandlerMap = new HashMap<>();
        // Map的key就是需要替换的原始表名
//        tableNameHandlerMap.put("user",new ITableNameHandler(){
//            @Override
//            public String dynamicTableName(MetaObject metaObject, String sql, String tableName) {
//                return "user_" + myTenantHandler.getTenantId(true);
//            }
//        });
//        dynamicTableNameParser.setTableNameHandlerMap(tableNameHandlerMap);
//        sqlParserList.add(dynamicTableNameParser);

        paginationInterceptor.setSqlParserList(sqlParserList);

        // 特定SQL过滤
        paginationInterceptor.setSqlParserFilter(new ISqlParserFilter() {
            @Override
            public boolean doFilter(MetaObject metaObject) {
                MappedStatement mappedStatement = SqlParserHelper.getMappedStatement(metaObject);
                // dao中的方法
                if ("com.hn.multitenancy.service.dao.UserMapper.updateById".equals(mappedStatement.getId())) {
                    return true;
                }
                return false;
            }
        });

        return paginationInterceptor;
    }


    @Bean
    @ConfigurationProperties("spring.datasource.dynamic.datasource.china")
    public DataSource chinaDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean
    @ConfigurationProperties("spring.datasource.dynamic.datasource.singapore")
    public DataSource singaporeDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean
    public DataSource routingDataSource(
            @Qualifier("chinaDataSource") DataSource chinaDataSource,
            @Qualifier("singaporeDataSource") DataSource singaporeDataSource
    ) {
        log.info("[master] - dataSource class : {}", chinaDataSource.getClass());
        log.info("[slave] - dataSource class : {}", singaporeDataSource.getClass());

        Map<Object, Object> targetDataSources = new HashMap<>();
        targetDataSources.put(DBTypeEnum.CHINA, chinaDataSource);
        targetDataSources.put(DBTypeEnum.SINGAPORE, singaporeDataSource);
        RoutingDataSource routingDataSource = new RoutingDataSource();
        routingDataSource.setDefaultTargetDataSource(chinaDataSource);
        routingDataSource.setTargetDataSources(targetDataSources);
        return routingDataSource;
    }

    @Bean
    public SqlSessionFactory sqlSessionFactory(
            @Qualifier("routingDataSource") DataSource routingDataSource,
            @Qualifier("paginationInterceptor") PaginationInterceptor paginationInterceptor
    ) throws Exception {

        Connection connection = routingDataSource.getConnection();
        log.info("connection class : {}", connection.getClass());

        MybatisSqlSessionFactoryBean sqlSessionFactoryBean = new MybatisSqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(routingDataSource);
        sqlSessionFactoryBean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath*:mybatis/mapper/*.xml"));
        sqlSessionFactoryBean.setTypeAliasesPackage("com.hn.multitenancy.share.model");

        MybatisConfiguration configuration = new MybatisConfiguration();
        configuration.setDefaultScriptingLanguage(MybatisXMLLanguageDriver.class);
        configuration.setJdbcTypeForNull(JdbcType.NULL);
        configuration.addInterceptor(paginationInterceptor);

        sqlSessionFactoryBean.setConfiguration(configuration);

        GlobalConfig globalConfig = new GlobalConfig();
        globalConfig.setBanner(false);
//        globalConfig.setSqlParserCache(true);

        sqlSessionFactoryBean.setGlobalConfig(globalConfig);

        sqlSessionFactoryBean.setTransactionFactory(new SpringManagedTransactionFactory());
        return sqlSessionFactoryBean.getObject();
    }

    @Bean
    public PlatformTransactionManager platformTransactionManager(@Qualifier("routingDataSource") DataSource routingDataSource) {
        return new DataSourceTransactionManager(routingDataSource);
    }

}
