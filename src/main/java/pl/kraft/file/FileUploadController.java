package pl.kraft.file;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import pl.kraft.exception.web.WrongImageFormatException;
import pl.kraft.file.service.StorageService;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@RestController
@RequestMapping("/api/file")
public class FileUploadController {

    private StorageService storageService;

    public FileUploadController(StorageService storageService) {
        this.storageService = storageService;
    }

    @GetMapping(value = "/{filename}")
    public ResponseEntity<byte[]> fromClasspathAsResEntity(@PathVariable String filename,
                                                           @RequestParam(required = false, defaultValue = "false") String profile)
            throws IOException {
        MediaType type = MediaType.ALL;
        ClassPathResource imageFile;

        if (profile.equals("true")) {
            imageFile = new ClassPathResource("../image-profile/" + filename);
        } else {
            imageFile = new ClassPathResource("../upload-dir/" + filename);
        }

        byte[] imageBytes = StreamUtils.copyToByteArray(imageFile.getInputStream());

        if (filename.contains(".")) {
            String[] spiltFilename = filename.split("\\.");
            switch (spiltFilename[1].toLowerCase()) {
                case "jpg" :
                case "jpeg" :
                    type = MediaType.IMAGE_JPEG; break;
                case "png" : type = MediaType.IMAGE_PNG; break;
                case "pdf" :  type = MediaType.APPLICATION_PDF; break;
                default: break;
            }
        }
        return ResponseEntity.ok().contentType(type).body(imageBytes);
    }

    @PostMapping("")
    public String uploadFile(@RequestParam("email") String email, @RequestParam("surname") String surname,
                             @RequestParam("file") MultipartFile file, @RequestParam(required = false, defaultValue = "false") String profile) {
        try {
            storageService.store(email, file, profile, surname);
            return file.getOriginalFilename();
        } catch (ArrayIndexOutOfBoundsException ex) {
            throw new WrongImageFormatException();
        }
    }

    @GetMapping("/downloadFile/{fileName:.+}")
    public ResponseEntity<Resource> downloadFile(@PathVariable String fileName, HttpServletRequest request,
                                                 @RequestParam(required = false, defaultValue = "false") String profile)  {
        // Load file as Resource
        Resource resource = storageService.loadAsResource(fileName, profile);

        // Try to determine file's content type
        String contentType = null;
        try {
            contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
        } catch (IOException ex) {
        }

        // Fallback to the default content type if type could not be determined
        if(contentType == null) {
            contentType = "application/octet-stream";
        }

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(contentType))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                .body(resource);
    }
}