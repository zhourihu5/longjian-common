package com.longfor.longjian.common.commonEntity;

import com.longfor.longjian.common.entity.TeamModulesStatusBase;
import com.longfor.longjian.common.service.TeamModulesStatusBaseService;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Data
@NoArgsConstructor
@Component
@Slf4j
public class TeamActiveModulesCommon {

    @Resource
    private TeamModulesStatusBaseService teamModulesStatusBaseService;

    /**
     * 工程管理
     */
    private Boolean GCGL;
    /**
     * 移动验房
     */
    private Boolean YDYF;
    /**
     * 工序管理
     */
    private Boolean GXGL;

    private Boolean SCSL;

    public TeamActiveModulesCommon(boolean gcgl, boolean ydyf, boolean gxgl, boolean scsl){
        this.GCGL = gcgl;
        this.YDYF = ydyf;
        this.GXGL = gxgl;
        this.SCSL = scsl;
    }

    public TeamActiveModulesCommon getTeamActiveModulesByTeamId(int teamId){
        // 此处默认工程管理和移动验房是开启的
        TeamActiveModulesCommon result = new TeamActiveModulesCommon(true,true,false,false);
        TeamModulesStatusBase item = teamModulesStatusBaseService.getByTeamId(teamId);
        if (item == null){
            log.debug("here");
            return result;
        }

        result.setGCGL(item.getGcgl() == 1);
        result.setYDYF(item.getYdyf() == 1);
        result.setGXGL(item.getGxgl() == 1);
        result.setSCSL(item.getScsl() == 1);

        result.setSCSL(true);
        result.setYDYF(true);

        return result;
    }
}
