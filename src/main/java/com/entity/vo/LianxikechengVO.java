package com.entity.vo;

import com.entity.LianxikechengEntity;

import com.baomidou.mybatisplus.annotations.TableName;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.io.Serializable;
 

/**
 * 练习课程
 * @author 
 * @email 
 * @date 2024-04-09 22:31:41
 */
public class LianxikechengVO  implements Serializable {
	private static final long serialVersionUID = 1L;

	 			
	/**
	 * 封面
	 */
	
	private String fengmian;
		
	/**
	 * 课程视频
	 */
	
	private String kechengshipin;
		
	/**
	 * 课程简介
	 */
	
	private String kechengjianjie;
		
	/**
	 * 发布时间
	 */
		
	@JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat 
	private Date fabushijian;
		
	/**
	 * 教练账号
	 */
	
	private String jiaolianzhanghao;
		
	/**
	 * 教练姓名
	 */
	
	private String jiaolianxingming;
				
	
	/**
	 * 设置：封面
	 */
	 
	public void setFengmian(String fengmian) {
		this.fengmian = fengmian;
	}
	
	/**
	 * 获取：封面
	 */
	public String getFengmian() {
		return fengmian;
	}
				
	
	/**
	 * 设置：课程视频
	 */
	 
	public void setKechengshipin(String kechengshipin) {
		this.kechengshipin = kechengshipin;
	}
	
	/**
	 * 获取：课程视频
	 */
	public String getKechengshipin() {
		return kechengshipin;
	}
				
	
	/**
	 * 设置：课程简介
	 */
	 
	public void setKechengjianjie(String kechengjianjie) {
		this.kechengjianjie = kechengjianjie;
	}
	
	/**
	 * 获取：课程简介
	 */
	public String getKechengjianjie() {
		return kechengjianjie;
	}
				
	
	/**
	 * 设置：发布时间
	 */
	 
	public void setFabushijian(Date fabushijian) {
		this.fabushijian = fabushijian;
	}
	
	/**
	 * 获取：发布时间
	 */
	public Date getFabushijian() {
		return fabushijian;
	}
				
	
	/**
	 * 设置：教练账号
	 */
	 
	public void setJiaolianzhanghao(String jiaolianzhanghao) {
		this.jiaolianzhanghao = jiaolianzhanghao;
	}
	
	/**
	 * 获取：教练账号
	 */
	public String getJiaolianzhanghao() {
		return jiaolianzhanghao;
	}
				
	
	/**
	 * 设置：教练姓名
	 */
	 
	public void setJiaolianxingming(String jiaolianxingming) {
		this.jiaolianxingming = jiaolianxingming;
	}
	
	/**
	 * 获取：教练姓名
	 */
	public String getJiaolianxingming() {
		return jiaolianxingming;
	}
			
}
