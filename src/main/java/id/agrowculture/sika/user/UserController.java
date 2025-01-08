package id.agrowculture.sika.user;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import id.agrowculture.sika.system.Result;
import id.agrowculture.sika.system.StatusCode;
import id.agrowculture.sika.user.converter.UserDtoToUserConverter;
import id.agrowculture.sika.user.converter.UserToUserDtoConverter;
import id.agrowculture.sika.user.dto.UserDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("${api.endpoint.base-url}/users")
@RequiredArgsConstructor
public class UserController {
  private final UserService userService;

  private final UserDtoToUserConverter userDtoToUserConverter; // Convert userDto to user.

  private final UserToUserDtoConverter userToUserDtoConverter; // Convert user to userDto.

  @GetMapping
  public Result findAllUsers() {
    List<User> foundUsers = this.userService.findAll();

    // Convert foundUsers to a list of UserDtos.
    List<UserDto> userDtos = foundUsers.stream()
        .map(this.userToUserDtoConverter::convert)
        .collect(Collectors.toList());

    // Note that UserDto does not contain password field.
    return new Result(true, StatusCode.SUCCESS, "Find All Success", userDtos);
  }

  @GetMapping("/{userId}")
  public Result findUserById(@PathVariable Integer userId) {
    User foundUser = this.userService.findById(userId);
    UserDto userDto = this.userToUserDtoConverter.convert(foundUser);
    return new Result(true, StatusCode.SUCCESS, "Find One Success", userDto);
  }

  /**
   * We are not using UserDto, but User, since we require password.
   *
   * @param newUser
   * @return
   */
  @PostMapping
  public Result addUser(@Valid @RequestBody User newUser) {
    User savedUser = this.userService.save(newUser);
    UserDto savedUserDto = this.userToUserDtoConverter.convert(savedUser);
    return new Result(true, StatusCode.SUCCESS, "Add Success", savedUserDto);
  }

  // We are not using this to update password, need another changePassword method
  // in this class.
  @PutMapping("/{userId}")
  public Result updateUser(@PathVariable Integer userId, @Valid @RequestBody UserDto userDto) {
    User update = this.userDtoToUserConverter.convert(userDto);
    User updatedUser = this.userService.update(userId, update);
    UserDto updatedUserDto = this.userToUserDtoConverter.convert(updatedUser);
    return new Result(true, StatusCode.SUCCESS, "Update Success", updatedUserDto);
  }

  @DeleteMapping("/{userId}")
  public Result deleteUser(@PathVariable Integer userId) {
    this.userService.delete(userId);
    return new Result(true, StatusCode.SUCCESS, "Delete Success");
  }

}
