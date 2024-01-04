package io.security.corespringsecurity.service.kbo;

import io.security.corespringsecurity.domain.entity.schedule.Location;

import java.util.List;

public interface LocationService {

    List<Location> findAll();
}
