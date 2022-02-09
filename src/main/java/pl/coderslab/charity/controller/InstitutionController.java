package pl.coderslab.charity.controller;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.charity.entity.Institution;
import pl.coderslab.charity.service.InstitutionService;
import javax.validation.Valid;
import java.util.List;

@Controller
@AllArgsConstructor
@RequestMapping("/institution")
public class InstitutionController {

    private final InstitutionService institutionService;

    @GetMapping("/institutionList")
    public String institutionListView(){
        return "institutionList";
    }

    @GetMapping("/addInstitution")
    public String addInstitutionView(Model model){
        model.addAttribute("institution", new Institution());
        return "addInstitution";
    }

    @PostMapping("/addInstitution")
    public String addInstitution(@ModelAttribute("institution") @Valid Institution institution, BindingResult result){
        if(result.hasErrors()){
            return "addInstitution";
        }
        institutionService.addInstitution(institution);
        return "redirect:/institution/institutionList";
    }

    @GetMapping("/editInstitution")
    public String editInstitutionView(@RequestParam(name = "id") Long institutionId, Model model){

        Institution institution = institutionService.findInstitutionById(institutionId);
        model.addAttribute("institution", institution);

        return "addInstitution";
    }

    @PostMapping("/editInstitution")
    public String editInstitution(@ModelAttribute("institution") @Valid Institution institution, BindingResult result){
        if(result.hasErrors()){
            return "addInstitution";
        }
        institutionService.addInstitution(institution);
        return "redirect:/institution/institutionList";
    }

    @GetMapping("/deleteInstitutionView")
    public String deleteInstitutionView(@RequestParam(name = "id") Long institutionId, Model model){

        Institution institution = institutionService.findInstitutionById(institutionId);
        model.addAttribute("institution", institution);

        return "deleteInstitution";
    }

    @GetMapping("/deleteInstitution")
    public String deleteInstitution(@RequestParam(name = "id") Long institutionId){

        institutionService.deleteInstitution(institutionId);
        return "redirect:/institution/institutionList";
    }

    @ModelAttribute("institutions")
    public List<Institution> institutionList(){
        return institutionService.findAllInstitutions();
    }
}
