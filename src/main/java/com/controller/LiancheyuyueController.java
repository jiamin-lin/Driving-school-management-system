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

import com.entity.LiancheyuyueEntity;
import com.entity.view.LiancheyuyueView;

import com.service.LiancheyuyueService;
import com.service.TokenService;
import com.utils.PageUtils;
import com.utils.R;
import com.utils.MPUtil;
import com.utils.MapUtils;
import com.utils.CommonUtil;
import java.io.IOException;

/**
 * 练车预约
 * 后端接口
 * @author 
 * @email 
 * @date 2024-04-09 22:31:42
 */
@RestController
@RequestMapping("/liancheyuyue")
public class LiancheyuyueController {
    @Autowired
    private LiancheyuyueService liancheyuyueService;




    



    /**
     * 后台列表
     */
    @RequestMapping("/page")
    public R page(@RequestParam Map<String, Object> params,LiancheyuyueEntity liancheyuyue,
		HttpServletRequest request){
		String tableName = request.getSession().getAttribute("tableName").toString();
		if(tableName.equals("yonghu")) {
			liancheyuyue.setYonghuzhanghao((String)request.getSession().getAttribute("username"));
		}
		if(tableName.equals("jiaxiaojiaolian")) {
			liancheyuyue.setJiaolianzhanghao((String)request.getSession().getAttribute("username"));
		}
        EntityWrapper<LiancheyuyueEntity> ew = new EntityWrapper<LiancheyuyueEntity>();

		PageUtils page = liancheyuyueService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, liancheyuyue), params), params));

        return R.ok().put("data", page);
    }
    
    /**
     * 前台列表
     */
	@IgnoreAuth
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params,LiancheyuyueEntity liancheyuyue, 
		HttpServletRequest request){
        EntityWrapper<LiancheyuyueEntity> ew = new EntityWrapper<LiancheyuyueEntity>();

		PageUtils page = liancheyuyueService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, liancheyuyue), params), params));
        return R.ok().put("data", page);
    }



	/**
     * 列表
     */
    @RequestMapping("/lists")
    public R list( LiancheyuyueEntity liancheyuyue){
       	EntityWrapper<LiancheyuyueEntity> ew = new EntityWrapper<LiancheyuyueEntity>();
      	ew.allEq(MPUtil.allEQMapPre( liancheyuyue, "liancheyuyue")); 
        return R.ok().put("data", liancheyuyueService.selectListView(ew));
    }

	 /**
     * 查询
     */
    @RequestMapping("/query")
    public R query(LiancheyuyueEntity liancheyuyue){
        EntityWrapper< LiancheyuyueEntity> ew = new EntityWrapper< LiancheyuyueEntity>();
 		ew.allEq(MPUtil.allEQMapPre( liancheyuyue, "liancheyuyue")); 
		LiancheyuyueView liancheyuyueView =  liancheyuyueService.selectView(ew);
		return R.ok("查询练车预约成功").put("data", liancheyuyueView);
    }
	
    /**
     * 后台详情
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id){
        LiancheyuyueEntity liancheyuyue = liancheyuyueService.selectById(id);
        return R.ok().put("data", liancheyuyue);
    }

    /**
     * 前台详情
     */
	@IgnoreAuth
    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") Long id){
        LiancheyuyueEntity liancheyuyue = liancheyuyueService.selectById(id);
        return R.ok().put("data", liancheyuyue);
    }
    



    /**
     * 后台保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody LiancheyuyueEntity liancheyuyue, HttpServletRequest request){
    	//ValidatorUtils.validateEntity(liancheyuyue);
        liancheyuyueService.insert(liancheyuyue);
        return R.ok();
    }
    
    /**
     * 前台保存
     */
    @RequestMapping("/add")
    public R add(@RequestBody LiancheyuyueEntity liancheyuyue, HttpServletRequest request){
    	//ValidatorUtils.validateEntity(liancheyuyue);
        liancheyuyueService.insert(liancheyuyue);
        return R.ok();
    }





    /**
     * 修改
     */
    @RequestMapping("/update")
    @Transactional
    public R update(@RequestBody LiancheyuyueEntity liancheyuyue, HttpServletRequest request){
        //ValidatorUtils.validateEntity(liancheyuyue);
        liancheyuyueService.updateById(liancheyuyue);//全部更新
        return R.ok();
    }

    /**
     * 审核
     */
    @RequestMapping("/shBatch")
    @Transactional
    public R update(@RequestBody Long[] ids, @RequestParam String sfsh, @RequestParam String shhf){
        List<LiancheyuyueEntity> list = new ArrayList<LiancheyuyueEntity>();
        for(Long id : ids) {
            LiancheyuyueEntity liancheyuyue = liancheyuyueService.selectById(id);
            liancheyuyue.setSfsh(sfsh);
            liancheyuyue.setShhf(shhf);
            list.add(liancheyuyue);
        }
        liancheyuyueService.updateBatchById(list);
        return R.ok();
    }


    

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Long[] ids){
        liancheyuyueService.deleteBatchIds(Arrays.asList(ids));
        return R.ok();
    }
    
	










}
