# Reactor API different operations





**Overview of the Project**

    This is the implemention SpringBoot maven project and performed different operations of reactor API adding the basic concepts.
    
    The main idea behind this project is to explore the Reactor API and different operations provided by it.



**How to Run Application**

 Make sure you have jdk 1.8 and maven installed in your system

    Clone the project using url:  'https://gitlab.mynisum.com/vksingh/reactor-api-mongo-kafka.git.git'
    Clone the client project  using url: https://gitlab.mynisum.com/vksingh/reative-client.git
    Run The different Test Cases to get different operations Available on Reactor-API.
    
    Run the whole project to understand the realtime implementation of the reactor API

**Note:** Make sure MongoDB  and kafka is installed into your system:

**Mongodb installation guide**

    Download the zip file:
    Run the following command;
        >cd Downloads
        > mv mongodb-osx-x86_64-3.0.7.tgz ~/
    
    Extract MongoDB from the the downloaded archive, and change the name of the directory to something more palatable: 
        > cd ~/ > tar -zxvf mongodb-osx-x86_64-3.0.7.tgz > mv mongodb-osx-x86_64-3.0.7 mongodb
    
    Create the directory where Mongo will store data, create the “db” directory. ou can create the directory in the default location by running
     mkdir -p /data/db
    
    Make sure that the /data/db directory has the right permissions by running
    
        > sudo chown -R `id -un` /data/db
        > Enter your password
    
    Run the Mongo daemon, in one terminal window run 
    ~/mongodb/bin/mongod.
     This will start the Mongo server.
    Run the Mongo shell, with the Mongo daemon running in one terminal, type ~/mongodb/bin/mongo in another terminal window. This will run the Mongo shell which is an application to access data in MongoDB.
    create EmployeeDB database into the monogodb;






**Kafka installation**

    $ brew install kafka
    
    zookeeper-server-start /usr/local/etc/kafka/zookeeper.properties
    kafka-server-start /usr/local/etc/kafka/server.properties
    
    
    If we get any issue in running kafka server:
    To fix this issue, we need to change the server.properties file.
    $ vim /usr/local/etc/kafka/server.properties
    Here uncomment the server settings and update the value from
    listeners=PLAINTEXT://:9092
    
    kafka-topics --create --zookeeper localhost:2181 --replication-factor 1 --partitions 1 --topic test
    
    kafka-console-producer --broker-list localhost:9092 --topic test
    >send first message
    >send second message
    >wow it is working
    
    kafka-console-consumer --bootstrap-server localhost:9092 --topic test --from-beginning



**Official links**

    http://www.vinsguru.com/reactive-programming-schedulers/