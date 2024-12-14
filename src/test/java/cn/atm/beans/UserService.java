package cn.atm.beans;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@ToString
public class UserService {

    private String name;

    public UserService(String name){
        this.name = name;
    }
    public void queryUserInfo(){
        System.out.println("查询用户信息");
    }
}
