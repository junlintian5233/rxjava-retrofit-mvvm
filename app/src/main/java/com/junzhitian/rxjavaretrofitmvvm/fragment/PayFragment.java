package com.junzhitian.rxjavaretrofitmvvm.fragment;

import android.app.Dialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.widget.RadioGroup;

import com.junzhitian.rxjavaretrofitmvvm.R;
import com.junzhitian.rxjavaretrofitmvvm.base.BaseFragment;
import com.junzhitian.rxjavaretrofitmvvm.databinding.FmtPayBinding;
import com.junzhitian.rxjavaretrofitmvvm.model.bean.WechatPayInfo;
import com.junzhitian.rxjavaretrofitmvvm.view.PayView;
import com.junzhitian.rxjavaretrofitmvvm.viewmodel.PayVM;

/**
 * @作者:TJ
 * @时间:2019/8/9
 * @描述:
 */
public class PayFragment extends BaseFragment<PayVM, FmtPayBinding> implements PayView {

    //支付宝支付
    private static final int     RQF_PAY = 0x1;
    private              int     mContainerId;
    private              String  mOrderNo;
    //支付方式 1支付宝2微信3钱包
    private              int     mType   = 0;
    //是否为订单支付
    private              boolean isPurchase;
    private              String  mSumMoney;
    private              Dialog  mPwdDialog;

    @Override
    protected PayVM createVM() {
        return new PayVM(mActivity, this);
    }

    @Override
    public int onSetLayoutRes() {
        return R.layout.fmt_pay;
    }

    @Override
    public void initData() {
        mOrderNo = getArguments().getString("orderNo");
        isPurchase = getArguments().getBoolean("isPurchase", false);
//        mDataBinding.setHandler(new EventHandler());
//        mDataBinding.setShowBalance(isOrderPay);
//        mDataBinding.setIsPayOrder(isOrderPay);
    }

    @Override
    public void initView(Bundle savedInstanceState) {
//        if (isOrderPay) {
//            mSumMoney = getArguments().getString("sumMoney");
//            mDataBinding.tvPayMoney.setText("¥" + mSumMoney);
//        }
        registerOnPayFinishReceiver();
    }

    @Override
    public void onPaySuccess() {
        mPwdDialog.dismiss();
        //skipPayResultPage(true);
    }

    @Override
    public void onPayFailure() {
        mPwdDialog.dismiss();
        //skipPayResultPage(false);
    }


    /**
     * 跳转支付结果页面
     **/
    private void skipPayResultPage(boolean isSuccess) {
//        PayResultFragment fragment = new PayResultFragment();
//        Bundle            args     = new Bundle();
//        args.putBoolean("isSuccess", isSuccess);
//        args.putBoolean("isOrderPay", isOrderPay);
//        fragment.setArguments(args);
//        changeFragment(mContainerId, fragment);
    }

    @Override
    public void onWechatPayResponse(WechatPayInfo response) {
        //invokeWeChatPay(response);
    }

    @Override
    public void onAliPayResponse(String result) {
        //invokeAliPay(result);
    }

    /**
     * 事件处理
     */
    public class EventHandler {

        public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {
//            KeyboardUtils.closeKeyboard(mActivity);
//            switch (checkedId) {
//                case R.id.rbtn_alipay:
//                    mType = 1;
//                    break;
//                case R.id.rbtn_wechat:
//                    mType = 2;
//                    break;
//                case R.id.rbtn_wellet:
//                    mType = 3;
//                    break;
//            }
        }

        public void pay() {
//            switch (mType) {
//                case 1:
//                    if (isOrderPay) {
//                        mViewModel.aliPay(mOrderNo);
//                    } else {
//                        mViewModel.alipayCharge(mDataBinding.etChargeMoney.getText().toString());
//                    }
//                    break;
//                case 2:
//                    if (isOrderPay) {
//                        mViewModel.weChatPay(mOrderNo);
//                    } else {
//                        mViewModel.weChatCharge(mDataBinding.etChargeMoney.getText().toString());
//                    }
//                    break;
//                case 3:
//                    if (Config.getUserInfo().getIs_set_pay_password().equals("1")) {
//                        inputPassword();
//                    } else {
//                        showToastMessage("请先前往个人中心账号安全设置支付密码");
//                    }
//                    break;
//                default:
//                    showToastMessage("请选择支付方式");
//                    break;
//            }

        }
    }

