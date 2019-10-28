package weatherservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class LocationController {

    @Autowired
    private LocationRepository locationRepository;

    @Autowired
    private LocationService locationService;

    @GetMapping("/locations")
    public String list(Model model) {
        model.addAttribute("locations", locationService.getAllLocations());
        return "locations";
    }

    @GetMapping("/locations/{id}")
    public String view(Model model, @PathVariable Long id) {
        model.addAttribute("location", locationService.getOneLocationById(id));
        return "location";
    }

    @CacheEvict(cacheNames = "locations", allEntries = true)
    @GetMapping("/flushcaches")
    public String flushCaches(Model model) {
        model.addAttribute("locations", locationRepository.findAll());
        return "locations";
    }

    @CacheEvict(cacheNames = "locations", allEntries = true)
    @PostMapping("/locations")
    public String add(@ModelAttribute Location location) {
        locationRepository.save(location);
        return "redirect:/locations";
    }
}
