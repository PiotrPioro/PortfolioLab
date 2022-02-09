package pl.coderslab.charity.controller;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.charity.entity.Category;
import pl.coderslab.charity.entity.Donation;
import pl.coderslab.charity.entity.Institution;
import pl.coderslab.charity.entity.User;
import pl.coderslab.charity.service.CategoryService;
import pl.coderslab.charity.service.DonationService;
import pl.coderslab.charity.service.InstitutionService;
import pl.coderslab.charity.service.UserService;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.List;
import java.util.Set;

@Controller
@AllArgsConstructor
@SessionAttributes("loggedUser")
@RequestMapping("/donation")
public class DonationController {

    private final DonationService donationService;
    private final InstitutionService institutionService;
    private final CategoryService categoryService;
    private final UserService userService;

    @GetMapping("/addDonation")
    public String addDonationView(Model model){
        model.addAttribute("donation", new Donation());
        return "form";
    }

    @PostMapping("/addDonation")
    public String addDonation(@ModelAttribute("donation") @Valid Donation donation, BindingResult result,
                              HttpSession session){
        if(result.hasErrors()){
            return "form";
        }
        donationService.addDonation(donation);

        User user = (User) session.getAttribute("loggedUser");
        Set<Donation> donations = user.getDonations();
        donations.add(donation);
        user.setDonations(donations);
        userService.addUser(user);

        return "redirect:/user/checkRole";
    }

    @ModelAttribute("institutions")
    public List<Institution> institutionList(){
        return institutionService.findAllInstitutions();
    }

    @ModelAttribute("category")
    public List<Category> categoriesList(){
        return categoryService.findAllCategories();
    }
}
