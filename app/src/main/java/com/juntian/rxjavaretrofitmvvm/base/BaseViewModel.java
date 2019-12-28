package com.juntian.rxjavaretrofitmvvm.base;


import com.juntian.basicapp.utils.SpUtils;

/**
 * @作者:TJ
 * @时间:2019-04-12 17:53
 * @描述:
 */
public abstract class BaseViewModel<M extends BaseModel, V extends BaseView> {

    public final String TAG = this.getClass().getSimpleName();

    public BaseActivity mContext;
    public M            mModel;
    public V            mView;
    public SpUtils      mSpUtils;

    public BaseViewModel(BaseActivity context, V view) {
        mContext = context;
        mView = view;
        mModel = createModel();
        mSpUtils = SpUtils.getInstance();
    }

    public void onDestroy() {
        if (mModel != null) {
            mModel.onDestroy();
        }
    }


    public abstract M createModel();


}
