package com.entity.view;

import com.entity.BiyexueyuanEntity;

import com.baomidou.mybatisplus.annotations.TableName;
import org.apache.commons.beanutils.BeanUtils;
import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;

import java.io.Serializable;
import com.utils.EncryptUtil;
 

/**
 * 毕业学员
 * 后端返回视图实体辅助类   
 * （通常后端关联的表或者自定义的字段需要返回使用）
 * @author 
 * @email 
 * @date 2024-04-09 22:31:42
 */
@TableName("biyexueyuan")
public class BiyexueyuanView  extends BiyexueyuanEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	public BiyexueyuanView(){
	}
 
 	public BiyexueyuanView(BiyexueyuanEntity biyexueyuanEntity){
 	try {
			BeanUtils.copyProperties(this, biyexueyuanEntity);
		} catch (IllegalAccessException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
 		
	}


}
