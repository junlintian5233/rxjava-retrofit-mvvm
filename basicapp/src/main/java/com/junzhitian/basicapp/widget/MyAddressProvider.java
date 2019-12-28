//package com.cnsunrun.commonui.widget;
//
//import android.content.Context;
//
//
//import java.util.ArrayList;
//import java.util.Collections;
//import java.util.List;
//
//import chihane.jdaddressselector.AddressProvider;
//import chihane.jdaddressselector.model.City;
//import chihane.jdaddressselector.model.County;
//import chihane.jdaddressselector.model.Province;
//
//public class MyAddressProvider implements AddressProvider {
//
//    private Context   mContext;
//    private UserModel mUserModel;
//
//    public MyAddressProvider(Context context) {
//        mContext = context;
//        mUserModel = new UserModel(context);
//    }
//
//    @Override
//    public void provideProvinces(final AddressReceiver<Province> addressReceiver) {
//        mUserModel.getAreaList(1, new ResponseCallback<List<Area>>() {
//
//            @Override
//            public void onResponse(List<Area> list) {
//                List<Province> provinces = new ArrayList<>();
//                for (int i = 0; i < list.size(); i++) {
//                    Area     area     = list.get(i);
//                    Province province = new Province();
//                    province.id = area.getId();
//                    province.name = area.getTitle();
//                    provinces.add(province);
//                }
//                Collections.reverse(provinces);
//                addressReceiver.send(provinces);
//            }
//        });
//    }
//
//    @Override
//    public void provideCitiesWith(int provinceId, final AddressReceiver<City> addressReceiver) {
//        if (provinceId != 130) {
//            List<City> cities = new ArrayList<>();
//            addressReceiver.send(null);
//            return;
//        }
//        mUserModel.getAreaList(provinceId, new ResponseCallback<List<Area>>() {
//
//            @Override
//            public void onResponse(List<Area> list) {
//                List<City> cities = new ArrayList<>();
//                for (int i = 0; i < list.size(); i++) {
//                    Area area = list.get(i);
//                    City city = new City();
//                    city.id = area.getId();
//                    city.name = area.getTitle();
//                    cities.add(city);
//                }
//                Collections.reverse(cities);
//                addressReceiver.send(cities);
//            }
//        });
//    }
//
//    @Override
//    public void provideCountiesWith(int cityId, final AddressReceiver<County> addressReceiver) {
//        mUserModel.getAreaList(cityId, new ResponseCallback<List<Area>>() {
//
//            @Override
//            public void onResponse(List<Area> list) {
//                List<County> counties = new ArrayList<>();
//                for (int i = 0; i < list.size(); i++) {
//                    Area   area   = list.get(i);
//                    County county = new County();
//                    county.id = area.getId();
//                    county.name = area.getTitle();
//                    counties.add(county);
//                }
//                Collections.reverse(counties);
//                addressReceiver.send(counties);
//            }
//        });
//    }
//
//}
