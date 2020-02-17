import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


@JsonIgnoreProperties(ignoreUnknown = true)
public class Post {

    Integer id;
    Integer userId;
    String title;
    String body;
    String emptyStuff;

    public String getEmptyStuff() {
        return emptyStuff;
    }

    public void setEmptyStuff(String emptyStuff) {
        this.emptyStuff = emptyStuff;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }
}


