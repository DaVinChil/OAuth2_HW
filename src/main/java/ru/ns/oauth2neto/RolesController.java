package ru.ns.oauth2neto;

import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.security.RolesAllowed;

@RestController
public class RolesController {
    @GetMapping("/welcome")
    @Secured("ROLE_READ")
    public String getWelcome() {
        return "Welcome!!!";
    }

    @GetMapping("/note")
    @RolesAllowed("ROLE_WRITE")
    public String getNote() {
        return "No notes currently available.";
    }

    @GetMapping("/royal")
    @PreAuthorize("hasAnyRole('ROLE_WRITE', 'ROLE_DELETE')")
    public String getRoyalGreeting(){
        return "Glad to see you";
    }

    @GetMapping("/accessByName")
    @PreAuthorize("#username == authentication.principal.username")
    public String personalGreeting(@RequestParam("username") String username){
        return "Welcome, " + username;
    }
}
