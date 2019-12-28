package com.juntian.rxjavaretrofitmvvm.viewmodel;

import com.juntian.rxjavaretrofitmvvm.base.BaseActivity;
import com.juntian.rxjavaretrofitmvvm.base.BaseView;
import com.juntian.rxjavaretrofitmvvm.base.BaseViewModel;
import com.juntian.rxjavaretrofitmvvm.http.ResponseCallback;
import com.juntian.rxjavaretrofitmvvm.model.CommonModel;
import com.juntian.rxjavaretrofitmvvm.view.WebviewView;

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
