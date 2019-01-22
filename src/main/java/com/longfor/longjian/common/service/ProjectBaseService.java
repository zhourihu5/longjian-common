package com.longfor.longjian.common.service;

import com.longfor.longjian.common.dao.ProjectBaseMapper;
import com.longfor.longjian.common.entity.ProjectBase;
import com.longfor.longjian.common.util.ExampleUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;

@Service
@Slf4j
public class ProjectBaseService {

    @Resource
    private ProjectBaseMapper projectBaseMapper;

    public ProjectBase getByIdNoFoundErr(int pid) {
        Example example = new Example(ProjectBase.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("id",pid);
        ExampleUtil.addDeleteAtJudge(example);
        return projectBaseMapper.selectOneByExample(example);
    }
}
