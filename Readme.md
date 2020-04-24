Simple Rest API for work with JSON.
For use clone the project and run 'mvn spring-boot: run' in the root folder (pom file level).
If you terminate the app with 'Ctrl+Z' port may be stay busy so find PID with 'lsoft -i -P | grep 9001'
and kill the procces with 'kill -9 PID_NUM'

API
GET localhost:9001/persons
GET localhost:9001/person/{id)    id must be between 7 - 12
