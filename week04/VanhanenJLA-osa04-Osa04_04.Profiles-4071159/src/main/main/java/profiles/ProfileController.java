package profiles;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ProfileController {

    @Autowired
    private Environment environment;

    @Profile("Production")
    @ResponseBody
    @GetMapping("/profile")
    public String getProfile() {
        String s = new String();
        for (String p : environment.getActiveProfiles()) {
            s += p;
        }
        return s;
    }

    @Profile("Production")
    @RequestMapping("/")
    public String home(Model model) {
        String profile = new String();
        for (String p : environment.getActiveProfiles()) {
            profile += p;
        }
        model.addAttribute("profile", profile);
        return "index";
    }

}
