package io.security.corespringsecurity.repository.kbo;


import io.security.corespringsecurity.domain.entity.schedule.Location;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface LocationRepository extends JpaRepository<Location,Long> {

    @Query("SELECT l FROM Location l WHERE l.locationName = :location")
    List<Location> findByLocation(@Param("location") String location);

}
