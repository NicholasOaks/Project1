package com.project1.services;

import com.project1.models.*;
import com.project1.dao.ReimbursementDAOImpl;

import java.util.ArrayList;

import com.project1.dao.ReimbursementDAO;

public class ReimService {

    ReimbursementDAO rdao = new ReimbursementDAOImpl();

    public ReimService(){
        this.rdao = new ReimbursementDAOImpl();
    }

    public ReimService(ReimbursementDAO rdao){
        this.rdao = rdao;
    }

    public ArrayList<Reimbursement> getAllReimbursementsFromUserId(int id){
        return rdao.getAllReimbursementsFromUserId(id);
    }

    public void createLodgingReimbursement(double amount,String description, int id){
        Reimbursement reimbursement = new Reimbursement(amount, description, id);
        rdao.createReimbursment(reimbursement, 1);
    }

    public void createTravelReimbursement(double amount,String description, int id){
        Reimbursement reimbursement = new Reimbursement(amount, description, id);
        rdao.createReimbursment(reimbursement, 2);
    }

    public void createFoodReimbursement(double amount,String description, int id){
        Reimbursement reimbursement = new Reimbursement(amount, description, id);
        rdao.createReimbursment(reimbursement, 3);
    }

    public void createOtherReimbursement(double amount,String description, int id){
        Reimbursement reimbursement = new Reimbursement(amount, description, id);
        rdao.createReimbursment(reimbursement, 4);
    }

    public ArrayList<Reimbursement> getAllReimbursements(){
        return rdao.getAllReimbursements();
    }

    public ArrayList<Reimbursement> getAllReimArePending(){
        return rdao.getAllReimbursementsFilteredByStatus(1);
    }

    public ArrayList<Reimbursement> getAllReimAreDenied(){
        return rdao.getAllReimbursementsFilteredByStatus(2);
    }

    public ArrayList<Reimbursement> getAllReimAreApproved(){
        return rdao.getAllReimbursementsFilteredByStatus(3);
    }

    public void denyReim(Reimbursement reim, int resolverId){
        rdao.updateStatusOfReimbursement(reim, resolverId, 2);
    }

    public void acceptReim(Reimbursement reim, int resolverId){
        rdao.updateStatusOfReimbursement(reim, resolverId, 3);
    }





    
    
}
