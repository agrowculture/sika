package id.agrowculture.sika.security;

import java.util.HashMap;
import java.util.Map;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import id.agrowculture.sika.user.MyUserPrincipal;
import id.agrowculture.sika.user.User;
import id.agrowculture.sika.user.converter.UserToUserDtoConverter;
import id.agrowculture.sika.user.dto.UserDto;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthService {

  private final JwtProvider jwtProvider;

  private final UserToUserDtoConverter userToUserDtoConverter;

  public Map<String, Object> getLoginInfo(Authentication authentication) {
    MyUserPrincipal principal = (MyUserPrincipal) authentication.getPrincipal();
    User user = principal.getUser();
    UserDto userDto = this.userToUserDtoConverter.convert(user);

    Map<String, Object> loginResultMap = new HashMap<>();
    loginResultMap.put("user", userDto);

    return loginResultMap;
  }

  public Map<String, Object> createLoginInfo(Authentication authentication) {
    MyUserPrincipal principal = (MyUserPrincipal) authentication.getPrincipal();
    User user = principal.getUser();
    UserDto userDto = this.userToUserDtoConverter.convert(user);

    String token = this.jwtProvider.createToken(authentication);

    Map<String, Object> loginResultMap = new HashMap<>();
    loginResultMap.put("userInfo", userDto);
    loginResultMap.put("token", token);

    return loginResultMap;
  }

  public Map<String, Object> createLoginInfo(MyUserPrincipal myUserPrincipal) {
    User user = myUserPrincipal.getUser();
    UserDto userDto = this.userToUserDtoConverter.convert(user);

    String token = this.jwtProvider.createToken(myUserPrincipal);

    Map<String, Object> loginResultMap = new HashMap<>();
    loginResultMap.put("userInfo", userDto);
    loginResultMap.put("token", token);

    return loginResultMap;
  }

}
