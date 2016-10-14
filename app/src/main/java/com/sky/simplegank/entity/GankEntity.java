package com.sky.simplegank.entity;

import java.util.List;

/**
 * Created by tonycheng on 2016/10/14.
 */

public class GankEntity {

    /*{
        "_id": "57e7e7fd421aa95de3b8aad9",
        "createdAt": "2016-09-25T23:06:37.257Z",
        "desc": "支持左右两边同时滚动的进度条",
        "images": [
        "http://img.gank.io/08a79d2c-d17d-41df-8e93-f8c7322ca99e"],
        "publishedAt": "2016-10-13T11:30:10.490Z",
        "source": "web",
        "type": "Android",
        "url": "https://github.com/alex5241/AwesomeProgressbar",
        "used": true,
        "who": "alex"
    }*/

    private String _id;
    private String createdAt;
    private String desc;
    private List<String> images;
    private String publishedAt;
    private String source;
    private String type;
    private String url;
    private String used;
    private String who;

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getWho() {
        return who;
    }

    public void setWho(String who) {
        this.who = who;
    }

    public String getUsed() {
        return used;
    }

    public void setUsed(String used) {
        this.used = used;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getPublishedAt() {
        return publishedAt;
    }

    public void setPublishedAt(String publishedAt) {
        this.publishedAt = publishedAt;
    }

    public List<String> getImages() {
        return images;
    }

    public void setImages(List<String> images) {
        this.images = images;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }
}
