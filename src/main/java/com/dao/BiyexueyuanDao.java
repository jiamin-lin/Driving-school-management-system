package com.dao;

import com.entity.BiyexueyuanEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import org.apache.ibatis.annotations.Param;
import com.entity.vo.BiyexueyuanVO;
import com.entity.view.BiyexueyuanView;


/**
 * 毕业学员
 * 
 * @author 
 * @email 
 * @date 2024-04-09 22:31:42
 */
public interface BiyexueyuanDao extends BaseMapper<BiyexueyuanEntity> {
	
	List<BiyexueyuanVO> selectListVO(@Param("ew") Wrapper<BiyexueyuanEntity> wrapper);
	
	BiyexueyuanVO selectVO(@Param("ew") Wrapper<BiyexueyuanEntity> wrapper);
	
	List<BiyexueyuanView> selectListView(@Param("ew") Wrapper<BiyexueyuanEntity> wrapper);

	List<BiyexueyuanView> selectListView(Pagination page,@Param("ew") Wrapper<BiyexueyuanEntity> wrapper);

	
	BiyexueyuanView selectView(@Param("ew") Wrapper<BiyexueyuanEntity> wrapper);
	

}
