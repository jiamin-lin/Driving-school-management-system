package com.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import java.util.List;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.utils.PageUtils;
import com.utils.Query;


import com.dao.LianxikechengDao;
import com.entity.LianxikechengEntity;
import com.service.LianxikechengService;
import com.entity.vo.LianxikechengVO;
import com.entity.view.LianxikechengView;

@Service("lianxikechengService")
public class LianxikechengServiceImpl extends ServiceImpl<LianxikechengDao, LianxikechengEntity> implements LianxikechengService {
	
	
    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<LianxikechengEntity> page = this.selectPage(
                new Query<LianxikechengEntity>(params).getPage(),
                new EntityWrapper<LianxikechengEntity>()
        );
        return new PageUtils(page);
    }
    
    @Override
	public PageUtils queryPage(Map<String, Object> params, Wrapper<LianxikechengEntity> wrapper) {
		  Page<LianxikechengView> page =new Query<LianxikechengView>(params).getPage();
	        page.setRecords(baseMapper.selectListView(page,wrapper));
	    	PageUtils pageUtil = new PageUtils(page);
	    	return pageUtil;
 	}

    
    @Override
	public List<LianxikechengVO> selectListVO(Wrapper<LianxikechengEntity> wrapper) {
 		return baseMapper.selectListVO(wrapper);
	}
	
	@Override
	public LianxikechengVO selectVO(Wrapper<LianxikechengEntity> wrapper) {
 		return baseMapper.selectVO(wrapper);
	}
	
	@Override
	public List<LianxikechengView> selectListView(Wrapper<LianxikechengEntity> wrapper) {
		return baseMapper.selectListView(wrapper);
	}

	@Override
	public LianxikechengView selectView(Wrapper<LianxikechengEntity> wrapper) {
		return baseMapper.selectView(wrapper);
	}


}
