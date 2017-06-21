package ru.yalymar.mapping.model.models;

/**
 * @author slavalymar
 * @since 19.06.2017
 * @version 1
 */
public class Image {

    private int id;
    private String url;
    private Ad ad;

    public Image() {
    }

    public Image(String url) {
        this.url = url;
    }

    public Image(String url, Ad ad) {
        this.url = url;
        this.ad = ad;
    }

    public Ad getAd() {
        return ad;
    }

    public void setAd(Ad ad) {
        this.ad = ad;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
