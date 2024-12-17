package cn.atm.springframework.beans.factory.config;

import cn.atm.springframework.beans.PropertyValues;
import lombok.Data;

@Data
public class BeanDefinition {
    private Class beanClass;
    // 属性
    private PropertyValues propertyValues;

    public BeanDefinition(Class beanClass, PropertyValues propertyValues) {
        this.beanClass = beanClass;
        this.propertyValues = propertyValues != null ? propertyValues : new PropertyValues();
    }

    public BeanDefinition(Class beanClass) {
        this.beanClass = beanClass;
        this.propertyValues = new PropertyValues();
    }
}
