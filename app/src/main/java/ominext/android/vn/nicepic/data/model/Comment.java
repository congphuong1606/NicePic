package ominext.android.vn.nicepic.data.model;

/**
 * Created by MyPC on 28/07/2017.
 */

public class Comment {
    private String cmtId;
    private String cmtContent;
    private String cmtTime;
    private String cmtPic;
    private String cmtUser;

    public Comment(String cmtId, String cmtContent, String cmtTime, String cmtPic, String cmtUser) {
        this.cmtId = cmtId;
        this.cmtContent = cmtContent;
        this.cmtTime = cmtTime;
        this.cmtPic = cmtPic;
        this.cmtUser = cmtUser;
    }

    public Comment() {
    }

    public String getCmtId() {
        return cmtId;
    }

    public void setCmtId(String cmtId) {
        this.cmtId = cmtId;
    }

    public String getCmtContent() {
        return cmtContent;
    }

    public void setCmtContent(String cmtContent) {
        this.cmtContent = cmtContent;
    }

    public String getCmtTime() {
        return cmtTime;
    }

    public void setCmtTime(String cmtTime) {
        this.cmtTime = cmtTime;
    }

    public String getCmtPic() {
        return cmtPic;
    }

    public void setCmtPic(String cmtPic) {
        this.cmtPic = cmtPic;
    }

    public String getCmtUser() {
        return cmtUser;
    }

    public void setCmtUser(String cmtUser) {
        this.cmtUser = cmtUser;
    }
}
