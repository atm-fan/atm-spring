package cn.atm.springframework.beans.factory.xml;

import cn.atm.springframework.beans.BeansException;
import cn.atm.springframework.beans.PropertyValue;
import cn.atm.springframework.beans.factory.config.BeanDefinition;
import cn.atm.springframework.beans.factory.config.BeanReference;
import cn.atm.springframework.beans.factory.support.AbstractBeanDefinitionReader;
import cn.atm.springframework.beans.factory.support.BeanDefinitionRegistry;
import cn.atm.springframework.core.io.DefaultResourceLoader;
import cn.atm.springframework.core.io.Resource;
import cn.atm.springframework.core.io.ResourceLoader;
import cn.hutool.core.util.StrUtil;
import cn.hutool.core.util.XmlUtil;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import java.io.InputStream;

public class XmlBeanDefinitionReader extends AbstractBeanDefinitionReader {

    public XmlBeanDefinitionReader(BeanDefinitionRegistry registry, ResourceLoader resourceLoader) {
        super(registry, resourceLoader);
    }

    public XmlBeanDefinitionReader(BeanDefinitionRegistry registry) {
        super(registry);
    }

    @Override
    public void loadBeanDefinitions(String location) throws BeansException {
        DefaultResourceLoader resourceLoader = new DefaultResourceLoader();
        Resource resource = resourceLoader.getResource(location);

        loadBeanDefinitions(resource);


    }

    @Override
    public void loadBeanDefinitions(Resource resource) throws BeansException {
        try {
            doLoadBeanDefinitions(resource.getInputStream());
        } catch (Exception e) {
            throw new BeansException("IOException parsing XML document from " + resource, e);
        }
    }

    @Override
    public void loadBeanDefinitions(Resource... resources) throws BeansException {
        for (Resource resource : resources) {
            loadBeanDefinitions(resource);
        }

    }

    public void doLoadBeanDefinitions(InputStream inputStream) throws BeansException, ClassNotFoundException {
        Document document = XmlUtil.readXML(inputStream);
        Element root = document.getDocumentElement();
        NodeList childNodes = root.getChildNodes();
        for (int i = 0; i < childNodes.getLength(); i++) {
            if (!(childNodes.item(i) instanceof Element)) {
                continue;
            }
            if (!"bean".equals(childNodes.item(i).getNodeName())) {
                continue;
            }

            Element bean = (Element) childNodes.item(i);
            String id = bean.getAttribute("id");
            String className = bean.getAttribute("class");
            String name = bean.getAttribute("name");

            Class<?> clazz = Class.forName(className);

            String beanName = StrUtil.isNotBlank(id) ? id : name;
            if (StrUtil.isBlank(beanName)) {
                beanName = StrUtil.lowerFirst(clazz.getSimpleName());
            }

            BeanDefinition beanDefinition = new BeanDefinition(clazz);

            for (int j = 0; j < bean.getChildNodes().getLength(); j++) {
                if (!(bean.getChildNodes().item(j) instanceof Element)) {
                    continue;
                }
                if (!"property".equals(bean.getChildNodes().item(j).getNodeName())) {
                    continue;
                }

                Element property = (Element) bean.getChildNodes().item(j);
                String attrName = property.getAttribute("name");
                String attrValue = property.getAttribute("value");
                String attrRef = property.getAttribute("ref");

                Object value = StrUtil.isNotBlank(attrRef) ? new BeanReference(attrRef) : attrValue;

                PropertyValue propertyValue = new PropertyValue(attrName, value);
                beanDefinition.getPropertyValues().addPropertyValue(propertyValue);


            }

            if(getRegistry().containsBeanDefinition(beanName)){
                throw new BeansException("The beanName is exist");
            }

            // 注册beanDefinition
            getRegistry().registerBeanDefinition(beanName, beanDefinition);
        }
    }
}
