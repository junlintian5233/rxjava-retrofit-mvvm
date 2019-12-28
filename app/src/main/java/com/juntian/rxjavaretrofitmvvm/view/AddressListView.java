package com.juntian.rxjavaretrofitmvvm.view;

import com.juntian.rxjavaretrofitmvvm.base.BaseView;
import com.juntian.rxjavaretrofitmvvm.model.bean.Address;

import java.util.List;

/**
 * @作者:TJ
 * @时间:2019/8/24
 * @描述:
 */
public interface AddressListView extends BaseView {

    void onAddressListResult(List<Address> list);
}
