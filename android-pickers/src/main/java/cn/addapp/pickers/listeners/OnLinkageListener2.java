package cn.addapp.pickers.listeners;


import cn.addapp.pickers.entity.Province2;

/**
 * @author matt
 * blog: addapp.cn
 */

public interface OnLinkageListener2 {
    /**
     * 选择地址
     *
     * @param province the province
     * @param city    the city
     * @param county   the county ，if {@code hideCounty} is true，this is null
     */
    void onAddressPicked(Province2.DataBean province, Province2.DataBean.ChildsBeanX city, Province2.DataBean.ChildsBeanX.ChildsBean county);
    void  onCancel();
    void onSubmit();
}
