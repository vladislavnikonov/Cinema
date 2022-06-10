package edu.school21.cinema.controllers;

import edu.school21.cinema.services.FilmService;
import edu.school21.cinema.services.HallService;
import edu.school21.cinema.services.SessionService;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.text.ParseException;

@Controller
@RequestMapping(value = "/admin/panel")
public class SessionController {
    private final SessionService sessionService;
    private final FilmService filmService;
    private final HallService hallService;

    public SessionController(SessionService sessionService, FilmService filmService, HallService hallService) {
        this.sessionService = sessionService;
        this.filmService = filmService;
        this.hallService = hallService;
    }

    @GetMapping(value = "/sessions", consumes = MediaType.ALL_VALUE)
    public String getSessions(Model model) {
        model.addAttribute("sessions", sessionService.findAll());
        model.addAttribute("halls", hallService.findAll());
        model.addAttribute("films", filmService.findAll());
        return "sessions";
    }

    @PostMapping(value = "/saveSession", consumes = MediaType.ALL_VALUE)
    public String saveSession(Model model,
                              @ModelAttribute("selectedFilm") Long filmId,
                              @ModelAttribute("selectedHall") Long hallId,
                              @ModelAttribute("ticketCost") Integer ticketCost,
                              @ModelAttribute("sessionDate") String sessionDate) throws ParseException {
        if (!sessionService.create(filmId, hallId, ticketCost, sessionDate)) {
            model.addAttribute("error", "A session in this hall already exists at this time");
            return getSessions(model);
        }
        return "redirect:/admin/panel/sessions";
    }

}
