package com.uss.UpdatedSpringSecurity.Service.Impl;

import com.uss.UpdatedSpringSecurity.Dto.Login;
import com.uss.UpdatedSpringSecurity.Dto.UserDto;
import com.uss.UpdatedSpringSecurity.Entity.User;
import com.uss.UpdatedSpringSecurity.Repository.UserRepository;
import com.uss.UpdatedSpringSecurity.Security.JwtService;
import com.uss.UpdatedSpringSecurity.Service.IUserService;
import com.uss.UpdatedSpringSecurity.Service.UserInfoDetails;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements IUserService, UserDetailsService {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    private final ModelMapper modelMapper;
    private final AuthenticationManager authenticationManage;
    private final JwtService jwtService;
    @Autowired
    public UserServiceImpl(UserRepository userRepository, BCryptPasswordEncoder passwordEncoder, ModelMapper modelMapper, AuthenticationManager authenticationManage, JwtService jwtService) {
        this.userRepository=userRepository;
        this.passwordEncoder=passwordEncoder;
        this.modelMapper=modelMapper;
        this.authenticationManage = authenticationManage;
        this.jwtService = jwtService;
    }
    @Override
    public UserDto addUser(UserDto userDto) throws Exception {
        Optional<User> user=userRepository.findByEmail(userDto.getEmail());
        if (user.isPresent()){
            throw new Exception("User is Already Exist, Please Login.");
        }
        else {
            userDto.setPassword(passwordEncoder.encode(userDto.getPassword()));
            User userInfo=modelMapper.map(userDto,User.class);
            userRepository.save(userInfo);
            return modelMapper.map(userInfo, UserDto.class);
        }
    }

    @Override
    public UserDto updateUser(UserDto userDto) throws Exception {
        User user=userRepository.findUserByEmail(userDto.getEmail());
        if (user!=null){
            userDto.setPassword(passwordEncoder.encode(userDto.getPassword()));
            user.setPassword(userDto.getPassword());
            user.setName(user.getName());
            user.setEmail(user.getEmail());
            user.setRole(user.getRole());
            User userInfo=modelMapper.map(userDto,User.class);
            userRepository.save(userInfo);
            return modelMapper.map(userInfo, UserDto.class);
        }
        else {
            throw new Exception("User is Not Exist, Please Register.");

        }
    }

    @Override
    public void deleteUser(String email) throws Exception {
        User user=userRepository.findUserByEmail(email);
        if(user!=null){
            userRepository.delete(user);
        }
        else {
            throw new Exception("User Not Found");
        }
    }

    @Override
    public UserDto getUserByEmail(String email) throws Exception {
        User user=userRepository.findUserByEmail(email);
        if(user!=null){
            return  modelMapper.map(user, UserDto.class);
        }
        else {
            throw new Exception("User Not Found");
        }
    }

    @Override
    public List<UserDto> getAllUsers() {
        return null;
    }

    @Override
    public String logIn(Login login) {
        Authentication authentication = authenticationManage.authenticate(new UsernamePasswordAuthenticationToken(login.getEmail(), login.getPassword()));
        if (authentication.isAuthenticated()){
            return jwtService.generateToken(login.getEmail());
        }
        else {
            throw new UsernameNotFoundException("Invalid User Email or Password");
        }
    }


    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<User> UserInfo = userRepository.findByEmail(email);
        return UserInfo.map(UserInfoDetails::new)
                .orElseThrow(()->new UsernameNotFoundException("User Not Found Against this Email"+email));
    }
}
