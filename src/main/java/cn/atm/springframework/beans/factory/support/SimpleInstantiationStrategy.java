package cn.atm.springframework.beans.factory.support;

import cn.atm.springframework.beans.BeansException;
import cn.atm.springframework.beans.factory.config.BeanDefinition;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/**
 * JDK 实例化策略
 */
public class SimpleInstantiationStrategy implements InstantiationStrategy{
    @Override
    public Object instantiate(BeanDefinition beanDefinition, String beanName, Constructor ctor, Object[] args) throws BeansException {
        Class clazz = beanDefinition.getBeanClass();
        Object bean;
        try {
            if(ctor!=null){
                bean = clazz.getDeclaredConstructor(ctor.getParameterTypes()).newInstance(args);
            }else {
                bean = clazz.getDeclaredConstructor().newInstance();
            }

        } catch (InstantiationException | IllegalAccessException | InvocationTargetException| NoSuchMethodException e) {
            throw new BeansException("Failed to instantiate [" + clazz.getName() + "]", e);
        }

        return bean;
    }
}
