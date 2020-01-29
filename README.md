# Place REST API
REST API written using Java, Spring, Hibernate and MySQL
## Running

To run API for yourself you have to use Maven and specify your own MySQL db credentials then run these commands in root project directory

```bash
mvn clean install

mvn spring-boot:run

mvn test -Dtest=com.jkdev.placeRest.*
```

## To get list of all places
```
Request:
curl -X GET -i http://place-rest-api.herokuapp.com/places

Response:
HTTP/1.1 200
Server: Cowboy
Connection: keep-alive
Vary: Origin
Vary: Access-Control-Request-Method
Vary: Access-Control-Request-Headers
Content-Type: application/json
Transfer-Encoding: chunked
Date: Mon, 28 Oct 2019 13:50:15 GMT
Via: 1.1 vegur

[{"id":2,"name":"Burger Restaurant","localisation":"ul.fajna, Poznan","openingHours":"12-20","phone":"666999000","opinionList":[]},
{"id":3,"name":"Hotdog Foodtruck","localisation":"Wyspa Slodowa, Wroclaw","openingHours":"12-20","phone":"123987456","opinionList":[]},
{"id":11,"name":"Test","localisation":"Test","openingHours":"11-12","phone":"3456493403","opinionList":[]},
{"id":1,"name":"Zoo","localisation":"ul.zoo, Cracow","openingHours":"10-18","phone":"535030936","opinionList":[]}]

```

## To get a place with specific id
```
Request:
curl -X GET http://place-rest-api.herokuapp.com/places/1

Response:
HTTP/1.1 200
Server: Cowboy
Connection: keep-alive
Vary: Origin
Vary: Access-Control-Request-Method
Vary: Access-Control-Request-Headers
Content-Type: application/json
Transfer-Encoding: chunked
Date: Mon, 28 Oct 2019 13:50:15 GMT
Via: 1.1 vegur

{"id":1,"name":"Zoo","localisation":"ul.zoo, Cracow","openingHours":"10-18","phone":"535030936","opinionList":[]}

Error response:

HTTP/1.1 404
Server: Cowboy
Connection: keep-alive
Vary: Origin
Vary: Access-Control-Request-Method
Vary: Access-Control-Request-Headers
Content-Type: application/json
Transfer-Encoding: chunked
Date: Mon, 28 Oct 2019 13:50:15 GMT
Via: 1.1 vegur

{"status":404,"message":"Place id not found - 14","timeStamp":1572270765523}

```

## To get a specific place opinions
```
Request:
curl -X GET -i http://place-rest-api.herokuapp.com/places/{place.id}/opinions

Response:
HTTP/1.1 200
Server: Cowboy
Connection: keep-alive
Vary: Origin
Vary: Access-Control-Request-Method
Vary: Access-Control-Request-Headers
Content-Type: application/json
Transfer-Encoding: chunked
Date: Mon, 28 Oct 2019 13:53:55 GMT
Via: 1.1 vegur

[]

```

## To delete specific place
```
Request:
curl -X DELETE -i http://place-rest-api.herokuapp.com/places/{place.id}
Success response:

HTTP/1.1 200
Server: Cowboy
Connection: keep-alive
Vary: Origin
Vary: Access-Control-Request-Method
Vary: Access-Control-Request-Headers
Content-Type: text/plain;charset=UTF-8
Content-Length: 24
Date: Mon, 28 Oct 2019 13:55:09 GMT
Via: 1.1 vegur

Deleted place with id - 11


Error response:

HTTP/1.1 404
Server: Cowboy
Connection: keep-alive
Vary: Origin
Vary: Access-Control-Request-Method
Vary: Access-Control-Request-Headers
Content-Type: application/json
Transfer-Encoding: chunked
Date: Mon, 28 Oct 2019 13:55:36 GMT
Via: 1.1 vegur

{"status":404,"message":"Place id not found - 11","timeStamp":1572270936730}
```

## To delete/get/put specific opinion for a place
```
Request:
curl -X DELETE/GET/PUT -i http://place-rest-api.herokuapp.com/places/{place.id}/opinions/{opinion.id}
```

## Contributing
For major problems, please open an issue to discuss about the bugs and help me fix it.

## TODO
- Create front-end for API
- Create custom exceptions
- Secure API with Spring Security
- Change data layer to use Spring Data JPA
- Migrate database to PostgreSQL
