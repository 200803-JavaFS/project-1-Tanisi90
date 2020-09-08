const url = "http://localhost:8080/Project1/"

let userId = sessionStorage.getItem("user_id");

async function getUserReimbursement() {
    //console.log("In function");
    document.getElementById("reimbursementb").innerText = " ";
    let userId = sessionStorage.getItem("user_id");
    //console.log("user:" + userId);

    let resp = await fetch(url + "Reimbursements/" + userId, {
        credentials: 'include'
    });

    if (resp.status === 200) {
        console.log(resp);
        let rdata = await resp.json();
        for (let reimbursement of rdata) {
            console.log(reimbursement);

            let row = document.createElement("tr");
            let firstcell = document.createElement("td");
            firstcell.innerText = reimbursement.reimb_id;
            row.appendChild(firstcell);
            let secondcell = document.createElement("td");
            secondcell.innerText = reimbursement.reimb_amount;
            row.appendChild(secondcell);
            let thirdcell = document.createElement("td");
            let submittime = new Date(reimbursement.reimb_submitted);
            thirdcell.innerHTML = submittime.toLocaleDateString();
            row.appendChild(thirdcell);
            if (reimbursement.reimb_resolved != null) {
                let fourthcell = document.createElement("td");
                let resolvedtime = new Date(reimbursement.reimb_resolved);
                // console.log("Am in if statement");
                fourthcell.innerHTML = resolvedtime.toLocaleDateString();
                row.appendChild(fourthcell);
            } else {
                let fourthcell = document.createElement("td");
                //console.log("Am in else statement");
                row.appendChild(fourthcell);
            }
            let fifthcell = document.createElement("td");
            fifthcell.innerHTML = reimbursement.reimb_description;
            row.appendChild(fifthcell);
            let sixthcell = document.createElement("td");
            sixthcell.innerHTML = reimbursement.reimb_author;
            row.appendChild(sixthcell);
            if (reimbursement.reimb_resolver != null) {
                let seventhcell = document.createElement("td");
                seventhcell.innerHTML = reimbursement.reimb_resolver;
                row.appendChild(seventhcell);
            } else {
                let seventhcell = document.createElement("td");
                row.appendChild(seventhcell);
            }
            let eigthcell = document.createElement("td");
            eigthcell.innerHTML = reimbursement.reimb_status_id;
            row.appendChild(eigthcell);
            let ninethcell = document.createElement("td");
            ninethcell.innerHTML = reimbursement.reimb_type_id;
            row.appendChild(ninethcell);
            document.getElementById("reimbursementb").appendChild(row);
        }
    }
}
async function addReimbFunction() {

    // public int reimb_id;
    // public double reimb_amount;
    // public String reimb_submitted;
    // public String reimb_resolved;
    // public String reimb_description;
    // public String reimb_author;
    // public String reimb_resolver;
    // public String reimb_status_id;
    // public String reimb_type_id;
    let lrb = document.getElementsByClassName("form-check-input") // grabs a list of my radio buttons
    let rTypeId;
    for (let r of lrb){
        console.log(r)
        if(r.checked == true){
            rTypeId = r.value;  
        }
    }
    console.log(rTypeId)
    let reimburs = {
        reimb_amount: document.getElementById("Reimbursement_Amount").value,
        reimb_description: document.getElementById("Reimbursement Description").value,
        reimb_author: sessionStorage.getItem("user_id"),
        reimb_status_id: 1,
        reimb_type_id: rTypeId
    }

    console.log(reimburs);

    let resp = await fetch(url + "Reimbursements", {
        method: 'POST',
        body: JSON.stringify(reimburs),
        credentials: 'include'
    });

    if (resp.status === 201) {
        getUserReimbursement(); //Once I add it will call my other function to repopulate.
        document.getElementById("grabbing").innerText = "Reimbursement Submitted"

    }
}



async function logout() {

    let resp = await fetch(url + "logout", {
        credentials: 'include',
    });
    if (resp.status === 200) {
        console.log("logout");
        window.location.replace = "project1.html";
    }


}


