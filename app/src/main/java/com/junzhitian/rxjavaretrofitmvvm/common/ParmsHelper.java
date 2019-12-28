package com.junzhitian.rxjavaretrofitmvvm.common;

import com.junzhitian.basicapp.utils.Tools;

import java.util.HashMap;
import java.util.Map;

import static com.junzhitian.rxjavaretrofitmvvm.common.Constants.UID;
import static com.junzhitian.rxjavaretrofitmvvm.common.Constants.UPWD;

/**
 * @author:TJ
 * @date:2017/10/14 16:30
 * @description:
 */

public class ParmsHelper {

    /**
     * 创建只map参数集合
     *
     * @return
     */
    public static Map<String, Object> create() {
        Map<String, Object> map = new HashMap<>();
        map.put(UID, Config.getUid());
        map.put(UPWD, Config.getUserInfo() == null || Tools.isEmpty(Config.getUserInfo().getPassword()) ? "" : Config.getUserInfo().getPassword());
        return map;
    }

    public static Map<String, Object> createEmpty() {
        Map<String, Object> map = new HashMap<>();
        return map;
    }


    /**
     * 创建map参数集合+一个参数
     *
     * @return
     */
    public static Map<String, Object> create(String key, Object value) {
        Map<String, Object> map = create();
        map.put(key, value);
        return map;
    }

    /**
     * 创建map参数集合+2个参数
     *
     * @return
     */
    public static Map<String, Object> create(String key1, Object value1, String key2, Object value2) {
        Map<String, Object> map = create(key1, value1);
        map.put(key2, value2);
        return map;
    }

    /**
     * 创建map参数集合+3个参数
     *
     * @return
     */
    public static Map<String, Object> create(String key1, Object value1, String key2, Object value2,
                                             String key3, Object value3) {
        Map<String, Object> map = create(key1, value1, key2, value2);
        map.put(key3, value3);
        return map;
    }
}
