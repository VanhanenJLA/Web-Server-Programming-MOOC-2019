package examsandquestions;

import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class QuestionController {

    @Autowired
    QuestionRepository qr;

    @GetMapping("/questions")
    public String list(Model model) {
        model.addAttribute("questions", qr.findAll());
        return "questions";
    }

    @PostMapping("/questions")
    public String addQuestion(@RequestParam String title, @RequestParam String content) {
        qr.save(new Question(title, content, new ArrayList<>()));
        return "redirect:/questions";
    }

}
