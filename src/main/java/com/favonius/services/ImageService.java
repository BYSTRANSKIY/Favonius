package com.favonius.services;

import com.favonius.entities.Image;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

public interface ImageService {
    Optional<Image> findById(Long id);

    Image toImageEntity(MultipartFile file) throws IOException;
}
