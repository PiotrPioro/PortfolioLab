package pl.coderslab.charity.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.coderslab.charity.entity.Token;
import pl.coderslab.charity.entity.User;
import pl.coderslab.charity.repository.TokenRepository;

import java.time.LocalDateTime;
import java.util.Set;

@Service
@AllArgsConstructor
public class TokenService {

    private final TokenRepository tokenRepository;
    private final UserService userService;

    public Token findByName(String name){
        return tokenRepository.findByName(name);
    }

    public void addToken(String tokenName, User user){

        LocalDateTime date = LocalDateTime.now();
        LocalDateTime date1 = date.plusHours(1);

        Token token = new Token();
        token.setName(tokenName);
        token.setDate(date1);
        tokenRepository.save(token);

        Set<Token> tokenSet = user.getTokens();
        tokenSet.add(token);
        user.setTokens(tokenSet);
        userService.addUser(user);
    }
}
