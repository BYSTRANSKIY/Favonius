package com.favonius.services.impl;

import com.favonius.entities.Image;
import com.favonius.repositories.ImageRepository;
import com.favonius.services.ImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ImageServiceImpl implements ImageService {
    private final ImageRepository imageRepository;

    @Override
    public Optional<Image> findById(Long id) {
        return imageRepository.findById(id);
    }
}
