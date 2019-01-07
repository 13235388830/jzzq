package cn.addapp.pickers.entity;

import java.util.List;

/**
 * 省份
 * <br/>
 * Author:matt : addapp.cn
 * DateTime:2016-10-15 19:06
 *
 */
public class Province2 {


    /**
     * code : 200
     * msg : 操作成功
     * data : [{"code":"120000","name":"天津市","hasChild":1,"childs":[{"code":"120100","name":"市辖区","hasChild":1,"childs":[{"code":"120101","name":"和平区","hasChild":0,"childs":null},{"code":"120102","name":"河东区","hasChild":0,"childs":null},{"code":"120103","name":"河西区","hasChild":0,"childs":null},{"code":"120104","name":"南开区","hasChild":0,"childs":null},{"code":"120105","name":"河北区","hasChild":0,"childs":null},{"code":"120106","name":"红桥区","hasChild":0,"childs":null},{"code":"120107","name":"塘沽区","hasChild":0,"childs":null},{"code":"120108","name":"汉沽区","hasChild":0,"childs":null},{"code":"120109","name":"大港区","hasChild":0,"childs":null},{"code":"120110","name":"东丽区","hasChild":0,"childs":null},{"code":"120111","name":"西青区","hasChild":0,"childs":null},{"code":"120112","name":"津南区","hasChild":0,"childs":null},{"code":"120113","name":"北辰区","hasChild":0,"childs":null},{"code":"120114","name":"武清区","hasChild":0,"childs":null},{"code":"120115","name":"宝坻区","hasChild":0,"childs":null}]}]}]
     */

    private int code;
    private String msg;
    private List<DataBean> data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * code : 120000
         * name : 天津市
         * hasChild : 1
         * childs : [{"code":"120100","name":"市辖区","hasChild":1,"childs":[{"code":"120101","name":"和平区","hasChild":0,"childs":null},{"code":"120102","name":"河东区","hasChild":0,"childs":null},{"code":"120103","name":"河西区","hasChild":0,"childs":null},{"code":"120104","name":"南开区","hasChild":0,"childs":null},{"code":"120105","name":"河北区","hasChild":0,"childs":null},{"code":"120106","name":"红桥区","hasChild":0,"childs":null},{"code":"120107","name":"塘沽区","hasChild":0,"childs":null},{"code":"120108","name":"汉沽区","hasChild":0,"childs":null},{"code":"120109","name":"大港区","hasChild":0,"childs":null},{"code":"120110","name":"东丽区","hasChild":0,"childs":null},{"code":"120111","name":"西青区","hasChild":0,"childs":null},{"code":"120112","name":"津南区","hasChild":0,"childs":null},{"code":"120113","name":"北辰区","hasChild":0,"childs":null},{"code":"120114","name":"武清区","hasChild":0,"childs":null},{"code":"120115","name":"宝坻区","hasChild":0,"childs":null}]}]
         */

        private String code;
        private String name;
        private int hasChild;
        private List<ChildsBeanX> childs;

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getHasChild() {
            return hasChild;
        }

        public void setHasChild(int hasChild) {
            this.hasChild = hasChild;
        }

        public List<ChildsBeanX> getChilds() {
            return childs;
        }

        public void setChilds(List<ChildsBeanX> childs) {
            this.childs = childs;
        }

        public static class ChildsBeanX {
            /**
             * code : 120100
             * name : 市辖区
             * hasChild : 1
             * childs : [{"code":"120101","name":"和平区","hasChild":0,"childs":null},{"code":"120102","name":"河东区","hasChild":0,"childs":null},{"code":"120103","name":"河西区","hasChild":0,"childs":null},{"code":"120104","name":"南开区","hasChild":0,"childs":null},{"code":"120105","name":"河北区","hasChild":0,"childs":null},{"code":"120106","name":"红桥区","hasChild":0,"childs":null},{"code":"120107","name":"塘沽区","hasChild":0,"childs":null},{"code":"120108","name":"汉沽区","hasChild":0,"childs":null},{"code":"120109","name":"大港区","hasChild":0,"childs":null},{"code":"120110","name":"东丽区","hasChild":0,"childs":null},{"code":"120111","name":"西青区","hasChild":0,"childs":null},{"code":"120112","name":"津南区","hasChild":0,"childs":null},{"code":"120113","name":"北辰区","hasChild":0,"childs":null},{"code":"120114","name":"武清区","hasChild":0,"childs":null},{"code":"120115","name":"宝坻区","hasChild":0,"childs":null}]
             */

            private String code;
            private String name;
            private int hasChild;
            private List<ChildsBean> childs;

            public String getCode() {
                return code;
            }

            public void setCode(String code) {
                this.code = code;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public int getHasChild() {
                return hasChild;
            }

            public void setHasChild(int hasChild) {
                this.hasChild = hasChild;
            }

            public List<ChildsBean> getChilds() {
                return childs;
            }

            public void setChilds(List<ChildsBean> childs) {
                this.childs = childs;
            }

            public static class ChildsBean {
                /**
                 * code : 120101
                 * name : 和平区
                 * hasChild : 0
                 * childs : null
                 */

                private String code;
                private String name;
                private int hasChild;
                private Object childs;

                public String getCode() {
                    return code;
                }

                public void setCode(String code) {
                    this.code = code;
                }

                public String getName() {
                    return name;
                }

                public void setName(String name) {
                    this.name = name;
                }

                public int getHasChild() {
                    return hasChild;
                }

                public void setHasChild(int hasChild) {
                    this.hasChild = hasChild;
                }

                public Object getChilds() {
                    return childs;
                }

                public void setChilds(Object childs) {
                    this.childs = childs;
                }
            }
        }
    }
}