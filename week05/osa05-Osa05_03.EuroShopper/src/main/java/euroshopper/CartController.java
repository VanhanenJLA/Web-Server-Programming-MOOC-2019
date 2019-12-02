package euroshopper;

import euroshopper.*;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.*;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

/**
 *
 * @author Jouni
 */
@Controller
public class CartController {

    @Autowired
    private ShoppingCart sc;

    @Autowired
    private ItemRepository ir;

//    @Autowired
//    private HttpSession session;
    @GetMapping("/cart")
    public String showCart(Model model) {
        model.addAttribute("items", sc.getItems());
        model.addAttribute("sum", sc.getSum());
        return "cart";
    }

    @PostMapping(value = "/cart/items/{id}")
    public String add(@PathVariable("id") Long id) {
        sc.addToCart(ir.getOne(id));
        return "redirect:/cart";
    }
}
