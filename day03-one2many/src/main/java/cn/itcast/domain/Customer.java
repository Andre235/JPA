package cn.itcast.domain;

import jdk.nashorn.internal.ir.CallNode;
import lombok.Data;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * @author : 赵静超
 * @date : 2019/9/19 8:39
 * @description : 配置实体类与表的映射关系，属性和数据库字段的映射关系
 */
@Data   //读写()、hashCode()、equals()、toString()
@Entity
@Table(name = "cst_customer")
@SuppressWarnings("all")
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

    //配置客户和联系人之间的关系(一对多映射关系)
    //使用注解的形式配置映射关系
    @OneToMany(targetEntity = LinkMan.class)
    @JoinColumn(name="lkm_cust_id",referencedColumnName = "cust_id")
    Set<LinkMan> linkManSet= new HashSet<LinkMan>();

}
