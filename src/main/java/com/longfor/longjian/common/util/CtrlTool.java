package com.longfor.longjian.common.util;

import com.alibaba.fastjson.JSON;
import com.longfor.longjian.common.FeignClient.IPermissionService;
import com.longfor.longjian.common.base.LjBaseResponse;
import com.longfor.longjian.common.consts.LoginEnum;
import com.longfor.longjian.common.consts.YesNoEnum;
import com.longfor.longjian.common.req.feignClientReq.ProjectPermissionReq;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@Slf4j
@Component
public class CtrlTool {

    @Autowired
    private IPermissionService ipermissionService;

    /**
     * 項目鑒權
     * @param request
     * @param perm
     */
    public void ProjPerm(HttpServletRequest request,String perm) throws Exception {
//        proj = c.MustGet("cur_proj").(*models.Project)
//        user = c.MustGet("user").(*zj3uc_models.User) todo
        Integer proj = 6;
        Integer user = 7556;

        ProjectPermissionReq projectPermissionReq = new ProjectPermissionReq();
        projectPermissionReq.setProj_id(proj);
        projectPermissionReq.setUser_id(user);
        projectPermissionReq.setPer_title(perm);

        if (StringUtils.isNotBlank(perm)){
            Enum hasPer;
            try {
                LjBaseResponse<Object> res = ipermissionService.projectPermission(projectPermissionReq);
                log.warn("project permission result:" + JSON.toJSONString(res));
                hasPer = YesNoEnum.valueOf (((Map<String, Object>) res.getData()).get("has_per").toString());
                if (!YesNoEnum.Yes.equals(hasPer)){
                    String err = LoginEnum.NO_PERMISSION.getName() + "--" + perm;
                    log.warn(err);
                    throw new Exception(err);
                }
            }catch (Exception e){
                log.warn(e + "");
                throw e;
            }
        }
    }

}
