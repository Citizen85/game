package myproject.game.services.impl;

import myproject.game.dao.UserRepository;
import myproject.game.enums.Status;
import myproject.game.mappers.UserMapper;
import myproject.game.models.dto.SessionDto;
import myproject.game.models.dto.UserDto;
import myproject.game.models.entities.User;
import myproject.game.services.SessionService;
import myproject.game.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private SessionService sessionService;
    @Autowired
    private UserRepository userRepository;
    private UserMapper userMapper = UserMapper.INSTANCE;

    @Override
    public UserDto createUser(UserDto userDto) {
        User user = userMapper.userDtoToUser(userDto);
        user = userRepository.save(user);
        return userMapper.userToUserDto(user);
    }

    @Override
    public UserDto updateUser(String auth, UserDto userDto) {
        SessionDto sessionDto = sessionService.getSessionInfo(auth);
        if(!sessionDto.getUser().isAdmin()){
            throw new RuntimeException("Not Allowed!!!");
        }
        User user = userRepository.findById(userDto.getId()).orElse(null);
        if(user!=null){
            user = userMapper.INSTANCE.userDtoToUser(userDto);
            user.setUserName(userDto.getUserName());
            user.setAdmin(userDto.isAdmin());
            user.setEmail(user.getEmail());
            user.setPwd(user.getPwd());
            user.setStatus(userDto.getStatus());
            user = userRepository.save(user);
        }
        return userMapper.INSTANCE.userToUserDto(user);
    }

    @Override
    public UserDto findById(Long id) {
        User user = userRepository.findById(id).orElse(null);
        if(user==null){
            throw new RuntimeException("User Not Found");
        }
        return userMapper.userToUserDto(user);
    }

    @Override
    public UserDto findByUserNameAndPwd(String userName, String password) {
        User user = userRepository.findByUserNameAndPwd(userName, password);
        List<User> users = userRepository.findAllByStatus(Status.ACTIVE);
        if(user==null){
            throw new RuntimeException("Wrong email or password");
        }
        if(!users.contains(user)){
            throw new RuntimeException("User is not Active");
        }
        return UserMapper.INSTANCE.userToUserDto(user);
    }

    @Override
    public UserDto findAdmin(boolean isAdmin) {
        User user = userRepository.findByIsAdmin(true);
        return UserMapper.INSTANCE.userToUserDto(user);
    }

    @Override
    public List<UserDto> findAllUsers() {
        List<User> users = userRepository.findAll();
        return userMapper.usersToUserDtos(users);
    }

    @Override
    public List<UserDto> findAllActiveUsers() {
        List<User> users = userRepository.findAllByStatus(Status.ACTIVE);
        System.out.println(users);
        return userMapper.usersToUserDtos(users);
    }

    @Override
    public UserDto deleteUser(String auth, Long id) {
        SessionDto sessionDto = sessionService.getSessionInfo(auth);
        if(!sessionDto.getUser().isAdmin()){
            throw new RuntimeException("Not Allowed!!!");
        }
        User user = userRepository.findById(id).orElse(null);
        if(user!=null){
            user.setStatus(Status.NOT_ACTIVE);
        }
        return userMapper.userToUserDto(user);
    }
}
