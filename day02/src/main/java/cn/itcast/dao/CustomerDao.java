package cn.itcast.dao;

import cn.itcast.domain.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;


import java.util.List;

/**
 * @author : 赵静超
 * @date : 2019/9/19 8:46
 * @description : 创建符合spring data jpa规范的dao接口（只需要提供接口，不需要编写相应的实现类）
 *                      JpaRepository<需要操作的实体类，实体类对应的主键类型>
 *                          提供基本的CRUD操作
 *                      JpaSpecificationExecutor<需要操作的实体类>
 *                          提供复杂查询操作(分页查询)
 */
@SuppressWarnings("all")
public interface CustomerDao extends JpaRepository<Customer,Long>, JpaSpecificationExecutor<Customer> {

    /**
     * 根据客户名称查询客户
     * 使用jpql查询
     * 配置jpql语句，使用@Query注解
     * jqpl：from Customer where custName = ?
     */
    @Query(value = "from Customer where custName = ?1")
    List<Customer> findJpql(String custName);

    /**
     * 使用Query注解通过客户姓名和id进行查询
     */
    @Query(value = "from Customer where custName =?1 and custId =?2")
    Customer findByNameAndId(String name,Long id);

    /**
     * 通过传入的id进行对客户姓名的更新
     */
    @Query(value = "update Customer set custName = ?2 where custId = ?1")
    @Modifying  //表名该方法进行一个更新操作
    void updateCustomer(Long id,String name);

    /**
     * spring data jpa 使用jqpl实现SQL查询
     * 使用sql语句查新所有用户
     *      sql : select * from cst_customer
     *      query查询配置：
     *          nativeQuery : false(jpql查询方式) | true(sql查询方式)
     */
    @Query(value = "select * from cst_customer",nativeQuery = true)
    List<Object[]> findAllBySQL();

    /**
     * 实现sql根据姓名进行模糊查询
     */
    @Query(value = "select * from cst_customer where cust_name like ?1",nativeQuery = true)
    List<Object[]> findByNameLike(String name);

    /**
     * 方法命名规则查询
     *      findBy + 属性名(findByCustName)
     */
    List<Customer> findByCustNameLike(String name);

    /**
     * 方法命名规则查询(多条件查询)
     *      根据客户名称模糊查询以及所属行业精准查询
     */
    List<Customer> findByCustNameLikeAndCustIndustry(String nameLike,String industry);
}
