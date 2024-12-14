package cn.atm.springframework.beans.factory.config;


import lombok.Data;

@Data
public class BeanDefinition {
    private Class beanClass;

    public BeanDefinition(Class beanClass){
        this.beanClass = beanClass;
    }
}
