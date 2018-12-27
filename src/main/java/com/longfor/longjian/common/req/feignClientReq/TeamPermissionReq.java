package com.longfor.longjian.common.req.feignClientReq;

import lombok.Data;

@Data
public class TeamPermissionReq {
    /**
     * 用户ID
     */
    private Integer user_id;
    /**
     * 集团ID
     */
    private Integer team_id;
    /**
     * 权限点标题
     */
    private String per_title;
}
