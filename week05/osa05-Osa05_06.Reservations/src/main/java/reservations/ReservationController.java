package reservations;

import java.time.LocalDate;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ReservationController {

    @Autowired
    private ReservationRepository rr;

    @Autowired
    private AccountRepository ar;

    @GetMapping("/reservations")
    public String view(Model model) {
        model.addAttribute("reservations", rr.findAll());
        return "reservations";
    }

    @PostMapping("/reservations")
    public String addReservation(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate from,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate to) {

        Authentication auth = SecurityContextHolder
                .getContext()
                .getAuthentication();
        Account user = ar.findByUsername(auth.getName());

        if (canBook(from, to)) {
            rr.save(new Reservation(user, from, to));
        }

        return "redirect:/reservations";
    }

    boolean canBook(LocalDate from, LocalDate to) {
        List<Reservation> rs = rr.findAll();
        for (Reservation r : rs) {
            if (from.isAfter(r.getReservationFrom())) {
                if (from.isBefore(r.getReservationTo())) {
                    return false;
                }
            }
            if (r.getReservationFrom().isAfter(from)) {
                if (r.getReservationFrom().isBefore(to)) {
                    return false;
                }
            }
        }
        return true;
    }
}
