package sevencode.util.dynamic.datasource.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/***********************************************
 * @Author: Seven
 * @Date: 2021/3/26 14:30
 * @Description: 租户信息实体类
 * @Modified:
 * @Version: 1.0.0
 * @Copyright 2020-2021 Comleader - Powered By 凌鉴项目组
 **********************************************/
@SuppressWarnings("ALL")
@Data
@TableName("tenement_info")
public class TenementInfo {
    //租户信息对应的用户ID
    @TableId(value = "id")
    private String id;
    //租户ID
    private String tenementId;
    //租户是否有私有数据库
    private String isPrivateDb;
    //私有数据库类型
    private String privateDbType;
    //私有数据库地址
    private String privateDbUrl;
    //私有数据库用户名
    private String privateDbUser;
    //私有数据库密码
    private String privateDbPassword;
}
