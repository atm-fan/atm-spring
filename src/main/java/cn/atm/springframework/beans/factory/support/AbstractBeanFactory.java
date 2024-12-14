package cn.atm.springframework.beans.factory.support;

import cn.atm.springframework.beans.factory.BeanFactory;
import cn.atm.springframework.beans.BeansException;
import cn.atm.springframework.beans.factory.config.BeanDefinition;

public abstract class AbstractBeanFactory extends DefaultSingletonBeanRegistry implements BeanFactory {

    /**
     * 有参
     * @param beanName
     * @param args
     * @return
     */
    @Override
    public Object getBean(String beanName, Object[] args) {
        Object singletonBean = getSingleton(beanName);
        if(singletonBean!=null){
            return singletonBean;
        }

        BeanDefinition beanDefinition = getBeanDefinition(beanName);
        singletonBean = createBean(beanName, beanDefinition, args);
        return singletonBean;
    }

    /**
     * 无参
     * @param beanName
     * @return
     * @throws BeansException
     */
    @Override
    public Object getBean(String beanName) throws BeansException {
        Object singletonBean = getSingleton(beanName);
        if(singletonBean!=null){
            return singletonBean;
        }

        BeanDefinition beanDefinition = getBeanDefinition(beanName);
        singletonBean = createBean(beanName, beanDefinition, null);
        return singletonBean;
    }

    protected abstract BeanDefinition getBeanDefinition(String beanName) throws BeansException;

    protected abstract Object createBean(String beanName, BeanDefinition beanDefinition, Object[] args) throws BeansException;
}
