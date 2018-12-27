package com.longfor.longjian.common.FeignClient;

import com.longfor.gaia.gfs.web.feign.LFFeignClient;
import com.longfor.gaia.gfs.web.feign.config.LFFeignConfiguration;
import com.longfor.longjian.common.base.LjBaseResponse;
import com.longfor.longjian.common.req.feignClientReq.ProjectPermissionReq;
import com.longfor.longjian.common.req.feignClientReq.TeamPermissionReq;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Service
@LFFeignClient(group = "longjian-basic-server",value = "permission",configuration = LFFeignConfiguration.class)
public interface IPermissionService {

    /**
     * 项目鉴权
     * 检查用户是否有此项目权限
     * @param projectPermissionReq
     * @return
     */
    @PostMapping(value = "project_permission" ,produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    LjBaseResponse<Object> projectPermission(@RequestBody ProjectPermissionReq projectPermissionReq);


    /**
     * 公司鉴权
     * 检查用户是否有此集团/公司权限
     * @param teamPermissionReq
     * @return
     */
    @PostMapping(value = "team_permission" ,produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    LjBaseResponse<Object> teamPermission(@RequestBody TeamPermissionReq teamPermissionReq);
}
