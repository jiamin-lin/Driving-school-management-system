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

import com.entity.KaoshiyuyueEntity;
import com.entity.view.KaoshiyuyueView;

import com.service.KaoshiyuyueService;
import com.service.TokenService;
import com.utils.PageUtils;
import com.utils.R;
import com.utils.MPUtil;
import com.utils.MapUtils;
import com.utils.CommonUtil;
import java.io.IOException;

/**
 * 考试预约
 * 后端接口
 * @author 
 * @email 
 * @date 2024-04-09 22:31:42
 */
@RestController
@RequestMapping("/kaoshiyuyue")
public class KaoshiyuyueController {
    @Autowired
    private KaoshiyuyueService kaoshiyuyueService;




    



    /**
     * 后台列表
     */
    @RequestMapping("/page")
    public R page(@RequestParam Map<String, Object> params,KaoshiyuyueEntity kaoshiyuyue,
		HttpServletRequest request){
		String tableName = request.getSession().getAttribute("tableName").toString();
		if(tableName.equals("yonghu")) {
			kaoshiyuyue.setYonghuzhanghao((String)request.getSession().getAttribute("username"));
		}
		if(tableName.equals("jiaxiaojiaolian")) {
			kaoshiyuyue.setJiaolianzhanghao((String)request.getSession().getAttribute("username"));
		}
        EntityWrapper<KaoshiyuyueEntity> ew = new EntityWrapper<KaoshiyuyueEntity>();

		PageUtils page = kaoshiyuyueService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, kaoshiyuyue), params), params));

        return R.ok().put("data", page);
    }
    
    /**
     * 前台列表
     */
	@IgnoreAuth
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params,KaoshiyuyueEntity kaoshiyuyue, 
		HttpServletRequest request){
        EntityWrapper<KaoshiyuyueEntity> ew = new EntityWrapper<KaoshiyuyueEntity>();

		PageUtils page = kaoshiyuyueService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, kaoshiyuyue), params), params));
        return R.ok().put("data", page);
    }



	/**
     * 列表
     */
    @RequestMapping("/lists")
    public R list( KaoshiyuyueEntity kaoshiyuyue){
       	EntityWrapper<KaoshiyuyueEntity> ew = new EntityWrapper<KaoshiyuyueEntity>();
      	ew.allEq(MPUtil.allEQMapPre( kaoshiyuyue, "kaoshiyuyue")); 
        return R.ok().put("data", kaoshiyuyueService.selectListView(ew));
    }

	 /**
     * 查询
     */
    @RequestMapping("/query")
    public R query(KaoshiyuyueEntity kaoshiyuyue){
        EntityWrapper< KaoshiyuyueEntity> ew = new EntityWrapper< KaoshiyuyueEntity>();
 		ew.allEq(MPUtil.allEQMapPre( kaoshiyuyue, "kaoshiyuyue")); 
		KaoshiyuyueView kaoshiyuyueView =  kaoshiyuyueService.selectView(ew);
		return R.ok("查询考试预约成功").put("data", kaoshiyuyueView);
    }
	
    /**
     * 后台详情
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id){
        KaoshiyuyueEntity kaoshiyuyue = kaoshiyuyueService.selectById(id);
        return R.ok().put("data", kaoshiyuyue);
    }

    /**
     * 前台详情
     */
	@IgnoreAuth
    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") Long id){
        KaoshiyuyueEntity kaoshiyuyue = kaoshiyuyueService.selectById(id);
        return R.ok().put("data", kaoshiyuyue);
    }
    



    /**
     * 后台保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody KaoshiyuyueEntity kaoshiyuyue, HttpServletRequest request){
    	//ValidatorUtils.validateEntity(kaoshiyuyue);
        kaoshiyuyueService.insert(kaoshiyuyue);
        return R.ok();
    }
    
    /**
     * 前台保存
     */
    @RequestMapping("/add")
    public R add(@RequestBody KaoshiyuyueEntity kaoshiyuyue, HttpServletRequest request){
    	//ValidatorUtils.validateEntity(kaoshiyuyue);
        kaoshiyuyueService.insert(kaoshiyuyue);
        return R.ok();
    }





    /**
     * 修改
     */
    @RequestMapping("/update")
    @Transactional
    public R update(@RequestBody KaoshiyuyueEntity kaoshiyuyue, HttpServletRequest request){
        //ValidatorUtils.validateEntity(kaoshiyuyue);
        kaoshiyuyueService.updateById(kaoshiyuyue);//全部更新
        return R.ok();
    }

    /**
     * 审核
     */
    @RequestMapping("/shBatch")
    @Transactional
    public R update(@RequestBody Long[] ids, @RequestParam String sfsh, @RequestParam String shhf){
        List<KaoshiyuyueEntity> list = new ArrayList<KaoshiyuyueEntity>();
        for(Long id : ids) {
            KaoshiyuyueEntity kaoshiyuyue = kaoshiyuyueService.selectById(id);
            kaoshiyuyue.setSfsh(sfsh);
            kaoshiyuyue.setShhf(shhf);
            list.add(kaoshiyuyue);
        }
        kaoshiyuyueService.updateBatchById(list);
        return R.ok();
    }


    

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Long[] ids){
        kaoshiyuyueService.deleteBatchIds(Arrays.asList(ids));
        return R.ok();
    }
    
	










}
