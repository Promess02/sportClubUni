Server module of a Sport Club System Application built using Spring Boot, Hibernate and MySQL. Develop as an university project for education purposes.
System uses MVC architecture and handles functionalities like Membership, Activities, Calendars, Teams, News Posts, providing information about the club etc.
It was also developed to register and handle users with varying priveleges: guest user, user without club membership, user with club membership, trainer, admin.

App in order to work needs an existing MySQL database with its information provided in file SportClubUni/src/main/resources/external_config.yml, created by user 
with a following code template:

server:
  port: [PORT]
spring:
  datasource:
    password: [DATASOURCE_PASSWORD]
    username: [DATASOURCE_USERNAME]
    url: jdbc:mysql://localhost:3306/sportclub?characterEncoding=utf8&&useSSL=false
    driver-class-name: com.mysql.jdbc.Driver
  mail:
    host: smtp.gmail.com
    port: 587
    username: [SOURCE_EMAIL_ADDRESS]
    password: [USER_PASSWORD_TO_SMTP_SERVER]
    properties:
      mail:
        smtp:
          auth: true
          starttls:
            enable: true

guide on Spring email: https://www.baeldung.com/spring-email
