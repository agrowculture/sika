package id.agrowculture.sika.user;

import java.io.Serializable;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "sika_user")
@Setter
@Getter
public class User implements Serializable {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Integer id;

  @NotEmpty(message = "username is required.")
  private String username;

  @NotEmpty(message = "password is required.")
  private String password;

  private boolean enabled;

  @NotEmpty(message = "roles are required.")
  private String roles; // Space separated string

  public User() {
  }
}
