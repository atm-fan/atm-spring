package cn.atm.beans;

import java.util.HashMap;
import java.util.Map;

public class UserDao {
    // 用户数据
    private final static Map<String, String> userData = new HashMap<>();

    static {
        userData.put("1001", "zhangfan");
        userData.put("1002", "lisi");
        userData.put("1003", "wangwu");
    }

    // 查询用户数据
    public String queryUserName(String uid){
       return userData.get(uid);
    }
}
