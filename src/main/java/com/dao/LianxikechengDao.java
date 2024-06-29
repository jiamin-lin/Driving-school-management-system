package com.dao;

import com.entity.LianxikechengEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import org.apache.ibatis.annotations.Param;
import com.entity.vo.LianxikechengVO;
import com.entity.view.LianxikechengView;


/**
 * 练习课程
 * 
 * @author 
 * @email 
 * @date 2024-04-09 22:31:41
 */
public interface LianxikechengDao extends BaseMapper<LianxikechengEntity> {
	
	List<LianxikechengVO> selectListVO(@Param("ew") Wrapper<LianxikechengEntity> wrapper);
	
	LianxikechengVO selectVO(@Param("ew") Wrapper<LianxikechengEntity> wrapper);
	
	List<LianxikechengView> selectListView(@Param("ew") Wrapper<LianxikechengEntity> wrapper);

	List<LianxikechengView> selectListView(Pagination page,@Param("ew") Wrapper<LianxikechengEntity> wrapper);

	
	LianxikechengView selectView(@Param("ew") Wrapper<LianxikechengEntity> wrapper);
	

}
