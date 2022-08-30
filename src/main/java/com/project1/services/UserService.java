package com.project1.services;

import com.project1.models.User;
import com.project1.dao.UserDAO;
import com.project1.dao.UserDAOImpl;

public class UserService {

    UserDAO udao = new UserDAOImpl();

    public UserService(){
        this.udao = new UserDAOImpl();
    }

    public UserService(UserDAO udao){
        this.udao = udao;        
    }

    public Boolean validateUser(User loginUser){
        User validUser = getUserFromUsername(loginUser.getUsername());

        if (validUser == null){
            return false;
        }

        if (validUser.getPassword().equals(loginUser.getPassword())){
            return true;
        }

        return false;
    }

    public User getUserFromUsername(String username){
        return udao.getUserByUsername(username);
    }

    public User getUserFromUserID(int id){
        return udao.getUserByUserId(id);
    }
    
}
