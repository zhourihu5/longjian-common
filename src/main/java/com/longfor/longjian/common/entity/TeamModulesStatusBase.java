package com.longfor.longjian.common.entity;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Table(name = "team_modules_status")
public class TeamModulesStatusBase {

    @Id
    private Integer id;

    @Column(name = "team_id")
    private Integer teamId;

    private Integer gcgl;

    private Integer ydyf;

    private Integer gxgl;

    private Integer scsl;

    /**
     * 巡检模块
     */
    private Integer xunjian;

    /**
     * 进度管理
     */
    private Integer plan;

    @Column(name = "create_at")
    private Date createAt;

    @Column(name = "update_at")
    private Date updateAt;

    @Column(name = "delete_at")
    private Date deleteAt;

    private Integer docs;

    private Integer ydkf;

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
     * @return gcgl
     */
    public Integer getGcgl() {
        return gcgl;
    }

    /**
     * @param gcgl
     */
    public void setGcgl(Integer gcgl) {
        this.gcgl = gcgl;
    }

    /**
     * @return ydyf
     */
    public Integer getYdyf() {
        return ydyf;
    }

    /**
     * @param ydyf
     */
    public void setYdyf(Integer ydyf) {
        this.ydyf = ydyf;
    }

    /**
     * @return gxgl
     */
    public Integer getGxgl() {
        return gxgl;
    }

    /**
     * @param gxgl
     */
    public void setGxgl(Integer gxgl) {
        this.gxgl = gxgl;
    }

    /**
     * @return scsl
     */
    public Integer getScsl() {
        return scsl;
    }

    /**
     * @param scsl
     */
    public void setScsl(Integer scsl) {
        this.scsl = scsl;
    }

    /**
     * 获取巡检模块
     *
     * @return xunjian - 巡检模块
     */
    public Integer getXunjian() {
        return xunjian;
    }

    /**
     * 设置巡检模块
     *
     * @param xunjian 巡检模块
     */
    public void setXunjian(Integer xunjian) {
        this.xunjian = xunjian;
    }

    /**
     * 获取进度管理
     *
     * @return plan - 进度管理
     */
    public Integer getPlan() {
        return plan;
    }

    /**
     * 设置进度管理
     *
     * @param plan 进度管理
     */
    public void setPlan(Integer plan) {
        this.plan = plan;
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
     * @return docs
     */
    public Integer getDocs() {
        return docs;
    }

    /**
     * @param docs
     */
    public void setDocs(Integer docs) {
        this.docs = docs;
    }

    /**
     * @return ydkf
     */
    public Integer getYdkf() {
        return ydkf;
    }

    /**
     * @param ydkf
     */
    public void setYdkf(Integer ydkf) {
        this.ydkf = ydkf;
    }

}