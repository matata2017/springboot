package com.xxy.springboot.controller;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.xxy.springboot.dao.GirlDao;
import com.xxy.springboot.entity.Girl;
import com.xxy.springboot.service.GirlManager;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * Created by Shinelon on 2017/11/28.
 */
@Controller
@RequestMapping(value = "/girl")
public class GirlController {
    private static final Logger log = Logger.getLogger(GirlController.class);
    @Autowired
    private GirlManager girlManager;
    @RequestMapping("/test")
    public String test(){
        return "/spider/index";
    }
    @RequestMapping("/spider")
    public String spider(){

       // girlManager.spiderMan();
        return "index";
    }
    @RequestMapping("login")
    public ModelAndView login(){
        ModelAndView mv = new ModelAndView("index");
        List<String> lists =Lists.newArrayList();
        lists.add("A");
        lists.add("B");
        lists.add("C");
        Map<String,String> maps = Maps.newHashMap();
        maps.put("A","hahaha");
        maps.put("B","xixixi");
        maps.put("C","gagaga");
        mv.addObject("mylist",lists);
        mv.addObject("mymap",maps);
        return  mv;
    }
}
