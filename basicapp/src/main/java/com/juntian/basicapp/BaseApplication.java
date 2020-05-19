package com.juntian.basicapp;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.Application;
import android.content.Context;
import android.content.Intent;

import androidx.fragment.app.Fragment;

import java.util.Iterator;
import java.util.LinkedList;

/**
 * 用于程序启动时的初始化操作,和Activity的管理
 */
public class BaseApplication extends Application {
    private LinkedList<Activity> activityList = null;// 已启动的Activity集合
    private LinkedList<Fragment> fragments    = null;// 已加载的Fragment集合

    private static BaseApplication application;// 全局上下文对象

    @Override
    public void onCreate() {
        super.onCreate();
        application = this;
        String processName = getCurProcessName(this);
        if (getPackageName().equals(processName)) {// 判断当前Application实例是否属于App基础进程
            activityList = new LinkedList<Activity>();
            fragments = new LinkedList<Fragment>();
        }
    }


    /**
     * 获取当前进程名字
     *
     * @param context
     * @return
     */
    protected String getCurProcessName(Context context) {
        int             pid              = android.os.Process.myPid();
        ActivityManager mActivityManager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        for (ActivityManager.RunningAppProcessInfo appProcess : mActivityManager.getRunningAppProcesses()) {
            if (appProcess.pid == pid) {
                return appProcess.processName;
            }
        }
        return null;
    }

    /**
     * 获得当前程序全局上下文实例
     *
     * @return
     */
    public static BaseApplication getInstance() {
        if (application == null)
            throw new RuntimeException("BaseApplication 未被正常初始化!");
        return application;
    }

    /**
     * 启动Activity(FLAG_ACTIVITY_NEW_TASK 方式)
     *
     * @param cls
     */
    public void startAct(Class<?> cls) {
        Intent intent = new Intent(BaseApplication.getInstance(), cls);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

    // ---------------------------------------Activity和Fragment管理相关方法-------------------------------------

    /**
     * 获取当前正在栈顶的Activity 字节码文件
     *
     * @return
     */
    public Class<?> getCurrentAct() {
        return activityList.size() == 0 ? null : activityList.getLast().getClass();
    }

    /**
     * 添加Activity到管理栈中,方便统一管理
     *
     * @param activity
     */
    public void addActivity(Activity activity) {
        if (activity != null && !activityList.contains(activity))
            activityList.add(activity);
    }

    /**
     * 从栈中移除Activity
     *
     * @param activity
     */
    public void removeActivity(Activity activity) {
        if (activityList.size() != 0)
            activityList.remove(activity);
    }

    /**
     * 将Fragment加入栈中以便于管理
     *
     * @param fragment
     */
    public void addFragment(Fragment fragment) {
        fragments.add(fragment);
    }

    public void removeFragment(Fragment fragment) {
        fragments.remove(fragment);
    }

    /**
     * 关闭所有Activity
     *
     * @param ignore 需要忽略的Activity列表
     */
    public void closeAllActivity(Class<?>... ignore) {
        Iterator<Activity> iterator = activityList.iterator();
        while (iterator.hasNext()) {
            Activity activity = (Activity) iterator.next();
            try {
                if (ignore == null || !isContaint(activity, ignore))// 忽略列表为空或者当前Act未包含在忽略列表中时,进行关闭
                {
                    iterator.remove();
                    activity.finish();
                }
            } catch (Exception e) {
                /* 忽略关闭时产生的异常 */
                e.printStackTrace();
            }
        }
    }

    /**
     * 关闭指定Activity
     *
     * @param closes 待关闭的Activity列表
     */
    public void closeActivitys(Class<?>... closes) {
        if (closes == null)
            return;
        Iterator<Activity> iterator = activityList.iterator();
        while (iterator.hasNext()) {
            Activity activity = (Activity) iterator.next();
            try {
                if (isContaint(activity, closes)) {
                    activity.finish();
                    iterator.remove();
                }
            } catch (Exception e) {
            }
        }
    }

    /**
     * 判断activity是否在忽略列表中
     *
     * @param act
     * @param ignore
     * @return
     */
    private boolean isContaint(Object act, Class<?>... ignore) {
        for (Class<?> class1 : ignore)
            if (act.getClass() == class1)
                return true;
        return false;
    }

    /**
     * 关闭指定Fragment 会连同Fragment所在的Activity一起关闭
     *
     * @param closes
     */
    public void closeFragments(Class<?>... closes) {//
        if (closes == null)
            return;
        Iterator<Fragment> iterator = fragments.iterator();
        while (iterator.hasNext()) {
            Fragment activity = (Fragment) iterator.next();
            try {
                if (isContaint(activity, closes)) {
                    iterator.remove();
                }
            } catch (Exception e) {
                /* 忽略关闭时产生的异常 */
                e.printStackTrace();
            }
        }
    }
}
