package com.example.jzzq.main.Information.bean;

import java.util.List;

public class InforamtionBean {

    /**
     * data : [{"createTime":"2018-03-02","createTimeDate":"2018-03-02T16:33:52+08:00","id":149,"introduce":"　","pic":"http://img.cdn.meikejob.com/campus_life/article_icon/HR ：xueshenghuideganbu，womenbuganyao！_1519979622906.jpg","praiseNum":0,"praiseState":0,"publisherIcon":"http://img.cdn.meikejob.com/campus_life/publisher_icon/yuan_1512550063346.jpg","publisherName":"语安","title":"HR ：学生会的干部，我们不敢要！"},{"createTime":"2018-02-06","createTimeDate":"2018-02-06T16:13:00+08:00","id":144,"introduce":"你还在低层次的加班吗？","pic":"http://img.cdn.meikejob.com/campus_life/article_icon/meigezhichangrendouyingzhidaoguanyu\u201cjiaban\u200b\u201ddemimi_1517904773779.jpg","praiseNum":0,"praiseState":0,"publisherIcon":"http://img.cdn.meikejob.com/campus_life/publisher_icon/fangtou_1508825300907.jpg","publisherName":"方头","title":"每个职场人都应知道关于\u201c加班\u200b\u201d的秘密"},{"createTime":"2018-02-06","createTimeDate":"2018-02-06T16:10:19+08:00","id":142,"introduce":"哪怕是实习小白，也要努力成为发光的那个人啊！","pic":"http://img.cdn.meikejob.com/campus_life/article_icon/shixiyaozuoshenmecaisuanxuedaodongxi？_1517904612280.jpg","praiseNum":0,"praiseState":0,"publisherIcon":"http://img.cdn.meikejob.com/campus_life/publisher_icon/mitangqinqin_1512550142725.jpg","publisherName":"蜜糖亲亲","title":"实习要做什么才算学到东西？"},{"createTime":"2018-02-01","createTimeDate":"2018-02-01T16:59:13+08:00","id":138,"introduce":"干货","pic":"http://img.cdn.meikejob.com/campus_life/article_icon/mianshiduocidanyimianjiugua，zenmepo？_1517475548428.jpg","praiseNum":0,"praiseState":0,"publisherIcon":"http://img.cdn.meikejob.com/campus_life/publisher_icon/liyi_1510650598881.jpg","publisherName":"李奕","title":"面试多次但一面就挂，怎么破？"},{"createTime":"2018-01-29","createTimeDate":"2018-01-29T15:26:05+08:00","id":136,"introduce":"这件单品冬天可以代替白衬衫","pic":"http://img.cdn.meikejob.com/campus_life/article_icon/zhichangnvhaidongtianchuanshenmeneidagaojishushiyoubaonuan？_1517210759912.jpg","praiseNum":0,"praiseState":0,"publisherIcon":"http://img.cdn.meikejob.com/campus_life/publisher_icon/mitangqinqin_1512550142725.jpg","publisherName":"蜜糖亲亲","title":"职场女孩冬天穿什么内搭高级舒适又保暖？"},{"createTime":"2018-01-23","createTimeDate":"2018-01-23T17:50:31+08:00","id":132,"introduce":"5类京东综合价值观和能力量化标准，你知道几个？","pic":"http://img.cdn.meikejob.com/campus_life/article_icon/liuqiangdong：youzhei7zhongtezhideren，woyidingzhongyong！_1516701025564.jpg","praiseNum":0,"praiseState":0,"publisherIcon":"http://img.cdn.meikejob.com/campus_life/publisher_icon/zhouxingxing_1510283230492.jpg","publisherName":"周星星","title":"刘强东：有这7种特质的人，我一定重用！"},{"createTime":"2018-01-19","createTimeDate":"2018-01-19T14:49:47+08:00","id":127,"introduce":"干货","pic":"http://img.cdn.meikejob.com/campus_life/article_icon/zhei8zhongshixishengdezhuanzhengjilvweiling_1516344580559.jpg","praiseNum":0,"praiseState":0,"publisherIcon":"http://img.cdn.meikejob.com/campus_life/publisher_icon/xingxingcao_1512549991424.jpg","publisherName":"星星草","title":"这8种实习生的转正几率为零"},{"createTime":"2018-01-17","createTimeDate":"2018-01-17T18:07:36+08:00","id":126,"introduce":"与其混错的圈子，还不如不混。","pic":"http://img.cdn.meikejob.com/campus_life/article_icon/fangfa_1516183601483.jpg","praiseNum":0,"praiseState":0,"publisherIcon":"http://img.cdn.meikejob.com/campus_life/publisher_icon/liyi_1510650598881.jpg","publisherName":"李奕","title":"你的职场小圈子，正在杀死你的努力"}]
     * status : 0
     * msg : Success
     * runtime : 9
     */

    private int status;
    private String msg;
    private int runtime;
    private List<DataBean> data;

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

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * createTime : 2018-03-02
         * createTimeDate : 2018-03-02T16:33:52+08:00
         * id : 149
         * introduce : 　
         * pic : http://img.cdn.meikejob.com/campus_life/article_icon/HR ：xueshenghuideganbu，womenbuganyao！_1519979622906.jpg
         * praiseNum : 0
         * praiseState : 0
         * publisherIcon : http://img.cdn.meikejob.com/campus_life/publisher_icon/yuan_1512550063346.jpg
         * publisherName : 语安
         * title : HR ：学生会的干部，我们不敢要！
         */

        private String createTime;
        private String createTimeDate;
        private int id;
        private String introduce;
        private String pic;
        private int praiseNum;
        private int praiseState;
        private String publisherIcon;
        private String publisherName;
        private String title;

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

        public String getPic() {
            return pic;
        }

        public void setPic(String pic) {
            this.pic = pic;
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

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }
    }
}
