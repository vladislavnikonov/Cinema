package edu.school21.cinema.controllers;

import edu.school21.cinema.models.Session;
import edu.school21.cinema.repositories.HallRepository;
import edu.school21.cinema.repositories.SessionRepository;
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
import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
@RequestMapping(value = "/admin/panel")
public class SessionController {
    private final SessionService sessionService;
    private final FilmService filmService;
    private final HallService hallService;
    private final SimpleDateFormat formatter;
    private final SessionRepository sessionRepository;

    public SessionController(SessionService sessionService, FilmService filmService, HallService hallService, SessionRepository sessionRepository) {
        this.sessionService = sessionService;
        this.filmService = filmService;
        this.hallService = hallService;
        this.sessionRepository = sessionRepository;
        this.formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
    }

    @GetMapping(value = "/sessions", consumes = MediaType.ALL_VALUE)
    public String getSessions(Model model) {
        model.addAttribute("sessions", sessionService.getAll());
        model.addAttribute("halls", hallService.getAll());
        model.addAttribute("films", filmService.findAll());
        return "sessions";
    }

    @PostMapping(value = "/saveSession", consumes = MediaType.ALL_VALUE)
    public String saveSession(Model model,
                              @ModelAttribute("selectedFilm") Long filmId,
                              @ModelAttribute("selectedHall") Long hallId,
                              @ModelAttribute("ticketCost") Integer ticketCost,
                              @ModelAttribute("sessionDate") String sessionDate) throws ParseException {
        Date date = formatter.parse(sessionDate);
        if (sessionRepository.getByHallIdAndSessionDate(hallId, date) != null) {
            model.addAttribute("error", "A session in this hall already exists at this time");
            return getSessions(model);
        } else {
            Session session = new Session();
            session.setTicketCost(ticketCost);
            session.setSessionDate(date);
            sessionService.save(session, filmId, hallId);

            return "redirect:/admin/panel/sessions";
        }
    }

}
