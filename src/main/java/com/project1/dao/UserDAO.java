package com.project1.dao;

import com.project1.models.User;

public interface UserDAO {

    //Get a user object by username
    User getUserByUsername(String username);

    //Get a user by userId
    User getUserByUserId(int id);

    
    
}
