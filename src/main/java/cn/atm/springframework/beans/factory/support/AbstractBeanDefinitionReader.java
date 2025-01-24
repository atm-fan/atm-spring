package cn.atm.springframework.beans.factory.support;

import cn.atm.springframework.core.io.DefaultResourceLoader;
import cn.atm.springframework.core.io.ResourceLoader;

public abstract class AbstractBeanDefinitionReader implements BeanDefinitionReader {
    private  BeanDefinitionRegistry registry;
    private ResourceLoader resourceLoader;

    public AbstractBeanDefinitionReader(BeanDefinitionRegistry registry, ResourceLoader resourceLoader) {
        this.registry = registry;
        this.resourceLoader = resourceLoader;
    }

    // 构造函数 入参只有registry
    public AbstractBeanDefinitionReader(BeanDefinitionRegistry registry) {
        this(registry, new DefaultResourceLoader());
    }

    // 获取BeanDefinitionRegistry
    @Override
    public BeanDefinitionRegistry getRegistry() {
        return registry;
    }

    // 获取ResourceLoader
    @Override
    public ResourceLoader getResourceLoader() {
        return resourceLoader;
    }
}
