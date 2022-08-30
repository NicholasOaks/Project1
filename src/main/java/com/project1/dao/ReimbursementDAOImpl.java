package com.project1.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.project1.models.*;

import java.util.ArrayList;




public class ReimbursementDAOImpl implements ReimbursementDAO {
    
    static Logger logger = LogManager.getLogger(UserDAOImpl.class);

    private final String url = "jdbc:postgresql://database-1.cdiptlvyscwx.us-east-1.rds.amazonaws.com/Reimbursment";
    private final String username = "postgres";
    private final String password = "password";


    //Get all Reimmbursments and employee has submiteed via there user id
public ArrayList<Reimbursement> getAllReimbursementsFromUserId(int id){
    ArrayList<Reimbursement> List = new ArrayList<>();

    try(Connection conn = DriverManager.getConnection(this.url, this.username, this.password)) {

        //sql that we will be executing
        String sql = "select reimb_id, reimb_amount, reimb_submitted, reimb_resolved, reimb_description, reimb_receipt, reimb_author_id, reimb_resolver_id, reimb_status, reimb_type  from reimbursement inner join reimbursement_status on reimbursement.reimb_status_id = reimbursement_status.reimb_status_id inner join reimbursement_type on reimbursement.reimb_type_id = reimbursement_type.reimb_type_id where reimb_author_id = "+id+" order by reimb_resolved nulls first;";
        PreparedStatement ps = conn.prepareStatement(sql);

        //execute the SQL statement and return the result set into the variable rs
        ResultSet rs = ps.executeQuery();

        //iterate through the result set
        while(rs.next()){
            List.add(new Reimbursement(rs.getInt(1), rs.getDouble(2), rs.getTimestamp(3), rs.getTimestamp(4), rs.getString(5), rs.getBytes(6), rs.getInt(7), rs.getInt(8), rs.getString(9), rs.getString(10)));
        }
        
    } catch (SQLException e) {
        logger.error("SQL Exception getting getting all reimbursements from an user id", e);
    }
    return List;

};

//Create a new reimbursement 
public void createReimbursment(Reimbursement reimbursement, int type){

    try(Connection conn = DriverManager.getConnection(this.url, this.username, this.password)) {

        //sql that we will be executing
        String sql = "insert into reimbursement (reimb_amount, reimb_description, reimb_author_id, reimb_type_id) values ("+reimbursement.getAmount()+",?,"+reimbursement.getAuthorId()+","+type+");";
        PreparedStatement ps = conn.prepareStatement(sql);

        ps.setString(1,reimbursement.getDescritpion());

        //execute the SQL statement and create user in database
        ps.executeUpdate();
        
    } catch (SQLException e) {
        logger.error("SQL Exception creating reimbursement", e);
    }

};

//Get all reimbursements in database
public ArrayList<Reimbursement> getAllReimbursements(){

    ArrayList<Reimbursement> List = new ArrayList<>();

    try(Connection conn = DriverManager.getConnection(this.url, this.username, this.password)) {

        //sql that we will be executing
        String sql = "select reimb_id, reimb_amount, reimb_submitted, reimb_resolved, reimb_description, reimb_receipt, reimb_author_id, reimb_resolver_id, reimb_status, reimb_type  from reimbursement inner join reimbursement_status on reimbursement.reimb_status_id = reimbursement_status.reimb_status_id inner join reimbursement_type on reimbursement.reimb_type_id = reimbursement_type.reimb_type_id order by reimb_resolved nulls first";
        PreparedStatement ps = conn.prepareStatement(sql);

        //execute the SQL statement and return the result set into the variable rs
        ResultSet rs = ps.executeQuery();

        //iterate through the result set
        while(rs.next()){
            List.add(new Reimbursement(rs.getInt(1), rs.getDouble(2), rs.getTimestamp(3), rs.getTimestamp(4), rs.getString(5), rs.getBytes(6), rs.getInt(7), rs.getInt(8), rs.getString(9), rs.getString(10)));
        }
        
    } catch (SQLException e) {
        logger.error("SQL Exception getting getting all reimbursements", e);
    }
    return List;
    
};

//Get all reimbursements via filtering status
public ArrayList<Reimbursement> getAllReimbursementsFilteredByStatus(int status){

    ArrayList<Reimbursement> List = new ArrayList<>();

    try(Connection conn = DriverManager.getConnection(this.url, this.username, this.password)) {

        //sql that we will be executing
        String sql = "select reimb_id, reimb_amount, reimb_submitted, reimb_resolved, reimb_description, reimb_receipt, reimb_author_id, reimb_resolver_id, reimb_status, reimb_type  from reimbursement inner join reimbursement_status on reimbursement.reimb_status_id = reimbursement_status.reimb_status_id inner join reimbursement_type on reimbursement.reimb_type_id = reimbursement_type.reimb_type_id where reimbursement.reimb_status_id = "+status+" order by reimb_resolved nulls first";
        PreparedStatement ps = conn.prepareStatement(sql);

        //execute the SQL statement and return the result set into the variable rs
        ResultSet rs = ps.executeQuery();

        //iterate through the result set
        while(rs.next()){
            List.add(new Reimbursement(rs.getInt(1), rs.getDouble(2), rs.getTimestamp(3), rs.getTimestamp(4), rs.getString(5), rs.getBytes(6), rs.getInt(7), rs.getInt(8), rs.getString(9), rs.getString(10)));
        }
        
    } catch (SQLException e) {
        logger.error("SQL Exception getting getting all reimbursements with a filterted status", e);
    }
    return List;

};

//Change the status of a reimbursements and update date approved (0:Pending 1:Denied 2:Accepted)
public void updateStatusOfReimbursement(Reimbursement reimbursement, int resolverid, int status){

    try(Connection conn = DriverManager.getConnection(this.url, this.username, this.password)) {

        //sql that we will be executing
        String sql = "update reimbursement set reimb_resolved = current_timestamp, reimb_resolver_id = "+resolverid+", reimb_status_id  = "+status+" where reimb_id = "+reimbursement.getId();
        ;
        PreparedStatement ps = conn.prepareStatement(sql);

        //execute the SQL statement and create user in database
        ps.executeUpdate();
        
    } catch (SQLException e) {
        logger.error("SQL Exception updating reimbursement", e);
    }

};

}

