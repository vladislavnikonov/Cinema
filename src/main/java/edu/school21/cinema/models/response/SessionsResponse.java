package edu.school21.cinema.models.response;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class SessionsResponse {
    private Long id;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm")
    private Date dateTime;

    private FilmResponse film;

    public SessionsResponse() {
    }

    public SessionsResponse(Long id, Date dateTime, FilmResponse film) {
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

    public FilmResponse getFilm() {
        return film;
    }

    public void setFilm(FilmResponse film) {
        this.film = film;
    }
}
