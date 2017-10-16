package edu.umkc.ch043097.freshstart;


public class Job {
    private String location;
    private String title;
    private String description;

    public Job(String l, String t, String d){
        location = l;
        title = t;
        description = d;
    }

    public void setAll(String l, String t, String d){
        location = l;
        title = t;
        description = d;
    }

    public String getLocation(){
        return location;
    }

    public String getTitle(){
        return title;
    }

    public String getDescription(){
        return description;
    }

    public void setLocation(String l){
        location = l;
    }

    public void setTitle(String t){
        title = t;
    }

    public void setDescription(String d){
        description = d;
    }

    public String toString(){
        return "Location: " + location + ", Title: " + title + ", Description: " + description;
    }

}
