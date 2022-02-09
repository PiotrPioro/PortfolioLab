package pl.coderslab.charity.controller;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.coderslab.charity.entity.User;
import pl.coderslab.charity.service.AdminService;
import javax.servlet.http.HttpSession;

@Controller
@AllArgsConstructor
@RequestMapping("/admin")
public class AdminHome {

    private final AdminService adminService;

    @GetMapping("/adminHome")
    public String adminHome(HttpSession session, Model model){

        User user = (User) session.getAttribute("loggedUser");
        model.addAttribute("user", user);

        return "adminHome";
    }

    @GetMapping("/adminList")
    public String adminList(Model model){

        model.addAttribute("adminList", adminService.findAllAdmins());
        return "adminList";
    }
}
