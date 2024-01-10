package com.uss.UpdatedSpringSecurity.Service;

import com.uss.UpdatedSpringSecurity.Dto.Login;
import com.uss.UpdatedSpringSecurity.Dto.UserDto;

import java.util.List;

public interface IUserService {
    UserDto addUser(UserDto userDto) throws Exception;
    UserDto updateUser(UserDto userDto) throws Exception;
    void deleteUser(String email) throws Exception;
    UserDto getUserByEmail(String email) throws Exception;
    List<UserDto> getAllUsers();
    String logIn(Login login);

}
