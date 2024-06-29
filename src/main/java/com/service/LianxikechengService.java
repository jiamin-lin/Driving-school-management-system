package com.service;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.IService;
import com.utils.PageUtils;
import com.entity.LianxikechengEntity;
import java.util.List;
import java.util.Map;
import com.entity.vo.LianxikechengVO;
import org.apache.ibatis.annotations.Param;
import com.entity.view.LianxikechengView;


/**
 * 练习课程
 *
 * @author 
 * @email 
 * @date 2024-04-09 22:31:41
 */
public interface LianxikechengService extends IService<LianxikechengEntity> {

    PageUtils queryPage(Map<String, Object> params);
    
   	List<LianxikechengVO> selectListVO(Wrapper<LianxikechengEntity> wrapper);
   	
   	LianxikechengVO selectVO(@Param("ew") Wrapper<LianxikechengEntity> wrapper);
   	
   	List<LianxikechengView> selectListView(Wrapper<LianxikechengEntity> wrapper);
   	
   	LianxikechengView selectView(@Param("ew") Wrapper<LianxikechengEntity> wrapper);
   	
   	PageUtils queryPage(Map<String, Object> params,Wrapper<LianxikechengEntity> wrapper);

   	

}

