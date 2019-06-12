package test.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import test.model.Image;
import test.model.User;
import test.service.Impl.ImageService;
import test.service.Impl.UserService;
import test.util.ErrorWrapper;

import java.util.Optional;


@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;
    private final ImageService imageService;

    @Autowired
    public UserController(UserService userService, ImageService imageService){
        this.userService = userService;
        this.imageService = imageService;
    }

    @GetMapping("/get")
    public int getAll(){
        return userService.getAll().size();
    }

    @GetMapping("/add")
    public Long addNewUser(@RequestParam String Uri, @RequestParam String name, @RequestParam String email){
        Optional<Image> image = imageService.findByUri(Uri);
        System.out.println(image.isPresent()==true? "hi":"fuck");
        User user = new User.Builder().setEmail(email).setName(name).setImage(image).build();
        System.out.println(user.getImage().getUri());
        return userService.save(user).getId();


    }

    @GetMapping("/get")
    public String getCurrUser(@RequestParam("id") Long id) throws Exception{
        Optional<User> user = userService.findById(id);
        if (user.isPresent()){
            return new ObjectMapper().writeValueAsString(new ErrorWrapper.Builder().setError_code(0L).setMessage(new ObjectMapper().writeValueAsString(user.get())));
        } else {
            return new ObjectMapper().writeValueAsString(new ErrorWrapper.Builder().setError_code(1L).setMessage("user doesnt exist").build());
        }
    }
}
