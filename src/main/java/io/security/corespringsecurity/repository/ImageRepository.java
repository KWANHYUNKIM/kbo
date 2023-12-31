package io.security.corespringsecurity.repository;

import io.security.corespringsecurity.domain.entity.ImageEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ImageRepository extends JpaRepository<ImageEntity, Long> {

}