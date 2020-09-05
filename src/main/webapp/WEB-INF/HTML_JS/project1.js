const url = "http://localhost:8080/Project1/"

document.getElementById("loginbtn").addEventListener("click", loginFunc);

async function loginFunc(){
    let usern = document.getElementById("username").value;
    let userp = document.getElementById("password").value;

    let user = {  // this creates the user object
        username : usern,
        password : userp
    }

    let resp = await fetch(url + "Login", {
        method: 'POST',
        body : JSON.stringify(user),
        credentials: "include"
    })

    if(resp.status === 200){
        document.getElementById("login row").innerText = "YOU HAVE LOGGED IN!";
        let button = document.createElement('button')
        button.className = "btn btn-success";
        // Redirect elsewhere
    }else {
        document.getElementById("login row").innerText = "Your Username or Password is incorrect!";
    }
}