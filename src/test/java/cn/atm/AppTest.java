package cn.atm;

import cn.atm.beans.UserService;
import cn.atm.springframework.beans.factory.config.BeanDefinition;
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

}
