package org.example.ws;

import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import jakarta.jws.WebService;

import java.util.Date;
import java.util.List;

//POJO Plain Old Java Object ==> simple classe java
@WebService(serviceName = "BanqueWS")
public class BanqueService {
    @WebMethod(operationName = "Convert")
    public double conversion(@WebParam(name = "montant") double mt){
        return mt*11.01;
    }

    @WebMethod
    public Compte getCompte(@WebParam(name = "code") int code){
        return new Compte(code,new Date(),Math.random()*9888);
    }

    @WebMethod
    public List<Compte> comptes(){
        return List.of(
        new Compte(1,new Date(),Math.random()*9888),
        new Compte(2,new Date(),Math.random()*9888),
        new Compte(3,new Date(),Math.random()*9888)

        );
    }
}
