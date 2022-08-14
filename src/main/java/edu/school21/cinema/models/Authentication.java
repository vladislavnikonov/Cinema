package edu.school21.cinema.models;

import java.util.Date;
import java.util.Objects;

public class Authentication {
    private Long id;
    private User user;
    private Date authenticationsDate;
    private String ipAddress;

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

    public Date getAuthenticationsDate() {
        return authenticationsDate;
    }

    public void setAuthenticationsDate(Date authenticationsDate) {
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
