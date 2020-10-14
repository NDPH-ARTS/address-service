# address-service
Provides CRUD operations 


The following properties are required:

    -Djdbc.driver=**db driver class name** 
    -Djdbc.url=< **arts db path** > 
    -Djdbc.username= 
    -Djdbc.password=
    -Dserver.port=**http port**

*e.g.*
```-Djdbc.driver=org.postgresql.Driver -Djdbc.url=jdbc:postgresql://localhost:5432/arts -Djdbc.username=postgres -Djdbc.password= -Dserver.port=8081```
