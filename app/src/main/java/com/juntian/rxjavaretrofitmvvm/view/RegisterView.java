package com.juntian.rxjavaretrofitmvvm.view;

import com.juntian.rxjavaretrofitmvvm.base.BaseView;
import com.juntian.rxjavaretrofitmvvm.model.bean.Categary;

import java.util.List;

/**
 * @作者:TJ
 * @时间:2019/9/11
 * @描述:
 */
public interface RegisterView extends BaseView {

    void onSchoolList(List<Categary> list);
}
