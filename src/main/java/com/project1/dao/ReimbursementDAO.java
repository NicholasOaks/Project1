package com.project1.dao;

import com.project1.models.*;
import java.util.ArrayList;

public interface ReimbursementDAO {

//Get all Reimmbursments and employee has submiteed via there user id
ArrayList<Reimbursement> getAllReimbursementsFromUserId(int id);

//Create a new reimbursement 
void createReimbursment(Reimbursement reimbursement, int type);

//Get all reimbursements in database
ArrayList<Reimbursement> getAllReimbursements();

//Get all reimbursements via filtering status
ArrayList<Reimbursement> getAllReimbursementsFilteredByStatus(int status);

//Change the status of a reimbursements and update date approved (0:Pending 1:Denied 2:Accepted)
void updateStatusOfReimbursement(Reimbursement reimbursement, int resolverid, int status);
    
}
