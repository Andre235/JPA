package cn.itcast.dao;

import cn.itcast.domain.LinkMan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * @author : 赵静超
 * @date : 2019/9/20 9:40
 * @description :  联系人dao接口
 */
public interface LinkManDao extends JpaRepository<LinkMan,Long>, JpaSpecificationExecutor<LinkMan> {
}
