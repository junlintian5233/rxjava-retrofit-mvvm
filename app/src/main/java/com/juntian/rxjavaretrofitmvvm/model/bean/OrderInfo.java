package com.juntian.rxjavaretrofitmvvm.model.bean;

import com.juntian.rxjavaretrofitmvvm.constant.OrderState;
import com.juntian.basicapp.utils.AmountUtils;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * @作者:TJ
 * @时间:2019/8/12
 * @描述:
 */
public class OrderInfo {


    /**
     * id : 49
     * member_id : 100053
     * store_id : 2
     * store_title : 湖北军鑫劳保有限公司
     * order_no : 1908274129327689
     * money_real : 10.00
     * freight : 0.00
     * status : 10
     * assist_status : 0
     * buyer_rate : 0
     * add_time : 2019-08-27 17:21:29
     * status_title : 等待买家付款
     * list : [{"id":"86","order_id":"49","product_id":"297","product_sku_id":"2869","product_title":"保安服","product_price":"10.00","product_image":"https://www.51laobao.net//chaozudianshang/Uploads/Product/Cover/5d033c88e9955.jpg","product_num":"1","product_spec":[{"spec_id":1,"spec_title":"颜色","spec_value_id":"1","spec_value_title":"红色"}]}]
     */

    private String             id;
    private String             member_id;
    private String             store_id;
    private String             store_title;
    private String             order_no;
    private String             money_real;
    private String             freight;
    private String             status;
    private String             assist_status;
    private String             buyer_rate;//0未评价 1评价
    private String             add_time;
    private String             status_title;
    private List<ProductOrder> list;

    private String leftButton;
    private String rightButton;


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
            default:
                return "";
        }
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getAssist_status() {
        return assist_status;
    }

    public void setAssist_status(String assist_status) {
        this.assist_status = assist_status;
    }

    public String getBuyer_rate() {
        return buyer_rate;
    }

    public void setBuyer_rate(String buyer_rate) {
        this.buyer_rate = buyer_rate;
    }

    public String getAdd_time() {
        return add_time;
    }

    public void setAdd_time(String add_time) {
        this.add_time = add_time;
    }

    public String getStatus_title() {
        return status_title;
    }

    public void setStatus_title(String status_title) {
        this.status_title = status_title;
    }


    public List<ProductOrder> getList() {
        return list;
    }

    public void setList(List<ProductOrder> list) {
        this.list = list;
    }

    public static class ProductOrder {
        /**
         * id : 86
         * order_id : 49
         * product_id : 297
         * product_sku_id : 2869
         * product_title : 保安服
         * product_price : 10.00
         * product_image : https://www.51laobao.net//chaozudianshang/Uploads/Product/Cover/5d033c88e9955.jpg
         * product_num : 1
         * product_spec : [{"spec_id":1,"spec_title":"颜色","spec_value_id":"1","spec_value_title":"红色"}]
         */

        @SerializedName("id")
        private String            idX;
        private String            order_id;
        private String            product_id;
        private String            product_sku_id;
        private String            product_title;
        private String            product_price;
        private String            product_image;
        private String            product_num;
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


        public String getIdX() {
            return idX;
        }

        public void setIdX(String idX) {
            this.idX = idX;
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

        public String getProduct_sku_id() {
            return product_sku_id;
        }

        public void setProduct_sku_id(String product_sku_id) {
            this.product_sku_id = product_sku_id;
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

        public List<ProductSpec> getProduct_spec() {
            return product_spec;
        }

        public void setProduct_spec(List<ProductSpec> product_spec) {
            this.product_spec = product_spec;
        }

        public static class ProductSpec {
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
}
