package edu.school21.cinema.mapper;

import edu.school21.cinema.models.Film;
import edu.school21.cinema.models.Session;
import edu.school21.cinema.models.response.FilmResponse;
import edu.school21.cinema.models.response.SessionsResponse;
import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

@Component
public class SessionResponseMapper {

    private final String pathToImages;

    public SessionResponseMapper(String pathToImages) {
        this.pathToImages = pathToImages;
    }

    public List<SessionsResponse> mapToResponse(List<Session> rs) {
        List<SessionsResponse> response = new ArrayList<>();

        if(rs.isEmpty()) {
            return response;
        }
        for(Session s : rs) {
            SessionsResponse sessionsResponse = new SessionsResponse();
            sessionsResponse.setId(s.getSessionId());
            sessionsResponse.setDateTime(s.getSessionDate());

            FilmResponse filmResponse = new FilmResponse(s.getFilm().getTitle(), s.getFilm().getPoster());
            byte[] file = new byte[0];
            try {
                file = FileUtils.readFileToByteArray(new File(pathToImages +
                        filmResponse.getName() + "/" + filmResponse.getPosterUrl()));
            } catch (IOException e) {
                e.printStackTrace();
            }
            String encode = Base64.getEncoder().encodeToString(file);
            filmResponse.setPosterUrl(encode);
            sessionsResponse.setFilm(filmResponse);

            response.add(sessionsResponse);
        }
        return response;
    }

    public void encodePoster(Film film) {
        byte[] file = new byte[0];
        try {
            file = FileUtils.readFileToByteArray(new File(pathToImages +
                    film.getTitle() + "/" + film.getPoster()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        String encode = Base64.getEncoder().encodeToString(file);
        film.setPoster(encode);
    }
}
