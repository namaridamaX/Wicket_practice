package com.example.wsbp.service;

import com.example.wsbp.data.AuthUser;
import com.example.wsbp.data.ChatUser;

import java.util.List;

public interface IUserService {
    public void registerUser(String userName,String userPass);
    public void deleteUser(String userName);
    public boolean existsUser(String userName,String userPass);
    public List<AuthUser> findAuthUser();
    public void registerMessage(String userName,String Message);
    public List<ChatUser> findChat();
}
