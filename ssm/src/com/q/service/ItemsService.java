package com.q.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.q.dao.ItemsMapper;
import com.q.pojo.Items;
import com.q.pojo.ItemsExample;
import com.q.pojo.ItemsExample.Criteria;
import com.q.pojo.QueryVo;

@Service
public class ItemsService {
	
	@Resource
	ItemsMapper itemsMapper;
	
	public List<Items> findAll() {
		return itemsMapper.selectByExampleWithBLOBs(null);
//		return itemsMapper.selectByExample(null);
	}
	
	public Items findItemsByid(Integer id) {
		return itemsMapper.selectByPrimaryKey(id);
	}
	
	public void updateItem(Items items) {
		itemsMapper.updateByPrimaryKeySelective(items);
	}
	
	public List<Items> findItemsByNameAndDetail(QueryVo vo) {
		ItemsExample example = new ItemsExample();
		Criteria criteria = example.createCriteria();
		criteria.andNameLike("%" + vo.getItmes().getName() + "%");
		List<Items> itemList = itemsMapper.selectByExampleWithBLOBs(example);
		return itemList;
	}
}
