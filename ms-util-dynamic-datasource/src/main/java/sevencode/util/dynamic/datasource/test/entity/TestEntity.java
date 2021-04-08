package sevencode.util.dynamic.datasource.test.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/***********************************************
 * @Author: Seven
 * @Date: 2021/4/8 9:28
 * @Description:
 * @Modified:
 * @Version: 1.0.0
 * @Copyright 2020-2021 Comleader - Powered By 凌鉴项目组
 **********************************************/
@SuppressWarnings("ALL")
@Data
@TableName(value = "test")
public class TestEntity {

    @TableId(value = "id")
    private String id;

    private String context;

}
