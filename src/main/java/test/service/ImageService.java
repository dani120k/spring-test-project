package test.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import test.model.Image;
import test.model.User;
import test.service.ImageRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ImageService {
    @Autowired
    private ImageRepository repository;

    public List<Image> getAll(){
        return (List<Image>)repository.findAll();
    }

    public void save(Image img){
        repository.save(img);
    }

    public void delete(Image img){
        repository.delete(img);
    }

    public Optional<Image> findByUri(String uri){ return repository.findByUri(uri);}


}
