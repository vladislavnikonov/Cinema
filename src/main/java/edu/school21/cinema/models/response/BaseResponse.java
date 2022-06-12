package edu.school21.cinema.models.response;

import java.util.List;

public class BaseResponse {
    private List<SessionsResponse> sessions;

    public BaseResponse() {
    }

    public BaseResponse(List<SessionsResponse> sessions) {
        this.sessions = sessions;
    }

    public List<SessionsResponse> getSessions() {
        return sessions;
    }

    public void setSessions(List<SessionsResponse> sessions) {
        this.sessions = sessions;
    }
}
