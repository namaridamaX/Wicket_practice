package com.example.wsbp.service;

import com.example.wsbp.data.AuthUser;

import java.util.List;

public interface IUserService {
    public void registerUser(String userName,String userPass);
    public void deleteUser(String userName);
    public boolean existsUser(String userName,String userPass);
    public List<AuthUser> findAuthUser();
}
