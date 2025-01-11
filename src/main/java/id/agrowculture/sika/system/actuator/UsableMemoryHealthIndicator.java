package id.agrowculture.sika.system.actuator;

import java.io.File;

import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.boot.actuate.health.Status;
import org.springframework.stereotype.Component;

@Component
public class UsableMemoryHealthIndicator implements HealthIndicator {

  @Override
  public Health health() {
    File path = new File(".");
    long diskUsableInBytes = path.getUsableSpace();
    boolean isHealth = diskUsableInBytes >= 10 * 1024 * 1024; // 10 MB
    Status status = isHealth ? Status.UP : Status.DOWN;
    return Health
        .status(status)
        .withDetail("usable memory", diskUsableInBytes)
        .withDetail("threshold", 10 * 1024 * 1024)
        .build();
  }
}
