package calculator;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class CalculatorController {

    @GetMapping("/add")
    @ResponseBody
    public int add(@RequestParam Integer first, @RequestParam Integer second) {
        return first + second;
    }

    @GetMapping("/multiply")
    @ResponseBody
    public int multiply(@RequestParam Integer first, @RequestParam Integer second) {
        return first * second;
    }

}
