window.addEventListener("load", async () => {
	console.log("EXECUTED");

	let res = await fetch("http://localhost:8080/checkloginstatus", {
		method: "GET",
		credentials: "include",
	});

	// If the above request results in a 200 status code, that means we are actually logged in
	// So we need to take the userRole information and determine where to redirect them to
	if (res.status === 200) {
		let userObj = await res.json();

		if (userObj.userRole === "employee") {
			//If it's employee, send to this location below
			window.location.href = "employees-homepage.html";
		} else if (userObj.userRole === "manager") {
			window.location.href = "manager-homepage.html";
		}
	}
});

/************
 * Login functionality
 * Grab the button element
 * ******************/
let loginButton = document.querySelector("#login-btn");
//Adding event listener
loginButton.addEventListener("click", loginButtonClicked);

function loginButtonClicked() {
	login();
}

async function login() {
	let usernameInput = document.querySelector("#username");
	let passwordInput = document.querySelector("#password");

	try {
		let res = await fetch("http://localhost:8080/login", {
			method: "POST",
			credentials: "include", //To make sure that the browser retains the cookies and include it in future requests
			body: JSON.stringify({
				username: usernameInput.value,
				password: passwordInput.value,
			}), //JSON.stringify will take a JavaScript object and turn it into JSON
		});

		let data = await res.json();
		/***************
		 * Check if login is incorrect or not. If the status code is 400, data will represent an object with the 'message' property, which we can display
		 */
		if (res.status === 400) {
			let loginErrorMessage = document.createElement("p");
			let loginDiv = document.querySelector("#login-info");

			loginErrorMessage.innerHTML = data.message;
			loginErrorMessage.style.color = "red";
			loginDiv.appendChild(loginErrorMessage);
		}
		/**************
		 * However, if the status code is 200, the data variable will represent an object that corresponds with the user. We can go ahead and check what role the user has.
		 ************************/
		if (res.status === 200) {
			console.log(data.userRole);
			if (data.userRole === "employee") {
				//then redirect to employee homepage
				window.location.href = "employees-homepage.html";
			} else if (data.userRole === "manager") {
				window.location.href = "manager-homepage.html";
			}
		}
	} catch (err) {
		console.log(err);
	}
}
