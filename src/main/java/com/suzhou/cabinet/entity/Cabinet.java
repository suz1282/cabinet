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
public class Cabinet extends Model<Cabinet> {

    private static final long serialVersionUID=1L;

      private String id;

    private String name;

    private LocalDateTime createTime;

    private String regionId;

    private String longitude;

    private String latitude;

    private String delFlag;

    
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
    
    public LocalDateTime getCreateTime() {
        return createTime;
    }

      public void setCreateTime(LocalDateTime createTime) {
          this.createTime = createTime;
      }
    
    public String getRegionId() {
        return regionId;
    }

      public void setRegionId(String regionId) {
          this.regionId = regionId;
      }
    
    public String getLongitude() {
        return longitude;
    }

      public void setLongitude(String longitude) {
          this.longitude = longitude;
      }
    
    public String getLatitude() {
        return latitude;
    }

      public void setLatitude(String latitude) {
          this.latitude = latitude;
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
        return "Cabinet{" +
              "id=" + id +
                  ", name=" + name +
                  ", createTime=" + createTime +
                  ", regionId=" + regionId +
                  ", longitude=" + longitude +
                  ", latitude=" + latitude +
                  ", delFlag=" + delFlag +
              "}";
    }
}
