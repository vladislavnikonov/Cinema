package edu.school21.cinema.controllers;

import edu.school21.cinema.models.Film;
import edu.school21.cinema.services.FilmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Controller
@RequestMapping("/admin/panel")
public class FilmController {
    private final FilmService filmService;

    @Autowired
    public FilmController(FilmService filmService) {
        this.filmService = filmService;
    }

    @GetMapping(value = "/films")
    public String showAllFilms(Model model) {
        List<Film> films = filmService.findAll();
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
    public String saveFilm(Model model,
                           @ModelAttribute("title") String title,
                           @ModelAttribute("releaseYear") Integer releaseYear,
                           @ModelAttribute("ageRegistration") Integer ageRegistration,
                           @ModelAttribute("description") String description,
                           @ModelAttribute("file") MultipartFile file) {
        if (!filmService.create(title, releaseYear, ageRegistration, description, file)) {
            model.addAttribute("error", "A film with this title already exists");
            return "addFilm";
        }
        return "redirect:/admin/panel/films";
    }
}
