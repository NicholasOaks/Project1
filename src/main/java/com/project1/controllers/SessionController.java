package com.project1.controllers;

import com.project1.models.JsonResponce;
import com.project1.models.User;
import com.project1.services.UserService;

import io.javalin.http.Context;

public class SessionController {

    UserService us = new UserService();

    public void login(Context ctx){
        User user = ctx.bodyAsClass(User.class);

        if(us.validateUser(user)){
            User userFromDB = us.getUserFromUsername(user.getUsername());
            ctx.sessionAttribute("user", userFromDB);

            ctx.json(new JsonResponce(true, "login successful", userFromDB));
        }
        else{
            ctx.json(new JsonResponce(false, "invalid username or password", null));
        }
    }

    public void logout(Context ctx){

        ctx.sessionAttribute("user" ,null);
        ctx.json(new JsonResponce(true, "Session logged out", null));

    }

    public void checkSession (Context ctx){

        User user = ctx.sessionAttribute("user");

        if(user == null){
            ctx.json(new JsonResponce(false, "no session found", null));
        }
        else{
            ctx.json(new JsonResponce(true, "session found", user));
        }

    }
    
}
