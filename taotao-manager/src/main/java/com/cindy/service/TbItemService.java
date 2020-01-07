package com.cindy.service;

import java.util.List;

import com.cindy.bean.TbItem;
import com.cindy.common.TaoTaoResult;


public interface TbItemService {
	TbItem findTbItemById(Long tbItemId);//根据id查询指定商品

	List<TbItem> findTbItem(Integer page, Integer limit);

	Integer findCount();

	//TaoTaoResult deletemByIds(List<TbItem> items);

	TaoTaoResult updateItems(List<TbItem> items, Integer type);

	

	

	//int deleteById(List<TbItem> tbItems);

	

	
	

	

	
}
