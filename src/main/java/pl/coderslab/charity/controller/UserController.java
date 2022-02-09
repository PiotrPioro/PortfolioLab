package pl.coderslab.charity.controller;

import lombok.AllArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.charity.entity.Role;
import pl.coderslab.charity.entity.User;
import pl.coderslab.charity.service.UserService;
import javax.servlet.http.HttpSession;
import java.util.Set;

@Controller
@AllArgsConstructor
@RequestMapping("/user")
@SessionAttributes("loggedUser")
public class UserController {

    private final UserService userService;

    @GetMapping("/checkRole")
    public String checkRole(HttpSession session, @AuthenticationPrincipal UserDetails user){

        String email = user.getUsername();
        User user1 = userService.findByEmail(email);
        session.setAttribute("loggedUser", user1);

        Set<Role> roles = user1.getRoles();
        for (Role role : roles){
            if("ADMIN".equals(role.getRole())){
                return "redirect:/admin/adminHome";
            }
        }

        return "redirect:/user/userHome";
    }

    @GetMapping("/userHome")
    public String userHome(HttpSession session, Model model){

        User user = (User) session.getAttribute("loggedUser");
        model.addAttribute("user", user);

        return "userHome";
    }

    @GetMapping("/editUser")
    public String editUserView(HttpSession session, Model model){
        User user = (User) session.getAttribute("loggedUser");
        model.addAttribute("user", user);
        return "editUser";
    }

    @PostMapping("/editUser")
    public String editUser(@RequestParam("firstName") String firstName, @RequestParam("lastName") String lastName,
                           HttpSession session, Model model){
        User logUser = (User) session.getAttribute("loggedUser");
        userService.editUser(firstName, lastName, logUser.getEmail());
        User updatedUser = userService.findByEmail(logUser.getEmail());
        model.addAttribute("loggedUser", updatedUser);
        return "redirect:/user/checkRole";
    }

    @GetMapping("/editPassword")
    public String editPasswordView(HttpSession session, Model model){
        User user = (User) session.getAttribute("loggedUser");
        model.addAttribute("user", user);
        return "editPassword";
    }

    @PostMapping("/editPassword")
    public String editPassword(@RequestParam("password") String password, @RequestParam("repassword") String repassword,
                               HttpSession session){
        User logUser = (User) session.getAttribute("loggedUser");
        if(password.equals(repassword)) {
            userService.findByEmail(logUser.getEmail());
            userService.editPassword(password, logUser.getEmail());
            return "redirect:/user/checkRole";
        }
        return "editPassword";
    }

    @GetMapping("/showUser")
    public String showUserView(HttpSession session, Model model){
        User user = (User) session.getAttribute("loggedUser");
        model.addAttribute("user", user);
        return "showUser";
    }

    @GetMapping("/deleteUser")
    public String deleteUserView(HttpSession session, Model model){
        User user = (User) session.getAttribute("loggedUser");
        model.addAttribute("user", user);
        return "deleteUser";
    }

    @GetMapping("/delete")
    public String deleteUser(HttpSession session){
        User user = (User) session.getAttribute("loggedUser");
        userService.deleteUser(user.getEmail());
        return "redirect:/login";
    }

    @GetMapping("/userList")
    public String userList(Model model){

        model.addAttribute("userList", userService.findAllUsers());
        return "userList";
    }
}
