package test.service;

import org.springframework.data.repository.CrudRepository;
import test.model.Image;

import java.util.Optional;

public interface ImageRepository extends CrudRepository<Image, Long> {
    Optional<Image> findByUri(String uri);
}
