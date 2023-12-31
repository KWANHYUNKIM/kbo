package io.security.corespringsecurity.service.board;

import io.security.corespringsecurity.domain.entity.ImageEntity;


import java.util.List;

public interface ImageService {


    List<ImageEntity> findAll();
}
