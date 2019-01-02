package com.example.jzzq.main.Home.bean;

public class HomeActivityBean {
    /**
     * code : 0
     * data : {"detail":{"id":6,"title":"奢侈品店铺兼职北京首创奥特莱斯","type":"销售","city":"北京","area":"房山","salary":"22.0元/小时","date":"2018-08-16至2018-11-10","addr":"北京市北京房山区长阳镇悦盛路6号院首创奥特莱斯","contact":"张老师","contact_info":"{\"tel\":\"18917192741\",\"email\":\"598578956@qq.com\"}","count":"5","publisher":"上海新绿外包服务公司","req":"{\"语言\":\"普通话\",\"工作经验\":\"经验不限\",\"擅长类型\":\"擅长销售电子产品\"}","sex":"不限","time":"不限","detail":"在校本科生，大专或研究生；\n女生身高160cm以上，男生身高175cm以上；\n形象气质阳光积极、富有朝气；\n性格开朗，具较强的沟通能力；\n每周4天（包括双休日）"}}
     */

    private int code;
    private DataBean data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * detail : {"id":6,"title":"奢侈品店铺兼职北京首创奥特莱斯","type":"销售","city":"北京","area":"房山","salary":"22.0元/小时","date":"2018-08-16至2018-11-10","addr":"北京市北京房山区长阳镇悦盛路6号院首创奥特莱斯","contact":"张老师","contact_info":"{\"tel\":\"18917192741\",\"email\":\"598578956@qq.com\"}","count":"5","publisher":"上海新绿外包服务公司","req":"{\"语言\":\"普通话\",\"工作经验\":\"经验不限\",\"擅长类型\":\"擅长销售电子产品\"}","sex":"不限","time":"不限","detail":"在校本科生，大专或研究生；\n女生身高160cm以上，男生身高175cm以上；\n形象气质阳光积极、富有朝气；\n性格开朗，具较强的沟通能力；\n每周4天（包括双休日）"}
         */

        private DetailBean detail;

        public DetailBean getDetail() {
            return detail;
        }

        public void setDetail(DetailBean detail) {
            this.detail = detail;
        }

        public static class DetailBean {
            /**
             * id : 6
             * title : 奢侈品店铺兼职北京首创奥特莱斯
             * type : 销售
             * city : 北京
             * area : 房山
             * salary : 22.0元/小时
             * date : 2018-08-16至2018-11-10
             * addr : 北京市北京房山区长阳镇悦盛路6号院首创奥特莱斯
             * contact : 张老师
             * contact_info : {"tel":"18917192741","email":"598578956@qq.com"}
             * count : 5
             * publisher : 上海新绿外包服务公司
             * req : {"语言":"普通话","工作经验":"经验不限","擅长类型":"擅长销售电子产品"}
             * sex : 不限
             * time : 不限
             * detail : 在校本科生，大专或研究生；
             女生身高160cm以上，男生身高175cm以上；
             形象气质阳光积极、富有朝气；
             性格开朗，具较强的沟通能力；
             每周4天（包括双休日）
             */

            private int id;
            private String title;
            private String type;
            private String city;
            private String area;
            private String salary;
            private String date;
            private String addr;
            private String contact;
            private String contact_info;
            private String count;
            private String publisher;
            private String req;
            private String sex;
            private String time;
            private String detail;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }

            public String getCity() {
                return city;
            }

            public void setCity(String city) {
                this.city = city;
            }

            public String getArea() {
                return area;
            }

            public void setArea(String area) {
                this.area = area;
            }

            public String getSalary() {
                return salary;
            }

            public void setSalary(String salary) {
                this.salary = salary;
            }

            public String getDate() {
                return date;
            }

            public void setDate(String date) {
                this.date = date;
            }

            public String getAddr() {
                return addr;
            }

            public void setAddr(String addr) {
                this.addr = addr;
            }

            public String getContact() {
                return contact;
            }

            public void setContact(String contact) {
                this.contact = contact;
            }

            public String getContact_info() {
                return contact_info;
            }

            public void setContact_info(String contact_info) {
                this.contact_info = contact_info;
            }

            public String getCount() {
                return count;
            }

            public void setCount(String count) {
                this.count = count;
            }

            public String getPublisher() {
                return publisher;
            }

            public void setPublisher(String publisher) {
                this.publisher = publisher;
            }

            public String getReq() {
                return req;
            }

            public void setReq(String req) {
                this.req = req;
            }

            public String getSex() {
                return sex;
            }

            public void setSex(String sex) {
                this.sex = sex;
            }

            public String getTime() {
                return time;
            }

            public void setTime(String time) {
                this.time = time;
            }

            public String getDetail() {
                return detail;
            }

            public void setDetail(String detail) {
                this.detail = detail;
            }
        }
    }
}
