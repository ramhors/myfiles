window.addEventListener("load", async () => {
	let res = await fetch("http://localhost:8080/checkloginstatus", {
		credentials: "include",
		method: "GET",
	});

	if (res.status === 401) {
		window.location.href = "index.html";
	}

	populateTableWithReimbursement();
});

async function populateTableWithReimbursement() {
	let res = await fetch("http://localhost:8080/reimbursements", {
		credentials: "include",
		method: "GET",
	});

	let tbodyElement = document.querySelector("#reimbursement-table tbody");
	tbodyElement.innerHTML = "";
	let reimbursementArray = await res.json();

	for (let i = 0; i < reimbursementArray.length; i++) {
		let reimbursement = reimbursementArray[i];

		let tr = document.createElement("tr");

		let td1 = document.createElement("td");
		td1.innerHTML = reimbursement.reimbId;

		let td2 = document.createElement("td");
		td2.innerHTML = reimbursement.amount;

		let td3 = document.createElement("td");
		td3.innerHTML = reimbursement.dateSubmitted;

		let td4 = document.createElement("td");
		td4.innerHTML = reimbursement.dateResolved;

		let td5 = document.createElement("td"); //status
		let td6 = document.createElement("td"); //resolverId

		//If status is already approved, display the status and the resolverId
		let td7 = document.createElement("td");
		td7.innerHTML = reimbursement.type;

		let td8 = document.createElement("td");
		td8.innerHTML = reimbursement.description;

		let td9 = document.createElement("td");
		td9.innerHTML = reimbursement.author;

		let td10 = document.createElement("td");
		let td11 = document.createElement("td");
		let td12 = document.createElement("td");

		let viewImageButton = document.createElement("button");
		viewImageButton.innerHTML = "View Image";
		td12.appendChild(viewImageButton);

		viewImageButton.addEventListener("click", () => {
			let reimbursementImageModal = document.querySelector(
				"#receipt-image-modal"
			);
			//Close button
			let modalCloseElement = reimbursementImageModal.querySelector("button");
			modalCloseElement.addEventListener("click", () => {
				reimbursementImageModal.classList.remove("is-active");
			});

			let modalContentElement =
				reimbursementImageModal.querySelector(".modal-content");
			modalContentElement.innerHTML = "";

			let imageElement = document.createElement("img");
			imageElement.setAttribute(
				`src`,
				`http://localhost:8080/reimbursements/${reimbursement.reimbId}/image`
			);
			modalContentElement.appendChild(imageElement);

			reimbursementImageModal.classList.add("is-active");
		});

		if (reimbursement.resolver != 0) {
			td5.innerHTML = reimbursement.status;
			td6.innerHTML = reimbursement.resolver;
		} else {
			td5.innerHTML = "Pending";
			td6.innerHTML = "-";

			let statusInput = document.createElement("input");
			statusInput.setAttribute("type", "text");

			let statusButton = document.createElement("button");
			statusButton.innerText = "Approve Me";
			statusButton.addEventListener("click", async () => {
				let res = await fetch(
					`http://localhost:8080/reimbursements/${reimbursement.reimbId}/status`,
					{
						credentials: "include",
						method: "PATCH",
						body: JSON.stringify({
							status: statusInput.value,
						}),
					}
				);

				if (res.status === 200) {
					populateTableWithReimbursement();
				}
			});
			td10.appendChild(statusButton);
			td11.appendChild(statusInput);
		}
		tr.appendChild(td1);
		tr.appendChild(td2);
		tr.appendChild(td3);
		tr.appendChild(td4);
		tr.appendChild(td5);
		tr.appendChild(td6);
		tr.appendChild(td7);
		tr.appendChild(td8);
		tr.appendChild(td9);
		tr.appendChild(td10);
		tr.appendChild(td11);
		tr.appendChild(td12);

		tbodyElement.appendChild(tr);
	}
}
let logoutBtn = document.querySelector("#logout");

logoutBtn.addEventListener("click", async () => {
	let res = await fetch("http://localhost:8080/logout", {
		method: "POST",
		credentials: "include",
	});

	if (res.status === 200) {
		window.location.href = "index.html";
	}
});
