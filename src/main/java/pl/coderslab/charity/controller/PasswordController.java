package pl.coderslab.charity.controller;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.coderslab.charity.entity.Token;
import pl.coderslab.charity.entity.User;
import pl.coderslab.charity.service.EmailService;
import pl.coderslab.charity.service.TokenService;
import pl.coderslab.charity.service.UserService;
import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;

@Controller
@AllArgsConstructor
public class PasswordController {

    private final EmailService emailService;
    private final UserService userService;
    private final TokenService tokenService;

    @GetMapping("/resetPassword")
    public String resetPasswordView(){
        return "resetPassword";
    }

    @PostMapping("/resetPassword")
    public String resetPassword(@RequestParam(name = "email") String mail){

        String token = UUID.randomUUID().toString();
        tokenService.addToken(token, userService.findByEmail(mail));

        String subject = "Zmiana hasła";
        String text = "Twój kod to: " + token + " wejdź na http://localhost:8080/verifyToken";
        emailService.sendMessage(subject, text, mail);

        return "redirect:/home";
    }

    @GetMapping("/verifyToken")
    public String verifyTokenView(){
        return "verifyToken";
    }

    @PostMapping("/verifyToken")
    public String verifyToken(@RequestParam(name = "email") String mail, Model model,
                              @RequestParam(name = "token") String token, HttpSession session){

        User user = userService.findByEmail(mail);
        session.setAttribute("loggedUser", user);

        Token token2 = tokenService.findByName(token);
        LocalDateTime date = LocalDateTime.now();
        Set<Token> tokens = user.getTokens();

        for(Token token1 : tokens){
            if(token1.getDate().isAfter(date) && token1.getName().equals(token2.getName())){
                session.setAttribute("changePasswordUser", user);
                return "redirect:/newPassword";
            }
        }
        return "redirect:/login";
    }

    @GetMapping("/newPassword")
    public String newPassword(HttpSession session, Model model){
        User user = (User) session.getAttribute("changePasswordUser");
        model.addAttribute("user", user);
        return "editPassword";
    }

    @PostMapping("/newPassword")
    public String editPassword(@RequestParam("password") String password, HttpSession session,
                               @RequestParam("repassword") String repassword, @RequestParam(name = "email") String mail){

        if(password.equals(repassword)) {
            userService.editPassword(password, mail);
            return "redirect:/login";
        }
        session.setAttribute("changePasswordUser", userService.findByEmail(mail));
        return "redirect:/home";
    }
}
