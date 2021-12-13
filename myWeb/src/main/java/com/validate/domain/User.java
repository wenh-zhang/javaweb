package com.validate.domain;


import java.io.Serializable;

//用户类
public class User implements Serializable {
    private long id;
    private String username;
    private String email;
    private String password;
    private int activate_state;
    private String code;
    private String role;

    public User(){

    }
    public User(String userinfo){
        String[] arr = userinfo.split("\\s+"); //分割一个或者多个空格
        this.username=arr[0];
        this.email=arr[1];
        this.password=arr[2];
        this.activate_state= Integer.parseInt(arr[3]);
        this.code=arr[4];
        this.role=arr[5];
    }
    public User(long id,String username,String email,String password,int activate_state,String code,String role)
    {
        this.id=id;
        this.username=username;
        this.email=email;
        this.password=password;
        this.activate_state=activate_state;
        this.code=code;
        this.role=role;
    }

    public String getString(){
        return "用户名:"+username+"|"+"邮箱:"+email+"|"+"密码:"+password+"|"+"激活状态:"+activate_state+"|"+"激活码:"+code+"|"+"权限:"+role;
    }
    public long getId(){return id;}

    public void setId(long id){this.id=id;}

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getActivate_state(){
        return activate_state;
    }

    public void setActivate_state(int activate_state){
        this.activate_state=activate_state;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getRole(){return role;}

    public void setRole(String role) {
        this.role = role;
    }
}
