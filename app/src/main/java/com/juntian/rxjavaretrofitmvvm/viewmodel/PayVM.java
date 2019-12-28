package com.juntian.rxjavaretrofitmvvm.viewmodel;

import com.juntian.rxjavaretrofitmvvm.base.BaseActivity;
import com.juntian.rxjavaretrofitmvvm.base.BaseModel;
import com.juntian.rxjavaretrofitmvvm.base.BaseView;
import com.juntian.rxjavaretrofitmvvm.base.BaseViewModel;

/**
 * @作者:TJ
 * @时间:2019/8/6
 * @描述:
 */
public class PayVM extends BaseViewModel<BaseModel, BaseView> {

    public PayVM(BaseActivity context, BaseView view) {
        super(context, view);
    }

    @Override
    public BaseModel createModel() {
        return null;
    }

/*    *//**
     * 生成订单-商品详情
     *//*
    public void generateOrder(ConfirmOrderData data, List<ConfirmPurchase.Store> productList, String note) {
        if (data.getDefaultAddress() == null) {
            mView.showToastMessage("请选择收货地址");
            return;
        }
        Map<String, Object> map  = ParmsHelper.create();
        List<Spec>          list = new ArrayList<>();
        for (int i = 0; i < productList.size(); i++) {
            list.add(new Spec(productList.get(i).getSku_id(), productList.get(i).getProduct_num()));
        }
        map.put("spec", JSON.toJSONString(list));
        map.put("address_id", data.getDefaultAddress().getId());
        map.put("distribution", data.getDistributionType());
        map.put("is_invoice", data.getIsInvoice());
        map.put("note", note);
        mView.showLoadDialog();
        mModel.generateOrder(map, new ResponseCallback<SimpleResult>(mView) {
            @Override
            public void onResponse(SimpleResult simpleResult) {
                super.onResponse(simpleResult);
                ((ConfirmOrderView) mView).onGenerateOrderSuccess(simpleResult.getOrder_no());
            }
        });
    }


    *//**
     * 生成订单-购物车
     *//*
    public void generateOrder(ConfirmOrderData data, String cartIds, String note) {
        if (data.getDefaultAddress() == null) {
            mView.showToastMessage("请选择收货地址");
            return;
        }
        Map<String, Object> map = ParmsHelper.create();
        map.put("id", cartIds);
        map.put("is_invoice", data.getIsInvoice());
        map.put("note", note);
        map.put("address_id", data.getDefaultAddress().getId());
        mView.showLoadDialog();
        mModel.generateOrderByCart(map, new ResponseCallback<SimpleResult>(mView) {
            @Override
            public void onResponse(SimpleResult simpleResult) {
                super.onResponse(simpleResult);
                ((ConfirmOrderView) mView).onGenerateOrderSuccess(simpleResult.getOrder_no());
            }
        });
    }

    *//**
     * 余额支付
     *//*
    public void walletPay(String orderNo, String pwd) {
        mView.showLoadDialog();
        mModel.walletPay(ParmsHelper.create("order_no", orderNo, "pay_password", pwd),
                new ResponseCallback<HttpResult<String>>(mView) {
                    @Override
                    public void onResponse(HttpResult<String> simpleResult) {
                        super.onResponse(simpleResult);
                        mView.showToastMessage(simpleResult.getMsg());
                        ((PayView) mView).onPaySuccess();
                    }

                    @Override
                    public void onFailure(Throwable e) {
                        super.onFailure(e);
                        ((PayView) mView).onPayFailure();
                    }
                });
    }


    *//**
     * 微信支付请求
     *//*
    public void weChatPay(String orderNo) {
        mModel.weChatPay(orderNo, new ResponseCallback<WechatPayInfo>(mView) {
            @Override
            public void onResponse(WechatPayInfo result) {
                super.onResponse(result);
                LogUtils.e(TAG, "onResponse: " + result);
                ((PayView) mView).onWechatPayResponse(result);
            }
        });
    }

    *//**
     * 支付宝支付请求
     *//*
    public void aliPay(String orderNo) {
        mModel.aliPay(orderNo, new ResponseCallback<String>(mView) {
            @Override
            public void onResponse(String result) {
                super.onResponse(result);
                LogUtils.e(TAG, "onResponse: " + result);
                ((PayView) mView).onAliPayResponse(result);
            }
        });
    }

    *//**
     * 微信充值
     *//*
    public void weChatCharge(String money) {
        if (Tools.isEmpty(money)) {
            mView.showToastMessage("请输入充值金额");
            return;
        }
        String[] temp = (money).split("\\.");
        if (temp.length == 1 || (temp.length == 2 && temp[1].toCharArray().length <= 2)) {
            mModel.weChatCharge(ParmsHelper.create("money", money), new ResponseCallback<WechatPayInfo>(mView) {
                @Override
                public void onResponse(WechatPayInfo result) {
                    super.onResponse(result);
                    ((PayView) mView).onWechatPayResponse(result);
                }
            });
        } else {
            mView.showToastMessage("请输入正确的金额");
        }
    }

    *//**
     * 支付宝充值
     *//*
    public void alipayCharge(String money) {
        if (Tools.isEmpty(money)) {
            mView.showToastMessage("请输入充值金额");
            return;
        }
        String[] temp = (money).split("\\.");
        if (temp.length == 1 || (temp.length == 2 && temp[1].toCharArray().length <= 2)) {
            mModel.alipayCharge(ParmsHelper.create("money", money), new ResponseCallback<String>(mView) {
                @Override
                public void onResponse(String result) {
                    super.onResponse(result);
                    ((PayView) mView).onAliPayResponse(result);
                }
            });
        } else {
            mView.showToastMessage("请输入正确的金额");
        }
    }*/

}
