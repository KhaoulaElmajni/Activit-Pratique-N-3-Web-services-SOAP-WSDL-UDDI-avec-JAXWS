package me.elmajni.multiconnectorsms.soapWebService;

import com.jax.ws.web.service.Compte;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Repository
public class CompteRepository {

    private static final List<Compte> comptes = new ArrayList<>();

    public List<Compte> getAllComptes() {
        return comptes;
    }
    public double convert(double montant) {
        return montant * 10.54;
    }
    public Compte getCompte(Long code) {
        return new Compte(code, Math.random() * 9888, new Date());
    }

}
