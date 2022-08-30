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
    let response = await fetch("/api/get-reims-id");

    let responsBody = await response.json();

    return responsBody.data;
}

let logoutBtn = document.getElementById("logout-btn");
logoutBtn.addEventListener("click", () => {
    fetch("/api/session", { method: "DELETE" });
    window.location = "../";
});

/* 
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
*/

function displayReims(){
    reims.forEach(reim => {

        console.log(reim);

        let reimElem = document.createElement("div");
        reimElem.className = "reim";
        reimElem.id = `item-${reim.id}`;

        let leftSide = document.createElement("div");
        leftSide.className = "left-side";

        let topLayer = document.createElement("div");
        topLayer.id = "top-layer";
        topLayer.innerText = `${reim.type} worth ${reim.amount}$`;

        let submitted = document.createElement("div");
        submitted.id = "submitted"
        submitted.innerText = `Submitted: ${reim.submitted}`;

        let approved = document.createElement("div");
        approved.id = "approved";
        approved.innerText = `Approved: ${reim.resolved}`;

        let descriptionBox = document.createElement("div");
        descriptionBox.className = "description-box";

        let description = document.createElement("div");
        description.className = "description";
        description.innerText= `${reim.descritpion}`;

        let statusBlock = document.createElement("div");
        statusBlock.className = "status-block";

        let status = document.createElement("div");
        status.id = "status";
        status.innerText=`${reim.status}`;

        leftSide.appendChild(topLayer);
        leftSide.appendChild(submitted);
        leftSide.appendChild(approved);
        leftSide.appendChild(descriptionBox);
        descriptionBox.appendChild(description);
        statusBlock.appendChild(status);
        reimElem.appendChild(leftSide);
        reimElem.appendChild(statusBlock);
        reimsContainer.appendChild(reimElem);

    });

}