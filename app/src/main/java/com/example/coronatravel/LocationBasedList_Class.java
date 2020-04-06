package com.example.coronatravel;

public class LocationBasedList_Class {

    LocationBasedList_Class(String addr1,String contentid,String contenttypeid,String dist,String firstimage,String title)
    {
        this.addr1 =addr1;
        this.contentid=contentid;
        this.contenttypeid=contenttypeid;
        this.dist=dist;
        this.firstimage=firstimage;
        this.title=title;
    }

    private String addr1;
    private String contentid;
    private String contenttypeid;
    private String dist;
    private String firstimage;
    private String title;


    public String getAddr1() {
        return addr1;
    }

    public void setAddr1(String addr1) {
        this.addr1 = addr1;
    }

    public String getContentid() {
        return contentid;
    }

    public void setContentid(String contentid) {
        this.contentid = contentid;
    }

    public String getContenttypeid() {
        return contenttypeid;
    }

    public void setContenttypeid(String contenttypeid) {
        this.contenttypeid = contenttypeid;
    }

    public String getDist() {
        return dist;
    }

    public void setDist(String dist) {
        this.dist = dist;
    }

    public String getFirstimage() {
        return firstimage;
    }

    public void setFirstimage(String firstimage) {
        this.firstimage = firstimage;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

}
