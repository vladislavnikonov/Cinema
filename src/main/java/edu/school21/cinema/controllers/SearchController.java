package edu.school21.cinema.controllers;

import edu.school21.cinema.mapper.ResponseMapper;
import edu.school21.cinema.models.Film;
import edu.school21.cinema.models.Session;
import edu.school21.cinema.services.SessionService;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping(value = "/sessions")
public class SearchController {
    private final SessionService sessionService;
    private final ResponseMapper mapper;

    public SearchController(SessionService sessionService, ResponseMapper mapper) {
        this.sessionService = sessionService;
        this.mapper = mapper;
    }

    @GetMapping(value = "")
    public String getSearchField() {
        return "sessionsSearching";
    }

    @GetMapping(value = "/search", consumes = MediaType.ALL_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    ResponseMapper.Response searching(@ModelAttribute("filmName") String request) {
        if (request.isEmpty()) {
            return null;
        }
        List<Session> sessions = sessionService.searchByRequest(request);
        return mapper.mapToResponse(sessions);
    }

    @GetMapping(value = "/encode/{posterUrl}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.ALL_VALUE)
    public @ResponseBody
    String encodePoster(@PathVariable("posterUrl") String posterUrl) {
        if (posterUrl.isEmpty()) {
            return posterUrl;
        }
        Film film = new Film();
        film.setPoster(posterUrl);
        mapper.encodePoster(film);
        // TODO: 15.08.2022 добавить обработку ошибок
        return film.getPoster();
    }

    @GetMapping(value = "/{session-id}")
    public String getFilmInfoBySessionId(@PathVariable("session-id") Long sessionId, Model model) {
        Session session = sessionService.get(sessionId);
        mapper.encodePoster(session.getFilm());
        model.addAttribute("info", session);
        // TODO: 15.08.2022 добавить обработку ошибок
        return "filmSessionInfo";
    }
}
