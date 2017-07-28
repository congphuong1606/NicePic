package ominext.android.vn.nicepic.data.model;

import java.io.Serializable;

/**
 * Created by MyPC on 26/07/2017.
 */

public class Pic implements Serializable {
    String picId;
    String picUser;
    String picUrl;
    String picName;
    String picDescription;
    String picTimeUpdate;
    int picCmt;
    int picLike;

    public Pic(String picId, String picUser, String picUrl, String picName,
               String picDescription, String picTimeUpdate, int picCmt, int picLike) {
        this.picId = picId;
        this.picUser = picUser;
        this.picUrl = picUrl;
        this.picName = picName;
        this.picDescription = picDescription;
        this.picTimeUpdate = picTimeUpdate;
        this.picCmt = picCmt;
        this.picLike = picLike;
    }

    public String getPicId() {
        return picId;
    }

    public void setPicId(String picId) {
        this.picId = picId;
    }

    public String getPicUser() {
        return picUser;
    }

    public void setPicUser(String picUser) {
        this.picUser = picUser;
    }

    public String getPicName() {
        return picName;
    }

    public void setPicName(String picName) {
        this.picName = picName;
    }

    public String getPicDescription() {
        return picDescription;
    }

    public void setPicDescription(String picDescription) {
        this.picDescription = picDescription;
    }

    public String getPicTimeUpdate() {
        return picTimeUpdate;
    }

    public void setPicTimeUpdate(String picTimeUpdate) {
        this.picTimeUpdate = picTimeUpdate;
    }

    public String getPicUrl() {
        return picUrl;
    }

    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl;
    }

    public Pic() {
    }

    public int getPicCmt() {
        return picCmt;
    }

    public void setPicCmt(int picCmt) {
        this.picCmt = picCmt;
    }

    public int getPicLike() {
        return picLike;
    }

    public void setPicLike(int picLike) {
        this.picLike = picLike;
    }
}