package com.github.pagehelper.model;

import com.github.pagehelper.async.MybatisAsyncPage;
import com.github.pagehelper.async.SplitSize;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author lvjun
 */
@SplitSize(size = 1)
public class RsInventoryCondition extends MybatisAsyncPage {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    private Integer limit;

    private Integer offset;

    public RsInventoryCondition() {
        oredCriteria = new ArrayList<Criteria>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }

    public Integer getLimit() {
        return limit;
    }

    public void setOffset(Integer offset) {
        this.offset = offset;
    }

    public Integer getOffset() {
        return offset;
    }

    protected abstract static class GeneratedCriteria implements Serializable{
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andInventoryIdIsNull() {
            addCriterion("inventory_id is null");
            return (Criteria) this;
        }

        public Criteria andInventoryIdIsNotNull() {
            addCriterion("inventory_id is not null");
            return (Criteria) this;
        }

        public Criteria andInventoryIdEqualTo(Integer value) {
            addCriterion("inventory_id =", value, "inventoryId");
            return (Criteria) this;
        }

        public Criteria andInventoryIdNotEqualTo(Integer value) {
            addCriterion("inventory_id <>", value, "inventoryId");
            return (Criteria) this;
        }

        public Criteria andInventoryIdGreaterThan(Integer value) {
            addCriterion("inventory_id >", value, "inventoryId");
            return (Criteria) this;
        }

        public Criteria andInventoryIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("inventory_id >=", value, "inventoryId");
            return (Criteria) this;
        }

        public Criteria andInventoryIdLessThan(Integer value) {
            addCriterion("inventory_id <", value, "inventoryId");
            return (Criteria) this;
        }

        public Criteria andInventoryIdLessThanOrEqualTo(Integer value) {
            addCriterion("inventory_id <=", value, "inventoryId");
            return (Criteria) this;
        }

        public Criteria andInventoryIdIn(List<Integer> values) {
            addCriterion("inventory_id in", values, "inventoryId");
            return (Criteria) this;
        }

        public Criteria andInventoryIdNotIn(List<Integer> values) {
            addCriterion("inventory_id not in", values, "inventoryId");
            return (Criteria) this;
        }

        public Criteria andInventoryIdBetween(Integer value1, Integer value2) {
            addCriterion("inventory_id between", value1, value2, "inventoryId");
            return (Criteria) this;
        }

        public Criteria andInventoryIdNotBetween(Integer value1, Integer value2) {
            addCriterion("inventory_id not between", value1, value2, "inventoryId");
            return (Criteria) this;
        }

        public Criteria andSiteIdIsNull() {
            addCriterion("site_id is null");
            return (Criteria) this;
        }

        public Criteria andSiteIdIsNotNull() {
            addCriterion("site_id is not null");
            return (Criteria) this;
        }

        public Criteria andSiteIdEqualTo(Integer value) {
            addCriterion("site_id =", value, "siteId");
            return (Criteria) this;
        }

        public Criteria andSiteIdNotEqualTo(Integer value) {
            addCriterion("site_id <>", value, "siteId");
            return (Criteria) this;
        }

        public Criteria andSiteIdGreaterThan(Integer value) {
            addCriterion("site_id >", value, "siteId");
            return (Criteria) this;
        }

