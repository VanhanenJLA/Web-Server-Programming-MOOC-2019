package todoapplication;

import java.util.Map;
import java.util.TreeMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class TodoApplicationController {

    @Autowired
    private ItemRepository itemRepository;

    @GetMapping("/")
    public String list(Model model) {
        model.addAttribute("items", this.itemRepository.findAll());
        return "index";
    }

    @PostMapping("/")
    public String add(@RequestParam String name) {
        this.itemRepository.save(new Item(name));
        return "redirect:/";
    }

    @GetMapping("/{item}")
    public String getItem(Model model, @PathVariable Item item) {
        model.addAttribute("item", itemRepository.getOne(item.getId()));
        return "todo";
    }
}
