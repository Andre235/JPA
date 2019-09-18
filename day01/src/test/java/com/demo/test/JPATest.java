package com.demo.test;

import com.demo.entity.Customer;
import com.demo.utils.JPAUtils;
import org.junit.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

/**
 * @author : 赵静超
 * @date : 2019/9/18 13:08
 * @description : 测试类
 */
public class JPATest {

    /**
     * 测试jpa保存
     * jpa操作步骤
     *      1.加载配置文件获取实体管理器类工厂对象
     *      2.通过工厂对象获取实体管理器
     *      3.获取事务对象，开启事务
     *      4.完成增删改查操作
     *      5.提交事务(出现异常则回滚事务)
     *      6.释放资源
     *
     *      补充说明：
     *          EntityManagerFactory创建过程中比较浪费性能资源(耗时较高)
     *          是线程安全对象，不会出现多个线程操作同一个对象的问题
     *      如何解决浪费性能问题？
     *          将这些对象放在静态代码块中，程序启动的时候将这些对象加载到内存中，产生一个公共的factory对象，开辟一份空间供多个线程持续访问
     *          用static代码块形式创建factory对象
     */

    /**
     * merge()更新方法、remove()删除方法、find/getReference()根据id查询方法
     */
    @Test
    public void testSave(){
        EntityManager entityManager = JPAUtils.getEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        //3.1开启事务
        transaction.begin();
        Customer customer = new Customer();
        customer.setCustName("北大青鸟");
        customer.setCustIndustry("教育行业");
        //保存操作
        entityManager.persist(customer);
        transaction.commit();
        entityManager.close();
    }

    /**
     * 根据id查询客户
     * 立即加载
     */
    @Test
    public void testFind(){
        //1.根据工具类获取EntityManager实体管理器
        EntityManager entityManager = JPAUtils.getEntityManager();
        //2.获取事务，开启事务
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        //3.创建CRUD语句
            //查询数据的结果需要包装的实体类类型的字节码文件
            //查询主键id的取值
        Customer customer = entityManager.find(Customer.class, 1l);
        System.out.println(customer);
        //4.提交事务
        transaction.commit();
        //5.关闭资源
        entityManager.close();
    }

    /**
     * 测试GetReference方法
     * 延迟加载，返回的是一个代理对象（什么时候用什么时候查）
     */
    @Test
    public void testGetReference(){
        //1.根据工具类获取EntityManager实体管理器
        EntityManager entityManager = JPAUtils.getEntityManager();
        //2.获取事务，开启事务
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        //3.创建CRUD语句
        //查询数据的结果需要包装的实体类类型的字节码文件
        //查询主键id的取值
        Customer customer = entityManager.getReference(Customer.class, 1l);
        System.out.println(customer);
        //4.提交事务
        transaction.commit();
        //5.关闭资源
        entityManager.close();
    }

    /**
     * remove()删除方法
     */
    @Test
    public void testRemove(){
        //1.根据工具类获取EntityManager实体管理器
        EntityManager entityManager = JPAUtils.getEntityManager();
        //2.获取事务，开启事务
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        //3.先根据id查新，再进行删除操作
        Customer customer = entityManager.find(Customer.class, 1l);
        entityManager.remove(customer);
        //4.提交事务
        transaction.commit();
        //5.关闭资源
        entityManager.close();
    }

    /**
     * merge()更新操作
     */
    @Test
    public void testMerge(){
        //1.根据工具类获取EntityManager实体管理器
        EntityManager entityManager = JPAUtils.getEntityManager();
        //2.获取事务，开启事务
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        //3.先根据id查新，再进行更新操作
        Customer customer = entityManager.find(Customer.class, 2l);
        customer.setCustIndustry("IT教育行业");
        entityManager.merge(customer);
        //4.提交事务
        transaction.commit();
        //5.关闭资源
        entityManager.close();
    }

}
