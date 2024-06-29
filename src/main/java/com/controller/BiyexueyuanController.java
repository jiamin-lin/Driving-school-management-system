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

import com.entity.BiyexueyuanEntity;
import com.entity.view.BiyexueyuanView;

import com.service.BiyexueyuanService;
import com.service.TokenService;
import com.utils.PageUtils;
import com.utils.R;
import com.utils.MPUtil;
import com.utils.MapUtils;
import com.utils.CommonUtil;
import java.io.IOException;

/**
 * 毕业学员
 * 后端接口
 * @author 
 * @email 
 * @date 2024-04-09 22:31:42
 */
@RestController
@RequestMapping("/biyexueyuan")
public class BiyexueyuanController {
    @Autowired
    private BiyexueyuanService biyexueyuanService;




    



    /**
     * 后台列表
     */
    @RequestMapping("/page")
    public R page(@RequestParam Map<String, Object> params,BiyexueyuanEntity biyexueyuan,
		HttpServletRequest request){
		String tableName = request.getSession().getAttribute("tableName").toString();
		if(tableName.equals("jiaxiaojiaolian")) {
			biyexueyuan.setJiaolianzhanghao((String)request.getSession().getAttribute("username"));
		}
		if(tableName.equals("yonghu")) {
			biyexueyuan.setYonghuzhanghao((String)request.getSession().getAttribute("username"));
		}
        EntityWrapper<BiyexueyuanEntity> ew = new EntityWrapper<BiyexueyuanEntity>();

		PageUtils page = biyexueyuanService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, biyexueyuan), params), params));

        return R.ok().put("data", page);
    }
    
    /**
     * 前台列表
     */
	@IgnoreAuth
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params,BiyexueyuanEntity biyexueyuan, 
		HttpServletRequest request){
        EntityWrapper<BiyexueyuanEntity> ew = new EntityWrapper<BiyexueyuanEntity>();

		PageUtils page = biyexueyuanService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, biyexueyuan), params), params));
        return R.ok().put("data", page);
    }



	/**
     * 列表
     */
    @RequestMapping("/lists")
    public R list( BiyexueyuanEntity biyexueyuan){
       	EntityWrapper<BiyexueyuanEntity> ew = new EntityWrapper<BiyexueyuanEntity>();
      	ew.allEq(MPUtil.allEQMapPre( biyexueyuan, "biyexueyuan")); 
        return R.ok().put("data", biyexueyuanService.selectListView(ew));
    }

	 /**
     * 查询
     */
    @RequestMapping("/query")
    public R query(BiyexueyuanEntity biyexueyuan){
        EntityWrapper< BiyexueyuanEntity> ew = new EntityWrapper< BiyexueyuanEntity>();
 		ew.allEq(MPUtil.allEQMapPre( biyexueyuan, "biyexueyuan")); 
		BiyexueyuanView biyexueyuanView =  biyexueyuanService.selectView(ew);
		return R.ok("查询毕业学员成功").put("data", biyexueyuanView);
    }
	
    /**
     * 后台详情
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id){
        BiyexueyuanEntity biyexueyuan = biyexueyuanService.selectById(id);
        return R.ok().put("data", biyexueyuan);
    }

    /**
     * 前台详情
     */
	@IgnoreAuth
    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") Long id){
        BiyexueyuanEntity biyexueyuan = biyexueyuanService.selectById(id);
        return R.ok().put("data", biyexueyuan);
    }
    



    /**
     * 后台保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody BiyexueyuanEntity biyexueyuan, HttpServletRequest request){
    	//ValidatorUtils.validateEntity(biyexueyuan);
        biyexueyuanService.insert(biyexueyuan);
        return R.ok();
    }
    
    /**
     * 前台保存
     */
    @RequestMapping("/add")
    public R add(@RequestBody BiyexueyuanEntity biyexueyuan, HttpServletRequest request){
    	//ValidatorUtils.validateEntity(biyexueyuan);
        biyexueyuanService.insert(biyexueyuan);
        return R.ok();
    }





    /**
     * 修改
     */
    @RequestMapping("/update")
    @Transactional
    public R update(@RequestBody BiyexueyuanEntity biyexueyuan, HttpServletRequest request){
        //ValidatorUtils.validateEntity(biyexueyuan);
        biyexueyuanService.updateById(biyexueyuan);//全部更新
        return R.ok();
    }



    

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Long[] ids){
        biyexueyuanService.deleteBatchIds(Arrays.asList(ids));
        return R.ok();
    }
    
	










}
