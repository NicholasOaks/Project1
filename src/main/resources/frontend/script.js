window.onload = async () => {
    let response = await fetch("/api/session");

    let responseBody = await response.json();

    console.log(responseBody);

    if(responseBody.successful){
        if(responseBody.data.type == "Manager"){
            window.location = "./manager";
        }
        else if(responseBody.data.type == "Employee"){
            window.location = "./employee";
        }
    }
}

let loginFormElem = document.getElementById("login-form");

loginFormElem.addEventListener("submit", (event) => {
    //prevent the original implementation of the on submit event
    event.preventDefault();

    //retrieve values from input
    let userNameInputElement = document.getElementById("username-input");
    let passwordInputElement = document.getElementById("password-input");

    sendLoginRequest(userNameInputElement.value, passwordInputElement.value);

});

async function sendLoginRequest(username, password){

    let response = await fetch("/api/session", {
        method: "POST",
        //JSON.stringfy() converts javascript objects to json strings
        body: JSON.stringify({
            "username": username,
            "password": password
        })
    });

    let responseBody = await response.json();

    if(responseBody.success){
        //depending on who logs in send them to employee or manager dashboard

        if(responseBody.data.type == "Manager"){
            window.location = "./manager";
        }
        else if(responseBody.data.type == "Employee"){
            window.location = "./employee";
        }
        
        
    }else{
        //do unsuccessful stuff
        let messageElem = document.getElementById("message");

        messageElem.innerText = responseBody.message;
        messageElem.style.display = 'block';
    }
}