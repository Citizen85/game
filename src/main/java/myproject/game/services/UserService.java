package myproject.game.services;

import myproject.game.models.dto.UserDto;

import java.util.List;

public interface UserService {
    UserDto createUser(UserDto userDto);
    UserDto updateUser(String auth, UserDto userDto);
    UserDto findById(Long id);
    UserDto findByUserNameAndPwd(String userName, String password);
    UserDto findAdmin(boolean isAdmin);
    List<UserDto> findAllUsers ();
    List<UserDto> findAllActiveUsers();
    UserDto deleteUser (String auth, Long id);
}
