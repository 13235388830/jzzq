package cn.addapp.pickers.listeners;

import cn.addapp.pickers.entity.City;
import cn.addapp.pickers.entity.County;
import cn.addapp.pickers.entity.Province;

/**
 * @author matt
 * blog: addapp.cn
 */

public interface OnLinkageListener {
    /**
     * 选择地址
     *
     * @param province the province
     * @param city    the city
     * @param county   the county ，if {@code hideCounty} is true，this is null
     */
    void onAddressPicked(Province.DataBean province, Province.DataBean.YearBean city, Province.DataBean.YearBean.MasterBean county);
    void  onCancel();
    void onSubmit();
}
