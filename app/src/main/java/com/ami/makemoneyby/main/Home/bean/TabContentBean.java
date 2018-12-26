package com.ami.makemoneyby.main.Home.bean;

import java.util.List;

public class TabContentBean {

    /**
     * Success : true
     * Msg : 成功
     * ResultData : [{"ClassId":8,"ProductImg":"http://bulang.oss-cn-shenzhen.aliyuncs.com/2018/10/20/0ca66387964f74b0c2d2c2fb831519c3.jpg","ProductName":"跨境爆款花洒沐浴器净水器淋浴净化器除余氯过滤器 Shower Filter","ProductPrice":80,"DealNum":6,"IsCollage":false,"CollageName":"","ClassName":"日用","ProductId":412,"CommissionList":[{"UserLevel":1,"Commission":23},{"UserLevel":2,"Commission":2.3},{"UserLevel":3,"Commission":1.1}],"Furl":"http://shop.hudatech.cn/pages/goodsDetail.html?productId=412&SharedUserId="},{"ClassId":8,"ProductImg":"http://bulang.oss-cn-shenzhen.aliyuncs.com/2018/10/20/38b64d27f4b831adeaa17a912021e4fd.jpg","ProductName":"电镀家用净水器 厨房前置净化器水龙头过滤器家用OEM贴牌加工","ProductPrice":99,"DealNum":4,"IsCollage":false,"CollageName":"","ClassName":"日用","ProductId":411,"CommissionList":[{"UserLevel":1,"Commission":27},{"UserLevel":2,"Commission":2.7},{"UserLevel":3,"Commission":1.3}],"Furl":"http://shop.hudatech.cn/pages/goodsDetail.html?productId=411&SharedUserId="},{"ClassId":8,"ProductImg":"http://bulang.oss-cn-shenzhen.aliyuncs.com/2018/10/20/604495444c9eeb2875f53731d1fa3046.jpg","ProductName":"台式三级净水器厨房自来水直饮家用 多级过滤器OEM贴牌","ProductPrice":300,"DealNum":5,"IsCollage":false,"CollageName":"","ClassName":"日用","ProductId":410,"CommissionList":[{"UserLevel":1,"Commission":76},{"UserLevel":2,"Commission":7.6},{"UserLevel":3,"Commission":3.8}],"Furl":"http://shop.hudatech.cn/pages/goodsDetail.html?productId=410&SharedUserId="},{"ClassId":8,"ProductImg":"http://bulang.oss-cn-shenzhen.aliyuncs.com/2018/10/20/23f6ba45feb9a2950673797e38051d2d.jpg","ProductName":"10-15层过滤套装花洒沐浴器净水器滤水器除余氯过滤器贴牌","ProductPrice":80,"DealNum":4,"IsCollage":false,"CollageName":"","ClassName":"日用","ProductId":409,"CommissionList":[{"UserLevel":1,"Commission":17},{"UserLevel":2,"Commission":1.7},{"UserLevel":3,"Commission":0.8}],"Furl":"http://shop.hudatech.cn/pages/goodsDetail.html?productId=409&SharedUserId="}]
     * RowCount : 0
     * ErrNum : 0
     */

    private boolean Success;
    private String Msg;
    private int RowCount;
    private int ErrNum;
    private List<ResultDataBean> ResultData;

    public boolean isSuccess() {
        return Success;
    }

    public void setSuccess(boolean Success) {
        this.Success = Success;
    }

    public String getMsg() {
        return Msg;
    }

    public void setMsg(String Msg) {
        this.Msg = Msg;
    }

    public int getRowCount() {
        return RowCount;
    }

    public void setRowCount(int RowCount) {
        this.RowCount = RowCount;
    }

    public int getErrNum() {
        return ErrNum;
    }

    public void setErrNum(int ErrNum) {
        this.ErrNum = ErrNum;
    }

    public List<ResultDataBean> getResultData() {
        return ResultData;
    }

    public void setResultData(List<ResultDataBean> ResultData) {
        this.ResultData = ResultData;
    }

    public static class ResultDataBean {
        /**
         * ClassId : 8
         * ProductImg : http://bulang.oss-cn-shenzhen.aliyuncs.com/2018/10/20/0ca66387964f74b0c2d2c2fb831519c3.jpg
         * ProductName : 跨境爆款花洒沐浴器净水器淋浴净化器除余氯过滤器 Shower Filter
         * ProductPrice : 80.0
         * DealNum : 6
         * IsCollage : false
         * CollageName :
         * ClassName : 日用
         * ProductId : 412
         * CommissionList : [{"UserLevel":1,"Commission":23},{"UserLevel":2,"Commission":2.3},{"UserLevel":3,"Commission":1.1}]
         * Furl : http://shop.hudatech.cn/pages/goodsDetail.html?productId=412&SharedUserId=
         */

        private int ClassId;
        private String ProductImg;
        private String ProductName;
        private double ProductPrice;
        private int DealNum;
        private boolean IsCollage;
        private String CollageName;
        private String ClassName;
        private int ProductId;
        private String Furl;
        private List<CommissionListBean> CommissionList;

        public int getClassId() {
            return ClassId;
        }

        public void setClassId(int ClassId) {
            this.ClassId = ClassId;
        }

        public String getProductImg() {
            return ProductImg;
        }

        public void setProductImg(String ProductImg) {
            this.ProductImg = ProductImg;
        }

        public String getProductName() {
            return ProductName;
        }

        public void setProductName(String ProductName) {
            this.ProductName = ProductName;
        }

        public double getProductPrice() {
            return ProductPrice;
        }

        public void setProductPrice(double ProductPrice) {
            this.ProductPrice = ProductPrice;
        }

        public int getDealNum() {
            return DealNum;
        }

        public void setDealNum(int DealNum) {
            this.DealNum = DealNum;
        }

        public boolean isIsCollage() {
            return IsCollage;
        }

        public void setIsCollage(boolean IsCollage) {
            this.IsCollage = IsCollage;
        }

        public String getCollageName() {
            return CollageName;
        }

        public void setCollageName(String CollageName) {
            this.CollageName = CollageName;
        }

        public String getClassName() {
            return ClassName;
        }

        public void setClassName(String ClassName) {
            this.ClassName = ClassName;
        }

        public int getProductId() {
            return ProductId;
        }

        public void setProductId(int ProductId) {
            this.ProductId = ProductId;
        }

        public String getFurl() {
            return Furl;
        }

        public void setFurl(String Furl) {
            this.Furl = Furl;
        }

        public List<CommissionListBean> getCommissionList() {
            return CommissionList;
        }

        public void setCommissionList(List<CommissionListBean> CommissionList) {
            this.CommissionList = CommissionList;
        }

        public static class CommissionListBean {
            /**
             * UserLevel : 1
             * Commission : 23.0
             */

            private int UserLevel;
            private double Commission;

            public int getUserLevel() {
                return UserLevel;
            }

            public void setUserLevel(int UserLevel) {
                this.UserLevel = UserLevel;
            }

            public double getCommission() {
                return Commission;
            }

            public void setCommission(double Commission) {
                this.Commission = Commission;
            }
        }
    }

}
