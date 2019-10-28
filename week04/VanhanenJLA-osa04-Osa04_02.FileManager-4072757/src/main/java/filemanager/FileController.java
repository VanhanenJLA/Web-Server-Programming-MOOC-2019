package filemanager;

import java.io.IOException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class FileController
{
    @Autowired
    FileRepository fileRepository;

    @PostMapping("/files")
    public String save(@RequestParam("file") MultipartFile multiPartFile) throws IOException {
        File fo = new File();
        fo.setName(multiPartFile.getOriginalFilename());
        fo.setContentType(multiPartFile.getContentType());
        fo.setContentLength(multiPartFile.getSize());
        fo.setContent(multiPartFile.getBytes());

        fileRepository.save(fo);

        return "redirect:/files";
    }

    @GetMapping("/files")
    public String files(Model model) {
        List<File> files = fileRepository.findAll();
        model.addAttribute("files", files);
        return "files";
    }

    @GetMapping("/files/{id}")
    public ResponseEntity<byte[]> viewFile(@PathVariable Long id) {
        File fo = fileRepository.getOne(id);

        final HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.parseMediaType(fo.getContentType()));
        headers.setContentLength(fo.getContentLength());
        headers.add("Content-Disposition", "attachment; filename=" + fo.getName());
        return new ResponseEntity<>(fo.getContent(), headers, HttpStatus.CREATED);
    }

}
