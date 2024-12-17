package cn.atm.springframework.beans.factory.support;

import cn.atm.springframework.beans.BeansException;
import cn.atm.springframework.beans.PropertyValue;
import cn.atm.springframework.beans.PropertyValues;
import cn.atm.springframework.beans.factory.config.BeanDefinition;
import cn.atm.springframework.beans.factory.config.BeanReference;
import cn.hutool.core.bean.BeanUtil;

import java.lang.reflect.Constructor;

public abstract class AbstractAutowireCapableBeanFactory extends AbstractBeanFactory {
    // 使用 cglib方式实例化
    InstantiationStrategy instantiationStrategy = new CglibSubclassingInstantiationStrategy();

    @Override
    protected Object createBean(String beanName, BeanDefinition beanDefinition, Object[] args) throws BeansException {
        // 实例化Bean
        Object bean;
        try {
            bean = createBeanInstance(beanName, beanDefinition, args);
        } catch (Exception e) {
            throw new BeansException("Instantiation of bean failed", e);
        }

        // bean属性注入
        applyPropertyValues(beanName, bean, beanDefinition);

        // 缓存单例bean
        addSingletonObject(beanName, bean);
        return bean;
    }

    // 实例化bean
    protected Object createBeanInstance(String beanName, BeanDefinition beanDefinition, Object[] args) {
        // 使用哪个构造函数
        Constructor constructorToUse = null;
        Class clazz = beanDefinition.getBeanClass();
        Constructor<?>[] declaredConstructors = clazz.getDeclaredConstructors();

        // 目前根据传入参数的长度匹配 构造函数
        for (Constructor constructor : declaredConstructors) {
            if (args != null && constructor.getParameterTypes().length == args.length) {
                constructorToUse = constructor;

                break;
            }
        }

        Object bean = instantiationStrategy.instantiate(beanDefinition, beanName, constructorToUse, args);
        return bean;
    }

    /**
     * 属性填充
     * @param beanName
     * @param bean
     * @param beanDefinition
     */
    protected void applyPropertyValues(String beanName, Object bean, BeanDefinition beanDefinition) {
        try {
            PropertyValues propertyValues = beanDefinition.getPropertyValues();

            for (PropertyValue propertyValue: propertyValues.getPropertyValues()){
                String name = propertyValue.getName();
                Object value = propertyValue.getValue();

                // 获取
                if (value instanceof BeanReference) {
                    // A 依赖 B，获取 B 的实例化
                    BeanReference beanReference = (BeanReference) value;
                    value = getBean(beanReference.getBeanName());
                }
                // 属性填充
                BeanUtil.setFieldValue(bean, name, value);
            }


        }catch (Exception e){
            throw new BeansException("",e);
        }
    }

}
