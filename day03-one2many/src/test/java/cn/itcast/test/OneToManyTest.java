package cn.itcast.test;

import cn.itcast.dao.CustomerDao;
import cn.itcast.dao.LinkManDao;
import cn.itcast.domain.Customer;
import cn.itcast.domain.LinkMan;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author : 赵静超
 * @date : 2019/9/20 10:17
 * @description :  多表操作测试类
 */
@RunWith(SpringJUnit4ClassRunner.class)  //声明spring的单元测试环境
@ContextConfiguration(locations = "classpath:applicationContext.xml") //加载spring容器的配置文件信息
public class OneToManyTest {

    //将实体类对应的dao注入进来
    @Autowired
    private CustomerDao customerDao;
    @Autowired
    private LinkManDao linkManDao;

    /**
     * 保存一个客户，保存一个联系人操作
     */
    @Test
    @Transactional  //添加事务注解，保持事务一致性
    @Rollback(false)   //设置事务不自动回滚
    public void testSave(){
        //创建一个客户，创建一个联系人
        Customer customer = new Customer();
        LinkMan linkMan = new LinkMan();
        customer.setCustName("阿里集团");
        linkMan.setLkmName("阿里集团对接人");
        customer.getLinkManSet().add(linkMan);  //配置主表和从表的外键联系
        customerDao.save(customer);
        linkManDao.save(linkMan);
    }
}
