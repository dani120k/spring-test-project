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

    @GetMapping("/getAll")
    public int getAll(){
        return userService.getAll().size();
    }

    @GetMapping("/add")
    public String addNewUser(@RequestParam String Uri, @RequestParam String name, @RequestParam String email){
        Optional<Image> image = imageService.findByUri(Uri);
        if (image.isPresent()) {
            User user = new User.Builder().setEmail(email).setName(name).setImage(image).build();
            System.out.println(user.getImage().getUri());
            return userService.save(user).getId().toString();
        } else {
            return new ErrorWrapper.Builder().setError_code(1L).setMessage("Image doesnt exist").toString();
        }


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

    @GetMapping("/setStatus")
    public String setStatus(@RequestParam("id") Long id, @RequestParam("status") String status){
        boolean state;
        switch (status){
            case "Offline":
                state = false;
                break;
            case "Online":
                state = true;
                break;
            default:
                return new ErrorWrapper.Builder().setError_code(1L).setMessage("status incorrect").toString();
        }

        Optional<User> user = userService.findById(id);
        try{
            Thread.sleep(5000);
        }catch (InterruptedException ex){
            ex.printStackTrace();
        }
        if (user.isPresent()){
            user.get().setStatus(state);
            userService.save(user.get());
            return user.get().getId() + " " + !user.get().getStatus() + " " + user.get().getStatus();
        } else
            return new ErrorWrapper.Builder().setError_code(1L).setMessage("This user doesnt exist").toString();
    }
}
