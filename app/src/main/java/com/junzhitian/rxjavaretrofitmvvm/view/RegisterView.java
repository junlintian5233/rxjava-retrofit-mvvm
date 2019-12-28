package com.junzhitian.rxjavaretrofitmvvm.view;

import com.junzhitian.rxjavaretrofitmvvm.base.BaseView;
import com.junzhitian.rxjavaretrofitmvvm.model.bean.Categary;

import java.util.List;

/**
 * @作者:TJ
 * @时间:2019/9/11
 * @描述:
 */
public interface RegisterView extends BaseView {

    void onSchoolList(List<Categary> list);
}
