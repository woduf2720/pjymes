package com.example.pjymes.security.exception;

import com.google.gson.Gson;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.MediaType;

import java.io.IOException;
import java.util.Date;
import java.util.Map;

@Log4j2
public class AccessTokenException extends RuntimeException{
    TOKEN_ERROR token_error;

    public enum TOKEN_ERROR {
        UNACCEPT(401, "TOKEN is null or too short"),
        BADTYPE(401, "TOKEN type Bearer"),
        MALFORM(403, "Malformed Token"),
        EXPIRED(403, "Expired Token");

        private int status;
        private String msg;

        TOKEN_ERROR(int status, String msg){
            this.status = status;
            this.msg = msg;
        }

        public int getStatus() {
            return this.status;
        }

        public String getMsg() {
            return this.msg;
        }
    }

    public AccessTokenException(TOKEN_ERROR error) {
        super(error.name());
        this.token_error = error;
    }

    public void sendResponseError(HttpServletResponse response) {
        response.setStatus(token_error.getStatus());
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);

        Gson gson = new Gson();

        String responseStr = gson.toJson(Map.of(
                "msg", token_error.getMsg(),
                "time", new Date())
        );

        try {
            response.getWriter().println(responseStr);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
