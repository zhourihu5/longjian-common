package com.longfor.longjian.common.util;

import com.alibaba.fastjson.JSON;
import com.longfor.longjian.common.FeignClient.IPermissionService;
import com.longfor.longjian.common.base.LjBaseResponse;
import com.longfor.longjian.common.consts.LoginEnum;
import com.longfor.longjian.common.consts.YesNoEnum;
import com.longfor.longjian.common.req.feignClientReq.ProjectPermissionReq;
import com.longfor.longjian.common.req.feignClientReq.TeamPermissionReq;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@Slf4j
@Component
public class CtrlTool {

    @Autowired
    private IPermissionService ipermissionService;

    /**
     * 项目鉴权，单功能判断
     *
     * @param request
     * @param perm
     * @throws Exception
     */
    public void projPerm(HttpServletRequest request, String perm) throws Exception {
//        proj = c.MustGet("cur_proj").(*models.Project)
//        user = c.MustGet("user").(*zj3uc_models.User) todo
        Integer projId = 6;
        Integer userId = 7556;

        ProjectPermissionReq projectPermissionReq = new ProjectPermissionReq();
        projectPermissionReq.setProj_id(projId);
        projectPermissionReq.setUser_id(userId);
        projectPermissionReq.setPer_title(perm);

        if (StringUtils.isNotBlank(perm)) {
            Enum hasPer = YesNoEnum.No;
            try {
                LjBaseResponse<Object> res = ipermissionService.projectPermission(projectPermissionReq);
                log.warn("project permission result:" + JSON.toJSONString(res));
                hasPer = YesNoEnum.valueOf(((Map<String, Object>) res.getData()).get("has_per").toString());
                if (!YesNoEnum.Yes.equals(hasPer)) {
                    String err = LoginEnum.NO_PERMISSION.getName() + "--" + perm;
                    log.warn(err);
                    throw new Exception(err);
                }
            } catch (Exception e) {
                log.warn(e + "");
                throw e;
            }
        }
    }

    /**
     * 项目鉴权，多功能判断
     *
     * @param request
     * @param perms
     * @throws Exception
     */
    public void projPermMulti(HttpServletRequest request, String[] perms) throws Exception {
//        proj = c.MustGet("cur_proj").(*models.Project)
//        user = c.MustGet("user").(*zj3uc_models.User) todo
        Integer projId = 6;
        Integer userId = 7556;

        ProjectPermissionReq projectPermissionReq = new ProjectPermissionReq();
        projectPermissionReq.setProj_id(projId);
        projectPermissionReq.setUser_id(userId);

        if (perms.length > 0) {
            Enum hasPer = YesNoEnum.No;
            for (String perm : perms
            ) {
                try {
                    projectPermissionReq.setPer_title(perm);
                    LjBaseResponse<Object> res = ipermissionService.projectPermission(projectPermissionReq);
                    log.warn("project permission result:" + JSON.toJSONString(res));
                    hasPer = YesNoEnum.valueOf(((Map<String, Object>) res.getData()).get("has_per").toString());
                    if (YesNoEnum.Yes.equals(hasPer)) {
                        break;
                    }
                } catch (Exception e) {
                    log.warn(e + "");
                    throw e;
                }
            }
            if (!YesNoEnum.Yes.equals(hasPer)) {
                String err = LoginEnum.NO_PERMISSION.getName() + "--" + String.join("或", perms);
                log.warn(err);
                throw new Exception(err);
            }
        } else {
            throw new Exception("perms 不能为空");
        }
    }

    /**
     * 公司/集团 鉴权，单功能判断
     *
     * @param request
     * @param perm
     * @throws Exception
     */
    public void teamPerm(HttpServletRequest request, String perm) throws Exception {
//        team = c.MustGet("cur_team").(*zj3uc_models.Team)
//        user = c.MustGet("user").(*zj3uc_models.User) todo
        Integer teamId = 5;
        Integer userId = 7556;

        TeamPermissionReq teamPermissionReq = new TeamPermissionReq();
        teamPermissionReq.setTeam_id(teamId);
        teamPermissionReq.setUser_id(userId);
        teamPermissionReq.setPer_title(perm);

        if (StringUtils.isNotBlank(perm)) {
            Enum hasPer = YesNoEnum.No;
            try {
                LjBaseResponse<Object> res = ipermissionService.teamPermission(teamPermissionReq);
                log.warn("team permission result:" + JSON.toJSONString(res));
                hasPer = YesNoEnum.valueOf(((Map<String, Object>) res.getData()).get("has_per").toString());
                if (!YesNoEnum.Yes.equals(hasPer)) {
                    String err = LoginEnum.NO_PERMISSION.getName() + "--" + perm;
                    log.warn(err);
                    throw new Exception(err);
                }
            } catch (Exception e) {
                log.warn(e + "");
                throw e;
            }
        }
    }


    /**
     * 公司/集团 鉴权，多功能判断
     *
     * @param request
     * @param perms
     * @throws Exception
     */
    public void teamPermMulti(HttpServletRequest request, String[] perms) throws Exception {
//        team = c.MustGet("cur_team").(*zj3uc_models.Team)
//        user = c.MustGet("user").(*zj3uc_models.User) todo
        Integer teamId = 5;
        Integer userId = 7556;

        TeamPermissionReq teamPermissionReq = new TeamPermissionReq();
        teamPermissionReq.setTeam_id(teamId);
        teamPermissionReq.setUser_id(userId);

        if (perms.length > 0) {
            Enum hasPer = YesNoEnum.No;
            for (String perm : perms
            ) {
                try {
                    teamPermissionReq.setPer_title(perm);
                    LjBaseResponse<Object> res = ipermissionService.teamPermission(teamPermissionReq);
                    log.warn("team permission result:" + JSON.toJSONString(res));
                    hasPer = YesNoEnum.valueOf(((Map<String, Object>) res.getData()).get("has_per").toString());
                    if (YesNoEnum.Yes.equals(hasPer)) {
                        break;
                    }
                } catch (Exception e) {
                    log.warn(e + "");
                    throw e;
                }
            }
            if (!YesNoEnum.Yes.equals(hasPer)) {
                String err = LoginEnum.NO_PERMISSION.getName() + "--" + String.join("或", perms);
                log.warn(err);
                throw new Exception(err);
            }
        } else {
            throw new Exception("perms 不能为空");
        }
    }

}
