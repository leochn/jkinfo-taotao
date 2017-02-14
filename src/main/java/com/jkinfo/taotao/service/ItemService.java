package com.jkinfo.taotao.service;

import com.jkinfo.taotao.pojo.Item;
import com.jkinfo.taotao.pojo.JsonResult;

public interface ItemService extends BaseService<Item>{
	/**
	 * 
	 * @param page
	 * @param rows
	 * @return
	 */
	JsonResult queryPageList(Integer page, Integer rows);
	
	
	JsonResult queryPageListOrderBy(Integer page, Integer rows, String sortField, String sortOrder);
}
