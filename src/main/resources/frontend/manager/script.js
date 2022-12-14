let reimsContainer = document.getElementById("reim-container");
let reims;

window.addEventListener("load", async () => {
    
    let response = await fetch("/api/session");
    
    let responseBody = await response.json();

    console.log(responseBody);
    
    if(!responseBody.success){
        window.location = "../";
    }

    user = responseBody.data;

    reims = await getAllReims();
    displayReims();

});

async function getAllReims(){
    let response = await fetch("/api/get-all-reims");

    let responsBody = await response.json();

    return responsBody.data;
}

async function getAllPendingReims(){
    let response = await fetch("/api/get-all-reims/pending");

    let responsBody = await response.json();

    return responsBody.data;
}

async function getAllApprovedReims(){
    let response = await fetch("/api/get-all-reims/approved");

    let responsBody = await response.json();

    return responsBody.data;
}

async function getAllDeniedReims(){
    let response = await fetch("/api/get-all-reims/denied");

    let responsBody = await response.json();

    return responsBody.data;
}

let logoutBtn = document.getElementById("logout-btn");
logoutBtn.addEventListener("click", () => {
    fetch("/api/session", { method: "DELETE" });
    window.location = "../";
});

let allButton = document.getElementById("all");
allButton.addEventListener("click", async (event) => {
    event.preventDefault();

    reimsContainer.innerHTML = "";
    reims = await getAllReims();
    displayReims();
});

let pendButton = document.getElementById("pending");
pendButton.addEventListener("click", async (event) => {
    event.preventDefault();

    reimsContainer.innerHTML = "";
    reims = await getAllPendingReims();
    displayReims();
});

let deniedButton = document.getElementById("denied");
deniedButton.addEventListener("click", async (event) => {
    event.preventDefault();

    reimsContainer.innerHTML = "";
    reims = await getAllDeniedReims();
    displayReims();
});

let approvedButton = document.getElementById("approved");
approvedButton.addEventListener("click", async (event) => {
    event.preventDefault();

    reimsContainer.innerHTML = "";
    reims = await getAllApprovedReims();
    displayReims();
});
    




/* 
    <div class = reim-form>
        <div class = "reim">
            <div class="left-side">
                <div id="top-layer">T:  ::  $</div>
                <div id = submitted>Submitted: </div>
                <div id = approved>Approved: </div>
                <div class="description-box">
                    <div class = "description">description</div>
                </div>
            </div>
            <div class="status-block">
                <div id="status"status>Status</div>
            </div>
        </div>
        <div id = "form">
            <button class = "btn btn-danger">Deny</button>
            <button class = "btn btn-success">Approve</button>
        </div>
    </div>
*/

function displayReims(){
    reims.forEach(reim => {

        console.log(reim);

        let reimForm = document.createElement("div");
        reimForm.className = "reim-form";

        let reimElem = document.createElement("div");
        reimElem.className = "reim"
        reimElem.id = `item-${reim.id}`;

        let leftSide = document.createElement("div");
        leftSide.className = "left-side"

        let topLayer = document.createElement("div");
        topLayer.id = "top-layer";
        topLayer.innerText = `${reim.type} worth ${reim.amount}$`

        let submitted = document.createElement("div");
        submitted.id = "submitted-date"
        submitted.innerText = `Submitted: ${reim.submitted}`

        let approved = document.createElement("div");
        approved.id = "approved-date";
        approved.innerText = `Approved: ${reim.resolved}`

        let descriptionBox = document.createElement("div");
        descriptionBox.className = "description-box";

        let description = document.createElement("div");
        description.className = "description";
        description.innerText= `${reim.descritpion}`

        let statusBlock = document.createElement("div");
        if(reim.status == "Pending"){
            statusBlock.className = "status-block-pending";
        }
        else if(reim.status == "Approved"){
            statusBlock.className = "status-block-approved";
        }
        else if(reim.status == "Denied"){
            statusBlock.className = "status-block-denied";
        }
        

        let status = document.createElement("div");
        status.id = "status";
        status.innerText=`${reim.status}`

        let form = document.createElement("div");
        form.className = "form";

        let denyBtnElem = document.createElement("button");
        denyBtnElem.className = ("btn btn-danger");
        denyBtnElem.id = "deny-button";
        denyBtnElem.innerText = "Deny";

        let aproveBtnElem = document.createElement("button");
        aproveBtnElem.className = "btn btn-success";
        aproveBtnElem.id = "approve-button";
        aproveBtnElem.innerText = "Approve";

        denyBtnElem.addEventListener("click", async (event) =>{
            event.preventDefault();

            console.log("Clicked deny")

            await fetch("/api/deny", {
                method: "PATCH",
                body: JSON.stringify({
                    "id":reim.id
                })
            })

            denyBtnElem.remove();
            aproveBtnElem.remove();
            statusBlock.className = "status-block-denied";
            status.innerText=`Denied`;

        })

        aproveBtnElem.addEventListener("click", async (event) =>{
            event.preventDefault();

            console.log("Clicked approve")

            await fetch("/api/approve", {
                method: "PATCH",
                body: JSON.stringify({
                    "id":reim.id
                })
            })

            denyBtnElem.remove();
            aproveBtnElem.remove();
            statusBlock.className = "status-block-approved";
            status.innerText=`Approved`;

        })

        


        leftSide.appendChild(topLayer);
        leftSide.appendChild(submitted);
        leftSide.appendChild(approved);
        leftSide.appendChild(descriptionBox);
        descriptionBox.appendChild(description);
        statusBlock.appendChild(status);
        reimElem.appendChild(leftSide);
        reimElem.appendChild(statusBlock);

        form.appendChild(denyBtnElem);
        form.appendChild(aproveBtnElem);

        if(reim.status == "Pending"){
            reimForm.appendChild(reimElem);
            reimForm.appendChild(form);
            reimsContainer.appendChild(reimForm);
        }
        else{
            reimForm.appendChild(reimElem);
            reimsContainer.appendChild(reimForm); 
        }

       

    });

}