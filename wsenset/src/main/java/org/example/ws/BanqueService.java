package org.example.ws;

import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import jakarta.jws.WebService;

//POJO Plain Old Java Object
@WebService(serviceName = "BanqueWs")
public class BanqueService {
    @WebMethod(operationName = "Convert")
    public double conversion(@WebParam double mt){
        return mt*11;
    }

    public Compte getCompte(int code){
        return new Compte();
    }
}
