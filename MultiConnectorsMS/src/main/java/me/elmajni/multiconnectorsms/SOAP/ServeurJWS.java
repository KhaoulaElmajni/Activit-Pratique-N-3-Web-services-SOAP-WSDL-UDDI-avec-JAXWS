package me.elmajni.multiconnectorsms.SOAP;


import jakarta.xml.ws.Endpoint;

public class ServeurJWS {
    public static void main(String[] args) {
        Endpoint.publish("http://0.0.0.0:8088/",new BanqueService());
        System.out.println("Web Service déployé sur l'adresse http://0.0.0.0:8088/!");
    }
}
