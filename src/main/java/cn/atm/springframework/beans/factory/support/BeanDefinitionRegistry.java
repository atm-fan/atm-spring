package cn.atm.springframework.beans.factory.support;

import cn.atm.springframework.beans.factory.config.BeanDefinition;

public interface BeanDefinitionRegistry {
    /**
     * 向注册表中注册 BeanDefinition
     * @param beanName
     * @param beanDefinition
     */
    void registerBeanDefinition(String beanName, BeanDefinition beanDefinition);

    // 判断注册表中是否包含某个 BeanDefinition
    boolean containsBeanDefinition(String beanName);
}
