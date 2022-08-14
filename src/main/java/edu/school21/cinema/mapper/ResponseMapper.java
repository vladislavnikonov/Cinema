package edu.school21.cinema.mapper;

import com.fasterxml.jackson.annotation.JsonFormat;
import edu.school21.cinema.models.Session;
import edu.school21.cinema.services.impl.FilmServiceImpl;
import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Date;
import java.util.List;

@Component
public class ResponseMapper {
    private final String pathToImages;

    public ResponseMapper(String pathToImages) {
        this.pathToImages = pathToImages;
    }

    public Response mapToResponse(List<Session> rs) {
        List<Sessions> response = new ArrayList<>();

        if (rs.isEmpty()) {
            return new Response();
        }
        for (Session s : rs) {
            Film filmResponse = new Film(s.getFilm().getTitle(), s.getFilm().getPoster());
            Sessions sessions = new Sessions(s.getSessionId(), s.getSessionDate(), filmResponse);
            response.add(sessions);
        }
        return new Response(response);
    }

    public void encodePoster(edu.school21.cinema.models.Film film) {
        byte[] file = new byte[0];
        try {
            file = FileUtils.readFileToByteArray(new File(pathToImages +
                    FilmServiceImpl.DIR_POSTERS + "/" + film.getPoster()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        String encode = Base64.getEncoder().encodeToString(file);
        film.setPoster(encode);
    }

    static class Film {
        private String name;
        private String posterUrl;

        public Film(String name, String posterUrl) {
            this.name = name;
            this.posterUrl = posterUrl;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPosterUrl() {
            return posterUrl;
        }

        public void setPosterUrl(String posterUrl) {
            this.posterUrl = posterUrl;
        }
    }

    static class Sessions {
        private Long id;
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm")
        private Date dateTime;
        private Film film;

        public Sessions(Long id, Date dateTime, Film film) {
            this.id = id;
            this.dateTime = dateTime;
            this.film = film;
        }

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public Date getDateTime() {
            return dateTime;
        }

        public void setDateTime(Date dateTime) {
            this.dateTime = dateTime;
        }

        public Film getFilm() {
            return film;
        }

        public void setFilm(Film film) {
            this.film = film;
        }
    }

    public static class Response {
        private List<Sessions> sessions;

        public Response() {
        }

        public Response(List<Sessions> sessions) {
            this.sessions = sessions;
        }

        public List<Sessions> getSessions() {
            return sessions;
        }

        public void setSessions(List<Sessions> sessions) {
            this.sessions = sessions;
        }
    }
}
