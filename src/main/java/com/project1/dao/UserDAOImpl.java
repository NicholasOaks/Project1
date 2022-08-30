package com.project1.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.project1.models.User;

public class UserDAOImpl implements UserDAO{

    static Logger logger = LogManager.getLogger(UserDAOImpl.class);

    private final String url = "jdbc:postgresql://database-1.cdiptlvyscwx.us-east-1.rds.amazonaws.com/Reimbursment";
    private final String username = "postgres";
    private final String password = "password";

    @Override
    public User getUserByUsername(String username) {
        User user = null;
        
        //try with resources will close the object thats within the parenthesis
        try(Connection conn = DriverManager.getConnection(this.url, this.username, this.password)) {

            //sql that we will be executing
            String sql = "select user_id, user_username, user_password, user_first_name, user_last_name, user_email ,user_role from users inner join user_roles on users.user_role_id = user_roles.user_role_id where user_username = ?";
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setString(1, username);

            //execute the SQL statement and return the result set into the variable rs
            ResultSet rs = ps.executeQuery();

            //iterate through the result set
            while(rs.next()){
                user = new User(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7));
            }
            
        } catch (SQLException e) {
            logger.error("SQL Exception from getting a User from username", e);
        }

        return user;
    }

    @Override
    public User getUserByUserId(int id) {
        User user = null;
        
        //try with resources will close the object thats within the parenthesis
        try(Connection conn = DriverManager.getConnection(this.url, this.username, this.password)) {

            //sql that we will be executing
            String sql = "select user_id, user_username, user_password, user_first_name, user_last_name, user_email ,user_role from users inner join user_roles on users.user_role_id = user_roles.user_role_id where user_id = "+id;
            PreparedStatement ps = conn.prepareStatement(sql);

            //execute the SQL statement and return the result set into the variable rs
            ResultSet rs = ps.executeQuery();

            //iterate through the result set
            while(rs.next()){
                user = new User(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7));
            }
            
        } catch (SQLException e) {
            logger.error("SQL Exception from getting a User from username", e);
        }

        return user;
    }
    
}
