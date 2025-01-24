package cn.atm.springframework.core.io;

public interface ResourceLoader {
    final static String CLASSPATH_URL_PREFIX = "classpath:";

    // 返回指定资源位置对应的Resource对象
    Resource getResource(String location);
}
