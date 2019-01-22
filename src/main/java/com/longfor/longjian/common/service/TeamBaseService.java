package com.longfor.longjian.common.service;

import com.longfor.gaia.gfs.data.mybatis.datasource.LFAssignDataSource;
import com.longfor.longjian.common.dao.TeamBaseMapper;
import com.longfor.longjian.common.entity.TeamBase;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;

@Service
@Slf4j
public class TeamBaseService {

    @Resource
    private TeamBaseMapper teamBaseMapper;

    @LFAssignDataSource("zhijian2_apisvr")
    public TeamBase getTeamById(Integer teamId) {
        Example example = new Example(TeamBase.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("teamId",teamId);
//        ExampleUtil.addDeleteAtJudge(example);
        return teamBaseMapper.selectOneByExample(example);
    }
}
