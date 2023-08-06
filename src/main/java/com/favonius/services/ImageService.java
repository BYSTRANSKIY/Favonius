package com.favonius.services;

import com.favonius.entities.Image;

import java.util.Optional;

public interface ImageService {
    Optional<Image> findById(Long id);
}
