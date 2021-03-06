package cn.itcast.test;

import cn.itcast.dao.CustomerDao;
import cn.itcast.domain.Customer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.persistence.criteria.*;
import java.util.List;

/**
 * @author : 赵静超
 * @date : 2019/9/19 15:08
 * @description : Specification测试类(进行复杂查询,不用实现dao接口的方法)
 *                     1.动态传入查询属性，进行多字段查询
 *                     2.传入排序参数，可以按照不同字段进行排序
 *                     3.精确查询、模糊查询
 *                     4.分页查询
 */

@RunWith(SpringJUnit4ClassRunner.class) //声明spring提供的单元测试环境
@ContextConfiguration(locations = "classpath:applicationContext.xml")  //指定spring容器的配置信息
@SuppressWarnings("all")
public class SpecTest {

    @Autowired
    private CustomerDao customerDao;

    /**
     * 动态根据条件查询(今天根据Name查询 明天也可以根据行业查询)
     *      1.实现Specification<Customer>  并提供实体类泛型
     *      2.实现toPredicate方法(构造查询条件)
     *          root：获取需要查询的对象属性
     *          CriteriaBuilder：构造查询条件，内部封装了很多查询条件(精确查询、模糊查询、排序、分页)
     *      案例：根据客户名称查询
     *          1.获取要查询的属性
     *          2.构造查询条件
     */
    @Test
    public void testSpec1(){
        //匿名内部类
        Specification<Customer> spec = new Specification<Customer>() {
            public Predicate toPredicate(Root<Customer> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                Path<Object> custName = root.get("custName");
                Predicate equal = criteriaBuilder.equal(custName, "尚硅谷");
                return equal;
            }
        };
        Customer customer = customerDao.findOne(spec);
        System.out.println(customer);
    }

    /**
     * 多条件查询
     *      根据客户姓名和客户所属行业进行多条件查询
     *      且关系：criteriaBuilder.and(p1, p2);
     *      或关系：criteriaBuilder.or(p1, p2);
     */
    @Test
    public void testSpec2(){
        //匿名内部类
        Specification<Customer> specification = new Specification<Customer>() {
            public Predicate toPredicate(Root<Customer> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                Path<Object> custName = root.get("custName");
                Path<Object> custIndustry = root.get("custIndustry");
                //1.构造客户名的精准匹配查询
                Predicate p1 = criteriaBuilder.equal(custName, "尚硅谷");
                //2.构造客户所属行业的精准匹配查询
                Predicate p2 = criteriaBuilder.equal(custIndustry, "IT");
                //3.将多个查询条件组合到一起 组合(且关系 或关系)
                //Predicate and = criteriaBuilder.and(p1, p2);  //满足p1查询条件并且满足p2查询条件
                Predicate or = criteriaBuilder.or(p1, p2);  //满足p1查询条件或者满足p2查询条件
                return or;
            }
        };
        Customer one = customerDao.findOne(specification);
        System.out.println(one);
    }

    /**
     * 根据客户名称进行模糊匹配
     * equal()方法，直接得到path对象然后进行比较即可
     * like() gt lt ge le方法,得到path对象，path对象指明其参数类型的字节码文件，在进行比较
     */
    @Test
    public void testSpec3(){
        Specification<Customer> spec = new Specification<Customer>() {
            public Predicate toPredicate(Root<Customer> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                Path<Object> custName = root.get("custName");
                Predicate like = criteriaBuilder.like(custName.as(String.class), "尚硅谷%");
                return like;
            }
        };
        /*List<Customer> list = customerDao.findAll(spec);
        for (Customer customer : list) {
            System.out.println(customer);
        }*/

        /**
         * 按照字段进行排序操作
         * 1.创建Sort排序对象，调用用构造方法传入排序规则
         *      1.1第一个参数：排序的顺序(正序、倒序)
         *      1.2第二个参数：按照那个字段进行排序
         */
        Sort sort = new Sort(Sort.Direction.DESC,"custId");
        List<Customer> list = customerDao.findAll(spec, sort);
        for (Customer customer : list) {
            System.out.println(customer);
        }
    }

    /**
     * 使用spring data jpa specification对象进行分页查询
     */
    @Test
    public void testSpec4(){
        Specification spec = null; //没有查询条件
        //PageRequest是Pageable的实现类
        //参数：从第几页开始查，每页查询的条数
        Pageable pageabele = new PageRequest(0,2);
        //分页查询
        Page<Customer> page = customerDao.findAll(spec, pageabele);
        System.out.println(page.getTotalPages());   //得到总页数
        System.out.println(page.getTotalElements());  //得到总条数
        System.out.println(page.getContent());  //得到数据集合列表
    }
}
