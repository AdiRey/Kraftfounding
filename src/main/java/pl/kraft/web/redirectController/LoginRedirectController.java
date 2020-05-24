package pl.kraft.web.redirectController;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginRedirectController {
    private String pattern =
            "https://cas.prz.edu.pl/cas-server/login?service=http%3A%2F%2F192%2E168%2E100%2E11%2Fapi%2Fstudents%2Fdecoded";

    @GetMapping("/login")
    public String loginPageRedirect() {
        return String.format("redirect:%s", pattern);
    }
}
