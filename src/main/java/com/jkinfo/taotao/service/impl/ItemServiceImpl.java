package com.jkinfo.taotao.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.abel533.entity.Example;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jkinfo.taotao.mapper.ItemMapper;
import com.jkinfo.taotao.pojo.Item;
import com.jkinfo.taotao.pojo.JsonResult;
import com.jkinfo.taotao.service.ItemService;

@Service
public class ItemServiceImpl extends BaseServiceImpl<Item> implements ItemService{
	
	@Autowired
    private ItemMapper itemMapper;

	public JsonResult queryPageList(Integer page, Integer rows) {
	    // 设置分页信息
        PageHelper.startPage(page, rows);
        Example example = new Example(Item.class);
        example.setOrderByClause("created DESC");// 设置排序信息
        List<Item> items = this.itemMapper.selectByExample(example);
        PageInfo<Item> pageInfo = new PageInfo<Item>(items);
        return JsonResult.ok((int) pageInfo.getTotal(),items);
    }

	public JsonResult queryPageListOrderBy(Integer page, Integer rows, String sortField, String sortOrder) {
		Item item = new Item();
		PageInfo<Item> pageInfo = this.queryPageListByWhere(page, rows, sortField, sortOrder, item);
		return JsonResult.ok((int) pageInfo.getTotal(), pageInfo.getList());
	}

}
