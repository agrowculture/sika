package id.agrowculture.sika.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import id.agrowculture.sika.system.Result;
import id.agrowculture.sika.system.StatusCode;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("${api.endpoint.base-url}/auth")
public class AuthController {

  private static final Logger LOGGER = LoggerFactory.getLogger(AuthController.class);

  private final AuthService authService;

  @PostMapping("/sign-in")
  public Result signIn(Authentication authentication) {
    LOGGER.debug("Authenticated user: '{}'", authentication.getName());
    return new Result(true, StatusCode.SUCCESS, "User Info and JSON Web Token",
        this.authService.createLoginInfo(authentication));
  }
}
