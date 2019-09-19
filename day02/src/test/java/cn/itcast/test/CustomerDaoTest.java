package cn.itcast.test;

import cn.itcast.dao.CustomerDao;
import cn.itcast.domain.Customer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author : 赵静超
 * @date : 2019/9/19 8:55
 * @description :
 */
@RunWith(SpringJUnit4ClassRunner.class)//声明spring提供的单元测试环境
@ContextConfiguration(locations ="classpath:applicationContext.xml")//指定spring容器的配置信息
public class CustomerDaoTest {

    @Autowired //将CustomerDao对象自动装配到spring容器中
    private CustomerDao customerDao;

    /**
     * 测试findOne方法
     */
    @Test
    public void testFindOne(){
        Customer one = customerDao.findOne(4l);
        System.out.println(one);
    }

    /**
     * 测试save()方法 (保存或者更新)
     * 保存：当传入的字段没有id时，为保存方法
     * 更新：有id字段传入时，先查询数据库，有则进行更新操作
     */
    @Test
    public void testSave(){
        //保存操作
        Customer customer = new Customer();
        customer.setCustName("实习生");
        customer.setCustIndustry("底层码农");
        customer.setCustAddress("上海");
        Customer save = customerDao.save(customer);
        System.out.println(save);

        //更新数据库已有记录
        Customer customer1=new Customer();
        customer1.setCustId(3l);
        customer1.setCustIndustry("Java工程师");
        Customer save1 = customerDao.save(customer1);
        System.out.println(save1);

        //更新数据库没有的记录
        Customer customer2 =new Customer();
        customer2.setCustId(21l);
        customer2.setCustIndustry("后端工程师");
        customerDao.save(customer2);
    }

    /**
     * 测试删除方法
     */
    @Test
    public void testDelete(){
        customerDao.delete(10l);
    }

    /**
     * 测试查询所有方法
     */
    @Test
    public void testFindAll(){
        List<Customer> list = customerDao.findAll();
        for (Customer customer : list) {
            System.out.println(customer);
        }
    }

    /**
     * 统计数据库记录数量
     */
    @Test
    public void testCount(){
        long count = customerDao.count();
        System.out.println(count);
    }

    /**
     * 测试指定id记录是否存在
     */
    @Test
    public void testExists(){
        boolean exists = customerDao.exists(40l);
        System.out.println(exists);
    }

    /**
     * 测试getOne方法，在单元测试方法中正常执行还要添加@Transaction注解
     *      findOne():立即加载
     *      getOne(): 延迟加载
     */
    @Test
    @Transactional
    public void testGetOne(){
        Customer one = customerDao.getOne(4l);
        System.out.println(one);
    }
}
