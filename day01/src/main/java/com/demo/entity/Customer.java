package com.demo.entity;

import lombok.Cleanup;
import lombok.Data;

import javax.persistence.*;

/**
 * @author : 赵静超
 * @date : 2019/9/18 11:05
 * @description : 客户实体类
 * 配置映射关系
 *      实体类和表的映射关系
 *      实体类属性和表字段的映射关系
 */
@Data   //读写()、hashCode()、equals()、toString()
@Entity
@Table(name = "cst_customer")
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="cust_id")
    private Long custId;

    @Column(name="cust_name")
    private String custName;

    @Column(name="cust_source")
    private String custSource;

    @Column(name="cust_level")
    private String custLevel;

    @Column(name="cust_industry")
    private String custIndustry;

    @Column(name="cust_phone")
    private String custPhone;

    @Column(name="cust_address")
    private String custAddress;

}
