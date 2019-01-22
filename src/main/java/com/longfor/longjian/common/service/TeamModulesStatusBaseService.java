package com.longfor.longjian.common.service;

import com.longfor.gaia.gfs.data.mybatis.datasource.LFAssignDataSource;
import com.longfor.longjian.common.dao.TeamModulesStatusBaseMapper;
import com.longfor.longjian.common.entity.TeamModulesStatusBase;
import com.longfor.longjian.common.util.ExampleUtil;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;

/**
 * @author zkm
 * @date 2018/12/29 10:21
 */
@Service
public class TeamModulesStatusBaseService {

    @Resource
    private TeamModulesStatusBaseMapper teamModulesStatusBaseMapper;

    /**
     * 通过teamId查询
     *
     * @param teamId
     * @return
     */
    @LFAssignDataSource("zhijian2")
    public TeamModulesStatusBase getByTeamId(Integer teamId) {
        Example example = new Example(TeamModulesStatusBase.class);
        example.createCriteria().andEqualTo("teamId", teamId);
        ExampleUtil.addDeleteAtJudge(example);
        return teamModulesStatusBaseMapper.selectOneByExample(example);
    }

}
