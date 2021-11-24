let bodyElement = document.body;

//Creating an element here
let div = document.createElement("div");

div.innerHTML = <h1>Hello World</h1>;

//Adding an element to the DOM

bodyElement.appendChild(div);

//Selecting div id
let firstDiv = document.querySelector("#first-div");
console.log(firstDiv.textContent);
console.log(firstDiv.innerText);
console.log(firstDiv.innerHTML);

//Removing an element from DOM
div.remove;

//Modifying element attribute
let imgElement = document.querySelector("img");
imgElement.setAttribute(
	"src",
	"https://upload.wikimedia.org/wikipedia/en/thumb/3/30/Java_programming_language_logo.svg/1200px-Java_programming_language_logo.svg.png"
);
imgElement.setAttribute("width", "100");

/*

    Modifying the CSS styling of elements

*/
let firstDivChildren = firstDiv.children;
console.log(firstDivChildren);

firstDivChildren[0].style.color = "red";
firstDivChildren[0].style.backgroundColor = "gray";

firstDivChildren[1].style.color = "cyan";
firstDivChildren[1].style.backgroundColor = "red";

let students = [];

students.push({
	id: 1,
	firstName: "John",
	lastName: "Doe",
	classification: "Freshman",
	age: 18,
});

students.push({
	id: 2,
	firstName: "Jane",
	lastName: "Doe",
	classification: "Senior",
	age: 22,
});

students.push({
	id: 3,
	firstName: "Bach",
	lastName: "Tran",
	classification: "Freshman",
	age: 18,
});

let studentTable = document.querySelector("#student-table");
let studentTableTbody = studentTable.querySelector("tbody");

//Iterating over student array
function populateTableData() {
	studentTableTbody.innerHTML = ""; // clear out all child elements from tbody

	for (let i = 0; i < students.length; i++) {
		let student = students[i];

		//Creating elements here
		let tr = document.createElement("tr");

		let td1 = document.createElement("td");
		let td2 = document.createElement("td");
		let td3 = document.createElement("td");
		let td4 = document.createElement("td");
		let td5 = document.createElement("td");

		//Putting the properties in the elements
		td1.innerText = student.id;
		td2.innerText = student.firstName;
		td3.innerText = student.lastName;
		td4.innerText = student.classification;
		td5.innerText = student.age;

		tr.appendChild(td1);
		tr.appendChild(td2);
		tr.appendChild(td3);
		tr.appendChild(td4);
		tr.appendChild(td5);

		studentTableTbody.appendChild(tr);
	}
}

populateTableData();

let button = document.querySelector("#demo-button");

let buttonClickArrowFunction = () => {
	// 1. Grab the information from the inputs
	let studentIdInputElement = document.querySelector("#student-id-input");
	let firstNameInputElement = document.querySelector("#first-name-input");
	let lastNameInputElement = document.querySelector("#last-name-input");
	let classificationDropdownElement = document.querySelector(
		"#classification-dropdown"
	);
	let ageInputElement = document.querySelector("#age-input");

	// 2. Create a new student object with that information
	let newStudent = {
		id: studentIdInputElement.value,
		firstName: firstNameInputElement.value,
		lastName: lastNameInputElement.value,
		classification: classificationDropdownElement.value,
		age: ageInputElement.value,
	};

	// 3. Add the student object to the students array
	students.push(newStudent);

	// 4. invoke the populateTableData function
	populateTableData();
};
// add an event listener to the button:
button.addEventListener("click", buttonClickArrowFunction);
