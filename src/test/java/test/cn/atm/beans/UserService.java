package test.cn.atm.beans;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@ToString
public class UserService {

    private String uId;

    private UserDao userDao;

//    public UserService(String name){
//        this.name = name;
//    }
    public void queryUserInfo(){
        System.out.println("查询用户信息："+userDao.queryUserName(uId));
    }
}
