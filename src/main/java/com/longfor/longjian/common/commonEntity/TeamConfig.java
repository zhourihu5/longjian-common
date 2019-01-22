package com.longfor.longjian.common.commonEntity;

import com.longfor.longjian.common.entity.TeamSettingBase;

import java.util.ArrayList;
import java.util.List;

public class TeamConfig {
    private String compangName;
    private String logoUrl;
    private String systemName;
    private List<TeamSettingBase> refusedAcceptBuildCause = new ArrayList<>();

    public String getCompangName() {
        return compangName;
    }

    public void setCompangName(String compangName) {
        this.compangName = compangName;
    }

    public String getLogoUrl() {
        return logoUrl;
    }

    public void setLogoUrl(String logoUrl) {
        this.logoUrl = logoUrl;
    }

    public String getSystemName() {
        return systemName;
    }

    public void setSystemName(String systemName) {
        this.systemName = systemName;
    }

    public List<TeamSettingBase> getRefusedAcceptBuildCause() {
        return refusedAcceptBuildCause;
    }

    public void setRefusedAcceptBuildCause(List<TeamSettingBase> refusedAcceptBuildCause) {
        this.refusedAcceptBuildCause = refusedAcceptBuildCause;
    }
}
