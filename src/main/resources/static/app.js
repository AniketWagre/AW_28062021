
function getCardData() {
	document.getElementById("cardlist").innerHTML = "";
	var getUrl = "http://localhost:8191/credit-card/all";
	fetch(getUrl)
		.then(response => response.text())
		.then(result => {
			var data = JSON.parse(result)
			console.log(data);
			for (var i = 0; i < data.length; i++) {
				var eachCard = data[i];
				var cardString = "Bank:" + eachCard.bank + " " + "Card Number: " + eachCard.cardNumber + " " + "expiryDate: " + eachCard.expiryDate + " ";

				var node = document.createElement("LI");
				node.appendChild(document.createTextNode(cardString));
				document.getElementById("cardlist").appendChild(node);
			}
		}).catch(error => console.log('error', error))
}


function addCardData() {
	var bank = document.getElementById("bank").value;
	var cardNumber = document.getElementById("cardNumber").value;
	var expiryDate = document.getElementById("expiryDate").value;
	const postUrl = 'http://localhost:8191/credit-card/add';
	var card = {};
	card.attribute = [];
	card.attribute.push({});
	card.attribute[0].bank = bank;
	card.attribute[0].cardNumber = cardNumber;
	card.attribute[0].expiryDate = expiryDate;
	fetch(
		postUrl,
		{
			headers: { "Content-Type": "application/json" },
			body: JSON.stringify(card.attribute),
			method: "POST"
		}
	).then(response => response.text())
		.then(result => {
			document.getElementById("status").innerHTML = result;
		})
		.catch(error => {
			document.getElementById("status").innerHTML = 'Update failed';
		})
}

function validateCard() {
	var bank = document.getElementById("bank").value;
	var cardNumber = document.getElementById("cardNumber").value;
	var expiryDate = document.getElementById("expiryDate").value;
	if (bank == "") {
		alert("bank must be filled out");
		return false;
	}
	if (cardNumber.length != 16) {
		alert("Card Number must be filled out and should be 16 digits");
		return false;
	}
	if (expiryDate == "") {
		alert("Expiry Date must be filled out");
		return false;
	}
	addCardData();
}

function allowNumberOnly(event) {
	// Only ASCII character in that range allowed
	var ASCIICode = (event.which) ? event.which : event.keyCode
	if (ASCIICode > 31 && (ASCIICode < 48 || ASCIICode > 57))
		return false;
	return true;
}