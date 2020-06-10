package pl.kraft.file.service;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Path;
import java.util.stream.Stream;

public interface StorageService {
    void init();

    void store(String userName, MultipartFile file, String profile, String surname);

    Stream<Path> loadAll();

    Path load(String filename, String profile);

    Resource loadAsResource(String filename, String profile);

    void deleteAll();
}