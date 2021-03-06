package com.longfor.longjian.common.util;

import com.alibaba.fastjson.JSON;
import com.longfor.longjian.common.FeignClient.IPermissionService;
import com.longfor.longjian.common.base.LjBaseResponse;
import com.longfor.longjian.common.commonEntity.TeamActiveModulesCommon;
import com.longfor.longjian.common.commonEntity.TeamConfig;
import com.longfor.longjian.common.consts.LoginEnum;
import com.longfor.longjian.common.consts.YesNoEnum;
import com.longfor.longjian.common.entity.ProjectBase;
import com.longfor.longjian.common.entity.TeamBase;
import com.longfor.longjian.common.entity.TeamSettingBase;
import com.longfor.longjian.common.entity.UserBase;
import com.longfor.longjian.common.exception.LjBaseRuntimeException;
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
     * ??????????????????????????????
     *
     * @param request
     * @param perm
     */
    public void projPerm(HttpServletRequest request, String perm) {
        if (sessionInfo.getBaseInfo("cur_proj") == null){
            throw new LjBaseRuntimeException(-9999,"?????????????????????");
        }
        ProjectBase proj = (ProjectBase)sessionInfo.getBaseInfo("cur_proj");
        UserBase user = sessionInfo.getSessionUser();

        ProjectPermissionReq projectPermissionReq = new ProjectPermissionReq();
        projectPermissionReq.setProj_id(proj.getId());
        projectPermissionReq.setUser_id(user.getUserId());
        projectPermissionReq.setPer_title(perm);

        if (StringUtils.isNotBlank(perm)) {
            Enum hasPer = YesNoEnum.NO;
            try {
                LjBaseResponse<Object> res = ipermissionService.projectPermission(projectPermissionReq);
                log.warn("project permission result:" + JSON.toJSONString(res));
                hasPer = YesNoEnum.valueOf(((Map<String, Object>) res.getData()).get("has_per").toString());
                if (!YesNoEnum.YES.equals(hasPer)) {
                    String err = LoginEnum.NO_PERMISSION.getName() + "--" + perm;
                    log.warn(err);
                    throw new LjBaseRuntimeException(-9999,err);
                }
            } catch (Exception e) {
                log.warn(e + "");
                throw new LjBaseRuntimeException(-9999,e.getMessage());
            }
        }
    }

    /**
     * ??????????????????????????????
     *
     * @param request
     * @param perms
     */
    public void projPermMulti(HttpServletRequest request, String[] perms) {
        if (sessionInfo.getBaseInfo("cur_proj") == null){
            throw new LjBaseRuntimeException(-9999,"?????????????????????");
        }
        ProjectBase proj = (ProjectBase)sessionInfo.getBaseInfo("cur_proj");
        UserBase user = sessionInfo.getSessionUser();

        ProjectPermissionReq projectPermissionReq = new ProjectPermissionReq();
        projectPermissionReq.setProj_id(proj.getId());
        projectPermissionReq.setUser_id(user.getUserId());

        if (perms.length > 0) {
            Enum hasPer = YesNoEnum.NO;
            for (String perm : perms
            ) {
                try {
                    projectPermissionReq.setPer_title(perm);
                    LjBaseResponse<Object> res = ipermissionService.projectPermission(projectPermissionReq);
                    log.warn("project permission result:" + JSON.toJSONString(res));
                    hasPer = YesNoEnum.valueOf(((Map<String, Object>) res.getData()).get("has_per").toString());
                    if (YesNoEnum.YES.equals(hasPer)) {
                        break;
                    }
                } catch (Exception e) {
                    log.warn(e + "");
                    throw new LjBaseRuntimeException(-9999,e.getMessage());
                }
            }
            if (!YesNoEnum.YES.equals(hasPer)) {
                String err = LoginEnum.NO_PERMISSION.getName() + "--" + String.join("???", perms);
                log.warn(err);
                throw new LjBaseRuntimeException(-9999,err);
            }
        } else {
            throw new LjBaseRuntimeException(-9999,"perms ????????????");
        }
    }

    /**
     * ??????/?????? ????????????????????????
     *
     * @param request
     * @param perm
     */
    public void teamPerm(HttpServletRequest request, String perm) {
        if (sessionInfo.getBaseInfo("cur_team") == null){
            throw new LjBaseRuntimeException(-9999,"?????????????????????");
        }
        TeamBase team = (TeamBase) sessionInfo.getBaseInfo("cur_team");
        UserBase user = sessionInfo.getSessionUser();

        TeamPermissionReq teamPermissionReq = new TeamPermissionReq();
        teamPermissionReq.setTeam_id(team.getTeamId());
        teamPermissionReq.setUser_id(user.getUserId());
        teamPermissionReq.setPer_title(perm);

        if (StringUtils.isNotBlank(perm)) {
            Enum hasPer = YesNoEnum.NO;
            try {
                LjBaseResponse<Object> res = ipermissionService.teamPermission(teamPermissionReq);
                log.warn("team permission result:" + JSON.toJSONString(res));
                hasPer = YesNoEnum.valueOf(((Map<String, Object>) res.getData()).get("has_per").toString());
                if (!YesNoEnum.YES.equals(hasPer)) {
                    String err = LoginEnum.NO_PERMISSION.getName() + "--" + perm;
                    log.warn(err);
                    throw new LjBaseRuntimeException(-9999,err);
                }
            } catch (Exception e) {
                log.warn(e + "");
                throw new LjBaseRuntimeException(-9999,e.getMessage());
            }
        }
    }


    /**
     * ??????/?????? ????????????????????????
     *
     * @param request
     * @param perms
     */
    public void teamPermMulti(HttpServletRequest request, String[] perms) {
        if (sessionInfo.getBaseInfo("cur_team") == null){
            throw new LjBaseRuntimeException(-9999,"?????????????????????");
        }
        TeamBase team = (TeamBase) sessionInfo.getBaseInfo("cur_team");
        UserBase user = sessionInfo.getSessionUser();

        TeamPermissionReq teamPermissionReq = new TeamPermissionReq();
        teamPermissionReq.setTeam_id(team.getTeamId());
        teamPermissionReq.setUser_id(user.getUserId());

        if (perms.length > 0) {
            Enum hasPer = YesNoEnum.NO;
            for (String perm : perms
            ) {
                try {
                    teamPermissionReq.setPer_title(perm);
                    LjBaseResponse<Object> res = ipermissionService.teamPermission(teamPermissionReq);
                    log.warn("team permission result:" + JSON.toJSONString(res));
                    hasPer = YesNoEnum.valueOf(((Map<String, Object>) res.getData()).get("has_per").toString());
                    if (YesNoEnum.YES.equals(hasPer)) {
                        break;
                    }
                } catch (Exception e) {
                    log.warn(e + "");
                    throw new LjBaseRuntimeException(-9999,e.getMessage());
                }
            }
            if (!YesNoEnum.YES.equals(hasPer)) {
                String err = LoginEnum.NO_PERMISSION.getName() + "--" + String.join("???", perms);
                log.warn(err);
                throw new LjBaseRuntimeException(-9999,err);
            }
        } else {
            throw new LjBaseRuntimeException(-9999,"perms ????????????");
        }
    }


    public void projectRequired() {
        HttpServletRequest request = RequestContextHolderUtil.getRequest();
        Object projectIdStr = request.getParameter("project_id");
        log.debug("Start to get ProjectId : " + projectIdStr);
        //pId, err := strconv.Atoi(projectIdStr) ????????????????????????
        if (projectIdStr == null || StringUtils.isBlank(projectIdStr.toString())) {
            throw new LjBaseRuntimeException(-9999,"Fail to get projectId");
        }
        int pid = Integer.parseInt(projectIdStr.toString());

        ProjectBase project = projectBaseService.getByIdNoFoundErr(pid);
        if (project == null) {
            throw new LjBaseRuntimeException(-9999,"Fail to get project");
        } else {
            sessionInfo.setBaseInfo("cur_proj", project);
            //???????????????????????????
            sessionInfo.setBaseInfo("projectId",project.getId());
        }

        TeamBase team = teamBaseService.getTeamById(project.getTeamId());
        if (team == null) {
            throw new LjBaseRuntimeException(-9999,"Fail to get team");
        } else {
            sessionInfo.setBaseInfo("cur_team", team);
        }

        int groupId = team.getTeamId();
        if (team.getParentTeamId() > 0) {
            TeamBase group = teamBaseService.getTeamById(team.getParentTeamId());
            if (group == null) {
                throw new LjBaseRuntimeException(-9999,"Fail to get group");
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
            throw new LjBaseRuntimeException(-9999,"Fail to get teamSettings");
        }
        settings.forEach(c -> {
            switch (c.getKey()) {
                case "logo_url":
                    teamConfig.setLogoUrl(c.getValue());
                    break;
                case "company_name":
                    teamConfig.setCompangName(c.getValue());
                    break;
                case "system_name":
                    teamConfig.setSystemName(c.getValue());
                    break;
                default:
                    teamConfig.getRefusedAcceptBuildCause().add(c);
            }
        });

//        sessionInfo.setBaseInfo("config", cfg);
        sessionInfo.setBaseInfo("groupConfig", teamConfig);

        //????????????????????????
        TeamActiveModulesCommon modulesStatus = teamActiveModulesCommon.getTeamActiveModulesByTeamId(groupId);
        sessionInfo.setBaseInfo("modules",modulesStatus);
    }

    public void teamRequired() {
        HttpServletRequest request = RequestContextHolderUtil.getRequest();
        Object teamIdStr = request.getParameter("team_id");
        if (teamIdStr == null || StringUtils.isBlank(teamIdStr.toString())){
            teamIdStr = request.getParameter("groupId");
        }
        //teamId, err := strconv.Atoi(teamIdStr)????????????????????????
        if (teamIdStr == null || StringUtils.isBlank(teamIdStr.toString())){
            throw new LjBaseRuntimeException(-9999,"Fail to get teamId");
        }
        int teamId = Integer.parseInt(teamIdStr.toString());

        TeamBase team = teamBaseService.getTeamById(teamId);
        if (team == null) {
            throw new LjBaseRuntimeException(-9999,"Fail to get team");
        }
//        else {
//            sessionInfo.setBaseInfo("cur_team", team);
//        }
        TeamBase teamGroup = team;
        if (team.getParentTeamId() > 0){
            teamGroup = teamBaseService.getTeamById(team.getParentTeamId());
            if (teamGroup == null) {
                throw new LjBaseRuntimeException(-9999,"Fail to get group");
            }
        }

        TeamConfig teamConfig = new TeamConfig();
        teamConfig.setLogoUrl(displayLogoUrl);
        teamConfig.setCompangName(displayCompanyName);
        teamConfig.setSystemName(displaySystemName);

        List<TeamSettingBase> settings = teamSettingBaseService.getTeamSettingsByTeamId(teamGroup.getTeamId());
        if (settings == null || settings.size() <= 0) {
            throw new LjBaseRuntimeException(-9999,"Fail to get teamSettings");
        }
        settings.forEach(c -> {
            switch (c.getKey()) {
                case "logo_url":
                    teamConfig.setLogoUrl(c.getValue());
                    break;
                case "company_name":
                    teamConfig.setCompangName(c.getValue());
                    break;
                case "system_name":
                    teamConfig.setSystemName(c.getValue());
                    break;
                default:
                    teamConfig.getRefusedAcceptBuildCause().add(c);
            }
        });

        //????????????????????????
        TeamActiveModulesCommon modulesStatus = teamActiveModulesCommon.getTeamActiveModulesByTeamId(teamGroup.getTeamId());

        sessionInfo.setBaseInfo("cur_team", team);
        sessionInfo.setBaseInfo("cur_group", teamGroup);
        sessionInfo.setBaseInfo("team_group", teamGroup);
//        sessionInfo.setBaseInfo("config",  config.Config);
        sessionInfo.setBaseInfo("groupConfig", teamConfig);
        sessionInfo.setBaseInfo("modules", modulesStatus);
    }

}
