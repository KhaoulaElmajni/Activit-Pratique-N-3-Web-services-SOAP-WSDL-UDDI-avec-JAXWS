package me.elmajni;

import proxy.BanqueService;
import proxy.BanqueWS;
import proxy.Compte;

public class ClientWS {
    public static void main(String[] args) {
        //middleware
        BanqueService stub = new BanqueWS().getBanqueServicePort();
        System.out.println(stub.convert(5600));
        Compte compte = stub.getCompte(5);
        System.out.println(compte.getCode()+" "+compte.getSolde());
    }
}
