package org.example;

import jakarta.xml.ws.Endpoint;
import org.example.ws.BanqueService;

public class ServeurJWS {
    public static void main(String[] args) {
        Endpoint.publish("http://0.0.0.0:8085/",new BanqueService());
        System.out.println("Web Service déployé !");
    }
}
