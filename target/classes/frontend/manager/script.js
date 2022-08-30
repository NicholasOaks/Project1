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

let logoutBtn = document.getElementById("logout-btn");
logoutBtn.addEventListener("click", () => {
    fetch("/api/session", { method: "DELETE" });
    window.location = "../";
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
        submitted.id = "submitted"
        submitted.innerText = `Submitted: ${reim.submitted}`

        let approved = document.createElement("div");
        approved.id = "approved";
        approved.innerText = `Approved: ${reim.resolved}`

        let descriptionBox = document.createElement("div");
        descriptionBox.className = "description-box";

        let description = document.createElement("div");
        description.className = "description";
        description.innerText= `${reim.descritpion}`

        let statusBlock = document.createElement("div");
        statusBlock.className = "status-block";

        let status = document.createElement("div");
        status.id = "status";
        status.innerText=`${reim.status}`

        let form = document.createElement("div");
        form.className = "form";

        let denyBtnElem = document.createElement("button");
        denyBtnElem.className = ("btn btn-danger");
        denyBtnElem.innerText = "Deny";

        let aproveBtnElem = document.createElement("button");
        aproveBtnElem.className = "btn btn-success";
        aproveBtnElem.innerText = "Approve";


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