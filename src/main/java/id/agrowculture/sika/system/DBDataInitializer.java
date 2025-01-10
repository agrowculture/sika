package id.agrowculture.sika.system;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import id.agrowculture.sika.user.User;
import id.agrowculture.sika.user.UserService;
import lombok.RequiredArgsConstructor;

@Component
@Profile("dev")
@RequiredArgsConstructor
public class DBDataInitializer implements CommandLineRunner {

  private final UserService userService;

  @Override
  public void run(String... args) throws Exception {
    // Create some users.
    User u1 = new User();
    u1.setUsername("john");
    u1.setPassword("123456");
    u1.setEnabled(true);
    u1.setRoles("admin user");

    User u2 = new User();
    u2.setUsername("eric");
    u2.setPassword("654321");
    u2.setEnabled(true);
    u2.setRoles("user");

    User u3 = new User();
    u3.setUsername("tom");
    u3.setPassword("qwerty");
    u3.setEnabled(false);
    u3.setRoles("user");

    userService.save(u1);
    userService.save(u2);
    userService.save(u3);
  }

}
