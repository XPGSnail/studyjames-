package cn.studyjams.s2.sj0131.snail.entity;

import java.util.List;

/**
 * Created by hasee on 2017/5/11.
 */

public class Android {

    /**
     * _id : 5913cd08421aa90c7d49ad80
     * createdAt : 2017-05-11T10:31:36.254Z
     * desc : 找到阻碍你 Android App 性能的罪魁祸首！
     * images : ["http://img.gank.io/7bef123d-8055-47de-a1d0-f70e69b9430d"]
     * publishedAt : 2017-05-11T12:03:09.581Z
     * source : chrome
     * type : Android
     * url : https://github.com/seiginonakama/BlockCanaryEx
     * used : true
     * who : 代码家
     */

    private String _id;
    private String createdAt;
    private String desc;
    private String publishedAt;
    private String source;
    private String type;
    private String url;
    private boolean used;
    private String who;
    private List<String> images;

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getPublishedAt() {
        return publishedAt;
    }

    public void setPublishedAt(String publishedAt) {
        this.publishedAt = publishedAt;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public boolean isUsed() {
        return used;
    }

    public void setUsed(boolean used) {
        this.used = used;
    }

    public String getWho() {
        return who;
    }

    public void setWho(String who) {
        this.who = who;
    }

    public List<String> getImages() {
        return images;
    }

    public void setImages(List<String> images) {
        this.images = images;
    }
}
