package cn.itcast.dao;

import cn.itcast.domain.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * @author : 赵静超
 * @date : 2019/9/19 17:44
 * @description :
 */
public interface CustomerDao extends JpaRepository<Customer,Long>, JpaSpecificationExecutor<Customer> {
}
