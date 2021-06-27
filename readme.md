Welcome to Credit Card Application
Based on 
	Java-8
	Sprint Boot 2.5.1
	UI -> Javascript and HTML
	In-memory H2 database

Run the app using maven wrapper:
mvnw spring-boot:run
	
Open application UI at http://localhost:8191/welcome.html
UI has two features:
	1. Add new Credit Card details.
	2. GET/View/Refresh existing Credit card details

API Curls:

curl --location --request GET 'http://localhost:8191/credit-card/all'

curl --location --request POST 'http://localhost:8191/credit-card/add' \
--header 'Content-Type: application/json' \
--data-raw '[
    {
        "bank": "HSBC Canada",
        "cardNumber": "5601123456783456",
        "expiryDate": "2025-10"
    }
]'