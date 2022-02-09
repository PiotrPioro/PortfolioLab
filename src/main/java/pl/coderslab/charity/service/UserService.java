package pl.coderslab.charity.service;

import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pl.coderslab.charity.entity.Role;
import pl.coderslab.charity.entity.User;
import pl.coderslab.charity.repository.RoleRepository;
import pl.coderslab.charity.repository.UserRepository;
import javax.transaction.Transactional;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

@Service
@AllArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;

    public User findByEmail(String email){
        return userRepository.findByEmail(email);
    }

    @Transactional
    public void addUser(User user){
        userRepository.save(user);
    }

    @Transactional
    public void editUser(String firstName, String lastName, String email){
        User user = userRepository.findByEmail(email);
        user.setFirstName(firstName);
        user.setLastName(lastName);
        userRepository.save(user);
    }

    @Transactional
    public void editPassword(String password, String email){
        User user = userRepository.findByEmail(email);
        user.setPassword(passwordEncoder.encode(password));
        userRepository.save(user);
    }

    @Transactional
    public void deleteUser(String email){
        User user = userRepository.findByEmail(email);
        userRepository.delete(user);
    }

    public List<User> findAllUsers(){

        List<User> userList = userRepository.findAll();
        List<User> users = new LinkedList<>();

        for(User u : userList){
            Set<Role> roles = u.getRoles();
            if(!roles.contains(roleRepository.findByRole("ADMIN"))){
                users.add(u);
            }
        }

        return users;
    }
}
