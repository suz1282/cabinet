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
public class Announcement extends Model<Announcement> {

    private static final long serialVersionUID=1L;

      private String id;

    private String title;

    private String type;

    private String content;

    private LocalDateTime startTime;

    private LocalDateTime endTime;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;

    private String delFlag;

    private String pictureName;

    
    public String getId() {
        return id;
    }

      public void setId(String id) {
          this.id = id;
      }
    
    public String getTitle() {
        return title;
    }

      public void setTitle(String title) {
          this.title = title;
      }
    
    public String getType() {
        return type;
    }

      public void setType(String type) {
          this.type = type;
      }
    
    public String getContent() {
        return content;
    }

      public void setContent(String content) {
          this.content = content;
      }
    
    public LocalDateTime getStartTime() {
        return startTime;
    }

      public void setStartTime(LocalDateTime startTime) {
          this.startTime = startTime;
      }
    
    public LocalDateTime getEndTime() {
        return endTime;
    }

      public void setEndTime(LocalDateTime endTime) {
          this.endTime = endTime;
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
    
    public String getDelFlag() {
        return delFlag;
    }

      public void setDelFlag(String delFlag) {
          this.delFlag = delFlag;
      }
    
    public String getPictureName() {
        return pictureName;
    }

      public void setPictureName(String pictureName) {
          this.pictureName = pictureName;
      }

    @Override
    protected Serializable pkVal() {
          return this.id;
      }

    @Override
    public String toString() {
        return "Announcement{" +
              "id=" + id +
                  ", title=" + title +
                  ", type=" + type +
                  ", content=" + content +
                  ", startTime=" + startTime +
                  ", endTime=" + endTime +
                  ", createTime=" + createTime +
                  ", updateTime=" + updateTime +
                  ", delFlag=" + delFlag +
                  ", pictureName=" + pictureName +
              "}";
    }
}
