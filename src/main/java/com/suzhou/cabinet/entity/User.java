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
public class User extends Model<User> {

    private static final long serialVersionUID=1L;

      private String id;

    private String name;

    private String password;

      /**
     * 1:用户  2:快递员 3:管理员
     */
      private String type;

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
    
    public String getPassword() {
        return password;
    }

      public void setPassword(String password) {
          this.password = password;
      }
    
    public String getType() {
        return type;
    }

      public void setType(String type) {
          this.type = type;
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
        return "User{" +
              "id=" + id +
                  ", name=" + name +
                  ", password=" + password +
                  ", type=" + type +
                  ", delFlag=" + delFlag +
              "}";
    }
}
