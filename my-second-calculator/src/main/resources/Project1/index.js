let loginButton = document.querySelector(‘#login-btn’);
	loginButton.addEventListener(‘click’, loginButtonClick);
	function loginButtonClick() {
	let usernameInput = docume.querySelector(‘#username’)
let usernameInput = docume.querySelector(‘#password’)

	fetch(‘http://localhost:8080/login’, {
	method : “POST”,
	body : {
		username: usernameInput.value,
		password: passwordInput.value,
}
	})
}

