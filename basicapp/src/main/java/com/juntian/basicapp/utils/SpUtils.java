package com.juntian.basicapp.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Base64;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;


public class SpUtils {

    private        SharedPreferences.Editor mEditor;
    private        SharedPreferences        mSharedPreferences;
    private static SpUtils                  instance;


    public static SpUtils getInstance() {
        if (instance == null) {
            instance = new SpUtils();
        }
        return instance;
    }

    public void init(Context context) {
        mSharedPreferences = context.getSharedPreferences("CommonSharedPreferences", Context.MODE_PRIVATE);
        mEditor = mSharedPreferences.edit();
    }

    /*--------------- 读 ---------------*/

    public String getString(String key, String defValue) {
        return mSharedPreferences.getString(key, defValue);
    }

    /**
     * 读取整型
     */
    public int getInt(String key, int defValue) {
        return mSharedPreferences.getInt(key, defValue);
    }

    /**
     * 读取long
     */
    public long getLong(String key, long defValue) {
        return mSharedPreferences.getLong(key, defValue);
    }

    /**
     * 读取布尔型
     */
    public boolean getBoolean(String key, boolean defValue) {
        return mSharedPreferences.getBoolean(key, defValue);
    }

    /**
     * 清除所有数据
     */
    public void clearData() {
        mEditor.clear().commit();
    }


    /*--------------- 写 ---------------*/
    public void putString(String key, String value) {
        mEditor.putString(key, value);
        mEditor.commit();
    }

    /**
     * 写入int
     *
     * @param key
     * @param value
     */
    public void putInt(String key, int value) {
        mEditor.putInt(key, value);
        mEditor.commit();
    }

    /**
     * 写入long
     *
     * @param key
     * @param value
     */
    public void putLong(String key, long value) {
        mEditor.putLong(key, value);
        mEditor.commit();
    }

    /**
     * 写入boolean
     *
     * @param key
     * @param value
     */
    public void putBoolean(String key, boolean value) {
        mEditor.putBoolean(key, value);
        mEditor.commit();
    }


    /**
     * 存放实体类以及任意类型
     *
     * @param key
     * @param obj
     */
    public void putBean(String key, Object obj) {
        if (obj instanceof Serializable) {// obj必须实现Serializable接口，否则会出问题
            try {
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                ObjectOutputStream    oos  = new ObjectOutputStream(baos);
                oos.writeObject(obj);
                String string64 = new String(Base64.encode(baos.toByteArray(), 0));
                mEditor.putString(key, string64).commit();
            } catch (IOException e) {
                e.printStackTrace();
            }

        } else {
            throw new IllegalArgumentException("the obj must implement Serializble");
        }

    }

    public Object getBean(String key) {
        Object obj = null;
        try {
            String base64 = mSharedPreferences.getString(key, "");
            if (base64.equals("")) {
                return null;
            }
            byte[]               base64Bytes = Base64.decode(base64.getBytes(), 1);
            ByteArrayInputStream bais        = new ByteArrayInputStream(base64Bytes);
            ObjectInputStream    ois         = new ObjectInputStream(bais);
            obj = ois.readObject();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return obj;
    }

}
