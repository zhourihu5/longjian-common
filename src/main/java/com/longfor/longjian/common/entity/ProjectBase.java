package com.longfor.longjian.common.entity;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Table(name = "project")
public class ProjectBase {
    @Id
    @GeneratedValue(generator = "JDBC")
    private Integer id;

    /**
     * 项目名称
     */
    private String name;

    /**
     * 项目类型, 暂未使用, 目前都为0
     */
    private Integer type;
    @Column(name = "stage_id")
    private Integer stageId;

    /**
     * 项目所属公司id
     */
    @Column(name = "team_id")
    private Integer teamId;

    @Column(name = "create_at")
    private Date createAt;

    @Column(name = "update_at")
    private Date updateAt;

    @Column(name = "delete_at")
    private Date deleteAt;

    /**
     * 其他信息
     */
    @Column(name = "summarize_info")
    private String summarizeInfo;

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
     * 获取项目名称
     *
     * @return name - 项目名称
     */
    public String getName() {
        return name;
    }

    /**
     * 设置项目名称
     *
     * @param name 项目名称
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * 获取项目类型, 暂未使用, 目前都为0
     *
     * @return type - 项目类型, 暂未使用, 目前都为0
     */
    public Integer getType() {
        return type;
    }

    /**
     * 设置项目类型, 暂未使用, 目前都为0
     *
     * @param type 项目类型, 暂未使用, 目前都为0
     */
    public void setType(Integer type) {
        this.type = type;
    }

    /**
     * 获取项目所属公司id
     *
     * @return team_id - 项目所属公司id
     */
    public Integer getTeamId() {
        return teamId;
    }

    /**
     * 设置项目所属公司id
     *
     * @param teamId 项目所属公司id
     */
    public void setTeamId(Integer teamId) {
        this.teamId = teamId;
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
     * 获取其他信息
     *
     * @return summarize_info - 其他信息
     */
    public String getSummarizeInfo() {
        return summarizeInfo;
    }

    /**
     * 设置其他信息
     *
     * @param summarizeInfo 其他信息
     */
    public void setSummarizeInfo(String summarizeInfo) {
        this.summarizeInfo = summarizeInfo == null ? null : summarizeInfo.trim();
    }

    public Integer getStageId() {
        return stageId;
    }

    public void setStageId(Integer stageId) {
        this.stageId = stageId;
    }
}