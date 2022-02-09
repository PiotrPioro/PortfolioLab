package pl.coderslab.charity.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.coderslab.charity.entity.Donation;
import pl.coderslab.charity.repository.CategoryRepository;
import pl.coderslab.charity.repository.DonationRepository;
import pl.coderslab.charity.repository.InstitutionRepository;

import javax.transaction.Transactional;
import java.util.List;

@Service
@AllArgsConstructor
public class DonationService {

    private final DonationRepository donationRepository;

    public List<Donation> findAllDonations(){
        return donationRepository.findAll();
    }

    public Integer numberOfDonations(){
        return findAllDonations().size();
    }

    public Integer numberOfBags(){
        Integer result = 0;
        List<Donation> donations = findAllDonations();
        for(Donation donation : donations){
            result += donation.getQuantity();
        }
        return result;
    }

    @Transactional
    public void addDonation(Donation donation){
        donationRepository.save(donation);
    }
}
