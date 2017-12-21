package com.ltsh.chat.service.entity;
public class UserGroup extends BaseEntity {
    
        /**
         * 主键
         **/
        private Integer id;
        public void setId(Integer id) {
            this.id = id;
        }
        public Integer getId(){
            return id;
        }
        
        /**
         * 创建用户
         **/
        private Integer createBy;
        public void setCreateBy(Integer createBy) {
            this.createBy = createBy;
        }
        public Integer getCreateBy(){
            return createBy;
        }
        
        /**
         * 创建时间
         **/
        private java.util.Date createTime;
        public void setCreateTime(java.util.Date createTime) {
            this.createTime = createTime;
        }
        public java.util.Date getCreateTime(){
            return createTime;
        }
        
        /**
         * 修改用户
         **/
        private Integer updateBy;
        public void setUpdateBy(Integer updateBy) {
            this.updateBy = updateBy;
        }
        public Integer getUpdateBy(){
            return updateBy;
        }
        
        /**
         * 修改时间
         **/
        private java.util.Date updateTime;
        public void setUpdateTime(java.util.Date updateTime) {
            this.updateTime = updateTime;
        }
        public java.util.Date getUpdateTime(){
            return updateTime;
        }
        
        /**
         * 名称
         **/
        private String name;
        public void setName(String name) {
            this.name = name;
        }
        public String getName(){
            return name;
        }
        
        /**
         * 类型
         **/
        private Integer type;
        public void setType(Integer type) {
            this.type = type;
        }
        public Integer getType(){
            return type;
        }
        
        /**
         * 状态
         **/
        private String status;
        public void setStatus(String status) {
            this.status = status;
        }
        public String getStatus(){
            return status;
        }
        
        /**
         * 所有者
         **/
        private Integer owner;
        public void setOwner(Integer owner) {
            this.owner = owner;
        }
        public Integer getOwner(){
            return owner;
        }
        
        /**
         * 有效期
         **/
        private java.util.Date validity;
        public void setValidity(java.util.Date validity) {
            this.validity = validity;
        }
        public java.util.Date getValidity(){
            return validity;
        }
        
        /**
         * 级别类型
         **/
        private Integer levelType;
        public void setLevelType(Integer levelType) {
            this.levelType = levelType;
        }
        public Integer getLevelType(){
            return levelType;
        }
    
}
