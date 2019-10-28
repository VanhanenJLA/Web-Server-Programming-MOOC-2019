package hellopaths;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloPathsController {

    @GetMapping("/paths")
    @ResponseBody
    public String paths() {
        return "Paths";
    }

    @GetMapping("/hello")
    @ResponseBody
    public String hello() {
        return "Hello";
    }

}
