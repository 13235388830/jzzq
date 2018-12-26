package com.ami.makemoneyby.main.Information.bean;

public class InformationActivityBean {

    /**
     * data : {"canShare":1,"contentText":"","contentType":1,"contentUrl":"https://mejianzhi.kuaizhan.com/25/90/p506228037d043a","createTime":"2018-03-02","createTimeDate":"2018-03-02T16:33:52+08:00","id":149,"introduce":"　","praiseNum":0,"praiseState":0,"publisherIcon":"http://img.cdn.meikejob.com/campus_life/publisher_icon/yuan_1512550063346.jpg","publisherName":"语安","shareObj":{"content":"　","imageURL":"http://meikejobnew.oss-cn-shanghai.aliyuncs.com/meikejob_logo/logo_180_zj.png","shareShow":true,"shareURL":"","title":"HR ：学生会的干部，我们不敢要！"},"title":"HR ：学生会的干部，我们不敢要！"}
     * status : 0
     * msg : Success
     * runtime : 2
     */

    private DataBean data;
    private int status;
    private String msg;
    private int runtime;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getRuntime() {
        return runtime;
    }

    public void setRuntime(int runtime) {
        this.runtime = runtime;
    }

    public static class DataBean {
        /**
         * canShare : 1
         * contentText :
         * contentType : 1
         * contentUrl : https://mejianzhi.kuaizhan.com/25/90/p506228037d043a
         * createTime : 2018-03-02
         * createTimeDate : 2018-03-02T16:33:52+08:00
         * id : 149
         * introduce : 　
         * praiseNum : 0
         * praiseState : 0
         * publisherIcon : http://img.cdn.meikejob.com/campus_life/publisher_icon/yuan_1512550063346.jpg
         * publisherName : 语安
         * shareObj : {"content":"　","imageURL":"http://meikejobnew.oss-cn-shanghai.aliyuncs.com/meikejob_logo/logo_180_zj.png","shareShow":true,"shareURL":"","title":"HR ：学生会的干部，我们不敢要！"}
         * title : HR ：学生会的干部，我们不敢要！
         */

        private int canShare;
        private String contentText;
        private int contentType;
        private String contentUrl;
        private String createTime;
        private String createTimeDate;
        private int id;
        private String introduce;
        private int praiseNum;
        private int praiseState;
        private String publisherIcon;
        private String publisherName;
        private ShareObjBean shareObj;
        private String title;

        public int getCanShare() {
            return canShare;
        }

        public void setCanShare(int canShare) {
            this.canShare = canShare;
        }

        public String getContentText() {
            return contentText;
        }

        public void setContentText(String contentText) {
            this.contentText = contentText;
        }

        public int getContentType() {
            return contentType;
        }

        public void setContentType(int contentType) {
            this.contentType = contentType;
        }

        public String getContentUrl() {
            return contentUrl;
        }

        public void setContentUrl(String contentUrl) {
            this.contentUrl = contentUrl;
        }

        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }

        public String getCreateTimeDate() {
            return createTimeDate;
        }

        public void setCreateTimeDate(String createTimeDate) {
            this.createTimeDate = createTimeDate;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getIntroduce() {
            return introduce;
        }

        public void setIntroduce(String introduce) {
            this.introduce = introduce;
        }

        public int getPraiseNum() {
            return praiseNum;
        }

        public void setPraiseNum(int praiseNum) {
            this.praiseNum = praiseNum;
        }

        public int getPraiseState() {
            return praiseState;
        }

        public void setPraiseState(int praiseState) {
            this.praiseState = praiseState;
        }

        public String getPublisherIcon() {
            return publisherIcon;
        }

        public void setPublisherIcon(String publisherIcon) {
            this.publisherIcon = publisherIcon;
        }

        public String getPublisherName() {
            return publisherName;
        }

        public void setPublisherName(String publisherName) {
            this.publisherName = publisherName;
        }

        public ShareObjBean getShareObj() {
            return shareObj;
        }

        public void setShareObj(ShareObjBean shareObj) {
            this.shareObj = shareObj;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public static class ShareObjBean {
            /**
             * content : 　
             * imageURL : http://meikejobnew.oss-cn-shanghai.aliyuncs.com/meikejob_logo/logo_180_zj.png
             * shareShow : true
             * shareURL :
             * title : HR ：学生会的干部，我们不敢要！
             */

            private String content;
            private String imageURL;
            private boolean shareShow;
            private String shareURL;
            private String title;

            public String getContent() {
                return content;
            }

            public void setContent(String content) {
                this.content = content;
            }

            public String getImageURL() {
                return imageURL;
            }

            public void setImageURL(String imageURL) {
                this.imageURL = imageURL;
            }

            public boolean isShareShow() {
                return shareShow;
            }

            public void setShareShow(boolean shareShow) {
                this.shareShow = shareShow;
            }

            public String getShareURL() {
                return shareURL;
            }

            public void setShareURL(String shareURL) {
                this.shareURL = shareURL;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }
        }
    }

}
