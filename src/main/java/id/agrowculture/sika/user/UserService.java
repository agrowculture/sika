package id.agrowculture.sika.user;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import id.agrowculture.sika.system.exception.ObjectNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class UserService implements UserDetailsService {
  private final UserRepository userRepository;

  private final PasswordEncoder passwordEncoder;

  public List<User> findAll() {
    return this.userRepository.findAll();
  }

  public User findById(Integer userId) {
    return this.userRepository.findById(userId)
        .orElseThrow(() -> new ObjectNotFoundException("user", userId));
  }

  public User save(User newUser) {
    newUser.setPassword(this.passwordEncoder.encode(newUser.getPassword()));
    return this.userRepository.save(newUser);
  }

  public User update(Integer userId, User update) {
    User oldUser = this.userRepository.findById(userId)
        .orElseThrow(() -> new ObjectNotFoundException("user", userId));
    oldUser.setUsername(update.getUsername());
    oldUser.setEnabled(update.isEnabled());
    oldUser.setRoles(update.getRoles());
    return this.userRepository.save(oldUser);
  }

  public void delete(Integer userId) {
    this.userRepository.findById(userId)
        .orElseThrow(() -> new ObjectNotFoundException("user", userId));
    this.userRepository.deleteById(userId);
  }

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    return this.userRepository.findByUsername(username)
        .map(user -> new MyUserPrincipal(user))
        .orElseThrow(() -> new UsernameNotFoundException("User not found with username: " + username));
  }
}
