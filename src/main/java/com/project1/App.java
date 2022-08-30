package com.project1;

import com.project1.controllers.ReimbController;
import com.project1.controllers.SessionController;

import io.javalin.Javalin;
import io.javalin.http.staticfiles.Location;

public class App 
{
    public static void main( String[] args )
    {
        

        ReimbController rc = new ReimbController();
        SessionController sc = new SessionController();

        Javalin app = Javalin.create(config -> {
            //Location.CLASSPATH is referencing the resources folder for maven
            config.addStaticFiles("/frontend", Location.CLASSPATH);
        }).start(9900);

        //session endpoints
        app.get("/api/session", sc::checkSession);
        app.post("/api/session", sc::login);
        app.delete("/api/session", sc::logout);

        //reimbursement endpoints
        app.get("/api/get-all-reims", rc::getAllReimbs);
        app.get("/api/get-reims-id", rc::getReimbsFromUserId);

        app.post("/api/create-lodging", rc::createLodgingReimb);
        app.post("/api/create-travel", rc::createTravelReimb);
        app.post("/api/create-food", rc::createFoodReimb);
        app.post("/api/create-other", rc::createOtherReimb);

        app.patch("/api/approve",rc::approveReimb);
        app.patch("/api/deny",rc::denyReimb);


    }
}
