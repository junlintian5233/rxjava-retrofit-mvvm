package com.junzhitian.rxjavaretrofitmvvm.model.bean;

import com.junzhitian.rxjavaretrofitmvvm.interfaces.OrderState;
import com.junzhitian.basicapp.utils.AmountUtils;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

/**
 * @作者:TJ
 * @时间:2019/8/28
 * @描述:
 */
public class OrderDetails {


    /**
     * id : 39
     * member_id : 9
     * store_id : 1
     * store_title : 雅石居旗舰店
     * order_no : 1904106679787062
     * product_total : 5000.00
     * money_total : 5000.00
     * money_reduction : 0.00
     * money_real : 5000.00
     * freight : 0.00
     * pay_trade_no :
     * pay_time :
     * ship_time :
     * confirm_time : 0000-00-00 00:00:00
     * finish_time : 0000-00-00 00:00:00
     * buyer_rate : 0
     * is_finish : 0
     * status_title : 等待买家付款
     * list : [{"id":"42","order_id":"39","product_id":"14","product_title":"松石 【普贤菩萨】","product_price":"5000.00","product_image":"http://192.168.2.83Uploads/Product/Cover/5be401873a097.jpeg","product_num":"1","product_spec":[],"buyer_rate":"0"}]
     */

    private String        id;
    private String        member_id;
    private String        store_id;
    private String        store_title;
    private String        order_no;
    private String        product_total;
    private String        money_total;
    private String        money_reduction;
    private String        money_real;
    private String        freight;
    private String        pay_trade_no;
    private String        pay_time;
    private String        ship_time;
    private String        confirm_time;
    private String        finish_time;
    private String        buyer_rate;
    private String        is_finish;
    private String        status_title;
    private List<Product> list;
    private Address       address_info;
    private String        add_time;
    private String        pay_channel;
    private String        status;


    private String leftButton;
    private String rightButton;
    /**
     * rider_info : {"id":"5","account":"18618499056","nickname":"雷达","map_lat":"30.501766221788195","map_lng":"114.4137890625"}
     */

    private RiderInfo rider_info;


    public String getRightButton() {
        switch (status) {
            case OrderState.WAITINT_PAY:
                return "立即付款";
            case OrderState.WAITINT_DELIVERY:
                return "";
            case OrderState.WAITINT_RECEIVE:
                return "确认收货";
            case OrderState.WAITINT_COMMENT:
                return "0".equals(buyer_rate) ? "评价" : "";
            default:
                return "";
        }
    }


    /**
     * status 10：待付款 20：待发货 30：待收货 40：待评价
     **/
    public String getLeftButton() {
        switch (status) {
            case OrderState.WAITINT_PAY:
                return "取消订单";
            case OrderState.WAITINT_COMMENT:
                return "申请售后";
            default:
                return "";
        }
    }


    public Address getAddress_info() {
        return address_info;
    }

