package com.demo.test;

import com.demo.utils.JPAUtils;
import org.junit.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import java.util.List;

/**
 * @author : 赵静超
 * @date : 2019/9/18 18:21
 * @description : 测试jpql相关方法
 *                jpqlCRUD步骤：
 *                    1.编写jpql语句
 *                    2.创建query对象
 *                    3.执行语句返回结果集
 *
 */
public class JpqlTest {

    /**
     * jpql查询全部操作
     * sql:select * from cst_customer
     * jpql:from cst_customer
     */
    @Test
    public void testFindAll(){
        EntityManager entityManager = JPAUtils.getEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        String jpql= "from com.demo.entity.Customer";
        Query query = entityManager.createQuery(jpql);
        List resultList = query.getResultList();
        for (Object result : resultList) {
            System.out.println(result);
        }
        transaction.commit();
        entityManager.close();
    }

    /**
     * 根据ID倒序排列
     */
    @Test
    public void testOrderById(){
        EntityManager entityManager = JPAUtils.getEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        String jpql= "from Customer order by custId desc ";
        Query query = entityManager.createQuery(jpql);
        List resultList = query.getResultList();
        for (Object result : resultList) {
            System.out.println(result);
        }
        transaction.commit();
        entityManager.close();
    }

    /**
     * 统计客户总数
     */
    @Test
    public void testCount(){
        EntityManager entityManager = JPAUtils.getEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        String jpql= "select count (custId) from Customer ";
        Query query = entityManager.createQuery(jpql);
        Object result = query.getSingleResult();  //获取单一结果集
        System.out.println(result);
        transaction.commit();
        entityManager.close();
    }

    /**
     * 统计客户总数
     */
    @Test
    public void testPage(){
        EntityManager entityManager = JPAUtils.getEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        String jpql= "from Customer ";
        Query query = entityManager.createQuery(jpql);
        query.setFirstResult(0);  //从0开始查询，但是不包含0
        query.setMaxResults(2);
        List list = query.getResultList();
        for (Object o : list) {
            System.out.println(o);
        }
        transaction.commit();
        entityManager.close();
    }

    /**
     * 根据客户姓名进行模糊查询
     */
    @Test
    public void testCondition(){
        EntityManager entityManager = JPAUtils.getEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        String jpql= "from Customer where custName like ?";
        Query query = entityManager.createQuery(jpql);
        //进行传参
        query.setParameter(1,"尚硅谷%");  //对参数进行赋值
        List list = query.getResultList();
        for (Object o : list) {
            System.out.println(o);
        }
        transaction.commit();
        entityManager.close();
    }


}
