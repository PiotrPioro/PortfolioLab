package pl.coderslab.charity.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.coderslab.charity.entity.Institution;
import pl.coderslab.charity.repository.InstitutionRepository;
import javax.transaction.Transactional;
import java.util.List;

@Service
@AllArgsConstructor
public class InstitutionService {

    private final InstitutionRepository institutionRepository;

    public List<Institution> findAllInstitutions(){
        return institutionRepository.findAll();
    }

    @Transactional
    public void addInstitution(Institution institution){
        institutionRepository.save(institution);
    }

    public Institution findInstitutionById(Long id){
        return institutionRepository.findByInstitutionId(id);
    }

    @Transactional
    public void deleteInstitution(Long id){
        institutionRepository.deleteInstitutionById(id);
    }
}
