//Declaring the a variable called bodyElement
let bodyElement = document.body;

//Creating an Element
let div = document.createElement("div");

//Three ways of including content: innerHTML, innerText, textContent

div.innerHTML = "Hello World";

// Append the h1 element to the bodyElement
bodyElement.appendChild(div);

let student = [];

student.push({
	id: 1,
	firstName: "John",
	classification: "Doe",
	age: 18,
});

student.push({
	id: 2,
	firstName: "Barb",
	classification: "Jenn",
	age: 22,
});
