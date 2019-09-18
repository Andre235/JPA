package com.demo.utils;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

/**
 * @author : 赵静超
 * @date : 2019/9/18 16:44
 * @description : 解决实体管理器factory工厂浪费资源和耗时问题
 *                    通过静态代码块行驶，当程序第一次访问次工具类时，创建一个公共的实体管理器工厂，供多个线程持续访问
 *                当程序第一次调用getEntityManager()方法时，程序进入静态代码快创建一个factory放到内存中，再调用方法创建一个实体管理器
 *                当程序第二次调用getEntityManager()方法时，直接调用factory工厂对象创建实体管理器
 */
public class JPAUtils {
    private static EntityManagerFactory factory;
    static{
        factory = Persistence.createEntityManagerFactory("myJPA");
    }
    public static EntityManager getEntityManager(){
        return factory.createEntityManager();
    }
}
