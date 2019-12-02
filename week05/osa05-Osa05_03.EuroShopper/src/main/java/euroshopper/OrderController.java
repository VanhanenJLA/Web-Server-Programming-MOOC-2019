package euroshopper;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class OrderController {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ShoppingCart sc;

    @RequestMapping("/orders")
    public String list(Model model) {
        model.addAttribute("orders", orderRepository.findAll());
        return "orders";
    }

    @PostMapping("/orders")
    public String order(@RequestParam String name, @RequestParam String address) {

        List<OrderItem> orderItems = new ArrayList<>();
        sc.getItems().forEach((item, amount) -> {
            orderItems.add(new OrderItem(item, amount));
        });
        orderRepository.save(new Order(name, address, orderItems));
        sc.clearCart();

        return "redirect:/orders";
    }
}
