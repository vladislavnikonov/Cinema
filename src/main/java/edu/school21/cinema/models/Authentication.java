package edu.school21.cinema.models;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "cinema_authentications")
public class Authentication {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;
    @Column(name = "authentications_date")
    private LocalDateTime authenticationsDate;
    @Column(name = "ip_address")
    private String ipAddress;

    public Authentication(User user, LocalDateTime authenticationsDate, String ipAddress) {
        this.user = user;
        this.authenticationsDate = authenticationsDate;
        this.ipAddress = ipAddress;
    }

    public Authentication() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public LocalDateTime getAuthenticationsDate() {
        return authenticationsDate;
    }

    public void setAuthenticationsDate(LocalDateTime authenticationsDate) {
        this.authenticationsDate = authenticationsDate;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Authentication that = (Authentication) o;
        return Objects.equals(id, that.id) && Objects.equals(user, that.user) && Objects.equals(authenticationsDate, that.authenticationsDate) && Objects.equals(ipAddress, that.ipAddress);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, user, authenticationsDate, ipAddress);
    }

    @Override
    public String toString() {
        return "Authentication{" +
                "id=" + id +
                ", user=" + user +
                ", authenticationsDate=" + authenticationsDate +
                ", ipAddress='" + ipAddress + '\'' +
                '}';
    }
}
