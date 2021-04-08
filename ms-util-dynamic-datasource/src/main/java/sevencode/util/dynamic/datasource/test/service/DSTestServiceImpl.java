package sevencode.util.dynamic.datasource.test.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sevencode.util.dynamic.datasource.entity.TenementInfo;
import sevencode.util.dynamic.datasource.test.entity.TestEntity;
import sevencode.util.dynamic.datasource.test.persistence.DSTestRepository;

/***********************************************
 * @Author: Seven
 * @Date: 2021/4/7 16:22
 * @Description:
 * @Modified:
 * @Version: 1.0.0
 * @Copyright 2020-2021 Comleader - Powered By 凌鉴项目组
 **********************************************/
@SuppressWarnings("ALL")
@Service
public class DSTestServiceImpl implements DSTestService{

    @Autowired
    private DSTestRepository dsTestRepository;

    @Override
    public String test() {
        TestEntity testEntity = new TestEntity();
        testEntity.setId("1");
        TestEntity result = dsTestRepository.selectById(testEntity.getId());
        return result.toString();
    }
}
