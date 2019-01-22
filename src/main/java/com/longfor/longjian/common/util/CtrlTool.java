package com.longfor.longjian.common.util;

import com.alibaba.fastjson.JSON;
import com.longfor.longjian.common.FeignClient.IPermissionService;
import com.longfor.longjian.common.base.LjBaseResponse;
import com.longfor.longjian.common.commonEntity.TeamActiveModulesCommon;
import com.longfor.longjian.common.consts.LoginEnum;
import com.longfor.longjian.common.consts.YesNoEnum;
import com.longfor.longjian.common.entity.ProjectBase;
import com.longfor.longjian.common.entity.TeamBase;
import com.longfor.longjian.common.commonEntity.TeamConfig;
import com.longfor.longjian.common.entity.TeamModulesStatusBase;
import com.longfor.longjian.common.entity.TeamSettingBase;
import com.longfor.longjian.common.req.feignClientReq.ProjectPermissionReq;
import com.longfor.longjian.common.req.feignClientReq.TeamPermissionReq;
import com.longfor.longjian.common.service.ProjectBaseService;
import com.longfor.longjian.common.service.TeamBaseService;
import com.longfor.longjian.common.service.TeamSettingBaseService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@Slf4j
@Component
public class CtrlTool {

    @Autowired
    private IPermissionService ipermissionService;
    @Autowired
    private ProjectBaseService projectBaseService;
    @Autowired
    private SessionInfo sessionInfo;
    @Autowired
    private TeamBaseService teamBaseService;
    @Autowired
    private TeamSettingBaseService teamSettingBaseService;
    @Autowired
    private TeamActiveModulesCommon teamActiveModulesCommon;
    @Value("${display_logo_url}")
    private String displayLogoUrl;
    @Value("{display_company_name}")
    private String displayCompanyName;
    @Value("{display_system_name}")
    private String displaySystemName;

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


    public void projectRequired() throws Exception {
        HttpServletRequest request = RequestContextHolderUtil.getRequest();
        Object projectIdStr = request.getParameter("project_id");
        log.debug("Start to get ProjectId : " + projectIdStr);
        //pId, err := strconv.Atoi(projectIdStr) 字符串和数字转换
        if (projectIdStr == null || StringUtils.isBlank(projectIdStr.toString())) {
            throw new Exception("Fail to get projectId");
        }
        int pid = Integer.parseInt(projectIdStr.toString());

        ProjectBase project = projectBaseService.getByIdNoFoundErr(pid);
        if (project == null) {
            throw new Exception("Fail to get project");
        } else {
            sessionInfo.setBaseInfo("cur_proj", project);
        }

        TeamBase team = teamBaseService.getTeamById(project.getTeamId());
        if (team == null) {
            throw new Exception("Fail to get team");
        } else {
            sessionInfo.setBaseInfo("cur_team", team);
        }

        int groupId = team.getTeamId();
        if (team.getParentTeamId() > 0) {
            TeamBase group = teamBaseService.getTeamById(team.getParentTeamId());
            if (group == null) {
                throw new Exception("Fail to get group");
            } else {
                sessionInfo.setBaseInfo("cur_group", group);
                sessionInfo.setBaseInfo("team_group", group);
                groupId = group.getTeamId();
            }
        } else {
            sessionInfo.setBaseInfo("cur_group", team);
            sessionInfo.setBaseInfo("team_group", team);
        }

        TeamConfig teamConfig = new TeamConfig();
        teamConfig.setLogoUrl(displayLogoUrl);
        teamConfig.setCompangName(displayCompanyName);
        teamConfig.setSystemName(displaySystemName);

        List<TeamSettingBase> settings = teamSettingBaseService.getTeamSettingsByTeamId(groupId);
        if (settings == null || settings.size() <= 0) {
            throw new Exception("Fail to get teamSettings");
        }
        settings.forEach(c -> {
            switch (c.getKey()) {
                case "logo_url":
                    teamConfig.setLogoUrl(c.getValue());
                case "company_name":
                    teamConfig.setCompangName(c.getValue());
                case "system_name":
                    teamConfig.setSystemName(c.getValue());
                default:
                    teamConfig.getRefusedAcceptBuildCause().add(c);
            }
        });

//        sessionInfo.setBaseInfo("config", cfg);
        sessionInfo.setBaseInfo("groupConfig", teamConfig);

        //设置公司激活模块
        TeamActiveModulesCommon modulesStatus = teamActiveModulesCommon.getTeamActiveModulesByTeamId(groupId);
        sessionInfo.setBaseInfo("modules",modulesStatus);
    }

    public void teamRequired() throws Exception {
        HttpServletRequest request = RequestContextHolderUtil.getRequest();
        Object teamIdStr = request.getParameter("team_id");
        if (teamIdStr == null || StringUtils.isBlank(teamIdStr.toString())){
            teamIdStr = request.getParameter("groupId");
        }
        //teamId, err := strconv.Atoi(teamIdStr)字符串和数字转换
        if (teamIdStr == null || StringUtils.isBlank(teamIdStr.toString())){
            throw new Exception("Fail to get teamId");
        }
        int teamId = Integer.parseInt(teamIdStr.toString());

        TeamBase team = teamBaseService.getTeamById(teamId);
        if (team == null) {
            throw new Exception("Fail to get team");
        }
//        else {
//            sessionInfo.setBaseInfo("cur_team", team);
//        }
        TeamBase teamGroup = team;
        if (team.getParentTeamId() > 0){
            teamGroup = teamBaseService.getTeamById(team.getParentTeamId());
            if (teamGroup == null) {
                throw new Exception("Fail to get group");
            }
        }

        TeamConfig teamConfig = new TeamConfig();
        teamConfig.setLogoUrl(displayLogoUrl);
        teamConfig.setCompangName(displayCompanyName);
        teamConfig.setSystemName(displaySystemName);

        List<TeamSettingBase> settings = teamSettingBaseService.getTeamSettingsByTeamId(teamGroup.getTeamId());
        if (settings == null || settings.size() <= 0) {
            throw new Exception("Fail to get teamSettings");
        }
        settings.forEach(c -> {
            switch (c.getKey()) {
                case "logo_url":
                    teamConfig.setLogoUrl(c.getValue());
                case "company_name":
                    teamConfig.setCompangName(c.getValue());
                case "system_name":
                    teamConfig.setSystemName(c.getValue());
                default:
                    teamConfig.getRefusedAcceptBuildCause().add(c);
            }
        });

        //设置公司激活模块
        TeamActiveModulesCommon modulesStatus = teamActiveModulesCommon.getTeamActiveModulesByTeamId(teamGroup.getTeamId());

        sessionInfo.setBaseInfo("cur_team", team);
        sessionInfo.setBaseInfo("cur_group", teamGroup);
        sessionInfo.setBaseInfo("team_group", teamGroup);
//        sessionInfo.setBaseInfo("config",  config.Config);
        sessionInfo.setBaseInfo("groupConfig", teamConfig);
        sessionInfo.setBaseInfo("modules", modulesStatus);
    }

}
