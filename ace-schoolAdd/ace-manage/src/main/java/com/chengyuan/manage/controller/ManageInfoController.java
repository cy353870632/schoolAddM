package com.chengyuan.manage.controller;

import com.github.wxiaoqi.security.common.context.BaseContextHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @author chengyuan
 * @create 2018-11-08 14:58
 * @desc 管理端人员信息rest
 **/
@RestController
@Slf4j
@RequestMapping(value = "ManageInfo")
public class ManageInfoController {

    @RequestMapping(value = "getInfo",method = RequestMethod.POST)
    @ResponseBody
    public Object getManageInfo(HttpServletRequest request) throws Exception{
        return "wo shi ni baba 的 id："+ BaseContextHandler.getUserID();
    }

}
