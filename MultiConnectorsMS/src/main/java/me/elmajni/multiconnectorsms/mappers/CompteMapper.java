package me.elmajni.multiconnectorsms.mappers;


import me.elmajni.multiconnectorsms.dtos.CompteRequestDTO;
import me.elmajni.multiconnectorsms.dtos.CompteResponseDTO;
import me.elmajni.multiconnectorsms.entities.Compte;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CompteMapper {
    Compte fromompteRequestDTO(CompteRequestDTO compteRequestDTO);
    CompteResponseDTO fromCompte(Compte compte);
}
