#Procedure
1.Add key-value into the run-configuration environment section
2.Heat from postman http://localhost:8080/encrypt
3.Add in properties file with key-value and spring.cloud.config.server.encrypt.enabled=false
4.In body section pass password in raw-text field so it will encrypt.