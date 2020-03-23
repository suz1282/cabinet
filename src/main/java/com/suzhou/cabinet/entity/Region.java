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
public class Region extends Model<Region> {

    private static final long serialVersionUID=1L;

      private String id;

    private String name;

    private String parentId;

    private String boundaryPoint;

    private String delFlag;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;

    private String isDirectory;

    
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
    
    public String getParentId() {
        return parentId;
    }

      public void setParentId(String parentId) {
          this.parentId = parentId;
      }
    
    public String getBoundaryPoint() {
        return boundaryPoint;
    }

      public void setBoundaryPoint(String boundaryPoint) {
          this.boundaryPoint = boundaryPoint;
      }
    
    public String getDelFlag() {
        return delFlag;
    }

      public void setDelFlag(String delFlag) {
          this.delFlag = delFlag;
      }
    
    public LocalDateTime getCreateTime() {
        return createTime;
    }

      public void setCreateTime(LocalDateTime createTime) {
          this.createTime = createTime;
      }
    
    public LocalDateTime getUpdateTime() {
        return updateTime;
    }

      public void setUpdateTime(LocalDateTime updateTime) {
          this.updateTime = updateTime;
      }
    
    public String getIsDirectory() {
        return isDirectory;
    }

      public void setIsDirectory(String isDirectory) {
          this.isDirectory = isDirectory;
      }

    @Override
    protected Serializable pkVal() {
          return this.id;
      }

    @Override
    public String toString() {
        return "Region{" +
              "id=" + id +
                  ", name=" + name +
                  ", parentId=" + parentId +
                  ", boundaryPoint=" + boundaryPoint +
                  ", delFlag=" + delFlag +
                  ", createTime=" + createTime +
                  ", updateTime=" + updateTime +
                  ", isDirectory=" + isDirectory +
              "}";
    }
}
