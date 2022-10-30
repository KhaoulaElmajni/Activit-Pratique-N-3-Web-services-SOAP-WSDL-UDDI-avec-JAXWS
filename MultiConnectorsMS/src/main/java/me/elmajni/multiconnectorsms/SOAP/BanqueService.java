package me.elmajni.multiconnectorsms.SOAP;

import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import jakarta.jws.WebService;
import me.elmajni.multiconnectorsms.dtos.CompteRequestDTO;
import me.elmajni.multiconnectorsms.dtos.CompteResponseDTO;
import me.elmajni.multiconnectorsms.service.CompteService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.List;

//POJO Plain Old Java Object ==> simple classe java
@WebService(serviceName = "BanqueWS")
public class BanqueService {

    @Autowired
    private CompteService compteService;

    @WebMethod(operationName = "addCompte")
    public CompteResponseDTO addCompte(@WebParam(name = "compte") CompteRequestDTO compteRequestDTO){
        return compteService.saveCompte(compteRequestDTO);
    }

    @WebMethod
    public CompteResponseDTO getCompte(@WebParam(name = "id") Long id){
        return compteService.getCompte(id);
    }

    @WebMethod
    public List<CompteResponseDTO> comptes(){
        return compteService.getComptes();
    }
}
