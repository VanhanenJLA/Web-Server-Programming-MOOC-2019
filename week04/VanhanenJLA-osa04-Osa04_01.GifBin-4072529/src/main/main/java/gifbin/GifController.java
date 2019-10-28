/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gifbin;

import java.io.IOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author Jouni
 */
@Controller
public class GifController {
    @Autowired
    private GifRepository gifs;
    
    @GetMapping("/gifs")
    public String redirect() {
        return "redirect:/gifs/1";
    }
    
    @GetMapping(path = "/gifs/{id}/content", produces = "image/gif")
    @ResponseBody
    public byte[] getGif(@PathVariable Long id) {
        Gif gif = gifs.getOne(id);
        
        return gif.getContent();
    }
    
    @PostMapping("/gifs")
    public String uploadGif(@RequestParam("file") MultipartFile file) throws IOException {
        if (file.getContentType().equals("image/gif"))
        {
            Gif gif = new Gif();
            gif.setContent(file.getBytes());
            
            gifs.save(gif);
        }
        
        return "redirect:/gifs";
    }
    
    @GetMapping("/gifs/{id}")
    public String redirect(Model model, @PathVariable Long id) {
        model.addAttribute("count", gifs.count());
        
        if (gifs.existsById(id - 1)) {
            model.addAttribute("previous", id - 1);
        }
        if (gifs.existsById(id)) {
            model.addAttribute("current", id);
        }
        if (gifs.existsById(id + 1)) {
            model.addAttribute("next", id + 1);
        }
        
        return "gifs";
    }
}
