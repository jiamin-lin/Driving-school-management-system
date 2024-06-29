package com.entity.view;

import com.entity.JiaolianpingjiaEntity;

import com.baomidou.mybatisplus.annotations.TableName;
import org.apache.commons.beanutils.BeanUtils;
import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;

import java.io.Serializable;
import com.utils.EncryptUtil;
 

/**
 * 教练评价
 * 后端返回视图实体辅助类   
 * （通常后端关联的表或者自定义的字段需要返回使用）
 * @author 
 * @email 
 * @date 2024-04-09 22:31:42
 */
@TableName("jiaolianpingjia")
public class JiaolianpingjiaView  extends JiaolianpingjiaEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	public JiaolianpingjiaView(){
	}
 
 	public JiaolianpingjiaView(JiaolianpingjiaEntity jiaolianpingjiaEntity){
 	try {
			BeanUtils.copyProperties(this, jiaolianpingjiaEntity);
		} catch (IllegalAccessException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
 		
	}


}
