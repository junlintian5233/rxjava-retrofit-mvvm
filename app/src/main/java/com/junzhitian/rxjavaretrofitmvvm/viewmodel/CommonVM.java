package com.junzhitian.rxjavaretrofitmvvm.viewmodel;

import com.junzhitian.rxjavaretrofitmvvm.base.BaseActivity;
import com.junzhitian.rxjavaretrofitmvvm.base.BaseView;
import com.junzhitian.rxjavaretrofitmvvm.base.BaseViewModel;
import com.junzhitian.rxjavaretrofitmvvm.http.ResponseCallback;
import com.junzhitian.rxjavaretrofitmvvm.model.CommonModel;
import com.junzhitian.rxjavaretrofitmvvm.view.WebviewView;

/**
 * @作者:TJ
 * @时间:2019/9/2
 * @描述:
 */
public class CommonVM extends BaseViewModel<CommonModel, BaseView> {
    public CommonVM(BaseActivity context, BaseView view) {
        super(context, view);
    }

    @Override
    public CommonModel createModel() {
        return new CommonModel(mContext);
    }


    public void getAboutUs() {
        mModel.getAboutUs(new ResponseCallback<String>() {
            @Override
            public void onResponse(String s) {
                super.onResponse(s);
                ((WebviewView) mView).onWebCodeResult(s);
            }
        });
    }

}
