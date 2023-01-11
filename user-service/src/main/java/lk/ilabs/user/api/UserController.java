package lk.ilabs.user.api;

import lk.ilabs.user.dto.UserDTO;
import lk.ilabs.user.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/v1/users")
@RestController
@CrossOrigin
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(consumes = "application/json")
    public void saveUser(@RequestBody UserDTO user) {
        this.userService.saveUser(user);
    }

    @GetMapping(produces = "application/json", value = "/{username}")
    public UserDTO getUser(@PathVariable String username){
        return userService.findUser(username);
    }
}
