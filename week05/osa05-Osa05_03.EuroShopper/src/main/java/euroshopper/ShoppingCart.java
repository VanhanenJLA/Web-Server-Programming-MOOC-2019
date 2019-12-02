
package euroshopper;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import lombok.Data;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

/**
 *
 * @author Jouni
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Component
@Scope(value = "session", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class ShoppingCart implements Serializable {

    private Map<Item, Long> items = new HashMap<>();

    public void addToCart(Item item) {
        items.put(item, this.items.getOrDefault(item, 0L) + 1L);
    }

    public void clearCart() {
        items.clear();
    }

    public double getSum() {
        return items.keySet().stream()
                .map((item) -> item.getPrice() * items.get(item))
                .reduce(0.0, (sumSoFar, cost) -> sumSoFar + cost);
    }
}
