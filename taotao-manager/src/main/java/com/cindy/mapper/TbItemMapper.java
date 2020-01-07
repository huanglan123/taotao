package com.cindy.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.cindy.bean.TbItem;
import com.cindy.common.TaoTaoResult;

public interface TbItemMapper {

	TbItem findTbItemById(Long tbItemId);

	List<TbItem> findTbItem(Integer page, Integer limit);

	Integer findCount();

	

	int updateItemByIds(@Param("ids") List<Long> ids,@Param("type") Integer type);

	//int delteItemByIds(List<Long> ids);
	//TaoTaoResult updateItems(List<TbItem> items, Integer type);

	

	

	

}
