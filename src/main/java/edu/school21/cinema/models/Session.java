package edu.school21.cinema.models;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "cinema_sessions")
public class Session {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long sessionId;
    @Column(name = "ticket_cost")
    private Integer ticketCost;
    @Column(name = "session_date")
    private Date sessionDate;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "hall_id", referencedColumnName = "id")
    private Hall hall;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "film_id", referencedColumnName = "id")
    private Film film;

    public Session() {
    }

    public Session(Long sessionId, Integer ticketCost, Date sessionDate, Hall hall, Film film) {
        this.sessionId = sessionId;
        this.ticketCost = ticketCost;
        this.sessionDate = sessionDate;
        this.hall = hall;
        this.film = film;
    }

    public Session(Integer ticketCost, Date date) {
        this.ticketCost = ticketCost;
        this.sessionDate = date;
    }

    public Long getSessionId() {
        return sessionId;
    }

    public void setSessionId(Long sessionId) {
        this.sessionId = sessionId;
    }

    public Integer getTicketCost() {
        return ticketCost;
    }

    public void setTicketCost(Integer ticketCost) {
        this.ticketCost = ticketCost;
    }

    public Date getSessionDate() {
        return sessionDate;
    }

    public void setSessionDate(Date sessionDate) {
        this.sessionDate = sessionDate;
    }

    public Hall getHall() {
        return hall;
    }

    public void setHall(Hall hall) {
        this.hall = hall;
    }

    public Film getFilm() {
        return film;
    }

    public void setFilm(Film film) {
        this.film = film;
    }
}
