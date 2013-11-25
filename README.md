sensors_reading_DS
==================

Java system that integrates sensors and clients displays in a distributed environment through a CORBA middleware.

##Compiling CORBA idls

    $ idlj -fall <file_name>.idl

##Running the project

1) Instantiate name service

    $ sudo tnameserv
    
2) Run service jar

    $ java -jar service.jar

3) Run sensor jar

    $ java -jar sensor.jar T caldeira //Temperatura
    $ java -jar sensor.jar T tubulacao
    $ java -jar sensor.jar T camara
    $ java -jar sensor.jar P caldeira //Pressão
    $ java -jar sensor.jar P tubulacao
    $ java -jar sensor.jar P camara
    $ java -jar sensor.jar H caldeira //Humidade
    $ java -jar sensor.jar H tubulacao
    $ java -jar sensor.jar H camara
    
3) Run client jar

    $ java -jar client.jar T P
    $ java -jar client.jar P H
    $ java -jar client.jar H T
    
    
    
