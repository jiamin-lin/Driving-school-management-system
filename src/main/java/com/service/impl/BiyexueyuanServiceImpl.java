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


import com.dao.BiyexueyuanDao;
import com.entity.BiyexueyuanEntity;
import com.service.BiyexueyuanService;
import com.entity.vo.BiyexueyuanVO;
import com.entity.view.BiyexueyuanView;

@Service("biyexueyuanService")
public class BiyexueyuanServiceImpl extends ServiceImpl<BiyexueyuanDao, BiyexueyuanEntity> implements BiyexueyuanService {
	
	
    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<BiyexueyuanEntity> page = this.selectPage(
                new Query<BiyexueyuanEntity>(params).getPage(),
                new EntityWrapper<BiyexueyuanEntity>()
        );
        return new PageUtils(page);
    }
    
    @Override
	public PageUtils queryPage(Map<String, Object> params, Wrapper<BiyexueyuanEntity> wrapper) {
		  Page<BiyexueyuanView> page =new Query<BiyexueyuanView>(params).getPage();
	        page.setRecords(baseMapper.selectListView(page,wrapper));
	    	PageUtils pageUtil = new PageUtils(page);
	    	return pageUtil;
 	}

    
    @Override
	public List<BiyexueyuanVO> selectListVO(Wrapper<BiyexueyuanEntity> wrapper) {
 		return baseMapper.selectListVO(wrapper);
	}
	
	@Override
	public BiyexueyuanVO selectVO(Wrapper<BiyexueyuanEntity> wrapper) {
 		return baseMapper.selectVO(wrapper);
	}
	
	@Override
	public List<BiyexueyuanView> selectListView(Wrapper<BiyexueyuanEntity> wrapper) {
		return baseMapper.selectListView(wrapper);
	}

	@Override
	public BiyexueyuanView selectView(Wrapper<BiyexueyuanEntity> wrapper) {
		return baseMapper.selectView(wrapper);
	}


}
