package id.agrowculture.sika.user.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import id.agrowculture.sika.user.User;
import id.agrowculture.sika.user.dto.UserDto;

@Component
public class UserDtoToUserConverter implements Converter<UserDto, User> {

  @Override
  public User convert(UserDto source) {
    User user = new User();
    user.setUsername(source.username());
    user.setEnabled(source.enabled());
    user.setRoles(source.roles());
    return user;
  }

}
