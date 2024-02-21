package io.security.corespringsecurity.service.impl.kbo;

import io.security.corespringsecurity.domain.entity.schedule.Location;
import io.security.corespringsecurity.repository.kbo.LocationRepository;
import io.security.corespringsecurity.service.kbo.LocationService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LocationServiceImpl implements LocationService {
    @Autowired
    private LocationRepository locationRepository;
    @Override
    public List<Location> findAll() {
        return locationRepository.findAll();
    }

    @Override
    public List<Location> findByLocation(String location) {
        return locationRepository.findByLocation(location);
    }
}
