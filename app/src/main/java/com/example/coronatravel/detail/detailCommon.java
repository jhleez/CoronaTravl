package com.example.coronatravel.detail;

public class detailCommon {
    detailCommon(String addr1, String booktour, String firstimage, String mapx, String mapy, String mlevel,String overview, String title, String zipcode,String homepage, String tel, String telname){
        this.addr1 = addr1; //주소
        this.booktour=booktour; // 교과서 여행지 여부
        this.firstimage=firstimage; // 썸네일 이미지
        this.mapx=mapx; // GPS X좌표(WGS84 경도 좌표)
        this.mapy=mapy; // GPS Y좌표(WGS84 위도 좌표)
        this.mlevel=mlevel; // Map Level
        this.overview=overview; // 개요
        this.title=title; // 이름
        this.zipcode=zipcode; // 우편번호
        this.homepage = homepage;
        this.tel = tel;
        this.telname = telname;

    }
    private String homepage;
    private String tel;
    private String telname;
    private String addr1;
    private String booktour;
    private String firstimage;
    private String mapx;
    private String mapy;
    private String mlevel;
    private String overview;
    private String title;
    private String zipcode;

    public String getAddr1() {
        return addr1;
    }

    public void setAddr1(String addr1) {
        this.addr1 = addr1;
    }

    public String getBooktour() {
        return booktour;
    }

    public void setBooktour(String booktour) {
        this.booktour = booktour;
    }

    public String getFirstimage() {
        return firstimage;
    }

    public void setFirstimage(String firstimage) {
        this.firstimage = firstimage;
    }

    public String getMapx() {
        return mapx;
    }

    public void setMapx(String mapx) {
        this.mapx = mapx;
    }

    public String getMapy() {
        return mapy;
    }

    public void setMapy(String mapy) {
        this.mapy = mapy;
    }

    public String getMlevel() {
        return mlevel;
    }

    public void setMlevel(String mlevel) {
        this.mlevel = mlevel;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    public String getHomepage() {
        return homepage;
    }

    public void setHomepage(String homepage) {
        this.homepage = homepage;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getTel_name() {
        return telname;
    }

    public void setTel_name(String tel_name) {
        this.telname = tel_name;
    }
}
