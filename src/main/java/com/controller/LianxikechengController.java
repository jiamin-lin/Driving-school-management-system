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

import com.entity.LianxikechengEntity;
import com.entity.view.LianxikechengView;

import com.service.LianxikechengService;
import com.service.TokenService;
import com.utils.PageUtils;
import com.utils.R;
import com.utils.MPUtil;
import com.utils.MapUtils;
import com.utils.CommonUtil;
import java.io.IOException;

/**
 * 练习课程
 * 后端接口
 * @author 
 * @email 
 * @date 2024-04-09 22:31:41
 */
@RestController
@RequestMapping("/lianxikecheng")
public class LianxikechengController {
    @Autowired
    private LianxikechengService lianxikechengService;




    



    /**
     * 后台列表
     */
    @RequestMapping("/page")
    public R page(@RequestParam Map<String, Object> params,LianxikechengEntity lianxikecheng,
		HttpServletRequest request){
		String tableName = request.getSession().getAttribute("tableName").toString();
		if(tableName.equals("jiaxiaojiaolian")) {
			lianxikecheng.setJiaolianzhanghao((String)request.getSession().getAttribute("username"));
		}
        EntityWrapper<LianxikechengEntity> ew = new EntityWrapper<LianxikechengEntity>();

		PageUtils page = lianxikechengService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, lianxikecheng), params), params));

        return R.ok().put("data", page);
    }
    
    /**
     * 前台列表
     */
	@IgnoreAuth
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params,LianxikechengEntity lianxikecheng, 
		HttpServletRequest request){
        EntityWrapper<LianxikechengEntity> ew = new EntityWrapper<LianxikechengEntity>();

		PageUtils page = lianxikechengService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, lianxikecheng), params), params));
        return R.ok().put("data", page);
    }



	/**
     * 列表
     */
    @RequestMapping("/lists")
    public R list( LianxikechengEntity lianxikecheng){
       	EntityWrapper<LianxikechengEntity> ew = new EntityWrapper<LianxikechengEntity>();
      	ew.allEq(MPUtil.allEQMapPre( lianxikecheng, "lianxikecheng")); 
        return R.ok().put("data", lianxikechengService.selectListView(ew));
    }

	 /**
     * 查询
     */
    @RequestMapping("/query")
    public R query(LianxikechengEntity lianxikecheng){
        EntityWrapper< LianxikechengEntity> ew = new EntityWrapper< LianxikechengEntity>();
 		ew.allEq(MPUtil.allEQMapPre( lianxikecheng, "lianxikecheng")); 
		LianxikechengView lianxikechengView =  lianxikechengService.selectView(ew);
		return R.ok("查询练习课程成功").put("data", lianxikechengView);
    }
	
    /**
     * 后台详情
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id){
        LianxikechengEntity lianxikecheng = lianxikechengService.selectById(id);
        return R.ok().put("data", lianxikecheng);
    }

    /**
     * 前台详情
     */
	@IgnoreAuth
    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") Long id){
        LianxikechengEntity lianxikecheng = lianxikechengService.selectById(id);
        return R.ok().put("data", lianxikecheng);
    }
    



    /**
     * 后台保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody LianxikechengEntity lianxikecheng, HttpServletRequest request){
    	//ValidatorUtils.validateEntity(lianxikecheng);
        lianxikechengService.insert(lianxikecheng);
        return R.ok();
    }
    
    /**
     * 前台保存
     */
    @RequestMapping("/add")
    public R add(@RequestBody LianxikechengEntity lianxikecheng, HttpServletRequest request){
    	//ValidatorUtils.validateEntity(lianxikecheng);
        lianxikechengService.insert(lianxikecheng);
        return R.ok();
    }





    /**
     * 修改
     */
    @RequestMapping("/update")
    @Transactional
    public R update(@RequestBody LianxikechengEntity lianxikecheng, HttpServletRequest request){
        //ValidatorUtils.validateEntity(lianxikecheng);
        lianxikechengService.updateById(lianxikecheng);//全部更新
        return R.ok();
    }



    

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Long[] ids){
        lianxikechengService.deleteBatchIds(Arrays.asList(ids));
        return R.ok();
    }
    
	










}
