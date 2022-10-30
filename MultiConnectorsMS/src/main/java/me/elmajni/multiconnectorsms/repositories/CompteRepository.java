package me.elmajni.multiconnectorsms.repositories;

import me.elmajni.multiconnectorsms.entities.Compte;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompteRepository extends JpaRepository<Compte,Long> {
}
