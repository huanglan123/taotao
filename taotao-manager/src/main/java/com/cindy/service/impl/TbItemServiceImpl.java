package com.cindy.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cindy.bean.TbItem;
import com.cindy.common.TaoTaoResult;
import com.cindy.mapper.TbItemMapper;
import com.cindy.service.TbItemService;
@Service
public class TbItemServiceImpl implements TbItemService {

	@Autowired
	private TbItemMapper tbItemMapper;
	@Override
	public TbItem findTbItemById(Long tbItemId) {
		TbItem tbItem=tbItemMapper.findTbItemById(tbItemId);
		return tbItem;
	}
	@Override
	public List<TbItem> findTbItem(Integer page, Integer limit) {
		List<TbItem> tbItems=tbItemMapper.findTbItem(page,limit);
		return tbItems;
	}
	@Override
	public Integer findCount() {
		Integer i=tbItemMapper.findCount();
		return i;
	}
//	@Override
//	public TaoTaoResult deletemByIds(List<TbItem> items) {
//		List<Long> ids = new ArrayList<Long>();
//		for (TbItem item : items) {
//			ids.add(item.getId());
//		}
//		int count = tbItemMapper.delteItemByIds(ids);
//		if(count>0){
//			return TaoTaoResult.ok();
//		}
//		return TaoTaoResult.build(500, "删除有误");
//		
//	}
	@Override
	public TaoTaoResult updateItems(List<TbItem> items, Integer type) {
		List<Long> ids = new ArrayList<Long>();
		for (TbItem tbItem : items) {
			ids.add(tbItem.getId());
		}
		int count = tbItemMapper.updateItemByIds(ids, type);
		if(count>0&&type==0){
			return TaoTaoResult.build(200, "商品下架成功");
		}else if(count>0&&type==1){
			return TaoTaoResult.build(200, "商品上架成功");
		}else if(count>0&&type==2){
			return TaoTaoResult.build(200, "商品删除成功");
		}
		return TaoTaoResult.build(500, "操作有误");
	}
}
	
//	@Override
//	public int deleteById(List<TbItem> tbItems) {
//		int i=tbItemMapper.deleteById(tbItems);
//		return i;
//	}
	
	
	

	
	
	


