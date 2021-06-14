package com.example.lavacarapp.controller;

import com.example.lavacarapp.dao.DaoUser;
import com.example.lavacarapp.model.UserDatabase;
import com.example.lavacarapp.model.UserInfo;

import java.util.ArrayList;

public class UserCtrl {

    private final DaoUser daoUser;

    public UserCtrl (UserDatabase database){
        daoUser = new DaoUser(database);
    }

    public long salvarUser(UserInfo user){
        return daoUser.createUserInDB(user);
    }

    public boolean checkUser(UserInfo user){
        return daoUser.checkUser(user.getEmail(), user.getPassword());
    }

    public void alterarUser(String email, String name, String phone){
        daoUser.updateUserInDB(email, name, phone);
    }

    public UserInfo getUserInfo(String email){
        return daoUser.getUser(email);
    }

    public void deletarUser(String email){
        daoUser.deleteUserInDB(email);
    }
}
