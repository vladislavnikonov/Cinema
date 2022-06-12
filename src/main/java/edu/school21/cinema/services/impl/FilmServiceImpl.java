package edu.school21.cinema.services.impl;

import edu.school21.cinema.exceptions.NotAllDataException;
import edu.school21.cinema.exceptions.ObjectAlreadyExistsException;
import edu.school21.cinema.models.Film;
import edu.school21.cinema.models.SaveFilm;
import edu.school21.cinema.repositories.FilmRepository;
import edu.school21.cinema.services.FilmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.RenderedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@Service
public class FilmServiceImpl implements FilmService {

    private final FilmRepository filmRepository;
    private final String imagesPath;

    @Autowired
    public FilmServiceImpl(@Qualifier("filmRepositoryImpl") FilmRepository filmRepository,
                           @Qualifier("pathToImagesFolder") String imagesPath) {
        this.filmRepository = filmRepository;
        this.imagesPath = imagesPath;
    }

    @Override
    public void saveFilm(SaveFilm saveFilm) throws NotAllDataException, ObjectAlreadyExistsException {
        if (saveFilm == null || saveFilm.getTitle() == null
                || saveFilm.getAgeRestriction() == null || saveFilm.getReleaseYear() == null) {
            throw new NotAllDataException("Please enter all data");
        } else if (filmRepository.getByTitle(saveFilm.getTitle()) != null) {
            throw new ObjectAlreadyExistsException("A film with this title already exists");
        } else {
            String key = "";
            if (saveFilm.getFile().getOriginalFilename() != null && !saveFilm.getFile().getOriginalFilename().isEmpty()) {
                try {
                    key = UUID.randomUUID().toString();
                    upload(saveFilm.getFile().getBytes(), key, saveFilm.getTitle());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            Film film = new Film(saveFilm.getTitle(), saveFilm.getReleaseYear(),
                    saveFilm.getAgeRestriction(), saveFilm.getDescription(), key);
            filmRepository.save(film);
        }
    }

    @Override
    public void updateFilm(SaveFilm saveFilm) {
        String key;
        if (saveFilm.getFile().getOriginalFilename() != null && !saveFilm.getFile().getOriginalFilename().isEmpty()) {
            try {
                Film film = filmRepository.getByTitle(saveFilm.getTitle());
                key = UUID.randomUUID().toString();
                upload(saveFilm.getFile().getBytes(), key, saveFilm.getTitle());
                film.setPoster(key);
                filmRepository.save(film);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    @Override
    public List<Film> findAll() {
        return filmRepository.findAll();
    }

    @Override
    public boolean create(Film film) {
        if (filmRepository.getByTitle(film.getTitle()) != null) {
            return false;
        }
        filmRepository.save(film);
        return true;
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
                    upload(file.getBytes(), key, title);
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

    private void upload(byte[] resource, String keyName, String title) throws IOException {
        if (!Files.exists(Paths.get(imagesPath + title))) {
            if (!Files.exists(Paths.get(imagesPath))) {
                Files.createDirectories(Paths.get(imagesPath));
            }
            Files.createDirectories(Paths.get(imagesPath + title));
        }
        FileOutputStream newFile = new FileOutputStream(imagesPath + title + "/" + keyName);
        newFile.write(resource);
        newFile.close();
    }
}
