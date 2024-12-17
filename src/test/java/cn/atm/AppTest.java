package cn.atm;

import cn.atm.beans.UserDao;
import cn.atm.beans.UserService;
import cn.atm.springframework.beans.PropertyValue;
import cn.atm.springframework.beans.PropertyValues;
import cn.atm.springframework.beans.factory.config.BeanDefinition;
import cn.atm.springframework.beans.factory.config.BeanReference;
import cn.atm.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.junit.Test;

/**
 * Unit test for simple App.
 */
public class AppTest {

    @Test
    public void test_BeanFactory(){
        // 1.初始化 BeanFactory
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();

        // 2.注册 bean
        BeanDefinition beanDefinition = new BeanDefinition(UserService.class);
        beanFactory.registerBeanDefinition("userService", beanDefinition);

        // 3.第一次获取 bean
        UserService userService = (UserService) beanFactory.getBean("userService");
        userService.queryUserInfo();

        // 4.第二次获取 bean from Singleton
        UserService userService_singleton = (UserService) beanFactory.getSingleton("userService");
        userService_singleton.queryUserInfo();
    }


    /**
     * 不同构造参数创建bean
     */
    @Test
    public void test_initBean(){
        // 1.初始化 BeanFactory
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();

        // 2.注册 bean
        BeanDefinition beanDefinition = new BeanDefinition(UserService.class);
        beanFactory.registerBeanDefinition("userService", beanDefinition);

        // 3.第一次获取 bean
        UserService userService = (UserService) beanFactory.getBean("userService", new Object[]{"我是谁"});
        //userService.queryUserInfo();
        System.out.println(userService);

        UserService u2 =  (UserService) beanFactory.getBean("userService");
        System.out.println(u2);
    }

    /**
     * bean 属性注入测试
     */
    @Test
    public void test_property_set(){
        // 1.初始化 BeanFactory
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();

        // 2.注册 bean
        BeanDefinition beanDefinition = new BeanDefinition(UserDao.class);
        beanFactory.registerBeanDefinition("userDao", beanDefinition);

        PropertyValues propertyValues = new PropertyValues();
        propertyValues.addPropertyValue(new PropertyValue("uid","1002"));
        propertyValues.addPropertyValue(new PropertyValue("userDao", new BeanReference("userDao")));


        BeanDefinition beanDefinition1 = new BeanDefinition(UserService.class, propertyValues);
        beanFactory.registerBeanDefinition("userService", beanDefinition1);

        UserService userService = (UserService)beanFactory.getBean("userService");

        userService.queryUserInfo();
    }

}