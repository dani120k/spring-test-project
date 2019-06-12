package test.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import test.model.Image;
import test.service.Impl.ImageService;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.nio.file.Paths;

@RestController
@RequestMapping("/images")
public class ImageController {

    @Autowired
    private ImageService imageService;

    @RequestMapping(value = "/addTo", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<?> uploadFile(
            @RequestParam("uploadfile") MultipartFile uploadfile) {

        try {
            String filename = uploadfile.getOriginalFilename();
            System.out.println(filename);

            Image image = new Image();
            image.setId(1L);
            image.setUri(filename);
            imageService.save(image);

            // Save the file locally
            BufferedOutputStream stream =
                    new BufferedOutputStream(new FileOutputStream(new File("src/main/resources/pictures/" + filename)));
            stream.write(uploadfile.getBytes());
            stream.close();
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/add")
    public String addImage(@RequestParam("uploadfile") MultipartFile uploadfile){
        if (!uploadfile.isEmpty()) {
            try {
                byte[] bytes = uploadfile.getBytes();
                BufferedOutputStream stream =
                        new BufferedOutputStream(new FileOutputStream(new File(uploadfile.getName())));
                stream.write(bytes);
                stream.close();
                System.out.println("ok");
                Image image = new Image();
                image.setId(1L);
                image.setUri(uploadfile .getName());
                imageService.save(image);
                return "ok";
            } catch (Exception e) {
                System.out.println("notok");
                return "not ok";
            }
        } else {
            System.out.println("empty");
            return "not ok";
        }
    }
}
