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

    if(resp.status===200){
        console.log(resp);
        let rdata = await resp.json();
        for (let reimbursement of rdata){
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
            // if (reimbursement.reimb_resolved != null){
            //     let fourthcell = document.createElement("td");
            //     let resolvedtime = new Date(reimbursement.reimb_resolved);
            //    // console.log("Am in if statement");
            //     fourthcell.innerHTML = resolvedtime.toLocaleDateString();
            //     row.appendChild(fourthcell);
            // } else {
            //     let fourthcell = document.createElement("td");
            //   //console.log("Am in else statement");
            //     row.appendChild(fourthcell);
            // }
            let forthcell = document.createElement("td");
            forthcell.innerHTML = reimbursement.reimb_description;
            row.appendChild(forthcell);
            let fifthcell = document.createElement("td");
            fifthcell.innerHTML = reimbursement.reimb_author;
            row.appendChild(fifthcell);
            // if (reimbursement.reimb_resolver != null){
            //     let seventhcell = document.createElement("td");
            //     seventhcell.innerHTML = reimbursement.reimb_resolver;
            //     row.appendChild(seventhcell);
            // } else {
            //     let seventhcell = document.createElement("td");
            //     row.appendChild(seventhcell);
            // }
            let sixthcell = document.createElement("td");
            sixthcell.innerHTML = reimbursement.reimb_status_id;
            row.appendChild(sixthcell);
            let seventhcell = document.createElement("td");
            seventhcell.innerHTML = reimbursement.reimb_type_id;
            row.appendChild(seventhcell);
            document.getElementById("reimbursementb").appendChild(row);
        }
    }
}

async function getPending(){
    // automatically pull pending tickets
}

async function changeStatus(){
    // Approve or Deny status
}

async function getByStatus(){
    //Filters by status
}

async function findByEmployee(){
    //Will find by employee ID
}