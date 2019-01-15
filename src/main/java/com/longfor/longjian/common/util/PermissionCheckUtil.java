package com.longfor.longjian.common.util;

import com.longfor.longjian.common.FeignClient.IPermissionService;
import com.longfor.longjian.common.base.LjBaseResponse;
import com.longfor.longjian.common.req.feignClientReq.ProjectPermissionReq;
import com.longfor.longjian.common.req.feignClientReq.TeamPermissionReq;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.Map;

/**
 * 权限检查工具类
 *
 * @author zkm
 * @date 2019/1/15 11:44
 */
@Slf4j
@Component
public class PermissionCheckUtil {

    private static final String ALLOWED = "Yes";
    private static final String PERMISSION_KEY = "has_per";

    @Resource
    private IPermissionService initService;
    private static IPermissionService iPermissionService;

    @PostConstruct
    public void init() {
        iPermissionService = initService;
    }

    /**
     * 检查Project权限
     *
     * @param projectId
     * @param perTitle
     * @return
     */
    public static boolean checkProjectPermission(Integer projectId, String perTitle) {
        if (projectId == null || StringUtils.isEmpty(perTitle.trim())) {
            return false;
        }
        HttpSession session = RequestContextHolderUtil.getSession();
        Integer uid = session.getAttribute("uid") == null ? 0 : Integer.valueOf(session.getAttribute("uid").toString());
        ProjectPermissionReq projectPermissionReq = new ProjectPermissionReq();
        projectPermissionReq.setUser_id(uid);
        projectPermissionReq.setProj_id(projectId);
        projectPermissionReq.setPer_title(perTitle.trim());
        // 调用鉴权接口
        LjBaseResponse<Object> permission = iPermissionService.projectPermission(projectPermissionReq);
        Map<String, String> result = permission.getData() == null ? null : (Map<String, String>) permission.getData();
        if (result == null || !ALLOWED.equals(result.get(PERMISSION_KEY))) {
            log.info("Project权限检查接口 - uid={}的用户没有projectId={}的{}权限, 鉴权失败", uid, projectId, perTitle);
            return false;
        }
        session.setAttribute("projectId", projectId);
        log.info("Project权限检查接口 - uid={}的用户有projectId={}的{}权限, 鉴权成功", uid, projectId, perTitle);
        return true;
    }

    /**
     * 检查Team权限
     *
     * @param teamId
     * @param perTitle
     * @return
     */
    public static boolean checkTeamPermission(Integer teamId, String perTitle) {
        if (teamId == null || StringUtils.isEmpty(perTitle.trim())) {
            return false;
        }
        Integer uid = RequestContextHolderUtil.getSession().getAttribute("uid") == null ? 0 : Integer.valueOf(RequestContextHolderUtil.getSession().getAttribute("uid").toString());
        TeamPermissionReq teamPermissionReq = new TeamPermissionReq();
        teamPermissionReq.setUser_id(uid);
        teamPermissionReq.setTeam_id(teamId);
        teamPermissionReq.setPer_title(perTitle.trim());
        // 调用鉴权接口
        LjBaseResponse<Object> permission = iPermissionService.teamPermission(teamPermissionReq);
        Map<String, String> result = permission.getData() == null ? null : (Map<String, String>) permission.getData();
        if (result == null || !ALLOWED.equals(result.get(PERMISSION_KEY))) {
            log.info("Team权限检查接口 - uid={}的用户没有teamId={}的{}权限, 鉴权失败", uid, teamId, perTitle);
            return false;
        }
        log.info("Team权限检查接口 - uid={}的用户有teamId={}的{}权限, 鉴权成功", uid, teamId, perTitle);
        return true;
    }

}
