package edu.school21.cinema.controllers;

import edu.school21.cinema.mapper.SessionResponseMapper;
import edu.school21.cinema.models.Session;
import edu.school21.cinema.models.response.BaseResponse;
import edu.school21.cinema.services.SessionService;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.awt.*;
import java.util.List;

@Controller
public class SearchController {
    private SessionService sessionService;
    private SessionResponseMapper mapper;

    @GetMapping(value = "/")
    public String getSearchField() {
        return "sessionsSearching";
    }

    @GetMapping(value = "/search", consumes = MediaType.ALL_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody BaseResponse searching(@ModelAttribute("filmName") String request) {
        if (request.isEmpty()) {
            return null;
        }
        List<Session> serviceResponse = sessionService.searchByRequest(request);
        return new BaseResponse(mapper.mapToResponse(serviceResponse));
    }

    @GetMapping(value = "/{session-id}")
    public String getFilmInfoBySessionId(@PathVariable("session-id") Long sessionId, Model model) {
        Session session = sessionService.get(sessionId);
        mapper.encodePoster(session.getFilm());
        model.addAttribute("info", session);
        return "filmSessionInfo";
    }
}