    /**
     * 输入密码
     */
//    private void inputPassword() {
//        mPwdDialog = AlertDialogUtil.showPwdDialog(mActivity, mSumMoney, new GridPasswordView.OnPasswordChangedListener() {
//            @Override
//            public void onTextChanged(String psw) {
//
//            }
//
//            @Override
//            public void onInputFinish(String psw) {
//                mViewModel.walletPay(mOrderNo, psw);
//            }
//        });
//
//    }

    /**
     * 调起微信
     */
//    private void invokeWeChatPay(WechatPayInfo response) {
//        PayReq req = new PayReq();
//        req.appId = response.getAppid();
//        req.partnerId = response.getPartnerid();
//        req.nonceStr = response.getNoncestr();
//        req.packageValue = response.getPackageX();
//        req.timeStamp = response.getTimestamp();
//        req.prepayId = response.getPrepayid();
//        req.sign = response.getSign();
//        CommonApp.getInstance().createWXAPI().sendReq(req);
//    }

//    private void invokeAliPay(final String orderInfo) {
//        Runnable payRunnable = new Runnable() {
//
//            @Override
//            public void run() {
//                // 构造PayTask 对象
//                PayTask alipay = new PayTask(mActivity);
//                // 调用支付接口，获取支付结果
//                String  result = alipay.pay(orderInfo, true);
//                Message msg    = new Message();
//                msg.what = RQF_PAY;
//                msg.obj = result;
//                alipayHandler.sendMessage(msg);
//            }
//        };
//        //必须异步调用
//        Thread payThread = new Thread(payRunnable);
//        payThread.start();
//    }


    /**
     * 支付宝结果回传
     *//*
//    Handler alipayHandler = new Handler() {
//        @Override
//        public void handleMessage(Message msg) {
//            AliPayResult result = new AliPayResult((String) msg.obj);
//            switch (msg.what) {
//                case RQF_PAY:
//                    // 处理支付结果+
//                    LogUtils.e(TAG, "result=" + result);
//                    result.parseResult();
//                    String tips = result.getTips();
//                    showToastMessage(tips);
//                    if ("操作成功".equals(tips)) {
//                        PayResultFragment fragment = new PayResultFragment();
//                        Bundle            args     = new Bundle();
//                        args.putBoolean("isSuccess", true);
//                        args.putBoolean("isOrderPay", isOrderPay);
//                        fragment.setArguments(args);
//                        getActivity().getSupportFragmentManager().beginTransaction()
//                                .setCustomAnimations(R.anim.push_right_in, R.anim.push_left_out, R.anim.back_left_in, R.anim.back_right_out)
//                                .replace(mContainerId, fragment)
//                                .addToBackStack(null)
//                                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
//                                .commitAllowingStateLoss();
//                    } else {
//                        PayResultFragment fragment = new PayResultFragment();
//                        Bundle            args     = new Bundle();
//                        args.putBoolean("isSuccess", false);
//                        args.putBoolean("isOrderPay", isOrderPay);
//                        fragment.setArguments(args);
//                        getActivity().getSupportFragmentManager().beginTransaction()
//                                .setCustomAnimations(R.anim.push_right_in, R.anim.push_left_out, R.anim.back_left_in, R.anim.back_right_out)
//                                .replace(mContainerId, fragment)
//                                .addToBackStack(null)
//                                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
//                                .commitAllowingStateLoss();
//                    }
//                    break;
//                default:
//                    break;
//            }
//        }
//    };


    /**
     * 微信支付成功通知广播接收器
     */
    private BroadcastReceiver onPayFinishReceiver = new BroadcastReceiver() {

        @Override
        public void onReceive(Context context, Intent intent) {
            String payResult = intent.getStringExtra("payResult");
            if (payResult.equals("SUCCESS")) {
                //skipPayResultPage(true);
            }
        }
    };


    /**
     * 注册微信支付成功通知广播接收器
     */
    private void registerOnPayFinishReceiver() {
        IntentFilter filter = new IntentFilter();
        filter.addAction("ACTION_PAY_FINISH");
        mActivity.registerReceiver(onPayFinishReceiver, filter);
    }

    /**
     * 注销支付成功通知广播接收器
     */
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mActivity.unregisterReceiver(onPayFinishReceiver);
    }
}
