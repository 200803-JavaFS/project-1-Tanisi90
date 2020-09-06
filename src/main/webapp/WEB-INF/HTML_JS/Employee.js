const url = "http://localhost:8080/Project1/"

let userId = sessionStorage.getItem("user_id");

 async function getUserReimbursement() {
    console.log("In function");
    document.getElementById("reimbursementb").innerText = " ";
    let userId = sessionStorage.getItem("user_id");
    console.log("user:" + userId);

    let resp = await fetch(url + "UserReimbursements/" + userId, {
        credentials: 'include'
    });

    if(resp.status===200){
        console.log(resp);
        let rdata = await resp.json();
        for (let reimbursement of rdata){
            console.log(reimbursement);

            let row = document.createElement("table_row");
            let firstcell = document.createElement("table_data");
            firstcell.innerHTML = reimbursement.reimb_id;
            row.appendChild(firstcell);
            let secondcell = document.createElement("table_data");
            secondcell.innerHTML = reimbursement.reimb_amount;
            row.appendChild(secondcell);
            let thirdcell = document.createElement("table_data");
            let submittime = new Date(reimbursement.reimb_submitted);
            thirdcell.innerHTML = submittime.toLocaleDateString();
            row.appendChild(thirdcell);
            if (reimbursement.resolved != null){
                let fourthcell = document.createElement("table_data");
                let resolvedtime = new Date(reimbursement.reimb_resolved);
                fourthcell.innerHTML = resolvedtime.toLocaleDateString();
                row.appendChild(fourthcell);
            } else {
                let fourthcell = document.createElement("table_data");
                row.appendChild(fourthcell);
            }
            let fifthcell = document.createElement("table_data");
            fifthcell.innerHTML = reimbursement.reimb_description;
            row.appendChild(fifthcell);
            let sixthcell = document.createElement("table_data");
            sixthcell.innerHTML = reimbursement.reimb_author.userId;
            row.appendChild(sixthcell);
            if (reimbursement.reimbResolverId != null){
                let seventhcell = document.createElement("table_data");
                seventhcell.innerHTML = reimbursement.reimb_resolver.userId;
                row.appendChild(seventhcell);
            } else {
                let seventhcell = document.createElement("table_data");
                row.appendChild(seventhcell);
            }
            let eigthcell = document.createElement("table_data");
            eigthcell.innerHTML = reimbursement.reimb_status_id.reimb_status_id;
            row.appendChild(eigthcell);
            let ninethcell = document.createElement("table_data");
            ninethcell.innerHTML = reimbursement.reimb_type_id.reimb_type_id;
            row.appendChild(ninethcell);
            document.getElementById("reimbursementb").appendChild(row);
        }
    }

    async function logout() {

        let resp = await fetch(url + "logout", {
            credentials: 'include',
        });
        if (resp.status === 200) {
            console.log("logout");
            window.location.href = "project1.html";
        }
    }
}
