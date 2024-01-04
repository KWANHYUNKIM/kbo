package io.security.corespringsecurity.repository.profile;

import io.security.corespringsecurity.domain.entity.profile.ImageEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImageRepository extends JpaRepository<ImageEntity, Long> {

}