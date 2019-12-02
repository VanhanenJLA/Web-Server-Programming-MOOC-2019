package reloadheroes;

import java.util.UUID;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ReloadController {

    @Autowired
    private ReloadStatusRepository rss;

    @Autowired
    private HttpSession hs;

    @RequestMapping("*")
    public String reload(Model model) {   
        
        ReloadStatus rs = null;
        if (hs.getAttribute("name") != null) {
            rs = rss.findByName(hs.getAttribute("name").toString());
        }
        if (rs == null) {
            rs = new ReloadStatus(UUID.randomUUID().toString(), 0);
            hs.setAttribute("name", rs.getName());
        }
        rs.setReloads(rs.getReloads() + 1);
        rss.save(rs);
        
        model.addAttribute("status", rs);
        model.addAttribute("scores", 
                rss.findAll(new PageRequest(0,5, Sort.Direction.DESC, "reloads"))
                        .getContent());
        
        return "index";
    }
}
