package edu.school21.cinema.services.impl;

import edu.school21.cinema.models.Film;
import edu.school21.cinema.repositories.FilmRepository;
import edu.school21.cinema.services.FilmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;

@Service
public class FilmServiceImpl implements FilmService {
    public static final String DIR_POSTERS = "images";
    private final FilmRepository filmRepository;
    private final String imagesPath;

    @Autowired
    public FilmServiceImpl(@Qualifier("filmRepositoryImpl") FilmRepository filmRepository,
                           @Qualifier("pathToImagesFolder") String imagesPath) {
        this.filmRepository = filmRepository;
        this.imagesPath = imagesPath;
    }

    @Override
    public List<Film> findAll() {
        return filmRepository.findAll();
    }

    @Override
    public boolean create(String title, Integer releaseYear, Integer ageRegistration, String description, MultipartFile file) {
        if (filmRepository.getByTitle(title) != null) {
            return false;
        } else {
            String key = "";
            if (file.getOriginalFilename() != null && !file.getOriginalFilename().isEmpty()) {
                try {
                    key = UUID.randomUUID().toString();
                    upload(file.getBytes(), key);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            Film film = new Film(title, releaseYear,
                    ageRegistration, description, key);
            filmRepository.save(film);
            return true;
        }
    }

    private void upload(byte[] resource, String keyName) throws IOException {
        if (!Files.exists(Paths.get(imagesPath + DIR_POSTERS))) {
            if (!Files.exists(Paths.get(imagesPath))) {
                Files.createDirectories(Paths.get(imagesPath));
            }
            Files.createDirectories(Paths.get(imagesPath + DIR_POSTERS));
        }
        FileOutputStream newFile = new FileOutputStream(imagesPath + DIR_POSTERS + "/" + keyName);
        newFile.write(resource);
        newFile.close();
    }
}
