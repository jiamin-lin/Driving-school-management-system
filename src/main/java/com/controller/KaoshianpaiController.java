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

import com.entity.KaoshianpaiEntity;
import com.entity.view.KaoshianpaiView;

import com.service.KaoshianpaiService;
import com.service.TokenService;
import com.utils.PageUtils;
import com.utils.R;
import com.utils.MPUtil;
import com.utils.MapUtils;
import com.utils.CommonUtil;
import java.io.IOException;

/**
 * 考试安排
 * 后端接口
 * @author 
 * @email 
 * @date 2024-04-09 22:31:42
 */
@RestController
@RequestMapping("/kaoshianpai")
public class KaoshianpaiController {
    @Autowired
    private KaoshianpaiService kaoshianpaiService;




    



    /**
     * 后台列表
     */
    @RequestMapping("/page")
    public R page(@RequestParam Map<String, Object> params,KaoshianpaiEntity kaoshianpai,
		HttpServletRequest request){
		String tableName = request.getSession().getAttribute("tableName").toString();
		if(tableName.equals("yonghu")) {
			kaoshianpai.setYonghuzhanghao((String)request.getSession().getAttribute("username"));
		}
		if(tableName.equals("jiaxiaojiaolian")) {
			kaoshianpai.setJiaolianzhanghao((String)request.getSession().getAttribute("username"));
		}
        EntityWrapper<KaoshianpaiEntity> ew = new EntityWrapper<KaoshianpaiEntity>();

		PageUtils page = kaoshianpaiService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, kaoshianpai), params), params));

        return R.ok().put("data", page);
    }
    
    /**
     * 前台列表
     */
	@IgnoreAuth
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params,KaoshianpaiEntity kaoshianpai, 
		HttpServletRequest request){
        EntityWrapper<KaoshianpaiEntity> ew = new EntityWrapper<KaoshianpaiEntity>();

		PageUtils page = kaoshianpaiService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, kaoshianpai), params), params));
        return R.ok().put("data", page);
    }



	/**
     * 列表
     */
    @RequestMapping("/lists")
    public R list( KaoshianpaiEntity kaoshianpai){
       	EntityWrapper<KaoshianpaiEntity> ew = new EntityWrapper<KaoshianpaiEntity>();
      	ew.allEq(MPUtil.allEQMapPre( kaoshianpai, "kaoshianpai")); 
        return R.ok().put("data", kaoshianpaiService.selectListView(ew));
    }

	 /**
     * 查询
     */
    @RequestMapping("/query")
    public R query(KaoshianpaiEntity kaoshianpai){
        EntityWrapper< KaoshianpaiEntity> ew = new EntityWrapper< KaoshianpaiEntity>();
 		ew.allEq(MPUtil.allEQMapPre( kaoshianpai, "kaoshianpai")); 
		KaoshianpaiView kaoshianpaiView =  kaoshianpaiService.selectView(ew);
		return R.ok("查询考试安排成功").put("data", kaoshianpaiView);
    }
	
    /**
     * 后台详情
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id){
        KaoshianpaiEntity kaoshianpai = kaoshianpaiService.selectById(id);
        return R.ok().put("data", kaoshianpai);
    }

    /**
     * 前台详情
     */
	@IgnoreAuth
    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") Long id){
        KaoshianpaiEntity kaoshianpai = kaoshianpaiService.selectById(id);
        return R.ok().put("data", kaoshianpai);
    }
    



    /**
     * 后台保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody KaoshianpaiEntity kaoshianpai, HttpServletRequest request){
    	//ValidatorUtils.validateEntity(kaoshianpai);
        kaoshianpaiService.insert(kaoshianpai);
        return R.ok();
    }
    
    /**
     * 前台保存
     */
    @RequestMapping("/add")
    public R add(@RequestBody KaoshianpaiEntity kaoshianpai, HttpServletRequest request){
    	//ValidatorUtils.validateEntity(kaoshianpai);
        kaoshianpaiService.insert(kaoshianpai);
        return R.ok();
    }





    /**
     * 修改
     */
    @RequestMapping("/update")
    @Transactional
    public R update(@RequestBody KaoshianpaiEntity kaoshianpai, HttpServletRequest request){
        //ValidatorUtils.validateEntity(kaoshianpai);
        kaoshianpaiService.updateById(kaoshianpai);//全部更新
        return R.ok();
    }



    

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Long[] ids){
        kaoshianpaiService.deleteBatchIds(Arrays.asList(ids));
        return R.ok();
    }
    
	










}
