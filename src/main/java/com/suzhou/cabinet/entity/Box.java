package com.suzhou.cabinet.entity;

import com.baomidou.mybatisplus.extension.activerecord.Model;
import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author suz
 * @since 2020-03-23
 */
public class Box extends Model<Box> {

    private static final long serialVersionUID=1L;

      /**
     *  高度
     */
      private Double h;

      /**
     * 宽度
     */
      private Double w;

      /**
     * 深度
     */
      private Double d;

      private String id;

    private String name;

    private String isEmpty;

    private String cabinetId;

    private String delFlag;

    
    public Double getH() {
        return h;
    }

      public void setH(Double h) {
          this.h = h;
      }
    
    public Double getW() {
        return w;
    }

      public void setW(Double w) {
          this.w = w;
      }
    
    public Double getD() {
        return d;
    }

      public void setD(Double d) {
          this.d = d;
      }
    
    public String getId() {
        return id;
    }

      public void setId(String id) {
          this.id = id;
      }
    
    public String getName() {
        return name;
    }

      public void setName(String name) {
          this.name = name;
      }
    
    public String getIsEmpty() {
        return isEmpty;
    }

      public void setIsEmpty(String isEmpty) {
          this.isEmpty = isEmpty;
      }
    
    public String getCabinetId() {
        return cabinetId;
    }

      public void setCabinetId(String cabinetId) {
          this.cabinetId = cabinetId;
      }
    
    public String getDelFlag() {
        return delFlag;
    }

      public void setDelFlag(String delFlag) {
          this.delFlag = delFlag;
      }

    @Override
    protected Serializable pkVal() {
          return this.id;
      }

    @Override
    public String toString() {
        return "Box{" +
              "h=" + h +
                  ", w=" + w +
                  ", d=" + d +
                  ", id=" + id +
                  ", name=" + name +
                  ", isEmpty=" + isEmpty +
                  ", cabinetId=" + cabinetId +
                  ", delFlag=" + delFlag +
              "}";
    }
}
