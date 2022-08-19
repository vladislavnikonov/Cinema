package edu.school21.cinema.models;

import javax.persistence.*;

@Entity(name = "Film")
@Table(name = "cinema_films")
public class Film {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long filmId;
    @Column(name = "title")
    private String title;
    @Column(name = "release_year")
    private Integer releaseYear;
    @Column(name = "age_restrictions")
    private Integer ageRegistration;
    @Column(name = "description")
    private String description;
    @Column(name = "poster")
    private String poster;

    public Film() {
    }

    public Film(String title, Integer releaseYear, Integer ageRegistration, String description, String poster) {
        this.title = title;
        this.releaseYear = releaseYear;
        this.ageRegistration = ageRegistration;
        this.description = description;
        this.poster = poster;
    }

    public Long getFilmId() {
        return filmId;
    }

    public void setFilmId(Long filmId) {
        this.filmId = filmId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getReleaseYear() {
        return releaseYear;
    }

    public void setReleaseYear(Integer releaseYear) {
        this.releaseYear = releaseYear;
    }

    public Integer getAgeRegistration() {
        return ageRegistration;
    }

    public void setAgeRegistration(Integer ageRegistration) {
        this.ageRegistration = ageRegistration;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPoster() {
        return poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }
}
