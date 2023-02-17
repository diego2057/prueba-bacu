# Read Me First
The following was discovered as part of building this project:

* The original package name 'com.bacu.prueba-bacu' is invalid and this project uses 'com.bacu.pruebabacu' instead.

# Getting Started

### Guides
The following guides illustrate how to deploy the appi on HEROKU:

* First you should create a heroku account [here](https://id.heroku.com/login)
* Yuo can deploy mongo db on heroku or a other container of their preference, [here](https://www.mongodb.com/developer/products/atlas/use-atlas-on-heroku/) ou can see as deploy mongo on Heroku
* Clone the repository from [here](https://github.com/diego2057/prueba-bacu)
* Depending on the mongodb location you select, you should get the connection string and update it in the application.yml
* Commit the above change
* On Heroku go to deploy tab and download and install the HerokuCLI
* Once HerokuCLI is installed you can login from the console of your IDE
* Execute the follow command [heroku git:remote -a prueba-bacu]()
* Execute the follow command [git push heroku master]()
* Go to resources tab and open app. All done

## Test by Postman

You can test this api download the [Bacu ReservationAPI.postman_collection.json](Bacu%20ReservationAPI.postman_collection.json),
here you can see three request:
* GET http://localhost:8080/v1/reservations this return all reservations
* GET http://localhost:8080/v1/reservations/{usuarioID} return a reservation record with id -> usuarioID
* POST http://localhost:8080/v1/reservations create a reservation 
