package com.github.pagehelper.model;

import java.io.Serializable;
import java.util.Date;

/**
 * @author 
 */
public class RsInventory implements Serializable {
    private Integer inventoryId;

    private Integer siteId;

    private Integer goodsId;

    private Integer attrValueId;

    private String goodsSn;

    private String goodsAttr;

    private String goodsThumb;

    private Byte inventoryType;

    private Long orderGoodsId;

    private Integer quantity;

    private String adminName;

    private Date addTime;

    private Byte inventoryStatus;

    private String supplierLinkman;

    private String huoweiName;

    private String supplierId;

    private Integer weight;

    private Byte isDelete;

    private String buyId;

    private Byte isPandian;

    private String buyer;

    private Byte returnType;

    private Byte isBatch;

    private Date lastUpdateTime;

    private Integer billId;

    /**
     * 周转箱号
     */
    private String turnoverNo;

    private String remark;

    private static final long serialVersionUID = 1L;

    public Integer getInventoryId() {
        return inventoryId;
    }

    public void setInventoryId(Integer inventoryId) {
        this.inventoryId = inventoryId;
    }

    public Integer getSiteId() {
        return siteId;
    }

    public void setSiteId(Integer siteId) {
        this.siteId = siteId;
    }

    public Integer getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Integer goodsId) {
        this.goodsId = goodsId;
    }

    public Integer getAttrValueId() {
        return attrValueId;
    }

    public void setAttrValueId(Integer attrValueId) {
        this.attrValueId = attrValueId;
    }

    public String getGoodsSn() {
        return goodsSn;
    }

    public void setGoodsSn(String goodsSn) {
        this.goodsSn = goodsSn;
    }

    public String getGoodsAttr() {
        return goodsAttr;
    }

    public void setGoodsAttr(String goodsAttr) {
        this.goodsAttr = goodsAttr;
    }

    public String getGoodsThumb() {
        return goodsThumb;
    }

    public void setGoodsThumb(String goodsThumb) {
        this.goodsThumb = goodsThumb;
    }

    public Byte getInventoryType() {
        return inventoryType;
    }

    public void setInventoryType(Byte inventoryType) {
        this.inventoryType = inventoryType;
    }

    public Long getOrderGoodsId() {
        return orderGoodsId;
    }

    public void setOrderGoodsId(Long orderGoodsId) {
        this.orderGoodsId = orderGoodsId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public String getAdminName() {
        return adminName;
    }

    public void setAdminName(String adminName) {
        this.adminName = adminName;
    }

    public Date getAddTime() {
        return addTime;
    }

    public void setAddTime(Date addTime) {
        this.addTime = addTime;
    }

    public Byte getInventoryStatus() {
        return inventoryStatus;
    }

    public void setInventoryStatus(Byte inventoryStatus) {
        this.inventoryStatus = inventoryStatus;
    }

    public String getSupplierLinkman() {
        return supplierLinkman;
    }

    public void setSupplierLinkman(String supplierLinkman) {
        this.supplierLinkman = supplierLinkman;
    }

    public String getHuoweiName() {
        return huoweiName;
    }

    public void setHuoweiName(String huoweiName) {
        this.huoweiName = huoweiName;
    }

    public String getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(String supplierId) {
        this.supplierId = supplierId;
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    public Byte getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Byte isDelete) {
        this.isDelete = isDelete;
    }

    public String getBuyId() {
        return buyId;
    }

    public void setBuyId(String buyId) {
        this.buyId = buyId;
    }

    public Byte getIsPandian() {
        return isPandian;
    }

    public void setIsPandian(Byte isPandian) {
        this.isPandian = isPandian;
    }

    public String getBuyer() {
        return buyer;
    }

    public void setBuyer(String buyer) {
        this.buyer = buyer;
    }

    public Byte getReturnType() {
        return returnType;
    }

    public void setReturnType(Byte returnType) {
        this.returnType = returnType;
    }

    public Byte getIsBatch() {
        return isBatch;
    }

    public void setIsBatch(Byte isBatch) {
        this.isBatch = isBatch;
    }

    public Date getLastUpdateTime() {
        return lastUpdateTime;
    }

    public void setLastUpdateTime(Date lastUpdateTime) {
        this.lastUpdateTime = lastUpdateTime;
    }

    public Integer getBillId() {
        return billId;
    }

    public void setBillId(Integer billId) {
        this.billId = billId;
    }

    public String getTurnoverNo() {
        return turnoverNo;
    }

    public void setTurnoverNo(String turnoverNo) {
        this.turnoverNo = turnoverNo;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        RsInventory other = (RsInventory) that;
        return (this.getInventoryId() == null ? other.getInventoryId() == null : this.getInventoryId().equals(other.getInventoryId()))
            && (this.getSiteId() == null ? other.getSiteId() == null : this.getSiteId().equals(other.getSiteId()))
            && (this.getGoodsId() == null ? other.getGoodsId() == null : this.getGoodsId().equals(other.getGoodsId()))
            && (this.getAttrValueId() == null ? other.getAttrValueId() == null : this.getAttrValueId().equals(other.getAttrValueId()))
            && (this.getGoodsSn() == null ? other.getGoodsSn() == null : this.getGoodsSn().equals(other.getGoodsSn()))
            && (this.getGoodsAttr() == null ? other.getGoodsAttr() == null : this.getGoodsAttr().equals(other.getGoodsAttr()))
            && (this.getGoodsThumb() == null ? other.getGoodsThumb() == null : this.getGoodsThumb().equals(other.getGoodsThumb()))
            && (this.getInventoryType() == null ? other.getInventoryType() == null : this.getInventoryType().equals(other.getInventoryType()))
            && (this.getOrderGoodsId() == null ? other.getOrderGoodsId() == null : this.getOrderGoodsId().equals(other.getOrderGoodsId()))
            && (this.getQuantity() == null ? other.getQuantity() == null : this.getQuantity().equals(other.getQuantity()))
            && (this.getAdminName() == null ? other.getAdminName() == null : this.getAdminName().equals(other.getAdminName()))
            && (this.getAddTime() == null ? other.getAddTime() == null : this.getAddTime().equals(other.getAddTime()))
            && (this.getInventoryStatus() == null ? other.getInventoryStatus() == null : this.getInventoryStatus().equals(other.getInventoryStatus()))
            && (this.getSupplierLinkman() == null ? other.getSupplierLinkman() == null : this.getSupplierLinkman().equals(other.getSupplierLinkman()))
            && (this.getHuoweiName() == null ? other.getHuoweiName() == null : this.getHuoweiName().equals(other.getHuoweiName()))
            && (this.getSupplierId() == null ? other.getSupplierId() == null : this.getSupplierId().equals(other.getSupplierId()))
            && (this.getWeight() == null ? other.getWeight() == null : this.getWeight().equals(other.getWeight()))
            && (this.getIsDelete() == null ? other.getIsDelete() == null : this.getIsDelete().equals(other.getIsDelete()))
            && (this.getBuyId() == null ? other.getBuyId() == null : this.getBuyId().equals(other.getBuyId()))
            && (this.getIsPandian() == null ? other.getIsPandian() == null : this.getIsPandian().equals(other.getIsPandian()))
            && (this.getBuyer() == null ? other.getBuyer() == null : this.getBuyer().equals(other.getBuyer()))
            && (this.getReturnType() == null ? other.getReturnType() == null : this.getReturnType().equals(other.getReturnType()))
            && (this.getIsBatch() == null ? other.getIsBatch() == null : this.getIsBatch().equals(other.getIsBatch()))
            && (this.getLastUpdateTime() == null ? other.getLastUpdateTime() == null : this.getLastUpdateTime().equals(other.getLastUpdateTime()))
            && (this.getBillId() == null ? other.getBillId() == null : this.getBillId().equals(other.getBillId()))
            && (this.getTurnoverNo() == null ? other.getTurnoverNo() == null : this.getTurnoverNo().equals(other.getTurnoverNo()))
            && (this.getRemark() == null ? other.getRemark() == null : this.getRemark().equals(other.getRemark()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getInventoryId() == null) ? 0 : getInventoryId().hashCode());
        result = prime * result + ((getSiteId() == null) ? 0 : getSiteId().hashCode());
        result = prime * result + ((getGoodsId() == null) ? 0 : getGoodsId().hashCode());
        result = prime * result + ((getAttrValueId() == null) ? 0 : getAttrValueId().hashCode());
        result = prime * result + ((getGoodsSn() == null) ? 0 : getGoodsSn().hashCode());
        result = prime * result + ((getGoodsAttr() == null) ? 0 : getGoodsAttr().hashCode());
        result = prime * result + ((getGoodsThumb() == null) ? 0 : getGoodsThumb().hashCode());
        result = prime * result + ((getInventoryType() == null) ? 0 : getInventoryType().hashCode());
        result = prime * result + ((getOrderGoodsId() == null) ? 0 : getOrderGoodsId().hashCode());
        result = prime * result + ((getQuantity() == null) ? 0 : getQuantity().hashCode());
        result = prime * result + ((getAdminName() == null) ? 0 : getAdminName().hashCode());
        result = prime * result + ((getAddTime() == null) ? 0 : getAddTime().hashCode());
        result = prime * result + ((getInventoryStatus() == null) ? 0 : getInventoryStatus().hashCode());
        result = prime * result + ((getSupplierLinkman() == null) ? 0 : getSupplierLinkman().hashCode());
        result = prime * result + ((getHuoweiName() == null) ? 0 : getHuoweiName().hashCode());
        result = prime * result + ((getSupplierId() == null) ? 0 : getSupplierId().hashCode());
        result = prime * result + ((getWeight() == null) ? 0 : getWeight().hashCode());
        result = prime * result + ((getIsDelete() == null) ? 0 : getIsDelete().hashCode());
        result = prime * result + ((getBuyId() == null) ? 0 : getBuyId().hashCode());
        result = prime * result + ((getIsPandian() == null) ? 0 : getIsPandian().hashCode());
        result = prime * result + ((getBuyer() == null) ? 0 : getBuyer().hashCode());
        result = prime * result + ((getReturnType() == null) ? 0 : getReturnType().hashCode());
        result = prime * result + ((getIsBatch() == null) ? 0 : getIsBatch().hashCode());
        result = prime * result + ((getLastUpdateTime() == null) ? 0 : getLastUpdateTime().hashCode());
        result = prime * result + ((getBillId() == null) ? 0 : getBillId().hashCode());
        result = prime * result + ((getTurnoverNo() == null) ? 0 : getTurnoverNo().hashCode());
        result = prime * result + ((getRemark() == null) ? 0 : getRemark().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", inventoryId=").append(inventoryId);
        sb.append(", siteId=").append(siteId);
        sb.append(", goodsId=").append(goodsId);
        sb.append(", attrValueId=").append(attrValueId);
        sb.append(", goodsSn=").append(goodsSn);
        sb.append(", goodsAttr=").append(goodsAttr);
        sb.append(", goodsThumb=").append(goodsThumb);
        sb.append(", inventoryType=").append(inventoryType);
        sb.append(", orderGoodsId=").append(orderGoodsId);
        sb.append(", quantity=").append(quantity);
        sb.append(", adminName=").append(adminName);
        sb.append(", addTime=").append(addTime);
        sb.append(", inventoryStatus=").append(inventoryStatus);
        sb.append(", supplierLinkman=").append(supplierLinkman);
        sb.append(", huoweiName=").append(huoweiName);
        sb.append(", supplierId=").append(supplierId);
        sb.append(", weight=").append(weight);
        sb.append(", isDelete=").append(isDelete);
        sb.append(", buyId=").append(buyId);
        sb.append(", isPandian=").append(isPandian);
        sb.append(", buyer=").append(buyer);
        sb.append(", returnType=").append(returnType);
        sb.append(", isBatch=").append(isBatch);
        sb.append(", lastUpdateTime=").append(lastUpdateTime);
        sb.append(", billId=").append(billId);
        sb.append(", turnoverNo=").append(turnoverNo);
        sb.append(", remark=").append(remark);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}