        public Criteria andSiteIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("site_id >=", value, "siteId");
            return (Criteria) this;
        }

        public Criteria andSiteIdLessThan(Integer value) {
            addCriterion("site_id <", value, "siteId");
            return (Criteria) this;
        }

        public Criteria andSiteIdLessThanOrEqualTo(Integer value) {
            addCriterion("site_id <=", value, "siteId");
            return (Criteria) this;
        }

        public Criteria andSiteIdIn(List<Integer> values) {
            addCriterion("site_id in", values, "siteId");
            return (Criteria) this;
        }

        public Criteria andSiteIdNotIn(List<Integer> values) {
            addCriterion("site_id not in", values, "siteId");
            return (Criteria) this;
        }

        public Criteria andSiteIdBetween(Integer value1, Integer value2) {
            addCriterion("site_id between", value1, value2, "siteId");
            return (Criteria) this;
        }

        public Criteria andSiteIdNotBetween(Integer value1, Integer value2) {
            addCriterion("site_id not between", value1, value2, "siteId");
            return (Criteria) this;
        }

        public Criteria andGoodsIdIsNull() {
            addCriterion("goods_id is null");
            return (Criteria) this;
        }

        public Criteria andGoodsIdIsNotNull() {
            addCriterion("goods_id is not null");
            return (Criteria) this;
        }

        public Criteria andGoodsIdEqualTo(Integer value) {
            addCriterion("goods_id =", value, "goodsId");
            return (Criteria) this;
        }

        public Criteria andGoodsIdNotEqualTo(Integer value) {
            addCriterion("goods_id <>", value, "goodsId");
            return (Criteria) this;
        }

        public Criteria andGoodsIdGreaterThan(Integer value) {
            addCriterion("goods_id >", value, "goodsId");
            return (Criteria) this;
        }

        public Criteria andGoodsIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("goods_id >=", value, "goodsId");
            return (Criteria) this;
        }

        public Criteria andGoodsIdLessThan(Integer value) {
            addCriterion("goods_id <", value, "goodsId");
            return (Criteria) this;
        }

        public Criteria andGoodsIdLessThanOrEqualTo(Integer value) {
            addCriterion("goods_id <=", value, "goodsId");
            return (Criteria) this;
        }

        public Criteria andGoodsIdIn(List<Integer> values) {
            addCriterion("goods_id in", values, "goodsId");
            return (Criteria) this;
        }

        public Criteria andGoodsIdNotIn(List<Integer> values) {
            addCriterion("goods_id not in", values, "goodsId");
            return (Criteria) this;
        }

        public Criteria andGoodsIdBetween(Integer value1, Integer value2) {
            addCriterion("goods_id between", value1, value2, "goodsId");
            return (Criteria) this;
        }

        public Criteria andGoodsIdNotBetween(Integer value1, Integer value2) {
            addCriterion("goods_id not between", value1, value2, "goodsId");
            return (Criteria) this;
        }

        public Criteria andAttrValueIdIsNull() {
            addCriterion("attr_value_id is null");
            return (Criteria) this;
        }

        public Criteria andAttrValueIdIsNotNull() {
            addCriterion("attr_value_id is not null");
            return (Criteria) this;
        }

        public Criteria andAttrValueIdEqualTo(Integer value) {
            addCriterion("attr_value_id =", value, "attrValueId");
            return (Criteria) this;
        }

        public Criteria andAttrValueIdNotEqualTo(Integer value) {
            addCriterion("attr_value_id <>", value, "attrValueId");
            return (Criteria) this;
        }

        public Criteria andAttrValueIdGreaterThan(Integer value) {
            addCriterion("attr_value_id >", value, "attrValueId");
            return (Criteria) this;
        }

        public Criteria andAttrValueIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("attr_value_id >=", value, "attrValueId");
            return (Criteria) this;
        }

        public Criteria andAttrValueIdLessThan(Integer value) {
            addCriterion("attr_value_id <", value, "attrValueId");
            return (Criteria) this;
        }

        public Criteria andAttrValueIdLessThanOrEqualTo(Integer value) {
            addCriterion("attr_value_id <=", value, "attrValueId");
            return (Criteria) this;
        }

        public Criteria andAttrValueIdIn(List<Integer> values) {
            addCriterion("attr_value_id in", values, "attrValueId");
            return (Criteria) this;
        }

        public Criteria andAttrValueIdNotIn(List<Integer> values) {
            addCriterion("attr_value_id not in", values, "attrValueId");
            return (Criteria) this;
        }

        public Criteria andAttrValueIdBetween(Integer value1, Integer value2) {
            addCriterion("attr_value_id between", value1, value2, "attrValueId");
            return (Criteria) this;
        }

        public Criteria andAttrValueIdNotBetween(Integer value1, Integer value2) {
            addCriterion("attr_value_id not between", value1, value2, "attrValueId");
            return (Criteria) this;
        }

        public Criteria andGoodsSnIsNull() {
            addCriterion("goods_sn is null");
            return (Criteria) this;
        }

        public Criteria andGoodsSnIsNotNull() {
            addCriterion("goods_sn is not null");
            return (Criteria) this;
        }

        public Criteria andGoodsSnEqualTo(String value) {
            addCriterion("goods_sn =", value, "goodsSn");
            return (Criteria) this;
        }

        public Criteria andGoodsSnNotEqualTo(String value) {
            addCriterion("goods_sn <>", value, "goodsSn");
            return (Criteria) this;
        }

        public Criteria andGoodsSnGreaterThan(String value) {
            addCriterion("goods_sn >", value, "goodsSn");
            return (Criteria) this;
        }

        public Criteria andGoodsSnGreaterThanOrEqualTo(String value) {
            addCriterion("goods_sn >=", value, "goodsSn");
            return (Criteria) this;
        }

        public Criteria andGoodsSnLessThan(String value) {
            addCriterion("goods_sn <", value, "goodsSn");
            return (Criteria) this;
        }

        public Criteria andGoodsSnLessThanOrEqualTo(String value) {
            addCriterion("goods_sn <=", value, "goodsSn");
            return (Criteria) this;
        }

        public Criteria andGoodsSnLike(String value) {
            addCriterion("goods_sn like", value, "goodsSn");
            return (Criteria) this;
        }

        public Criteria andGoodsSnNotLike(String value) {
            addCriterion("goods_sn not like", value, "goodsSn");
            return (Criteria) this;
        }

        public Criteria andGoodsSnIn(List<String> values) {
            addCriterion("goods_sn in", values, "goodsSn");
            return (Criteria) this;
        }

        public Criteria andGoodsSnNotIn(List<String> values) {
            addCriterion("goods_sn not in", values, "goodsSn");
            return (Criteria) this;
        }

        public Criteria andGoodsSnBetween(String value1, String value2) {
            addCriterion("goods_sn between", value1, value2, "goodsSn");
            return (Criteria) this;
        }

        public Criteria andGoodsSnNotBetween(String value1, String value2) {
            addCriterion("goods_sn not between", value1, value2, "goodsSn");
            return (Criteria) this;
        }

        public Criteria andGoodsAttrIsNull() {
            addCriterion("goods_attr is null");
            return (Criteria) this;
        }

        public Criteria andGoodsAttrIsNotNull() {
            addCriterion("goods_attr is not null");
            return (Criteria) this;
        }

        public Criteria andGoodsAttrEqualTo(String value) {
            addCriterion("goods_attr =", value, "goodsAttr");
            return (Criteria) this;
        }

        public Criteria andGoodsAttrNotEqualTo(String value) {
            addCriterion("goods_attr <>", value, "goodsAttr");
            return (Criteria) this;
        }

        public Criteria andGoodsAttrGreaterThan(String value) {
            addCriterion("goods_attr >", value, "goodsAttr");
            return (Criteria) this;
        }

        public Criteria andGoodsAttrGreaterThanOrEqualTo(String value) {
            addCriterion("goods_attr >=", value, "goodsAttr");
            return (Criteria) this;
        }

        public Criteria andGoodsAttrLessThan(String value) {
            addCriterion("goods_attr <", value, "goodsAttr");
            return (Criteria) this;
        }

        public Criteria andGoodsAttrLessThanOrEqualTo(String value) {
            addCriterion("goods_attr <=", value, "goodsAttr");
            return (Criteria) this;
        }

        public Criteria andGoodsAttrLike(String value) {
            addCriterion("goods_attr like", value, "goodsAttr");
            return (Criteria) this;
        }

        public Criteria andGoodsAttrNotLike(String value) {
            addCriterion("goods_attr not like", value, "goodsAttr");
            return (Criteria) this;
        }

        public Criteria andGoodsAttrIn(List<String> values) {
            addCriterion("goods_attr in", values, "goodsAttr");
            return (Criteria) this;
        }

        public Criteria andGoodsAttrNotIn(List<String> values) {
            addCriterion("goods_attr not in", values, "goodsAttr");
            return (Criteria) this;
        }

        public Criteria andGoodsAttrBetween(String value1, String value2) {
            addCriterion("goods_attr between", value1, value2, "goodsAttr");
            return (Criteria) this;
        }

        public Criteria andGoodsAttrNotBetween(String value1, String value2) {
            addCriterion("goods_attr not between", value1, value2, "goodsAttr");
            return (Criteria) this;
        }

        public Criteria andGoodsThumbIsNull() {
            addCriterion("goods_thumb is null");
            return (Criteria) this;
        }

        public Criteria andGoodsThumbIsNotNull() {
            addCriterion("goods_thumb is not null");
            return (Criteria) this;
        }

        public Criteria andGoodsThumbEqualTo(String value) {
            addCriterion("goods_thumb =", value, "goodsThumb");
            return (Criteria) this;
        }

        public Criteria andGoodsThumbNotEqualTo(String value) {
            addCriterion("goods_thumb <>", value, "goodsThumb");
            return (Criteria) this;
        }

        public Criteria andGoodsThumbGreaterThan(String value) {
            addCriterion("goods_thumb >", value, "goodsThumb");
            return (Criteria) this;
        }

        public Criteria andGoodsThumbGreaterThanOrEqualTo(String value) {
            addCriterion("goods_thumb >=", value, "goodsThumb");
            return (Criteria) this;
        }

        public Criteria andGoodsThumbLessThan(String value) {
            addCriterion("goods_thumb <", value, "goodsThumb");
            return (Criteria) this;
        }

        public Criteria andGoodsThumbLessThanOrEqualTo(String value) {
            addCriterion("goods_thumb <=", value, "goodsThumb");
            return (Criteria) this;
        }

        public Criteria andGoodsThumbLike(String value) {
            addCriterion("goods_thumb like", value, "goodsThumb");
            return (Criteria) this;
        }

        public Criteria andGoodsThumbNotLike(String value) {
            addCriterion("goods_thumb not like", value, "goodsThumb");
            return (Criteria) this;
        }

        public Criteria andGoodsThumbIn(List<String> values) {
            addCriterion("goods_thumb in", values, "goodsThumb");
            return (Criteria) this;
        }

        public Criteria andGoodsThumbNotIn(List<String> values) {
            addCriterion("goods_thumb not in", values, "goodsThumb");
            return (Criteria) this;
        }

        public Criteria andGoodsThumbBetween(String value1, String value2) {
            addCriterion("goods_thumb between", value1, value2, "goodsThumb");
            return (Criteria) this;
        }

        public Criteria andGoodsThumbNotBetween(String value1, String value2) {
            addCriterion("goods_thumb not between", value1, value2, "goodsThumb");
            return (Criteria) this;
        }

        public Criteria andInventoryTypeIsNull() {
            addCriterion("inventory_type is null");
            return (Criteria) this;
        }

        public Criteria andInventoryTypeIsNotNull() {
            addCriterion("inventory_type is not null");
            return (Criteria) this;
        }

        public Criteria andInventoryTypeEqualTo(Byte value) {
            addCriterion("inventory_type =", value, "inventoryType");
            return (Criteria) this;
        }

        public Criteria andInventoryTypeNotEqualTo(Byte value) {
            addCriterion("inventory_type <>", value, "inventoryType");
            return (Criteria) this;
        }

        public Criteria andInventoryTypeGreaterThan(Byte value) {
            addCriterion("inventory_type >", value, "inventoryType");
            return (Criteria) this;
        }

        public Criteria andInventoryTypeGreaterThanOrEqualTo(Byte value) {
            addCriterion("inventory_type >=", value, "inventoryType");
            return (Criteria) this;
        }

        public Criteria andInventoryTypeLessThan(Byte value) {
            addCriterion("inventory_type <", value, "inventoryType");
            return (Criteria) this;
        }

        public Criteria andInventoryTypeLessThanOrEqualTo(Byte value) {
            addCriterion("inventory_type <=", value, "inventoryType");
            return (Criteria) this;
        }

        public Criteria andInventoryTypeIn(List<Byte> values) {
            addCriterion("inventory_type in", values, "inventoryType");
            return (Criteria) this;
        }

        public Criteria andInventoryTypeNotIn(List<Byte> values) {
            addCriterion("inventory_type not in", values, "inventoryType");
            return (Criteria) this;
        }

        public Criteria andInventoryTypeBetween(Byte value1, Byte value2) {
            addCriterion("inventory_type between", value1, value2, "inventoryType");
            return (Criteria) this;
        }

        public Criteria andInventoryTypeNotBetween(Byte value1, Byte value2) {
            addCriterion("inventory_type not between", value1, value2, "inventoryType");
            return (Criteria) this;
        }

        public Criteria andOrderGoodsIdIsNull() {
            addCriterion("order_goods_id is null");
            return (Criteria) this;
        }

        public Criteria andOrderGoodsIdIsNotNull() {
            addCriterion("order_goods_id is not null");
            return (Criteria) this;
        }

        public Criteria andOrderGoodsIdEqualTo(Long value) {
            addCriterion("order_goods_id =", value, "orderGoodsId");
            return (Criteria) this;
        }

        public Criteria andOrderGoodsIdNotEqualTo(Long value) {
            addCriterion("order_goods_id <>", value, "orderGoodsId");
            return (Criteria) this;
        }

        public Criteria andOrderGoodsIdGreaterThan(Long value) {
            addCriterion("order_goods_id >", value, "orderGoodsId");
            return (Criteria) this;
        }

        public Criteria andOrderGoodsIdGreaterThanOrEqualTo(Long value) {
            addCriterion("order_goods_id >=", value, "orderGoodsId");
            return (Criteria) this;
        }

        public Criteria andOrderGoodsIdLessThan(Long value) {
            addCriterion("order_goods_id <", value, "orderGoodsId");
            return (Criteria) this;
        }

        public Criteria andOrderGoodsIdLessThanOrEqualTo(Long value) {
            addCriterion("order_goods_id <=", value, "orderGoodsId");
            return (Criteria) this;
        }

        public Criteria andOrderGoodsIdIn(List<Long> values) {
            addCriterion("order_goods_id in", values, "orderGoodsId");
            return (Criteria) this;
        }

        public Criteria andOrderGoodsIdNotIn(List<Long> values) {
            addCriterion("order_goods_id not in", values, "orderGoodsId");
            return (Criteria) this;
        }

        public Criteria andOrderGoodsIdBetween(Integer value1, Integer value2) {
            addCriterion("order_goods_id between", value1, value2, "orderGoodsId");
            return (Criteria) this;
        }

        public Criteria andOrderGoodsIdNotBetween(Long value1, Long value2) {
            addCriterion("order_goods_id not between", value1, value2, "orderGoodsId");
            return (Criteria) this;
        }

        public Criteria andQuantityIsNull() {
            addCriterion("quantity is null");
            return (Criteria) this;
        }

        public Criteria andQuantityIsNotNull() {
            addCriterion("quantity is not null");
            return (Criteria) this;
        }

        public Criteria andQuantityEqualTo(Integer value) {
            addCriterion("quantity =", value, "quantity");
            return (Criteria) this;
        }

        public Criteria andQuantityNotEqualTo(Integer value) {
            addCriterion("quantity <>", value, "quantity");
            return (Criteria) this;
        }

        public Criteria andQuantityGreaterThan(Integer value) {
            addCriterion("quantity >", value, "quantity");
            return (Criteria) this;
        }

        public Criteria andQuantityGreaterThanOrEqualTo(Integer value) {
            addCriterion("quantity >=", value, "quantity");
            return (Criteria) this;
        }

        public Criteria andQuantityLessThan(Integer value) {
            addCriterion("quantity <", value, "quantity");
            return (Criteria) this;
        }

        public Criteria andQuantityLessThanOrEqualTo(Integer value) {
            addCriterion("quantity <=", value, "quantity");
            return (Criteria) this;
        }

        public Criteria andQuantityIn(List<Integer> values) {
            addCriterion("quantity in", values, "quantity");
            return (Criteria) this;
        }

        public Criteria andQuantityNotIn(List<Integer> values) {
            addCriterion("quantity not in", values, "quantity");
            return (Criteria) this;
        }

        public Criteria andQuantityBetween(Integer value1, Integer value2) {
            addCriterion("quantity between", value1, value2, "quantity");
            return (Criteria) this;
        }

        public Criteria andQuantityNotBetween(Integer value1, Integer value2) {
            addCriterion("quantity not between", value1, value2, "quantity");
            return (Criteria) this;
        }

        public Criteria andAdminNameIsNull() {
            addCriterion("admin_name is null");
            return (Criteria) this;
        }

        public Criteria andAdminNameIsNotNull() {
            addCriterion("admin_name is not null");
            return (Criteria) this;
        }

        public Criteria andAdminNameEqualTo(String value) {
            addCriterion("admin_name =", value, "adminName");
            return (Criteria) this;
        }

        public Criteria andAdminNameNotEqualTo(String value) {
            addCriterion("admin_name <>", value, "adminName");
            return (Criteria) this;
        }

        public Criteria andAdminNameGreaterThan(String value) {
            addCriterion("admin_name >", value, "adminName");
            return (Criteria) this;
        }

        public Criteria andAdminNameGreaterThanOrEqualTo(String value) {
            addCriterion("admin_name >=", value, "adminName");
            return (Criteria) this;
        }

        public Criteria andAdminNameLessThan(String value) {
            addCriterion("admin_name <", value, "adminName");
            return (Criteria) this;
        }

        public Criteria andAdminNameLessThanOrEqualTo(String value) {
            addCriterion("admin_name <=", value, "adminName");
            return (Criteria) this;
        }

        public Criteria andAdminNameLike(String value) {
            addCriterion("admin_name like", value, "adminName");
            return (Criteria) this;
        }

        public Criteria andAdminNameNotLike(String value) {
            addCriterion("admin_name not like", value, "adminName");
            return (Criteria) this;
        }

        public Criteria andAdminNameIn(List<String> values) {
            addCriterion("admin_name in", values, "adminName");
            return (Criteria) this;
        }

        public Criteria andAdminNameNotIn(List<String> values) {
            addCriterion("admin_name not in", values, "adminName");
            return (Criteria) this;
        }

        public Criteria andAdminNameBetween(String value1, String value2) {
            addCriterion("admin_name between", value1, value2, "adminName");
            return (Criteria) this;
        }

        public Criteria andAdminNameNotBetween(String value1, String value2) {
            addCriterion("admin_name not between", value1, value2, "adminName");
            return (Criteria) this;
        }

        public Criteria andAddTimeIsNull() {
            addCriterion("add_time is null");
            return (Criteria) this;
        }

        public Criteria andAddTimeIsNotNull() {
            addCriterion("add_time is not null");
            return (Criteria) this;
        }

        public Criteria andAddTimeEqualTo(Date value) {
            addCriterion("add_time =", value, "addTime");
            return (Criteria) this;
        }

        public Criteria andAddTimeNotEqualTo(Date value) {
            addCriterion("add_time <>", value, "addTime");
            return (Criteria) this;
        }

        public Criteria andAddTimeGreaterThan(Date value) {
            addCriterion("add_time >", value, "addTime");
            return (Criteria) this;
        }

        public Criteria andAddTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("add_time >=", value, "addTime");
            return (Criteria) this;
        }

        public Criteria andAddTimeLessThan(Date value) {
            addCriterion("add_time <", value, "addTime");
            return (Criteria) this;
        }

        public Criteria andAddTimeLessThanOrEqualTo(Date value) {
            addCriterion("add_time <=", value, "addTime");
            return (Criteria) this;
        }

        public Criteria andAddTimeIn(List<Date> values) {
            addCriterion("add_time in", values, "addTime");
            return (Criteria) this;
        }

        public Criteria andAddTimeNotIn(List<Date> values) {
            addCriterion("add_time not in", values, "addTime");
            return (Criteria) this;
        }

        public Criteria andAddTimeBetween(Date value1, Date value2) {
            addCriterion("add_time between", value1, value2, "addTime");
            return (Criteria) this;
        }

        public Criteria andAddTimeNotBetween(Date value1, Date value2) {
            addCriterion("add_time not between", value1, value2, "addTime");
            return (Criteria) this;
        }

        public Criteria andInventoryStatusIsNull() {
            addCriterion("inventory_status is null");
            return (Criteria) this;
        }

        public Criteria andInventoryStatusIsNotNull() {
            addCriterion("inventory_status is not null");
            return (Criteria) this;
        }

        public Criteria andInventoryStatusEqualTo(Byte value) {
            addCriterion("inventory_status =", value, "inventoryStatus");
            return (Criteria) this;
        }

        public Criteria andInventoryStatusNotEqualTo(Byte value) {
            addCriterion("inventory_status <>", value, "inventoryStatus");
            return (Criteria) this;
        }

        public Criteria andInventoryStatusGreaterThan(Byte value) {
            addCriterion("inventory_status >", value, "inventoryStatus");
            return (Criteria) this;
        }

        public Criteria andInventoryStatusGreaterThanOrEqualTo(Byte value) {
            addCriterion("inventory_status >=", value, "inventoryStatus");
            return (Criteria) this;
        }

        public Criteria andInventoryStatusLessThan(Byte value) {
            addCriterion("inventory_status <", value, "inventoryStatus");
            return (Criteria) this;
        }

        public Criteria andInventoryStatusLessThanOrEqualTo(Byte value) {
            addCriterion("inventory_status <=", value, "inventoryStatus");
            return (Criteria) this;
        }

        public Criteria andInventoryStatusIn(List<Byte> values) {
            addCriterion("inventory_status in", values, "inventoryStatus");
            return (Criteria) this;
        }

        public Criteria andInventoryStatusNotIn(List<Byte> values) {
            addCriterion("inventory_status not in", values, "inventoryStatus");
            return (Criteria) this;
        }

        public Criteria andInventoryStatusBetween(Byte value1, Byte value2) {
            addCriterion("inventory_status between", value1, value2, "inventoryStatus");
            return (Criteria) this;
        }

        public Criteria andInventoryStatusNotBetween(Byte value1, Byte value2) {
            addCriterion("inventory_status not between", value1, value2, "inventoryStatus");
            return (Criteria) this;
        }

        public Criteria andSupplierLinkmanIsNull() {
            addCriterion("supplier_linkman is null");
            return (Criteria) this;
        }

        public Criteria andSupplierLinkmanIsNotNull() {
            addCriterion("supplier_linkman is not null");
            return (Criteria) this;
        }

        public Criteria andSupplierLinkmanEqualTo(String value) {
            addCriterion("supplier_linkman =", value, "supplierLinkman");
            return (Criteria) this;
        }

        public Criteria andSupplierLinkmanNotEqualTo(String value) {
            addCriterion("supplier_linkman <>", value, "supplierLinkman");
            return (Criteria) this;
        }

        public Criteria andSupplierLinkmanGreaterThan(String value) {
            addCriterion("supplier_linkman >", value, "supplierLinkman");
            return (Criteria) this;
        }

        public Criteria andSupplierLinkmanGreaterThanOrEqualTo(String value) {
            addCriterion("supplier_linkman >=", value, "supplierLinkman");
            return (Criteria) this;
        }

        public Criteria andSupplierLinkmanLessThan(String value) {
            addCriterion("supplier_linkman <", value, "supplierLinkman");
            return (Criteria) this;
        }

        public Criteria andSupplierLinkmanLessThanOrEqualTo(String value) {
            addCriterion("supplier_linkman <=", value, "supplierLinkman");
            return (Criteria) this;
        }

        public Criteria andSupplierLinkmanLike(String value) {
            addCriterion("supplier_linkman like", value, "supplierLinkman");
            return (Criteria) this;
        }

        public Criteria andSupplierLinkmanNotLike(String value) {
            addCriterion("supplier_linkman not like", value, "supplierLinkman");
            return (Criteria) this;
        }

        public Criteria andSupplierLinkmanIn(List<String> values) {
            addCriterion("supplier_linkman in", values, "supplierLinkman");
            return (Criteria) this;
        }

        public Criteria andSupplierLinkmanNotIn(List<String> values) {
            addCriterion("supplier_linkman not in", values, "supplierLinkman");
            return (Criteria) this;
        }

        public Criteria andSupplierLinkmanBetween(String value1, String value2) {
            addCriterion("supplier_linkman between", value1, value2, "supplierLinkman");
            return (Criteria) this;
        }

        public Criteria andSupplierLinkmanNotBetween(String value1, String value2) {
            addCriterion("supplier_linkman not between", value1, value2, "supplierLinkman");
            return (Criteria) this;
        }

        public Criteria andHuoweiNameIsNull() {
            addCriterion("huowei_name is null");
            return (Criteria) this;
        }

        public Criteria andHuoweiNameIsNotNull() {
            addCriterion("huowei_name is not null");
            return (Criteria) this;
        }

        public Criteria andHuoweiNameEqualTo(String value) {
            addCriterion("huowei_name =", value, "huoweiName");
            return (Criteria) this;
        }

        public Criteria andHuoweiNameNotEqualTo(String value) {
            addCriterion("huowei_name <>", value, "huoweiName");
            return (Criteria) this;
        }

        public Criteria andHuoweiNameGreaterThan(String value) {
            addCriterion("huowei_name >", value, "huoweiName");
            return (Criteria) this;
        }

        public Criteria andHuoweiNameGreaterThanOrEqualTo(String value) {
            addCriterion("huowei_name >=", value, "huoweiName");
            return (Criteria) this;
        }

        public Criteria andHuoweiNameLessThan(String value) {
            addCriterion("huowei_name <", value, "huoweiName");
            return (Criteria) this;
        }

        public Criteria andHuoweiNameLessThanOrEqualTo(String value) {
            addCriterion("huowei_name <=", value, "huoweiName");
            return (Criteria) this;
        }

        public Criteria andHuoweiNameLike(String value) {
            addCriterion("huowei_name like", value, "huoweiName");
            return (Criteria) this;
        }

        public Criteria andHuoweiNameNotLike(String value) {
            addCriterion("huowei_name not like", value, "huoweiName");
            return (Criteria) this;
        }

        public Criteria andHuoweiNameIn(List<String> values) {
            addCriterion("huowei_name in", values, "huoweiName");
            return (Criteria) this;
        }

        public Criteria andHuoweiNameNotIn(List<String> values) {
            addCriterion("huowei_name not in", values, "huoweiName");
            return (Criteria) this;
        }

        public Criteria andHuoweiNameBetween(String value1, String value2) {
            addCriterion("huowei_name between", value1, value2, "huoweiName");
            return (Criteria) this;
        }

        public Criteria andHuoweiNameNotBetween(String value1, String value2) {
            addCriterion("huowei_name not between", value1, value2, "huoweiName");
            return (Criteria) this;
        }

        public Criteria andSupplierIdIsNull() {
            addCriterion("supplier_id is null");
            return (Criteria) this;
        }

        public Criteria andSupplierIdIsNotNull() {
            addCriterion("supplier_id is not null");
            return (Criteria) this;
        }

        public Criteria andSupplierIdEqualTo(String value) {
            addCriterion("supplier_id =", value, "supplierId");
            return (Criteria) this;
        }

        public Criteria andSupplierIdNotEqualTo(String value) {
            addCriterion("supplier_id <>", value, "supplierId");
            return (Criteria) this;
        }

        public Criteria andSupplierIdGreaterThan(String value) {
            addCriterion("supplier_id >", value, "supplierId");
            return (Criteria) this;
        }

        public Criteria andSupplierIdGreaterThanOrEqualTo(String value) {
            addCriterion("supplier_id >=", value, "supplierId");
            return (Criteria) this;
        }

        public Criteria andSupplierIdLessThan(String value) {
            addCriterion("supplier_id <", value, "supplierId");
            return (Criteria) this;
        }

        public Criteria andSupplierIdLessThanOrEqualTo(String value) {
            addCriterion("supplier_id <=", value, "supplierId");
            return (Criteria) this;
        }

        public Criteria andSupplierIdLike(String value) {
            addCriterion("supplier_id like", value, "supplierId");
            return (Criteria) this;
        }

        public Criteria andSupplierIdNotLike(String value) {
            addCriterion("supplier_id not like", value, "supplierId");
            return (Criteria) this;
        }

        public Criteria andSupplierIdIn(List<String> values) {
            addCriterion("supplier_id in", values, "supplierId");
            return (Criteria) this;
        }

        public Criteria andSupplierIdNotIn(List<String> values) {
            addCriterion("supplier_id not in", values, "supplierId");
            return (Criteria) this;
        }

        public Criteria andSupplierIdBetween(String value1, String value2) {
            addCriterion("supplier_id between", value1, value2, "supplierId");
            return (Criteria) this;
        }

        public Criteria andSupplierIdNotBetween(String value1, String value2) {
            addCriterion("supplier_id not between", value1, value2, "supplierId");
            return (Criteria) this;
        }

        public Criteria andWeightIsNull() {
            addCriterion("weight is null");
            return (Criteria) this;
        }

        public Criteria andWeightIsNotNull() {
            addCriterion("weight is not null");
            return (Criteria) this;
        }

        public Criteria andWeightEqualTo(Integer value) {
            addCriterion("weight =", value, "weight");
            return (Criteria) this;
        }

        public Criteria andWeightNotEqualTo(Integer value) {
            addCriterion("weight <>", value, "weight");
            return (Criteria) this;
        }

        public Criteria andWeightGreaterThan(Integer value) {
            addCriterion("weight >", value, "weight");
            return (Criteria) this;
        }

        public Criteria andWeightGreaterThanOrEqualTo(Integer value) {
            addCriterion("weight >=", value, "weight");
            return (Criteria) this;
        }

        public Criteria andWeightLessThan(Integer value) {
            addCriterion("weight <", value, "weight");
            return (Criteria) this;
        }

        public Criteria andWeightLessThanOrEqualTo(Integer value) {
            addCriterion("weight <=", value, "weight");
            return (Criteria) this;
        }

        public Criteria andWeightIn(List<Integer> values) {
            addCriterion("weight in", values, "weight");
            return (Criteria) this;
        }

        public Criteria andWeightNotIn(List<Integer> values) {
            addCriterion("weight not in", values, "weight");
            return (Criteria) this;
        }

        public Criteria andWeightBetween(Integer value1, Integer value2) {
            addCriterion("weight between", value1, value2, "weight");
            return (Criteria) this;
        }

        public Criteria andWeightNotBetween(Integer value1, Integer value2) {
            addCriterion("weight not between", value1, value2, "weight");
            return (Criteria) this;
        }

        public Criteria andIsDeleteIsNull() {
            addCriterion("is_delete is null");
            return (Criteria) this;
        }

        public Criteria andIsDeleteIsNotNull() {
            addCriterion("is_delete is not null");
            return (Criteria) this;
        }

        public Criteria andIsDeleteEqualTo(Byte value) {
            addCriterion("is_delete =", value, "isDelete");
            return (Criteria) this;
        }

        public Criteria andIsDeleteNotEqualTo(Byte value) {
            addCriterion("is_delete <>", value, "isDelete");
            return (Criteria) this;
        }

        public Criteria andIsDeleteGreaterThan(Byte value) {
            addCriterion("is_delete >", value, "isDelete");
            return (Criteria) this;
        }

        public Criteria andIsDeleteGreaterThanOrEqualTo(Byte value) {
            addCriterion("is_delete >=", value, "isDelete");
            return (Criteria) this;
        }

        public Criteria andIsDeleteLessThan(Byte value) {
            addCriterion("is_delete <", value, "isDelete");
            return (Criteria) this;
        }

        public Criteria andIsDeleteLessThanOrEqualTo(Byte value) {
            addCriterion("is_delete <=", value, "isDelete");
            return (Criteria) this;
        }

        public Criteria andIsDeleteIn(List<Byte> values) {
            addCriterion("is_delete in", values, "isDelete");
            return (Criteria) this;
        }

        public Criteria andIsDeleteNotIn(List<Byte> values) {
            addCriterion("is_delete not in", values, "isDelete");
            return (Criteria) this;
        }

        public Criteria andIsDeleteBetween(Byte value1, Byte value2) {
            addCriterion("is_delete between", value1, value2, "isDelete");
            return (Criteria) this;
        }

        public Criteria andIsDeleteNotBetween(Byte value1, Byte value2) {
            addCriterion("is_delete not between", value1, value2, "isDelete");
            return (Criteria) this;
        }

        public Criteria andBuyIdIsNull() {
            addCriterion("buy_id is null");
            return (Criteria) this;
        }

        public Criteria andBuyIdIsNotNull() {
            addCriterion("buy_id is not null");
            return (Criteria) this;
        }

        public Criteria andBuyIdEqualTo(String value) {
            addCriterion("buy_id =", value, "buyId");
            return (Criteria) this;
        }

        public Criteria andBuyIdNotEqualTo(String value) {
            addCriterion("buy_id <>", value, "buyId");
            return (Criteria) this;
        }

        public Criteria andBuyIdGreaterThan(String value) {
            addCriterion("buy_id >", value, "buyId");
            return (Criteria) this;
        }

        public Criteria andBuyIdGreaterThanOrEqualTo(String value) {
            addCriterion("buy_id >=", value, "buyId");
            return (Criteria) this;
        }

        public Criteria andBuyIdLessThan(String value) {
            addCriterion("buy_id <", value, "buyId");
            return (Criteria) this;
        }

        public Criteria andBuyIdLessThanOrEqualTo(String value) {
            addCriterion("buy_id <=", value, "buyId");
            return (Criteria) this;
        }

        public Criteria andBuyIdLike(String value) {
            addCriterion("buy_id like", value, "buyId");
            return (Criteria) this;
        }

        public Criteria andBuyIdNotLike(String value) {
            addCriterion("buy_id not like", value, "buyId");
            return (Criteria) this;
        }

        public Criteria andBuyIdIn(List<String> values) {
            addCriterion("buy_id in", values, "buyId");
            return (Criteria) this;
        }

        public Criteria andBuyIdNotIn(List<String> values) {
            addCriterion("buy_id not in", values, "buyId");
            return (Criteria) this;
        }

        public Criteria andBuyIdBetween(String value1, String value2) {
            addCriterion("buy_id between", value1, value2, "buyId");
            return (Criteria) this;
        }

        public Criteria andBuyIdNotBetween(String value1, String value2) {
            addCriterion("buy_id not between", value1, value2, "buyId");
            return (Criteria) this;
        }

        public Criteria andIsPandianIsNull() {
            addCriterion("is_pandian is null");
            return (Criteria) this;
        }

        public Criteria andIsPandianIsNotNull() {
            addCriterion("is_pandian is not null");
            return (Criteria) this;
        }

        public Criteria andIsPandianEqualTo(Byte value) {
            addCriterion("is_pandian =", value, "isPandian");
            return (Criteria) this;
        }

        public Criteria andIsPandianNotEqualTo(Byte value) {
            addCriterion("is_pandian <>", value, "isPandian");
            return (Criteria) this;
        }

        public Criteria andIsPandianGreaterThan(Byte value) {
            addCriterion("is_pandian >", value, "isPandian");
            return (Criteria) this;
        }

        public Criteria andIsPandianGreaterThanOrEqualTo(Byte value) {
            addCriterion("is_pandian >=", value, "isPandian");
            return (Criteria) this;
        }

        public Criteria andIsPandianLessThan(Byte value) {
            addCriterion("is_pandian <", value, "isPandian");
            return (Criteria) this;
        }

        public Criteria andIsPandianLessThanOrEqualTo(Byte value) {
            addCriterion("is_pandian <=", value, "isPandian");
            return (Criteria) this;
        }

        public Criteria andIsPandianIn(List<Byte> values) {
            addCriterion("is_pandian in", values, "isPandian");
            return (Criteria) this;
        }

        public Criteria andIsPandianNotIn(List<Byte> values) {
            addCriterion("is_pandian not in", values, "isPandian");
            return (Criteria) this;
        }

        public Criteria andIsPandianBetween(Byte value1, Byte value2) {
            addCriterion("is_pandian between", value1, value2, "isPandian");
            return (Criteria) this;
        }

        public Criteria andIsPandianNotBetween(Byte value1, Byte value2) {
            addCriterion("is_pandian not between", value1, value2, "isPandian");
            return (Criteria) this;
        }

        public Criteria andBuyerIsNull() {
            addCriterion("buyer is null");
            return (Criteria) this;
        }

        public Criteria andBuyerIsNotNull() {
            addCriterion("buyer is not null");
            return (Criteria) this;
        }

        public Criteria andBuyerEqualTo(String value) {
            addCriterion("buyer =", value, "buyer");
            return (Criteria) this;
        }

        public Criteria andBuyerNotEqualTo(String value) {
            addCriterion("buyer <>", value, "buyer");
            return (Criteria) this;
        }

        public Criteria andBuyerGreaterThan(String value) {
            addCriterion("buyer >", value, "buyer");
            return (Criteria) this;
        }

        public Criteria andBuyerGreaterThanOrEqualTo(String value) {
            addCriterion("buyer >=", value, "buyer");
            return (Criteria) this;
        }

        public Criteria andBuyerLessThan(String value) {
            addCriterion("buyer <", value, "buyer");
            return (Criteria) this;
        }

        public Criteria andBuyerLessThanOrEqualTo(String value) {
            addCriterion("buyer <=", value, "buyer");
            return (Criteria) this;
        }

        public Criteria andBuyerLike(String value) {
            addCriterion("buyer like", value, "buyer");
            return (Criteria) this;
        }

        public Criteria andBuyerNotLike(String value) {
            addCriterion("buyer not like", value, "buyer");
            return (Criteria) this;
        }

        public Criteria andBuyerIn(List<String> values) {
            addCriterion("buyer in", values, "buyer");
            return (Criteria) this;
        }

        public Criteria andBuyerNotIn(List<String> values) {
            addCriterion("buyer not in", values, "buyer");
            return (Criteria) this;
        }

        public Criteria andBuyerBetween(String value1, String value2) {
            addCriterion("buyer between", value1, value2, "buyer");
            return (Criteria) this;
        }

        public Criteria andBuyerNotBetween(String value1, String value2) {
            addCriterion("buyer not between", value1, value2, "buyer");
            return (Criteria) this;
        }

        public Criteria andReturnTypeIsNull() {
            addCriterion("return_type is null");
            return (Criteria) this;
        }

        public Criteria andReturnTypeIsNotNull() {
            addCriterion("return_type is not null");
            return (Criteria) this;
        }

        public Criteria andReturnTypeEqualTo(Byte value) {
            addCriterion("return_type =", value, "returnType");
            return (Criteria) this;
        }

        public Criteria andReturnTypeNotEqualTo(Byte value) {
            addCriterion("return_type <>", value, "returnType");
            return (Criteria) this;
        }

        public Criteria andReturnTypeGreaterThan(Byte value) {
            addCriterion("return_type >", value, "returnType");
            return (Criteria) this;
        }

        public Criteria andReturnTypeGreaterThanOrEqualTo(Byte value) {
            addCriterion("return_type >=", value, "returnType");
            return (Criteria) this;
        }

        public Criteria andReturnTypeLessThan(Byte value) {
            addCriterion("return_type <", value, "returnType");
            return (Criteria) this;
        }

        public Criteria andReturnTypeLessThanOrEqualTo(Byte value) {
            addCriterion("return_type <=", value, "returnType");
            return (Criteria) this;
        }

        public Criteria andReturnTypeIn(List<Byte> values) {
            addCriterion("return_type in", values, "returnType");
            return (Criteria) this;
        }

        public Criteria andReturnTypeNotIn(List<Byte> values) {
            addCriterion("return_type not in", values, "returnType");
            return (Criteria) this;
        }

        public Criteria andReturnTypeBetween(Byte value1, Byte value2) {
            addCriterion("return_type between", value1, value2, "returnType");
            return (Criteria) this;
        }

        public Criteria andReturnTypeNotBetween(Byte value1, Byte value2) {
            addCriterion("return_type not between", value1, value2, "returnType");
            return (Criteria) this;
        }

        public Criteria andIsBatchIsNull() {
            addCriterion("is_batch is null");
            return (Criteria) this;
        }

        public Criteria andIsBatchIsNotNull() {
            addCriterion("is_batch is not null");
            return (Criteria) this;
        }

        public Criteria andIsBatchEqualTo(Byte value) {
            addCriterion("is_batch =", value, "isBatch");
            return (Criteria) this;
        }

        public Criteria andIsBatchNotEqualTo(Byte value) {
            addCriterion("is_batch <>", value, "isBatch");
            return (Criteria) this;
        }

        public Criteria andIsBatchGreaterThan(Byte value) {
            addCriterion("is_batch >", value, "isBatch");
            return (Criteria) this;
        }

        public Criteria andIsBatchGreaterThanOrEqualTo(Byte value) {
            addCriterion("is_batch >=", value, "isBatch");
            return (Criteria) this;
        }

        public Criteria andIsBatchLessThan(Byte value) {
            addCriterion("is_batch <", value, "isBatch");
            return (Criteria) this;
        }

        public Criteria andIsBatchLessThanOrEqualTo(Byte value) {
            addCriterion("is_batch <=", value, "isBatch");
            return (Criteria) this;
        }

        public Criteria andIsBatchIn(List<Byte> values) {
            addCriterion("is_batch in", values, "isBatch");
            return (Criteria) this;
        }

        public Criteria andIsBatchNotIn(List<Byte> values) {
            addCriterion("is_batch not in", values, "isBatch");
            return (Criteria) this;
        }

        public Criteria andIsBatchBetween(Byte value1, Byte value2) {
            addCriterion("is_batch between", value1, value2, "isBatch");
            return (Criteria) this;
        }

        public Criteria andIsBatchNotBetween(Byte value1, Byte value2) {
            addCriterion("is_batch not between", value1, value2, "isBatch");
            return (Criteria) this;
        }

        public Criteria andLastUpdateTimeIsNull() {
            addCriterion("last_update_time is null");
            return (Criteria) this;
        }

        public Criteria andLastUpdateTimeIsNotNull() {
            addCriterion("last_update_time is not null");
            return (Criteria) this;
        }

        public Criteria andLastUpdateTimeEqualTo(Date value) {
            addCriterion("last_update_time =", value, "lastUpdateTime");
            return (Criteria) this;
        }

        public Criteria andLastUpdateTimeNotEqualTo(Date value) {
            addCriterion("last_update_time <>", value, "lastUpdateTime");
            return (Criteria) this;
        }

        public Criteria andLastUpdateTimeGreaterThan(Date value) {
            addCriterion("last_update_time >", value, "lastUpdateTime");
            return (Criteria) this;
        }

        public Criteria andLastUpdateTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("last_update_time >=", value, "lastUpdateTime");
            return (Criteria) this;
        }

        public Criteria andLastUpdateTimeLessThan(Date value) {
            addCriterion("last_update_time <", value, "lastUpdateTime");
            return (Criteria) this;
        }

        public Criteria andLastUpdateTimeLessThanOrEqualTo(Date value) {
            addCriterion("last_update_time <=", value, "lastUpdateTime");
            return (Criteria) this;
        }

        public Criteria andLastUpdateTimeIn(List<Date> values) {
            addCriterion("last_update_time in", values, "lastUpdateTime");
            return (Criteria) this;
        }

        public Criteria andLastUpdateTimeNotIn(List<Date> values) {
            addCriterion("last_update_time not in", values, "lastUpdateTime");
            return (Criteria) this;
        }

        public Criteria andLastUpdateTimeBetween(Date value1, Date value2) {
            addCriterion("last_update_time between", value1, value2, "lastUpdateTime");
            return (Criteria) this;
        }

        public Criteria andLastUpdateTimeNotBetween(Date value1, Date value2) {
            addCriterion("last_update_time not between", value1, value2, "lastUpdateTime");
            return (Criteria) this;
        }

        public Criteria andBillIdIsNull() {
            addCriterion("bill_id is null");
            return (Criteria) this;
        }

        public Criteria andBillIdIsNotNull() {
            addCriterion("bill_id is not null");
            return (Criteria) this;
        }

        public Criteria andBillIdEqualTo(Integer value) {
            addCriterion("bill_id =", value, "billId");
            return (Criteria) this;
        }

        public Criteria andBillIdNotEqualTo(Integer value) {
            addCriterion("bill_id <>", value, "billId");
            return (Criteria) this;
        }

        public Criteria andBillIdGreaterThan(Integer value) {
            addCriterion("bill_id >", value, "billId");
            return (Criteria) this;
        }

        public Criteria andBillIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("bill_id >=", value, "billId");
            return (Criteria) this;
        }

        public Criteria andBillIdLessThan(Integer value) {
            addCriterion("bill_id <", value, "billId");
            return (Criteria) this;
        }

        public Criteria andBillIdLessThanOrEqualTo(Integer value) {
            addCriterion("bill_id <=", value, "billId");
            return (Criteria) this;
        }

        public Criteria andBillIdIn(List<Integer> values) {
            addCriterion("bill_id in", values, "billId");
            return (Criteria) this;
        }

        public Criteria andBillIdNotIn(List<Integer> values) {
            addCriterion("bill_id not in", values, "billId");
            return (Criteria) this;
        }

        public Criteria andBillIdBetween(Integer value1, Integer value2) {
            addCriterion("bill_id between", value1, value2, "billId");
            return (Criteria) this;
        }

        public Criteria andBillIdNotBetween(Integer value1, Integer value2) {
            addCriterion("bill_id not between", value1, value2, "billId");
            return (Criteria) this;
        }

        public Criteria andTurnoverNoIsNull() {
            addCriterion("turnover_no is null");
            return (Criteria) this;
        }

        public Criteria andTurnoverNoIsNotNull() {
            addCriterion("turnover_no is not null");
            return (Criteria) this;
        }

        public Criteria andTurnoverNoEqualTo(String value) {
            addCriterion("turnover_no =", value, "turnoverNo");
            return (Criteria) this;
        }

        public Criteria andTurnoverNoNotEqualTo(String value) {
            addCriterion("turnover_no <>", value, "turnoverNo");
            return (Criteria) this;
        }

        public Criteria andTurnoverNoGreaterThan(String value) {
            addCriterion("turnover_no >", value, "turnoverNo");
            return (Criteria) this;
        }

        public Criteria andTurnoverNoGreaterThanOrEqualTo(String value) {
            addCriterion("turnover_no >=", value, "turnoverNo");
            return (Criteria) this;
        }

        public Criteria andTurnoverNoLessThan(String value) {
            addCriterion("turnover_no <", value, "turnoverNo");
            return (Criteria) this;
        }

        public Criteria andTurnoverNoLessThanOrEqualTo(String value) {
            addCriterion("turnover_no <=", value, "turnoverNo");
            return (Criteria) this;
        }

        public Criteria andTurnoverNoLike(String value) {
            addCriterion("turnover_no like", value, "turnoverNo");
            return (Criteria) this;
        }

        public Criteria andTurnoverNoNotLike(String value) {
            addCriterion("turnover_no not like", value, "turnoverNo");
            return (Criteria) this;
        }

        public Criteria andTurnoverNoIn(List<String> values) {
            addCriterion("turnover_no in", values, "turnoverNo");
            return (Criteria) this;
        }

        public Criteria andTurnoverNoNotIn(List<String> values) {
            addCriterion("turnover_no not in", values, "turnoverNo");
            return (Criteria) this;
        }

        public Criteria andTurnoverNoBetween(String value1, String value2) {
            addCriterion("turnover_no between", value1, value2, "turnoverNo");
            return (Criteria) this;
        }

        public Criteria andTurnoverNoNotBetween(String value1, String value2) {
            addCriterion("turnover_no not between", value1, value2, "turnoverNo");
            return (Criteria) this;
        }
    }

    /**
     */
    public static class Criteria extends GeneratedCriteria implements Serializable {

        protected Criteria() {
            super();
        }
    }

    public static class Criterion implements Serializable{
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}