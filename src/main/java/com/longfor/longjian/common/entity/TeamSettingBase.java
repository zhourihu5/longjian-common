package com.longfor.longjian.common.entity;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Table(name = "team_setting")
public class TeamSettingBase {
    @Id
    private Integer id;

    /**
     * 公司id， 关联team表team_id
     */
    @Column(name = "team_id")
    private Integer teamId;

    /**
     * 配置键
     */
    @Column(name = "`key`")
    private String key;

    /**
     * 配置值
     */
    @Column(name = "`value`")
    private String value;

    @Column(name = "create_at")
    private Date createAt;

    @Column(name = "update_at")
    private Date updateAt;

    @Column(name = "delete_at")
    private Date deleteAt;

    /**
     * @return id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取公司id， 关联team表team_id
     *
     * @return team_id - 公司id， 关联team表team_id
     */
    public Integer getTeamId() {
        return teamId;
    }

    /**
     * 设置公司id， 关联team表team_id
     *
     * @param teamId 公司id， 关联team表team_id
     */
    public void setTeamId(Integer teamId) {
        this.teamId = teamId;
    }

    /**
     * 获取配置键
     *
     * @return key - 配置键
     */
    public String getKey() {
        return key;
    }

    /**
     * 设置配置键
     *
     * @param key 配置键
     */
    public void setKey(String key) {
        this.key = key == null ? null : key.trim();
    }

    /**
     * 获取配置值
     *
     * @return value - 配置值
     */
    public String getValue() {
        return value;
    }

    /**
     * 设置配置值
     *
     * @param value 配置值
     */
    public void setValue(String value) {
        this.value = value == null ? null : value.trim();
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
}