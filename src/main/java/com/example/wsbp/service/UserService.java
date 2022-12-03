package com.example.wsbp.service;

import com.example.wsbp.data.AuthUser;
import com.example.wsbp.data.ChatUser;
import com.example.wsbp.repository.IAuthUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService implements IUserService{

    private IAuthUserRepository authUserRepos;

    @Autowired
    public UserService(IAuthUserRepository authUserRepos){
        this.authUserRepos = authUserRepos;
    }

    @Override
    //引数で名前とパスワードをいただいて、IAuthUserRepositoryのinsertメソッドを呼び出す。・・・(2)
    public void registerUser(String userName,String userPass){
        int n = authUserRepos.insert(userName,userPass);
        System.out.println("記録行数:" + n);
    }

    //(2)と仕事自体はほぼ一緒
    public void deleteUser(String userName){
        int n = authUserRepos.delete(userName);
        System.out.println("削除行数:" + n);
    }

    @Override
    //(2)と仕事自体はほぼ一緒
    public boolean existsUser(String userName,String userPass){
        var result = authUserRepos.exists(userName,userPass);
        System.out.println(userName + "," + userPass + "のユーザ照合結果："+result);
        return result;
    }

    @Override
    //AuthUser型のListを返す。中身は名前とパスワードが入っている。
    public List<AuthUser> findAuthUser(){
        var users = authUserRepos.find();
        System.out.println("データ件数:" + users.size());
        return users;
    }

    @Override
    //ChatUser型のListを返す。中身は名前とメッセージが入っている。
    public List<ChatUser> findChat(){
        var users = authUserRepos.Chat_find();
        System.out.println("データ件数:" + users.size());
        return users;
    }

    @Override
    //(2)と仕事自体はほぼ一緒
    public void registerMessage(String userName,String userMessage){
        int n = authUserRepos.Chat_insert(userName,userMessage);
        System.out.println(("記録行数:" + n));
    }
}
