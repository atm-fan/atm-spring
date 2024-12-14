package cn.atm.springframework.beans.factory.support;

import cn.atm.springframework.beans.BeansException;
import cn.atm.springframework.beans.factory.config.BeanDefinition;

import java.lang.reflect.Constructor;

public abstract class AbstractAutowireCapableBeanFactory extends AbstractBeanFactory {
    // 使用 cglib方式实例化
    InstantiationStrategy instantiationStrategy = new CglibSubclassingInstantiationStrategy();

    @Override
    protected Object createBean(String beanName, BeanDefinition beanDefinition, Object[] args) throws BeansException {
        Object bean;
        try {
            bean = createBeanInstance(beanName, beanDefinition, args);
        } catch (Exception e) {
            throw new BeansException("Instantiation of bean failed", e);
        }

        addSingletonObject(beanName, bean);
        return bean;
    }

    protected Object createBeanInstance(String beanName, BeanDefinition beanDefinition, Object[] args){
        // 使用哪个构造函数
        Constructor constructorToUse = null;
        Class clazz = beanDefinition.getBeanClass();
        Constructor<?>[] declaredConstructors = clazz.getDeclaredConstructors();

        // 目前根据传入参数的长度匹配 构造函数
        for (Constructor constructor: declaredConstructors){
            if(args!=null && constructor.getParameterTypes().length == args.length){
                constructorToUse = constructor;

                break;
            }
        }

        Object bean = instantiationStrategy.instantiate(beanDefinition,beanName,constructorToUse, args);
        return bean;
    }

}
