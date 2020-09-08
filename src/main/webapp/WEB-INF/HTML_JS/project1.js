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
        document.getElementById("login_row").innerText = "YOU HAVE LOGGED IN!";
        //console.log(resp);
        let uidresp = await resp.json();
        sessionStorage.setItem("user_id", uidresp);
        RolePages();
    }else {
        document.getElementById("login_row").innerText = "Your Username or Password is incorrect!";
    }

    async function RolePages() {

        let resp = await fetch(url + "Success", {
            method: 'GET',
            credentials: "include"
        });
        console.log(resp.status);
        if (resp.status === 200) {
            console.log(resp);
            let rdata = await resp.json();
            console.log(rdata);
            let UserId = rdata;
            sessionStorage.setItem("user_role_id", UserId);

            if (UserId == 1) {
                window.location.replace("Employee.html"); 
            } else if (UserId == 2) {
                window.location.replace("FinanceManager.html");
            }
            
        } else {
            document.getElementById("login_row").innerText = "Login failed!";
        }
    }
}
