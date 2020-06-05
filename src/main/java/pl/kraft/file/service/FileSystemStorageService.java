package pl.kraft.file.service;

import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import pl.kraft.file.FileEntity;
import pl.kraft.file.FileRepository;
import pl.kraft.file.StorageException;
import pl.kraft.file.StorageFileNotFoundException;
import pl.kraft.student.Student;
import pl.kraft.student.StudentRepository;
import pl.kraft.student.service.StudentService;

import javax.transaction.Transactional;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.NoSuchElementException;
import java.util.Random;
import java.util.UUID;
import java.util.stream.Stream;

@Service
public class FileSystemStorageService implements StorageService{
    private final Path rootLocation;
    private final Path imageLocation;
    private final StudentRepository studentRepository;
    private final FileRepository fileRepository;

    public FileSystemStorageService(StudentRepository studentRepository, FileRepository fileRepository) {
        this.studentRepository = studentRepository;
        this.fileRepository = fileRepository;
        this.rootLocation = Paths.get("upload-dir");
        this.imageLocation = Paths.get("image-profile");
    }

    @Override
    public void init() {
        try {
            Files.createDirectories(rootLocation);
        } catch (IOException e) {
            throw new StorageException("Could not initialize storage", e);
        }
    }

    @Override
    @Transactional
    public void store(String email, MultipartFile file, String profile, String surname) {
        Random random = new Random();
        String[] fileName = file.getOriginalFilename().trim().split("\\.");
        String fileNames = surname + random.nextInt() % 1_000_000 + "." + fileName[1];
        file.getOriginalFilename().replace(file.getOriginalFilename(), fileNames);
        String filename = StringUtils.cleanPath(fileNames);
        try {
            if (file.isEmpty()) {
                throw new StorageException("Filed to store empty file " + filename);
            }
            if (filename.contains("..")) {
                throw new StorageException("Cannot store file with relative path outside current directory " + filename);
            }
            try (InputStream inputStream = file.getInputStream()) {
                Student student = studentRepository.findByEmail(email).get();
                if (profile.equals("true")) {
                    Files.copy(inputStream, this.imageLocation.resolve(filename), StandardCopyOption.REPLACE_EXISTING);
                } else {
                    Files.copy(inputStream, this.rootLocation.resolve(filename), StandardCopyOption.REPLACE_EXISTING);
                }
                FileEntity myFile = new FileEntity();
                myFile.setName(filename);
                myFile.setStudent(student);
                fileRepository.save(myFile);
                if (profile.equals("true")) {
                    student.setImageUrl(myFile.getName());
                }
                student.addFile(myFile);

            }
        } catch (IOException e) {
            throw new StorageException("Failed to store file " + filename, e);
        } catch (NoSuchElementException e) {
            throw new StorageException("Failed to store file cuz user:" + email, e);
        }
    }

    @Override
    public Stream<Path> loadAll() {
        try {
            return Files.walk(this.rootLocation, 1)
                    .filter(path -> !path.equals(this.rootLocation))
                    .map(this.rootLocation::relativize);
        } catch (IOException e) {
            throw new StorageException("Failed to read stored files", e);
        }
    }

    @Override
    public Path load(String filename, String profile) {
        if (profile.equals("true"))
            return imageLocation.resolve(filename);
        else
            return rootLocation.resolve(filename);
    }

    @Override
    public Resource loadAsResource(String filename, String profile) {
        try {
            Path file = load(filename, profile);
            Resource resource = new UrlResource(file.toUri());
            if (resource.exists() || resource.isReadable()) {
                return resource;
            } else {
                throw new StorageFileNotFoundException("Could not read file: " + filename);
            }
        } catch (MalformedURLException e) {
            throw new StorageFileNotFoundException("Could not read file: " + filename, e);
        }
    }

    @Override
    public void deleteAll() {
        FileSystemUtils.deleteRecursively(rootLocation.toFile());
    }
}