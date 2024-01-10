package com.uss.UpdatedSpringSecurity.Controller;

import com.uss.UpdatedSpringSecurity.Dto.Login;
import com.uss.UpdatedSpringSecurity.Dto.UserDto;
import com.uss.UpdatedSpringSecurity.Service.Impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/user")
public class UserController {
    private final UserServiceImpl userService;
    @Autowired
   public UserController(UserServiceImpl userService) {
       this.userService = userService;
   }
   @PostMapping("/register")
   public ResponseEntity<UserDto> addUser(@RequestBody UserDto userDto) throws Exception {
        UserDto user=userService.addUser(userDto);
        return new ResponseEntity<UserDto>(user, HttpStatusCode.valueOf(200));
   }
    @PostMapping("/login")
    public ResponseEntity<String> logIn(@RequestBody Login login) throws Exception {
        String user=userService.logIn(login);
        return new ResponseEntity<String>(user,HttpStatusCode.valueOf(200));
    }


}
