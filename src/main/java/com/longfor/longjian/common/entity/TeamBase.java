package com.longfor.longjian.common.entity;

import java.util.Date;
import javax.persistence.*;
@Table(name = "team")
public class TeamBase {
    @Id
    @Column(name = "team_id")
    private Integer teamId;

    /**
     * 公司名称
     */
    @Column(name = "team_name")
    private String teamName;

    /**
     * 上级公司id
     */
    @Column(name = "parent_team_id")
    private Integer parentTeamId;

    /**
     * 层级路径
     */
    private String path;

    /**
     * 层级
     */
    private Integer level;

    /**
     * 显示顺序
     */
    @Column(name = "display_index")
    private Integer displayIndex;

    /**
     * # 用户支付情况 10=试用 20=付费用户 
     */
    @Column(name = "payment_type")
    private Integer paymentType;

    /**
     * # 集团归属用户id，只有集团才会有，其它情况为0
     */
    @Column(name = "owner_user_id")
    private Integer ownerUserId;

    /**
     * 企业编码
     */
    @Column(name = "group_code")
    private String groupCode;

    @Column(name = "create_at")
    private Date createAt;

    @Column(name = "update_at")
    private Date updateAt;

    @Column(name = "delete_at")
    private Date deleteAt;

    /**
     * 客户系统唯一识别码
     */
    @Column(name = "custom_id")
    private String customId;

    /**
     * 设置
     */
    private String setting;

    /**
     * 客户系统扩展信息
     */
    @Column(name = "custom_extra")
    private String customExtra;

    /**
     * @return team_id
     */
    public Integer getTeamId() {
        return teamId;
    }

    /**
     * @param teamId
     */
    public void setTeamId(Integer teamId) {
        this.teamId = teamId;
    }

    /**
     * 获取公司名称
     *
     * @return team_name - 公司名称
     */
    public String getTeamName() {
        return teamName;
    }

    /**
     * 设置公司名称
     *
     * @param teamName 公司名称
     */
    public void setTeamName(String teamName) {
        this.teamName = teamName == null ? null : teamName.trim();
    }

    /**
     * 获取上级公司id
     *
     * @return parent_team_id - 上级公司id
     */
    public Integer getParentTeamId() {
        return parentTeamId;
    }

    /**
     * 设置上级公司id
     *
     * @param parentTeamId 上级公司id
     */
    public void setParentTeamId(Integer parentTeamId) {
        this.parentTeamId = parentTeamId;
    }

    /**
     * 获取层级路径
     *
     * @return path - 层级路径
     */
    public String getPath() {
        return path;
    }

    /**
     * 设置层级路径
     *
     * @param path 层级路径
     */
    public void setPath(String path) {
        this.path = path == null ? null : path.trim();
    }

    /**
     * 获取层级
     *
     * @return level - 层级
     */
    public Integer getLevel() {
        return level;
    }

    /**
     * 设置层级
     *
     * @param level 层级
     */
    public void setLevel(Integer level) {
        this.level = level;
    }

    /**
     * 获取显示顺序
     *
     * @return display_index - 显示顺序
     */
    public Integer getDisplayIndex() {
        return displayIndex;
    }

    /**
     * 设置显示顺序
     *
     * @param displayIndex 显示顺序
     */
    public void setDisplayIndex(Integer displayIndex) {
        this.displayIndex = displayIndex;
    }

    /**
     * 获取# 用户支付情况 10=试用 20=付费用户 
     *
     * @return payment_type - # 用户支付情况 10=试用 20=付费用户 
     */
    public Integer getPaymentType() {
        return paymentType;
    }

    /**
     * 设置# 用户支付情况 10=试用 20=付费用户 
     *
     * @param paymentType # 用户支付情况 10=试用 20=付费用户 
     */
    public void setPaymentType(Integer paymentType) {
        this.paymentType = paymentType;
    }

    /**
     * 获取# 集团归属用户id，只有集团才会有，其它情况为0
     *
     * @return owner_user_id - # 集团归属用户id，只有集团才会有，其它情况为0
     */
    public Integer getOwnerUserId() {
        return ownerUserId;
    }

    /**
     * 设置# 集团归属用户id，只有集团才会有，其它情况为0
     *
     * @param ownerUserId # 集团归属用户id，只有集团才会有，其它情况为0
     */
    public void setOwnerUserId(Integer ownerUserId) {
        this.ownerUserId = ownerUserId;
    }

    /**
     * 获取企业编码
     *
     * @return group_code - 企业编码
     */
    public String getGroupCode() {
        return groupCode;
    }

    /**
     * 设置企业编码
     *
     * @param groupCode 企业编码
     */
    public void setGroupCode(String groupCode) {
        this.groupCode = groupCode == null ? null : groupCode.trim();
    }

    /**
     * @return create_at
     */
    public Date getCreateAt() {
        return createAt;
    }

    /**
     * @param createAt
     */
    public void setCreateAt(Date createAt) {
        this.createAt = createAt;
    }

    /**
     * @return update_at
     */
    public Date getUpdateAt() {
        return updateAt;
    }

    /**
     * @param updateAt
     */
    public void setUpdateAt(Date updateAt) {
        this.updateAt = updateAt;
    }

    /**
     * @return delete_at
     */
    public Date getDeleteAt() {
        return deleteAt;
    }

    /**
     * @param deleteAt
     */
    public void setDeleteAt(Date deleteAt) {
        this.deleteAt = deleteAt;
    }

    /**
     * 获取客户系统唯一识别码
     *
     * @return custom_id - 客户系统唯一识别码
     */
    public String getCustomId() {
        return customId;
    }

    /**
     * 设置客户系统唯一识别码
     *
     * @param customId 客户系统唯一识别码
     */
    public void setCustomId(String customId) {
        this.customId = customId == null ? null : customId.trim();
    }

    /**
     * 获取设置
     *
     * @return setting - 设置
     */
    public String getSetting() {
        return setting;
    }

    /**
     * 设置设置
     *
     * @param setting 设置
     */
    public void setSetting(String setting) {
        this.setting = setting == null ? null : setting.trim();
    }

    /**
     * 获取客户系统扩展信息
     *
     * @return custom_extra - 客户系统扩展信息
     */
    public String getCustomExtra() {
        return customExtra;
    }

    /**
     * 设置客户系统扩展信息
     *
     * @param customExtra 客户系统扩展信息
     */
    public void setCustomExtra(String customExtra) {
        this.customExtra = customExtra == null ? null : customExtra.trim();
    }
}