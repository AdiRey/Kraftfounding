package pl.kraft.web.redirectController;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginRedirectController {
    private String pattern =
            "https://cas.prz.edu.pl/cas-server/login?service=http%3A%2F%2Fwww.mpenar.kia.prz.edu.pl%2Fcasproxy.php%3Fredirect%3Dhttp%3A%2F%2Flocalhost%3A8081%2Flogin%2Fticket%26key%3Ded5fddea-9be9-4955-9718-fb429fed17f9";

    @GetMapping("/loginx")
    public String loginPageRedirect() {
        return String.format("redirect:%s", pattern);
    }
}
