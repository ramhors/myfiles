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

//Logout btn
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

		if (reimbursement.resolver != 0) {
			td5.innerHTML = reimbursement.status;
			td6.innerHTML = reimbursement.resolver;
		} else {
			td5.innerHTML = "Pending";
			td6.innerHTML = "--";
		}

		let td7 = document.createElement("td");
		td7.innerHTML = reimbursement.type;

		let td8 = document.createElement("td");
		td8.innerHTML = reimbursement.description;

		let td9 = document.createElement("td");
		td9.innerHTML = reimbursement.author;

		let td10 = document.createElement("td");
		let viewImageButton = document.createElement("button");
		viewImageButton.innerHTML = "View Image";
		td10.appendChild(viewImageButton);

		viewImageButton.addEventListener("click", () => {
			let reimbursementImageModal = document.querySelector(
				"#receipt-image-modal"
			);
			//Close button
			let modalCloseElement = reimbursementImageModal.querySelector("button");
			modalCloseElement.addEventListener("click", () => {
				reimbursementImageModal.classList.remove("is-active");
			});
			//Take element and use querySelector on it to find the child elements that are nested within it.
			let modalContentElement =
				reimbursementImageModal.querySelector(".modal-content");
			modalContentElement.innerHTML = "";

			let imageElement = document.createElement("img");
			imageElement.setAttribute(
				`src`,
				`http://localhost:8080/reimbursements/${reimbursement.reimbId}/image`
			);
			modalContentElement.appendChild(imageElement);

			reimbursementImageModal.classList.add("is-active"); //adding a class to the modal element to have it display
		});

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

		tbodyElement.appendChild(tr);
	}
}
//Submitting Reimbursement
let reimbursementSubmitButton = document.querySelector(
	"#submit-reimbursement-btn"
);
reimbursementSubmitButton.addEventListener("click", submitReimbursement);

async function submitReimbursement() {
	console.log("submit was clicked");

	let reimbursementTypeInput = document.querySelector("#reimbursement-type");
	let reimbursementAmountInput = document.querySelector(
		"#reimbursement-amount"
	);
	let reimbursementDescriptionInput = document.querySelector("#description");

	let reimbursementImageInput = document.querySelector("#reimbursement-image");

	const file = reimbursementImageInput.files[0];

	let formData = new FormData();
	formData.append("type", reimbursementTypeInput.value);
	formData.append("amount", reimbursementAmountInput.value);
	formData.append("description", reimbursementDescriptionInput.value);

	formData.append("receipt", file);

	let res = await fetch("http://localhost:8080/reimbursements", {
		method: "POST",
		credentials: "include",
		body: formData,
	});

	let data = await res.text();
	if (res.status === 201) {
		let successfulSubmit = document.createElement("p");
		let submitDiv = document.querySelector("#submit-info");

		successfulSubmit.innerHTML = data;

		successfulSubmit.style.color = "blue";

		submitDiv.appendChild(successfulSubmit);

		setTimeout(() => window.location.reload(), 1000);

		//populateTableWithReimbursement(); //Refreshing the table if it's successful
	} else if (res.status === 400) {
		let reimbursementForm = document.querySelector("reimbursement-submit-form");

		let data = await res.json();

		let pTag = document.createElement("p");
		pTag.innerHTML = data.message;
		pTag.style.color = "red";

		reimbursementForm.appendChild(pTag);
	}
}

// 	if (res.status === 200) {
// 		populateTableWithReimbursement();
// 		console.log(res);
// 	}
// 	setTimeout(() => window.location.reload(), 1000);
// }

// 	if (res.status === 200) {
// 		populateTableWithReimbursement();
// 		console.log(res);
// 	}
// 	setTimeout(() => window.location.reload(), 1000);
// }
