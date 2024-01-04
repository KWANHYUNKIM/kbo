package io.security.corespringsecurity.service.impl.board;

import io.security.corespringsecurity.domain.entity.profile.ImageEntity;
import io.security.corespringsecurity.repository.profile.ImageRepository;
import io.security.corespringsecurity.service.board.ImageService;
import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ImageServiceImpl implements ImageService {
    @Autowired
    private ImageRepository imageRepository;

    @Override
    public List<ImageEntity> findAll() {
        List<ImageEntity> images = imageRepository.findAll();
        return images;
    }
}
