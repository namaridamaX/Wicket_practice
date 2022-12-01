package com.example.wsbp.data;

import java.io.Serializable;

// AUTH_USER テーブルのデータを入れるクラス
// Wicketの Model に使うかもしれないクラスは、 implements Serializable をつける
public class AuthUser implements Serializable {

    private final String userName;
    private final String userPass;

    public AuthUser(String userName,String userPass){
        this.userName = userName;
        this.userPass = userPass;
    }

    public String getUserName(){
        return userName;
    }

    public String getUserPass(){
        return userPass;
    }

    @Override
    public boolean equals(Object o){
        if(this == o) return true;
        if(o == null || getClass() != o.getClass()) return false;

        AuthUser authuser = (AuthUser) o;

        if(!userName.equals(authuser.userName)) return false;
        return userPass.equals(authuser.userPass);
    }

    @Override
    public int hashCode(){
        int result = userName.hashCode();
        result = 31 * result + userPass.hashCode();
        return result;
    }
}
