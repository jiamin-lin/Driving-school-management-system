package com.service;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.IService;
import com.utils.PageUtils;
import com.entity.JiaolianpingjiaEntity;
import java.util.List;
import java.util.Map;
import com.entity.vo.JiaolianpingjiaVO;
import org.apache.ibatis.annotations.Param;
import com.entity.view.JiaolianpingjiaView;


/**
 * 教练评价
 *
 * @author 
 * @email 
 * @date 2024-04-09 22:31:42
 */
public interface JiaolianpingjiaService extends IService<JiaolianpingjiaEntity> {

    PageUtils queryPage(Map<String, Object> params);
    
   	List<JiaolianpingjiaVO> selectListVO(Wrapper<JiaolianpingjiaEntity> wrapper);
   	
   	JiaolianpingjiaVO selectVO(@Param("ew") Wrapper<JiaolianpingjiaEntity> wrapper);
   	
   	List<JiaolianpingjiaView> selectListView(Wrapper<JiaolianpingjiaEntity> wrapper);
   	
   	JiaolianpingjiaView selectView(@Param("ew") Wrapper<JiaolianpingjiaEntity> wrapper);
   	
   	PageUtils queryPage(Map<String, Object> params,Wrapper<JiaolianpingjiaEntity> wrapper);

   	

    List<Map<String, Object>> selectValue(Map<String, Object> params,Wrapper<JiaolianpingjiaEntity> wrapper);

    List<Map<String, Object>> selectTimeStatValue(Map<String, Object> params,Wrapper<JiaolianpingjiaEntity> wrapper);

    List<Map<String, Object>> selectGroup(Map<String, Object> params,Wrapper<JiaolianpingjiaEntity> wrapper);



}