    public void setAddress_info(Address address_info) {
        this.address_info = address_info;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMember_id() {
        return member_id;
    }

    public void setMember_id(String member_id) {
        this.member_id = member_id;
    }

    public String getStore_id() {
        return store_id;
    }

    public void setStore_id(String store_id) {
        this.store_id = store_id;
    }

    public String getStore_title() {
        return store_title;
    }

    public void setStore_title(String store_title) {
        this.store_title = store_title;
    }

    public String getOrder_no() {
        return order_no;
    }

    public void setOrder_no(String order_no) {
        this.order_no = order_no;
    }

    public String getProduct_total() {
        return product_total;
    }

    public void setProduct_total(String product_total) {
        this.product_total = product_total;
    }

    public String getMoney_total() {
        return money_total;
    }

    public void setMoney_total(String money_total) {
        this.money_total = money_total;
    }

    public String getMoney_reduction() {
        return money_reduction;
    }

    public void setMoney_reduction(String money_reduction) {
        this.money_reduction = money_reduction;
    }

    public String getMoney_real() {
        return money_real;
    }

    public void setMoney_real(String money_real) {
        this.money_real = money_real;
    }

    public String getFreight() {
        return freight;
    }

    public void setFreight(String freight) {
        this.freight = freight;
    }

    public String getPay_trade_no() {
        return pay_trade_no;
    }

    public void setPay_trade_no(String pay_trade_no) {
        this.pay_trade_no = pay_trade_no;
    }

    public String getPay_time() {
        return pay_time;
    }

    public void setPay_time(String pay_time) {
        this.pay_time = pay_time;
    }

    public String getShip_time() {
        return ship_time;
    }

    public void setShip_time(String ship_time) {
        this.ship_time = ship_time;
    }

    public String getConfirm_time() {
        return confirm_time;
    }

    public void setConfirm_time(String confirm_time) {
        this.confirm_time = confirm_time;
    }

    public String getFinish_time() {
        return finish_time;
    }

    public void setFinish_time(String finish_time) {
        this.finish_time = finish_time;
    }

    public String getBuyer_rate() {
        return buyer_rate;
    }

    public void setBuyer_rate(String buyer_rate) {
        this.buyer_rate = buyer_rate;
    }

    public String getIs_finish() {
        return is_finish;
    }

    public void setIs_finish(String is_finish) {
        this.is_finish = is_finish;
    }

    public String getStatus_title() {
        return status_title;
    }

    public void setStatus_title(String status_title) {
        this.status_title = status_title;
    }

    public List<Product> getList() {
        return list;
    }

    public void setList(List<Product> list) {
        this.list = list;
    }

    public String getAdd_time() {
        return add_time;
    }

    public void setAdd_time(String add_time) {
        this.add_time = add_time;
    }

    public String getPay_channel() {
        return pay_channel;
    }

    public void setPay_channel(String pay_channel) {
        this.pay_channel = pay_channel;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public RiderInfo getRider_info() {
        return rider_info;
    }

    public void setRider_info(RiderInfo rider_info) {
        this.rider_info = rider_info;
    }

    public static class Product implements Serializable {
        /**
         * id : 42
         * order_id : 39
         * product_id : 14
         * product_title : 松石 【普贤菩萨】
         * product_price : 5000.00
         * product_image : http://192.168.2.83Uploads/Product/Cover/5be401873a097.jpeg
         * product_num : 1
         * product_spec : []
         * buyer_rate : 0
         */

        private String            id;
        private String            order_id;
        private String            product_id;
        private String            product_title;
        private String            product_price;
        private String            product_image;
        private String            product_num;
        private String            buyer_rate;
        private List<ProductSpec> product_spec;


        private String spec_with_num;
        private String sum_money;

        public String getSum_money() {
            return "¥" + AmountUtils.moneyMul(String.valueOf(product_num), product_price);
        }

        public String getSpec_with_num() {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < product_spec.size(); i++) {
                ProductSpec productSpec = product_spec.get(i);
                sb.append(productSpec.spec_value_title);
                if (i != product_spec.size() - 1) {
                    sb.append(";");
                }
            }
            sb.append("\t\t");
            sb.append("数量x");
            sb.append(product_num);
            return sb.toString();
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getOrder_id() {
            return order_id;
        }

        public void setOrder_id(String order_id) {
            this.order_id = order_id;
        }

        public String getProduct_id() {
            return product_id;
        }

        public void setProduct_id(String product_id) {
            this.product_id = product_id;
        }

        public String getProduct_title() {
            return product_title;
        }

        public void setProduct_title(String product_title) {
            this.product_title = product_title;
        }

        public String getProduct_price() {
            return product_price;
        }

        public void setProduct_price(String product_price) {
            this.product_price = product_price;
        }

        public String getProduct_image() {
            return product_image;
        }

        public void setProduct_image(String product_image) {
            this.product_image = product_image;
        }

        public String getProduct_num() {
            return product_num;
        }

        public void setProduct_num(String product_num) {
            this.product_num = product_num;
        }

        public String getBuyer_rate() {
            return buyer_rate;
        }

        public void setBuyer_rate(String buyer_rate) {
            this.buyer_rate = buyer_rate;
        }

        public List<ProductSpec> getProduct_spec() {
            return product_spec;
        }

        public void setProduct_spec(List<ProductSpec> product_spec) {
            this.product_spec = product_spec;
        }

        public static class ProductSpec implements Serializable {
            /**
             * spec_id : 1
             * spec_title : 颜色
             * spec_value_id : 1
             * spec_value_title : 红色
             */

            private int    spec_id;
            private String spec_title;
            private String spec_value_id;
            private String spec_value_title;

            public int getSpec_id() {
                return spec_id;
            }

            public void setSpec_id(int spec_id) {
                this.spec_id = spec_id;
            }

            public String getSpec_title() {
                return spec_title;
            }

            public void setSpec_title(String spec_title) {
                this.spec_title = spec_title;
            }

            public String getSpec_value_id() {
                return spec_value_id;
            }

            public void setSpec_value_id(String spec_value_id) {
                this.spec_value_id = spec_value_id;
            }

            public String getSpec_value_title() {
                return spec_value_title;
            }

            public void setSpec_value_title(String spec_value_title) {
                this.spec_value_title = spec_value_title;
            }
        }
    }

    public static class RiderInfo {
        /**
         * id : 5
         * account : 18618499056
         * nickname : 雷达
         * map_lat : 30.501766221788195
         * map_lng : 114.4137890625
         */

        @SerializedName("id")
        private String idX;
        private String account;
        private String nickname;
        private String map_lat;
        private String map_lng;

        public String getIdX() {
            return idX;
        }

        public void setIdX(String idX) {
            this.idX = idX;
        }

        public String getAccount() {
            return account;
        }

        public void setAccount(String account) {
            this.account = account;
        }

        public String getNickname() {
            return nickname;
        }

        public void setNickname(String nickname) {
            this.nickname = nickname;
        }

        public String getMap_lat() {
            return map_lat;
        }

        public void setMap_lat(String map_lat) {
            this.map_lat = map_lat;
        }

        public String getMap_lng() {
            return map_lng;
        }

        public void setMap_lng(String map_lng) {
            this.map_lng = map_lng;
        }
    }
}
