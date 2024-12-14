package cn.atm.springframework.beans;

import cn.atm.springframework.beans.factory.config.BeanDefinition;

public class BeansException extends RuntimeException{
    public BeansException(String message){
        super(message);
    }

    public BeansException(String message, Throwable cause){
        super(message, cause);
    }
}
