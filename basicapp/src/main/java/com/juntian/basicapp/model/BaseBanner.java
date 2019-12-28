package com.juntian.basicapp.model;

import java.io.Serializable;

/**
 * @作者:TJ
 * @时间:2019/8/23
 * @描述:
 */
public class BaseBanner implements Serializable {

    private String id;
    private String save_path;
    private String url;
    private String url_type;
    private String logo;

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public BaseBanner() {
    }

    public BaseBanner(String save_path) {
        this.save_path = save_path;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSave_path() {
        if (save_path == null) {
            return logo;
        }
        return save_path;
    }

    public void setSave_path(String save_path) {
        this.save_path = save_path;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUrl_type() {
        return url_type;
    }

    public void setUrl_type(String url_type) {
        this.url_type = url_type;
    }

    @Override
    public String toString() {
        return "BaseBanner{" +
                "id='" + id + '\'' +
                ", save_path='" + save_path + '\'' +
                ", url='" + url + '\'' +
                ", url_type='" + url_type + '\'' +
                '}';
    }
}
