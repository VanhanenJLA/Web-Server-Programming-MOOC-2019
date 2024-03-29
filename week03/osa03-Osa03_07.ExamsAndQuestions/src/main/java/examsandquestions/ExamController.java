package examsandquestions;

import java.time.LocalDate;
import java.util.ArrayList;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ExamController {

    @Autowired
    ExamRepository er;

    @Autowired
    QuestionRepository qr;

    @GetMapping("/exams")
    public String listExams(Model model) {
        model.addAttribute("exams", er.findAll(Sort.by("examDate").descending()));
        return "exams";
    }

    @GetMapping("/exams/{examId}")
    public String viewExam(Model model, @PathVariable Long examId) {
        model.addAttribute("exam", er.getOne(examId));
        model.addAttribute("questions", qr.findAll());
        return "exam";
    }

    @PostMapping("/exams")
    public String addExam(@RequestParam String subject,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate examDate) {
        er.save(new Exam(subject, examDate, new ArrayList<>()));

        return "redirect:/exams";
    }

    @PostMapping("/exams/{examId}/questions/{questionId}")
    @Transactional
    public String addQuestionToExam(@PathVariable Long examId, @PathVariable Long questionId) {
        Exam e = er.getOne(examId);
        Question q = qr.getOne(questionId);

        q.getExams().add(e);
        e.getQuestions().add(q);

        return "redirect:/exams/" + examId;
    }
}
