package com.example.charity.Model;

public class post {
    private String postid;
    private String postimage;
    private String description;
    private String location;
    private String catagory;

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    private String publisher;

    public post(String postid, String postimage, String description, String location, String catagory, String publisher) {
        this.postid = postid;
        this.postimage = postimage;
        this.description = description;
        this.location = location;
        this.catagory = catagory;
        this.publisher = publisher;
    }

    public post() {
    }

    public String getPostid() {
        return postid;
    }

    public void setPostid(String postid) {
        this.postid = postid;
    }

    public String getPostimage() {
        return postimage;
    }

    public void setPostimage(String postimage) {
        this.postimage = postimage;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getCatagory() {
        return catagory;
    }

    public void setCatagory(String catagory) {
        this.catagory = catagory;
    }
}

