package me.elmajni.multiconnectorsms.service;

import me.elmajni.multiconnectorsms.dtos.CompteRequestDTO;
import me.elmajni.multiconnectorsms.dtos.CompteResponseDTO;
import me.elmajni.multiconnectorsms.entities.Compte;
import me.elmajni.multiconnectorsms.mappers.CompteMapper;
import me.elmajni.multiconnectorsms.repositories.CompteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class CompteServiceImpl implements CompteService {
    @Autowired
    private CompteRepository compteRepository;
    @Autowired
    private CompteMapper compteMapper;

    /*public CompteServiceImpl(CompteRepository compteRepository, CompteMapper compteMapper) {
        this.compteRepository = compteRepository;
        this.compteMapper = compteMapper;
    }*/

    @Override
    public CompteResponseDTO saveCompte(CompteRequestDTO compteRequestDTO) {
        Compte compte = compteMapper.fromompteRequestDTO(compteRequestDTO);
        Compte savedCompte = compteRepository.save(compte);
        return compteMapper.fromCompte(savedCompte);
    }

    @Override
    public CompteResponseDTO updateCompte(CompteRequestDTO compteRequestDTO) {
        Compte compte = compteMapper.fromompteRequestDTO(compteRequestDTO);
        Compte savedCompte = compteRepository.save(compte);
        return compteMapper.fromCompte(savedCompte);
    }

    @Override
    public void deleteCompte(Long id) {
        compteRepository.deleteById(id);
    }

    @Override
    public CompteResponseDTO getCompte(Long id) {
        Compte customer=compteRepository.findById(id).get();
        return compteMapper.fromCompte(customer);
    }

    @Override
    public List<CompteResponseDTO> getComptes() {
        List<Compte> customers=compteRepository.findAll();
        return customers
                .stream()
                .map((compte)->compteMapper.fromCompte(compte)).collect(Collectors.toList());
    }
}
