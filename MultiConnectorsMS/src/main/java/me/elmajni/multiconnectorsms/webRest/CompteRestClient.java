package me.elmajni.multiconnectorsms.webRest;

import com.jax.ws.web.service.Compte;
import lombok.AllArgsConstructor;
import me.elmajni.multiconnectorsms.dtos.CompteRequestDTO;
import me.elmajni.multiconnectorsms.dtos.CompteResponseDTO;
import me.elmajni.multiconnectorsms.service.CompteService;
import me.elmajni.multiconnectorsms.soapWebService.CompteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@AllArgsConstructor
@CrossOrigin("*")
@RequestMapping(path = "/api")
public class CompteRestClient {
    @Autowired
    private CompteService compteService;
    private CompteRepository compteRepository;

   /* @GetMapping(path = "/comptes/{id}")
    public CompteResponseDTO getCompte(@PathVariable Long id){
        return compteService.getCompte(id);
    }


    @GetMapping(path = "/comptes")
    public List<CompteResponseDTO> getComptes(){
        return compteService.getComptes();
    }

    @PostMapping(path = "/comptes")
    public CompteResponseDTO save(@RequestBody CompteRequestDTO compteRequestDTO){
        return compteService.saveCompte(compteRequestDTO);
    }

    @PutMapping(path = "/comptes/{id}")
    public CompteResponseDTO update(@RequestBody CompteRequestDTO compteRequestDTO, @PathVariable Long id){
        compteRequestDTO.setId(id);
        return compteService.saveCompte(compteRequestDTO);
    }


    @DeleteMapping(path = "/comptes/{id}")
    public void delete(@PathVariable Long id){
        compteService.deleteCompte(id);
    }
*/

    @GetMapping("comptes")
    public List<Compte> getAllComptes() {
        return compteRepository.getAllComptes();
    }

    @PostMapping("/comptes/convert")
    public Map<String, Object> convert(@RequestBody Map<String, Double> montant) {
        double montantConverted = montant.get("montant");
        return Map.of("valueAfterConversion", compteRepository.convert(montantConverted));
    }

    @GetMapping("/comptes/{code}")
    public Compte getCompte(@PathVariable Long code) {
        return compteRepository.getCompte(code);
    }
}
