package sevencode.util.dynamic.datasource.test.persistence;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import sevencode.util.dynamic.datasource.entity.TenementInfo;
import sevencode.util.dynamic.datasource.test.entity.TestEntity;

/***********************************************
 * @Author: Seven
 * @Date: 2021/4/7 16:24
 * @Description: 测试信息数据读写接口
 * @Modified:
 * @Version: 1.0.0
 * @Copyright 2020-2021 Comleader - Powered By 凌鉴项目组
 **********************************************/
@SuppressWarnings("ALL")
@Mapper
public interface DSTestRepository extends BaseMapper<TestEntity> {
}
