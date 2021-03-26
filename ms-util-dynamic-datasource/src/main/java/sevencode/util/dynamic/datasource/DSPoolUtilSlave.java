package sevencode.util.dynamic.datasource;

import org.springframework.stereotype.Component;
import org.apache.tomcat.jdbc.pool.DataSource;
import org.apache.tomcat.jdbc.pool.PoolProperties;

/***********************************************
 * @Author: Seven
 * @Date: 2021/3/26 11:34
 * @Description:
 * @Modified:
 * @Version: 1.0.0
 * @Copyright 2020-2021 Comleader - Powered By 凌鉴项目组
 **********************************************/
@SuppressWarnings("ALL")
@Component
public class DSPoolUtilSlave {
    //连接池属性支持
    private static PoolProperties poolProperties = new PoolProperties();
    //驱动替换--
    private static final String DRIVER_CLASS_NAME = DSParams.DRIVER_CJ_CLASS;
    //初始化连接池 仅创建一次  这也就是说该连接只有一个连接池供使用  一个连接池就够咱们默认数据源使用了
    private static DataSource dataSource = new DataSource();

    static {
        poolProperties.setMinIdle(10);
        poolProperties.setMaxActive(1000);
        //如果连接超时等待时间 -单位秒  这里设置为 一分钟
        poolProperties.setMaxWait(60000);
        /**
         * Mysql  5版本以下默认在url加上autoReconnect=true即可解决 在连接失效时由mysql帮我们完成对连接的判断性和断开与重连问题
         * 		  5版本以上新增机制默认8小时如果没有请求访问数据库连接 那么连接都将被mysql关闭 如果8小时之后再次访问,
         * 		  就是访问了一些失效的并且已经关闭的连接 由此在这里配置多长时间进行检验一次连接是否失效-失效即释放以及重新构建连接
         * 		  单位--毫秒 这里配置15分钟检验一次
         */
        poolProperties.setTimeBetweenEvictionRunsMillis(1080000);
        //配置一个链接在池中最小生存时间,单位-毫秒
        poolProperties.setMinEvictableIdleTimeMillis(1080000);
        //验证链接是否有效,参数必须设置为非空字符串,下第三项为true即生效
        poolProperties.setValidationQuery("SELECT 1");
        //指明链接是否被空闲链接回收器回收如果有进行检验,如果失败即回收链接
        poolProperties.setTestWhileIdle(true);
        //检验链接是否有效,如果无效舍去重新获取新的链接
        poolProperties.setTestOnBorrow(true);
        //指明是否在归还到连接池中前进行检验
        poolProperties.setTestOnReturn(true);
    }

    //配置数据源链接属性支持
    public static DataSource getPoolProperties(String url, String userName, String password) {
        poolProperties.setUrl(url);
        poolProperties.setUsername(userName);
        poolProperties.setPassword(password);
        poolProperties.setDriverClassName(DRIVER_CLASS_NAME);
        //连接池 依赖属性pool
        dataSource.setPoolProperties(poolProperties);
        return dataSource;
    }
}
