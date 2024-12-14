package cn.atm.springframework.beans.factory.support;

import cn.atm.springframework.beans.BeansException;
import cn.atm.springframework.beans.factory.config.SingletonBeanRegistry;

import java.util.HashMap;
import java.util.Map;

public class DefaultSingletonBeanRegistry implements SingletonBeanRegistry {

    // 单例对象map
    private Map<String, Object> singletonObjects = new HashMap<>();
    @Override
    public Object getSingleton(String beanName) {
        return singletonObjects.get(beanName);
    }

    protected void addSingletonObject(String beanName, Object singletonObject) throws BeansException {
        singletonObjects.put(beanName, singletonObject);
    }
}
