package io.security.corespringsecurity.repository.kbo;


import io.security.corespringsecurity.domain.entity.schedule.Location;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LocationRepository extends JpaRepository<Location,Long> {

}
