# Bank-Management
Simple bank management application

Uruchomienie aplikacji mvn compile spring-boot:run .

Przykłady wywołąnia api. 

Metoda GET http://localhost:8080/nbp/bid/eur - Pobranie kursu sprzedaży waluty. 
Metoda GET http://localhost:8080/nbp/ask/eur - Pobranie kursu kupna waluty.
Metoda POST http://localhost:8080/account - Tworzenie nowego konta 
Przykładowy request: 
{
	"pesel": "90010100001",
	"fullname": "test test",
	"balance": 100.0
}
Przykładowy response: 

{
   "pesel": "90010100001",
   "fullname": "test test",
   "subAccounts":    [
            {
         "currency": "PLN",
         "balance": 100
      },
            {
         "currency": "USD",
         "balance": 0
      }
   ]
}

Metoda GET http://localhost:8080/account/90010100001 - Pobranie danych konta po nr. pesel.

Metoda GET http://localhost:8080/account/subAccount/90010100001/PLN - Pobranie danych konta walutowego w PLN
Metoda GET http://localhost:8080/account/subAccount/90010100001/USD - Pobranie danych konta walutowego w USD
Przykładowy response:
{
   "currency": "PLN",
   "balance": 100
}

Metoda PATCH http://localhost:8080/account/subAccount/90010100001/100/PLN - Wpłata środków na konto walutowe w PLN dla danego usera.
Metoda PATCH http://localhost:8080/account/subAccount/90010100001/100/USD - Wpłata środków na konto walutowe w USD dla danego usera.

Metoda PATCH http://localhost:8080/account/subAccount/exchange/90010100001/50/USD - Przewalutowanie z konta USD na konto PLN wartości 50 dla danego usera.
Metoda PATCH http://localhost:8080/account/subAccount/exchange/90010100001/50/PLN - Przewalutowanie z konta PLN na konto USD wartości 50 dla danego usera.
Przykładowy response:
{
   "pesel": "90010100001",
   "fullname": "test test",
   "subAccounts":    [
            {
         "currency": "PLN",
         "balance": 150
      },
            {
         "currency": "USD",
         "balance": 13
      }
   ]
}
