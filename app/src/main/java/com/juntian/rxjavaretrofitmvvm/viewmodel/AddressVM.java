package com.juntian.rxjavaretrofitmvvm.viewmodel;

import com.juntian.rxjavaretrofitmvvm.base.BaseActivity;
import com.juntian.rxjavaretrofitmvvm.base.BaseView;
import com.juntian.rxjavaretrofitmvvm.base.BaseViewModel;
import com.juntian.rxjavaretrofitmvvm.constant.Constants;
import com.juntian.rxjavaretrofitmvvm.common.ParmsHelper;
import com.juntian.rxjavaretrofitmvvm.http.ResponseCallback;
import com.juntian.rxjavaretrofitmvvm.model.AddressModel;
import com.juntian.rxjavaretrofitmvvm.model.bean.Address;
import com.juntian.rxjavaretrofitmvvm.model.bean.HttpResult;
import com.juntian.rxjavaretrofitmvvm.view.AddressListView;

import java.util.List;
import java.util.Map;

/**
 * @作者:TJ
 * @时间:2019/8/15
 * @描述:
 */
public class AddressVM extends BaseViewModel<AddressModel, BaseView> {


    public AddressVM(BaseActivity context, BaseView view) {
        super(context, view);
    }

    @Override
    public AddressModel createModel() {
        return new AddressModel(mContext);
    }


    /**
     * 获取地址列表
     */
    public void getAddressList() {
        mModel.getAddressList(ParmsHelper.create(), new ResponseCallback<List<Address>>() {
            @Override
            public void onResponse(List<Address> addresses) {
                super.onResponse(addresses);
                ((AddressListView) mView).onAddressListResult(addresses);

                //保存默认地址
                boolean hasDefault = false;
                for (int i = 0; i < addresses.size(); i++) {
                    Address address = addresses.get(i);
                    if (address.getIs_default().equals("1")) {
                        mSpUtils.putBean(Constants.DEFAULT_ADDRESS, address);
                        hasDefault = true;
                        break;
                    }
                }
                if (!hasDefault && !addresses.isEmpty()) {
                    mSpUtils.putBean(Constants.DEFAULT_ADDRESS, addresses.get(0));
                }
            }
        });
    }


    /**
     * 删除地址
     */
    public void deleteAddress(String id) {
        mView.showLoadDialog();
        mModel.deleteAddress(ParmsHelper.create("id", id), new ResponseCallback<HttpResult<String>>(mView) {
            @Override
            public void onResponse(HttpResult<String> result) {
                super.onResponse(result);
                mView.showToastMessage(result.getMsg());
                mContext.onBackPressed();
            }
        });
    }

    /**
     * 编辑地址
     */
    public void editAddress(Address address, boolean isEditAddress) {
//        if (Tools.isEmpty(address.getReal_name())) {
//            mView.showToastMessage(R.string.pls_input_name);
//            return;
//        }
//        if (Tools.isEmpty(address.getMobile())) {
//            mView.showToastMessage(R.string.pls_input_phone);
//            return;
//        }
//        if (Tools.isEmpty(address.getProvince())) {
//            mView.showToastMessage(R.string.pls_select_area);
//            return;
//        }
//        if (Tools.isEmpty(address.getAddress())) {
//            mView.showToastMessage(R.string.pls_input_detail);
//            return;
//        }
        Map<String, Object> map = ParmsHelper.create();
        map.put("real_name", address.getReal_name());
        map.put("mobile", address.getMobile());
        map.put("province", address.getProvince());
        map.put("city", address.getCity());
        map.put("district", address.getDistrict());
        map.put("address", address.getAddress());
        map.put("is_default", address.getIs_default());
        map.put("map_lng", address.getMap_lng());
        map.put("map_lat", address.getMap_lat());
        if (isEditAddress) {
            map.put("id", address.getId());
        }
        mModel.editAddress(map, new ResponseCallback<HttpResult<String>>() {
            @Override
            public void onResponse(HttpResult<String> result) {
                super.onResponse(result);
                mView.showToastMessage(result.getMsg());
                mContext.onBackPressed();
            }
        });
    }

}
