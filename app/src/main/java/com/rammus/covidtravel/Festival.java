package com.rammus.covidtravel;

public class Festival {
    public Festival(String addr1, String areacode, String eventenddate, String eventstartdate, String firstimage, String title, String contentid, String contenttypeid, String overview) {
        this.addr1 = addr1;
        this.areacode = areacode;
        this.eventenddate = eventenddate;
        this.eventstartdate = eventstartdate;
        this.firstimage = firstimage;
        this.title = title;
        this.contentid = contentid;
        this.contenttypeid = contenttypeid;
        this.overview = overview;
    }

    String addr1;
    String areacode;
    String eventenddate;
    String eventstartdate;
    String firstimage;
    String title;
    String contentid;
    String contenttypeid;
    String overview;

    public String getAddr1() {
        return addr1;
    }

    public void setAddr1(String addr1) {
        this.addr1 = addr1;
    }

    public String getAreacode() {
        return areacode;
    }

    public void setAreacode(String areacode) {
        this.areacode = areacode;
    }

    public String getEventenddate() {
        return eventenddate;
    }

    public void setEventenddate(String eventenddate) {
        this.eventenddate = eventenddate;
    }

    public String getEventstartdate() {
        return eventstartdate;
    }

    public void setEventstartdate(String eventstartdate) {
        this.eventstartdate = eventstartdate;
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

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }
}
