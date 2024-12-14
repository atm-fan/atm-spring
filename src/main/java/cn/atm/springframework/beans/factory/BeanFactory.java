package cn.atm.springframework.beans.factory;


import cn.atm.springframework.beans.BeansException;

/**
 * bean 工厂
 */
public interface BeanFactory {
    Object getBean(String beanName) throws BeansException;

    Object getBean(String beanName, Object[] args) throws BeansException;
}
