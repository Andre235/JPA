package cn.itcast.test;

import cn.itcast.dao.CustomerDao;
import cn.itcast.domain.Customer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import sun.util.resources.cldr.asa.CurrencyNames_asa;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;

/**
 * @author : 赵静超
 * @date : 2019/9/19 8:55
 * @description :
 */
@RunWith(SpringJUnit4ClassRunner.class)//声明spring提供的单元测试环境
@ContextConfiguration(locations ="classpath:applicationContext.xml")//指定spring容器的配置信息
@SuppressWarnings("all")
public class JpqlTest {

    @Autowired //将CustomerDao对象自动装配到spring容器中
    private CustomerDao customerDao;

    @Test
    public void testFindJpql(){
        List<Customer> list= customerDao.findJpql("实习生");
        for (Customer customer : list) {
            System.out.println(customer);
        }
    }

    @Test
    public void testFindByNameAndId(){
        Customer customer = customerDao.findByNameAndId("北大青鸟", 5l);
        System.out.println(customer);
    }

    /**
     * spring data使用jqpl进行修改或者删除操作
     *      需要手动添加@Transactional事务支持
     *      默认执行结束后回滚事务
     *      @Rollback(false) 手动取消自动回滚事务
     */
    @Test
    @Transactional
    @Rollback(false)
    public void testUpdateCustomer(){
        customerDao.updateCustomer(2L,"黑马程序员牛皮");
        Customer one = customerDao.findOne(2l);
        System.out.println(one);
    }

    @Test
    public void testFindAllBySQL(){
        List<Object[]> list = customerDao.findAllBySQL();
        for (Object[] customer : list) {
            System.out.println(Arrays.toString(customer));
        }
    }

    @Test
    public void testFindByNameLike(){
        List<Object[]> list = customerDao.findByNameLike("尚硅谷%");
        for (Object[] customer : list) {
            System.out.println(Arrays.toString(customer));
        }
    }

    /**
     * 测试方法命名规则查询 findByName
     */
    @Test
    public void testFindByCustName(){
        List<Customer> list = customerDao.findByCustNameLike("尚硅谷%");
        for (Customer customer : list) {
            System.out.println(customer);
        }
    }

    /**
     * 使用方法命名规则进行多条件查询
     *      根据客户姓名模糊查询以及所属行业精准查询
     */
    @Test
    public void testFindByCustNameLikeAndCustAddress(){
        List<Customer> list = customerDao.findByCustNameLikeAndCustIndustry("尚硅谷%","后端工程师");
        for (Customer customer : list) {
            System.out.println(customer);
        }
    }
}
