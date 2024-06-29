package com.service;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.IService;
import com.utils.PageUtils;
import com.entity.BiyexueyuanEntity;
import java.util.List;
import java.util.Map;
import com.entity.vo.BiyexueyuanVO;
import org.apache.ibatis.annotations.Param;
import com.entity.view.BiyexueyuanView;


/**
 * 毕业学员
 *
 * @author 
 * @email 
 * @date 2024-04-09 22:31:42
 */
public interface BiyexueyuanService extends IService<BiyexueyuanEntity> {

    PageUtils queryPage(Map<String, Object> params);
    
   	List<BiyexueyuanVO> selectListVO(Wrapper<BiyexueyuanEntity> wrapper);
   	
   	BiyexueyuanVO selectVO(@Param("ew") Wrapper<BiyexueyuanEntity> wrapper);
   	
   	List<BiyexueyuanView> selectListView(Wrapper<BiyexueyuanEntity> wrapper);
   	
   	BiyexueyuanView selectView(@Param("ew") Wrapper<BiyexueyuanEntity> wrapper);
   	
   	PageUtils queryPage(Map<String, Object> params,Wrapper<BiyexueyuanEntity> wrapper);

   	

}

