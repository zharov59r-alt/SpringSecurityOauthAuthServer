package SpringSecurityOauthAuthServer.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PostFilter;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.access.prepost.PreFilter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import SpringSecurityOauthAuthServer.entity.User;
import SpringSecurityOauthAuthServer.repository.UserRepository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Slf4j
@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserRepository userRepository;

    //@PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/")
    public String hello() {
        SecurityContext securityContext = SecurityContextHolder.getContext();
        Authentication authentication = securityContext.getAuthentication();
        return "Hello " + authentication.getName() + " " + authentication.getAuthorities().size() + " " + authentication.getAuthorities();
    }

    @PostMapping("/")
    public String helloPost() {
        SecurityContext securityContext = SecurityContextHolder.getContext();
        Authentication authentication = securityContext.getAuthentication();
        return "Hello POST " + authentication.getName() + " " + authentication.getAuthorities().size() + " " + authentication.getAuthorities();
    }

    @PreFilter(value = "filterObject == authentication.name", filterTarget = "arr")
    @GetMapping("/prefilter")
    public List<String> prefilter(@RequestBody List<String> arr) {
        return arr;
    }

    @PostFilter(value = "filterObject == authentication.name") // filterObject ссылка на входной параметр arr
    @GetMapping("/postfilter")
    public List<String> postfilter() {
        List<String> arr = new ArrayList<>();
        arr.add("first");
        arr.add("second");
        arr.add("third");
        return arr;
    }


    @GetMapping("/user")
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @GetMapping("/userdetails")
    public Collection<GrantedAuthority> findUserDetails(@AuthenticationPrincipal UserDetails userDetails) {
        return (Collection<GrantedAuthority>) userDetails.getAuthorities();
    }

    @PostMapping("/user")
    public User save(@RequestBody User user) {
        return userRepository.save(user);
    }


    @PreAuthorize("hasPermission(#id, 'user', 'ADMIN')")
    @GetMapping("/user/{id}")
    public User findById(@PathVariable("id") Long id) {
        Optional<User> userOpt = userRepository.findById(id);
        return userOpt.orElse(null);
    }

    @DeleteMapping("/user/{id}")
    public void deleteById(@PathVariable("id") Long id) {
        userRepository.deleteById(id);
    }


}
