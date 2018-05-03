package com.jzbwlkj.leifeng.ui.bean;

import java.util.List;

/**
 * Created by Administrator on 2018/4/24.
 */

public class MallBean {

    /**
     * type_name : 生活用品
     * list : [{"id":1,"goods_name":"测试商品","type":0,"pic":"http://leifeng.jzbwlkj.com/upload/admin/20180331/5b657db4b37d382f71f1f3d16b47556a.jpg","price":243,"unit":"只","is_show":1,"add_time":1524210017}]
     */

    private String type_name;
    private List<ListBean> list;

    public String getType_name() {
        return type_name;
    }

    public void setType_name(String type_name) {
        this.type_name = type_name;
    }

    public List<ListBean> getList() {
        return list;
    }

    public void setList(List<ListBean> list) {
        this.list = list;
    }

    public static class ListBean {
        /**
         * id : 1
         * goods_name : 测试商品
         * type : 0
         * pic : http://leifeng.jzbwlkj.com/upload/admin/20180331/5b657db4b37d382f71f1f3d16b47556a.jpg
         * price : 243
         * unit : 只
         * is_show : 1
         * add_time : 1524210017
         */

        private int id;
        private String goods_name;
        private int type;
        private String pic;
        private int price;
        private String unit;
        private int is_show;
        private int add_time;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getGoods_name() {
            return goods_name;
        }

        public void setGoods_name(String goods_name) {
            this.goods_name = goods_name;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public String getPic() {
            return pic;
        }

        public void setPic(String pic) {
            this.pic = pic;
        }

        public int getPrice() {
            return price;
        }

        public void setPrice(int price) {
            this.price = price;
        }

        public String getUnit() {
            return unit;
        }

        public void setUnit(String unit) {
            this.unit = unit;
        }

        public int getIs_show() {
            return is_show;
        }

        public void setIs_show(int is_show) {
            this.is_show = is_show;
        }

        public int getAdd_time() {
            return add_time;
        }

        public void setAdd_time(int add_time) {
            this.add_time = add_time;
        }
    }

}
