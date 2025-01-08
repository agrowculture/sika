package id.agrowculture.sika.user.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import id.agrowculture.sika.user.User;
import id.agrowculture.sika.user.dto.UserDto;

@Component
public class UserToUserDtoConverter implements Converter<User, UserDto> {

  @Override
  public UserDto convert(User source) {
    final UserDto userDto = new UserDto(source.getId(),
        source.getUsername(),
        source.isEnabled(),
        source.getRoles());
    return userDto;
  }

}
