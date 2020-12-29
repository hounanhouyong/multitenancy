package com.hn.multitenancy.service.handler;
//
//import com.baomidou.mybatisplus.extension.plugins.tenant.TenantSchemaHandler;
//import com.baomidou.mybatisplus.extension.plugins.tenant.TenantSchemaSqlParser;
//import com.baomidou.mybatisplus.generator.config.po.TableInfo;
//import net.sf.jsqlparser.schema.Table;
//import net.sf.jsqlparser.statement.delete.Delete;
//import net.sf.jsqlparser.statement.insert.Insert;
//import net.sf.jsqlparser.statement.select.SelectBody;
//import net.sf.jsqlparser.statement.update.Update;
//
//
//public class MyTenantSchemaSqlParser extends TenantSchemaSqlParser {
//
//    private MyTenantSchemaHandler myTenantSchemaHandler;
//
//    @Override
//    public void processInsert(Insert insert) {
////        if (!this.getTenantSchemaHandler().doTableFilter(insert.getTable().getName())) {
//            String tableName = insert.getTable().getName();
//            String schemaName = myTenantSchemaHandler.getTenantSchema();
//            insert.setTable(new Table(schemaName, tableName));
////        }
//    }
//
//    @Override
//    public void processDelete(Delete delete) {
//
//    }
//
//    @Override
//    public void processUpdate(Update update) {
//
//    }
//
//    @Override
//    public void processSelectBody(SelectBody selectBody) {
//
//    }
//
//
//    public MyTenantSchemaHandler getMyTenantSchemaHandler() {
//        return this.myTenantSchemaHandler;
//    }
//
//    public MyTenantSchemaSqlParser setMyTenantSchemaHandler(final MyTenantSchemaHandler myTenantSchemaHandler) {
//        this.myTenantSchemaHandler = myTenantSchemaHandler;
//        return this;
//    }
//
//    public String toString() {
//        return "MyTenantSchemaSqlParser(myTenantSchemaHandler=" + this.getMyTenantSchemaHandler() + ")";
//    }
//}
