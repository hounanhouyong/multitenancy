//package com.hn.multitenancy.service.monitor;
//
//import com.zaxxer.hikari.HikariPoolMXBean;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.scheduling.annotation.Scheduled;
//import org.springframework.stereotype.Component;
//
//import javax.management.JMX;
//import javax.management.MBeanServer;
//import javax.management.MalformedObjectNameException;
//import javax.management.ObjectName;
//import java.lang.management.ManagementFactory;
//
//@Slf4j
//@Component
//public class HikariMonitor {
//
//    @Scheduled(cron = "0/3 * * * * ?")
//    public void HikariMonitor() {
//
//        MBeanServer mBeanServer = ManagementFactory.getPlatformMBeanServer();
//        ObjectName poolName = null;
//        try {
//            poolName = new ObjectName("com.zaxxer.hikari:type=Pool (multiTenantHikariCP)");
//        } catch (MalformedObjectNameException e) {
//            e.printStackTrace();
//        }
//
//        if (poolName == null) {
//            return;
//        }
//
//        HikariPoolMXBean poolProxy = JMX.newMXBeanProxy(mBeanServer, poolName, HikariPoolMXBean.class);
//
//        if (poolProxy == null) {
//            log.info("Hikari not initialized, please wait...");
//        } else {
//            log.info("HikariPoolState = "
//                    + "Active=[" + poolProxy.getActiveConnections() + "] "
//                    + "Idle=[" + poolProxy.getIdleConnections() + "] "
//                    + "Wait=["+poolProxy.getThreadsAwaitingConnection()+"] "
//                    + "Total=["+poolProxy.getTotalConnections()+"]");
//        }
//    }
//
//}
