import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.time.Instant;


@JsonIgnoreProperties(ignoreUnknown = true)
public class UserResponse {

    Integer id;

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    String error;
    String createdAt;
    String name;
    String job;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    String token;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }
}


