package me.elmajni.multiconnectorsms.graphQl;

import com.jax.ws.web.service.Compte;
import me.elmajni.multiconnectorsms.dtos.CompteRequestDTO;
import me.elmajni.multiconnectorsms.dtos.CompteResponseDTO;
import me.elmajni.multiconnectorsms.service.CompteService;
import me.elmajni.multiconnectorsms.soapWebService.CompteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping(path = "/api")
public class CompteGraphQLClient {
  /*  @Autowired
    private CompteService compteService;

    @QueryMapping
    public List<CompteResponseDTO> comptes(){
        return compteService.getComptes();
    }

    @QueryMapping
    public CompteResponseDTO getCompte(@Argument Long id){
        return compteService.getCompte(id);
    }

    *//*
     * Query : pour récupérer les données
     * Mutation : pour modifier les données*//*
    @MutationMapping //consiste à changer les données
    public CompteResponseDTO addCompte(@Argument CompteRequestDTO compteRequestDTO){
        return compteService.saveCompte(compteRequestDTO);
    }

    @MutationMapping //consiste à changer les données
    public CompteResponseDTO updateCompte(@Argument CompteRequestDTO compteRequestDTO){
        return compteService.updateCompte(compteRequestDTO);
    }

    @MutationMapping //consiste à changer les données
    public String deleteCompte(@Argument Long id){
        compteService.deleteCompte(id);
        return "deleted";
    }*/


    private CompteRepository compteRepository;

    @QueryMapping
    public List<Compte> getAllComptes() {
        return compteRepository.getAllComptes();
    }

    @QueryMapping
    public Compte getCompte(@Argument Integer code) {
        return compteRepository.getCompte(code.longValue());
    }

    @MutationMapping
    public Double convert(@Argument double montant) {
        return compteRepository.convert(montant);
    }
}
