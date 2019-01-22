package in.codeislife.springprocedure.controller;

import in.codeislife.springprocedure.model.User;
import in.codeislife.springprocedure.repository.UserRepository;
import in.codeislife.springprocedure.resource.UserResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.PersistenceContext;
import javax.persistence.StoredProcedureQuery;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class TestController {

    @Autowired
    private UserRepository userRepository;

    @PersistenceContext
    private EntityManager entityManager;

    @GetMapping(path = "/add")
    public String addNewUser(@RequestParam String name,
                             @RequestParam String email) {
        User n = new User();
        n.setName(name);
        n.setEmail(email);
        userRepository.save(n);
        return "Saved";
    }

    @GetMapping(path = "/all")
    public Iterable<User> getAllUsers() {
        return userRepository.findAll();
    }

    @GetMapping(path = "/call")
    public List<UserResource> getUser(@RequestParam String name) {
        StoredProcedureQuery storedProcedure = entityManager
                .createStoredProcedureQuery("FIND_USER_BY_NAME")
                .registerStoredProcedureParameter("u_name", String.class, ParameterMode.IN)
                .setParameter("u_name", name);

        List<Object[]> resultList = storedProcedure.getResultList();
        return resultList.stream()
                .map(c -> new UserResource((String) c[1], (String) c[2]))
                .collect(Collectors.toList());
    }
}
