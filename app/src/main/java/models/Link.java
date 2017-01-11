package models;

/**
 * Created by thiagoyf on 1/11/17.
 */

public class Link {
    private Integer id;
    private String title;
    private String category;
    private String url;
    private int status;

    // Default
    public Link(){

    }

    public Link(Integer id, String title, String category, String url, int status) {
        this.id = id;
        this.title = title;
        this.category = category;
        this.url = url;
        this.status = status;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getStatus(){
        return status;
    }

    public void setStatus(int status){
        this.status = status;
    }
}
