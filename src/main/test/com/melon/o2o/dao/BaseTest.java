package com.melon.o2o.dao;

import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @ClassName com.melon.o2o.dao.BaseTest：配置Spring和Junit整合，Junit启动时加载SpringIOC容器
 * @Description
 * @Author melon
 * @Date 2019-08-16 18:59
 * @Version
 */

@RunWith(SpringJUnit4ClassRunner.class)
//告诉junnit spring配置文件的位置
@ContextConfiguration({"classpath:spring/spring-dao.xml","classpath:spring/spring-service.xml","classpath:spring/spring-redis.xml"})
public class BaseTest {

}
