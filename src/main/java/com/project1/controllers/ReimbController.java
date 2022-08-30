package com.project1.controllers;

import java.util.List;

import com.project1.models.JsonResponce;
import com.project1.models.Reimbursement;
import com.project1.models.User;
import com.project1.services.ReimService;

import io.javalin.http.Context;

public class ReimbController {

    ReimService rs = new ReimService();
    
    public void getAllReimbs(Context ctx){

        List<Reimbursement> list = rs.getAllReimbursements();

        ctx.json(new JsonResponce(true, "Retrieving all reimbursements", list));

    }

    public void getReimbsFromUserId(Context ctx){
        User user = ctx.sessionAttribute("user");

        List<Reimbursement> list = rs.getAllReimbursementsFromUserId(user.getId());

        ctx.json(new JsonResponce(true, "Retrieving all reimbursements for "+user.getFirstName(), list));
    }

    public void createLodgingReimb(Context ctx){
        Reimbursement reim = ctx.bodyAsClass(Reimbursement.class);

        rs.createLodgingReimbursement(reim.getAmount(), reim.getDescritpion(), reim.getAuthorId());

        ctx.json(new JsonResponce(true, "Lodging reimbursement submitted", null));

    }

    public void createTravelReimb(Context ctx){
        Reimbursement reim = ctx.bodyAsClass(Reimbursement.class);

        rs.createTravelReimbursement(reim.getAmount(), reim.getDescritpion(), reim.getAuthorId());

        ctx.json(new JsonResponce(true, "Travel reimbursement submitted", null));

    }

    public void createFoodReimb(Context ctx){
        Reimbursement reim = ctx.bodyAsClass(Reimbursement.class);

        rs.createFoodReimbursement(reim.getAmount(), reim.getDescritpion(), reim.getAuthorId());

        ctx.json(new JsonResponce(true, "Food reimbursement submitted", null));

    }

    public void createOtherReimb(Context ctx){
        Reimbursement reim = ctx.bodyAsClass(Reimbursement.class);
   
        rs.createOtherReimbursement(reim.getAmount(), reim.getDescritpion(), reim.getAuthorId());

        ctx.json(new JsonResponce(true, "Other reimbursement submitted", null));

    }

    public void approveReimb(Context ctx){
        Reimbursement reim = ctx.bodyAsClass(Reimbursement.class);
        User user = ctx.sessionAttribute("user");

        rs.acceptReim(reim, user.getId());

        ctx.json(new JsonResponce(true, "Reimb approved by "+user.getFirstName(), null));

    }

    public void denyReimb(Context ctx){
        Reimbursement reim = ctx.bodyAsClass(Reimbursement.class);
        User user = ctx.sessionAttribute("user");

        rs.denyReim(reim, user.getId());

        ctx.json(new JsonResponce(true, "Reimb denied by "+user.getFirstName(), null));

    }

    
}
