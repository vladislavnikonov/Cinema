package edu.school21.cinema.controllers;

import edu.school21.cinema.models.Film;
import edu.school21.cinema.repositories.FilmRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/admin/panel")
public class FilmController {

    private final FilmRepository filmRepository;

    @Autowired
    public FilmController(FilmRepository filmRepository) {
        this.filmRepository = filmRepository;
    }

    @GetMapping(value = "/films")
    public String showAllFilms(Model model) {
        List<Film> films = filmRepository.findAll();
        model.addAttribute("films", films);
        return "films";
    }

    @GetMapping(value = "/addFilm")
    public String addFilm(Model model) {
        Film film = new Film();
        model.addAttribute("film", film);
        return "addFilm";
    }

    @PostMapping(value = "/saveFilm")
    public String saveFilm(Model model, @ModelAttribute("film") Film film) {
        if (film == null || film.getTitle() == null || film.getAgeRegistration() == null || film.getReleaseYear() == null) {
            model.addAttribute("error", "Please enter all data");
            return "addFilm";
        } else if (filmRepository.getByTitle(film.getTitle()) != null) {
            model.addAttribute("error", "A film with this title already exists");
            return "addFilm";
        } else {
            filmRepository.save(film);
        }
        return "redirect:/admin/panel/films";
    }

}
