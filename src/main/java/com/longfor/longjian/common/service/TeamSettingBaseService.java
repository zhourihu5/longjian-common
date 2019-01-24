package com.longfor.longjian.common.service;

import com.longfor.gaia.gfs.data.mybatis.datasource.LFAssignDataSource;
import com.longfor.longjian.common.dao.TeamSettingBaseMapper;
import com.longfor.longjian.common.entity.TeamBase;
import com.longfor.longjian.common.entity.TeamSettingBase;
import com.longfor.longjian.common.util.ExampleUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.List;

@Service
@Slf4j
public class TeamSettingBaseService {

    @Resource
    private TeamSettingBaseMapper teamSettingBaseMapper;

    @LFAssignDataSource("zhijian2_apisvr")
    public List<TeamSettingBase> getTeamSettingsByTeamId(int groupId) {
        Example example = new Example(TeamBase.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("teamId",groupId);
        ExampleUtil.addDeleteAtJudge(example);
        return teamSettingBaseMapper.selectByExample(example);
    }
}
