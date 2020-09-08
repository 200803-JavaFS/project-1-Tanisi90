const url = "http://localhost:8080/Project1/"

let userId = sessionStorage.getItem("user_id");

async function getUserReimbursement() {

    document.getElementById("reimbursementb2").innerText = "";

    let resp = await fetch(url + "Reimbursements", {
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
            document.getElementById("reimbursementb2").appendChild(row);
        }
    }
}

async function getByStatusFunc() {

    document.getElementById("reimbursementb3").innerText = "";

    let reimbStat = document.getElementById("status").value;
    let reimbStatId;
    console.log(reimbStat);
    console.log(reimbStat == "Pending");
   if(reimbStat == "Pending"){
        console.log(reimbStatId);
        reimbStatId = 1;
        console.log(reimbStatId);
   }else if (reimbStat == "Approved") {
        reimbStatId = 2;
   }else{
        reimbStatId = 3;
   }

   console.log("Am I here?");
   console.log(reimbStatId);
    let resp = await fetch(url+"StatusReimbursement/"+ reimbStatId, {
        credentials: "include"
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
            sixthcell.innerHTML = reimbursement.reimb_author.username;
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
            eigthcell.innerHTML = reimbursement.reimb_status_id.reimb_status;
            row.appendChild(eigthcell);
            let ninethcell = document.createElement("td");
            ninethcell.innerHTML = reimbursement.reimb_type_id.reimb_type;
            row.appendChild(ninethcell);
            document.getElementById("reimbursementb3").appendChild(row);
        }
    }
}

async function updateReimbStatusFunc(){

    let reimbId = document.getElementById("reimb_id");
    let reimbValue = reimbId.value;

    const reimbStatusArray = document.querySelectorAll('input[name="status"]');
    let chooseStatus;
    for (const reimbStatus of reimbStatusArray) {
        if (reimbStatus.checked) {
            rStatsChoice = reimbStatus.value;
            break;
        }
    }

    // public int reimb_id;
	// public double reimb_amount;
	// public String reimb_submitted;
	// public String reimb_resolved;
	// public String reimb_description;
	// public String reimb_author;
	// public String reimb_resolver;
	// public String reimb_status_id;
	// public String reimb_type_id;
	
    let reimbStatus = {
        reimb_id: reimbValue,
        reimb_author: userId,
        reimbStatus: chooseStatus
    }

    let resp = await fetch(url + "reimbursements", {
        method: 'POST',
        body: JSON.stringify(reimbStatus),
        credentials: "include"
    });

    if(resp.status===202) {
        document.getElementById("Success").innerHTML = "Reimbursement update successful";
    } else {
        document.getElementById("Success").innerHTML = "Reimbursement update failure";
    }
}

async function logoutFunc(){

    let resp = await fetch(url+"logout", {
        credentials: "include"
    });

    if(resp.status===200) {
        window.location.replace = "project1.html";
    }
}