package com.suzhou.cabinet.entity;

import com.baomidou.mybatisplus.extension.activerecord.Model;
import java.time.LocalDateTime;
import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author suz
 * @since 2020-03-23
 */
public class Order extends Model<Order> {

    private static final long serialVersionUID=1L;

      private String id;

    private String boxId;

    private LocalDateTime arrivalTime;

    private String courierId;

    private String userId;

    private String code;

    
    public String getId() {
        return id;
    }

      public void setId(String id) {
          this.id = id;
      }
    
    public String getBoxId() {
        return boxId;
    }

      public void setBoxId(String boxId) {
          this.boxId = boxId;
      }
    
    public LocalDateTime getArrivalTime() {
        return arrivalTime;
    }

      public void setArrivalTime(LocalDateTime arrivalTime) {
          this.arrivalTime = arrivalTime;
      }
    
    public String getCourierId() {
        return courierId;
    }

      public void setCourierId(String courierId) {
          this.courierId = courierId;
      }
    
    public String getUserId() {
        return userId;
    }

      public void setUserId(String userId) {
          this.userId = userId;
      }
    
    public String getCode() {
        return code;
    }

      public void setCode(String code) {
          this.code = code;
      }

    @Override
    protected Serializable pkVal() {
          return this.id;
      }

    @Override
    public String toString() {
        return "Order{" +
              "id=" + id +
                  ", boxId=" + boxId +
                  ", arrivalTime=" + arrivalTime +
                  ", courierId=" + courierId +
                  ", userId=" + userId +
                  ", code=" + code +
              "}";
    }
}
