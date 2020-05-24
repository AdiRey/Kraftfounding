package pl.kraft.web.redirectController;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class OthersRedirectController {
    @GetMapping("/")
    public String indexRedirect() {
        return "forward:page/index.html";
    }

    @GetMapping("/faq")
    public String faqRedirect() {
        return "forward:page/faq.html";
    }

    @GetMapping("/projects-offers")
    public String projectsOffersRedirect() {
        return "forward:page/project-offers.html";
    }

    @GetMapping("/profiles")
    public String profilesRedirect() {
        return "forward:page/profile.html";
    }

    @GetMapping("/my-profile")
    public String myProfileRedirect() {
        return "forward:page/my-profile.html";
    }

    @GetMapping("/sign-up")
    public String signUpRedirect() {
        return "forward:page/sign-up.html";
    }

    @GetMapping("/sign-in")
    public String signInRedirect() {
        return "forward:page/sign-in.html";
    }

    @GetMapping("/project")
    public String showSingleProjectRedirect() {
        return "forward:page/show_single_project.html";
    }

    @GetMapping("/edycja")
    public String editProfileRedirect() {
        return "forward:page/edycja.html";
    }

    @GetMapping("/addProject")
    public String addProjectRedirect() {
        return "forward:page/add-project.html";
    }

    @GetMapping("/youtube")
    public String youtubeRedirect() {
        return "redirect:https://www.youtube.com/watch?v=viUB2HP5S7c&feature=share&fbclid=IwAR1ssnfQs2TIGARYL62CWspKYv57RYLHLT_rKZXz0skrS9AUbpe2gItlY2Y";
    }
}
