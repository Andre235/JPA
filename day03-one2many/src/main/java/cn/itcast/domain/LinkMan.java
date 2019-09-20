package cn.itcast.domain;

import lombok.Data;

import javax.persistence.*;

/**
 * @author : 赵静超
 * @date : 2019/9/20 9:38
 * @description :
 */
@Entity
@Table(name="cst_linkman")
@Data
@SuppressWarnings("all")
public class LinkMan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "lkm_id")
    private Long lkmId; //联系人编号(主键)
    @Column(name = "lkm_name")
    private String lkmName;//联系人姓名
    @Column(name = "lkm_gender")
    private String lkmGender;//联系人性别
    @Column(name = "lkm_phone")
    private String lkmPhone;//联系人办公电话
    @Column(name = "lkm_mobile")
    private String lkmMobile;//联系人手机
    @Column(name = "lkm_email")
    private String lkmEmail;//联系人邮箱
    @Column(name = "lkm_position")
    private String lkmPosition;//联系人职位
    @Column(name = "lkm_memo")
    private String lkmMemo;//联系人备注

    /**
     * 配置联系人到客户的多对一映射关系
     *      使用注解的形式配置映射关系
     *       1.配置映射关系
     *       2.配置外键(中间表)
     */
    @ManyToOne(targetEntity = Customer.class)
    @JoinColumn(name="lkm_cust_id",referencedColumnName = "cust_id")
    private Customer customer;
}
