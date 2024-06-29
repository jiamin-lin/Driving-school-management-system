package com.controller;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Map;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletRequest;

import com.utils.ValidatorUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.annotation.IgnoreAuth;

import com.entity.XueyuanxinxiEntity;
import com.entity.view.XueyuanxinxiView;

import com.service.XueyuanxinxiService;
import com.service.TokenService;
import com.utils.PageUtils;
import com.utils.R;
import com.utils.MPUtil;
import com.utils.MapUtils;
import com.utils.CommonUtil;
import java.io.IOException;

/**
 * 学员信息
 * 后端接口
 * @author 
 * @email 
 * @date 2024-04-09 22:31:42
 */
@RestController
@RequestMapping("/xueyuanxinxi")
public class XueyuanxinxiController {
    @Autowired
    private XueyuanxinxiService xueyuanxinxiService;




    



    /**
     * 后台列表
     */
    @RequestMapping("/page")
    public R page(@RequestParam Map<String, Object> params,XueyuanxinxiEntity xueyuanxinxi,
		HttpServletRequest request){
		String tableName = request.getSession().getAttribute("tableName").toString();
		if(tableName.equals("jiaxiaojiaolian")) {
			xueyuanxinxi.setJiaolianzhanghao((String)request.getSession().getAttribute("username"));
		}
		if(tableName.equals("yonghu")) {
			xueyuanxinxi.setYonghuzhanghao((String)request.getSession().getAttribute("username"));
		}
        EntityWrapper<XueyuanxinxiEntity> ew = new EntityWrapper<XueyuanxinxiEntity>();

		PageUtils page = xueyuanxinxiService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, xueyuanxinxi), params), params));

        return R.ok().put("data", page);
    }
    
    /**
     * 前台列表
     */
	@IgnoreAuth
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params,XueyuanxinxiEntity xueyuanxinxi, 
		HttpServletRequest request){
        EntityWrapper<XueyuanxinxiEntity> ew = new EntityWrapper<XueyuanxinxiEntity>();

		PageUtils page = xueyuanxinxiService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, xueyuanxinxi), params), params));
        return R.ok().put("data", page);
    }



	/**
     * 列表
     */
    @RequestMapping("/lists")
    public R list( XueyuanxinxiEntity xueyuanxinxi){
       	EntityWrapper<XueyuanxinxiEntity> ew = new EntityWrapper<XueyuanxinxiEntity>();
      	ew.allEq(MPUtil.allEQMapPre( xueyuanxinxi, "xueyuanxinxi")); 
        return R.ok().put("data", xueyuanxinxiService.selectListView(ew));
    }

	 /**
     * 查询
     */
    @RequestMapping("/query")
    public R query(XueyuanxinxiEntity xueyuanxinxi){
        EntityWrapper< XueyuanxinxiEntity> ew = new EntityWrapper< XueyuanxinxiEntity>();
 		ew.allEq(MPUtil.allEQMapPre( xueyuanxinxi, "xueyuanxinxi")); 
		XueyuanxinxiView xueyuanxinxiView =  xueyuanxinxiService.selectView(ew);
		return R.ok("查询学员信息成功").put("data", xueyuanxinxiView);
    }
	
    /**
     * 后台详情
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id){
        XueyuanxinxiEntity xueyuanxinxi = xueyuanxinxiService.selectById(id);
        return R.ok().put("data", xueyuanxinxi);
    }

    /**
     * 前台详情
     */
	@IgnoreAuth
    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") Long id){
        XueyuanxinxiEntity xueyuanxinxi = xueyuanxinxiService.selectById(id);
        return R.ok().put("data", xueyuanxinxi);
    }
    



    /**
     * 后台保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody XueyuanxinxiEntity xueyuanxinxi, HttpServletRequest request){
    	//ValidatorUtils.validateEntity(xueyuanxinxi);
        xueyuanxinxiService.insert(xueyuanxinxi);
        return R.ok();
    }
    
    /**
     * 前台保存
     */
    @RequestMapping("/add")
    public R add(@RequestBody XueyuanxinxiEntity xueyuanxinxi, HttpServletRequest request){
    	//ValidatorUtils.validateEntity(xueyuanxinxi);
        xueyuanxinxiService.insert(xueyuanxinxi);
        return R.ok();
    }





    /**
     * 修改
     */
    @RequestMapping("/update")
    @Transactional
    public R update(@RequestBody XueyuanxinxiEntity xueyuanxinxi, HttpServletRequest request){
        //ValidatorUtils.validateEntity(xueyuanxinxi);
        xueyuanxinxiService.updateById(xueyuanxinxi);//全部更新
        return R.ok();
    }



    

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Long[] ids){
        xueyuanxinxiService.deleteBatchIds(Arrays.asList(ids));
        return R.ok();
    }
    
	










}
