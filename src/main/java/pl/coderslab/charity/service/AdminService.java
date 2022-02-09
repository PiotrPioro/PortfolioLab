package pl.coderslab.charity.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.coderslab.charity.entity.Role;
import pl.coderslab.charity.entity.User;
import pl.coderslab.charity.repository.UserRepository;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;


@Service
@AllArgsConstructor
public class AdminService {

    private final UserRepository userRepository;

    public List<User> findAllAdmins(){

        List<User> userList = userRepository.findAll();
        List<User> adminList = new LinkedList<>();

        for(User u : userList){
            Set<Role> roles = u.getRoles();
            for(Role r : roles){
                if(r.getId() == 2){
                    adminList.add(u);
                }
            }
        }

        return adminList;
    }
}
