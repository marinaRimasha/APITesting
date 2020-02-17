package my.reqres;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


@JsonIgnoreProperties(ignoreUnknown = true)
public class UserPage {
    public User[] getData() {
        return data;
    }
    Integer total_pages;

    public Integer getTotal_pages() {
        return total_pages;
    }

    public void setTotal_pages(Integer total_pages) {
        this.total_pages = total_pages;
    }

    public void setData(User[] data) {
        this.data = data;
    }

    User[] data;


}


