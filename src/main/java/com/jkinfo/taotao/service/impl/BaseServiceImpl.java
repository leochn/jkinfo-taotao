package com.jkinfo.taotao.service.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.github.abel533.entity.Example;
import com.github.abel533.mapper.Mapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jkinfo.taotao.pojo.BasePojo;
import com.jkinfo.taotao.service.BaseService;

public class BaseServiceImpl<T extends BasePojo> implements BaseService<T> {
	
	@Autowired
	private Mapper<T> mapper;

	public T queryById(Long id) {
		return this.mapper.selectByPrimaryKey(id);
	}

	public List<T> queryAll() {
		return this.mapper.select(null);
	}

	public T queryOne(T record) {
		return this.mapper.selectOne(record);
	}

	public List<T> queryListByWhere(T record) {
		return this.mapper.select(record);
	}

	public PageInfo<T> queryPageListByWhere(Integer page, Integer rows, T record) {
		// 设置分页条件
		PageHelper.startPage(page, rows);
		List<T> list = this.queryListByWhere(record);
		return new PageInfo<T>(list);
	}
	
	public PageInfo<T> queryPageListByWhere(Integer page, Integer rows, String sortField, String sortOrder, T record){
		// 设置分页条件
		PageHelper.startPage(page, rows);
		Class<? extends BasePojo> clazz = record.getClass();
		Example example = new Example(clazz);
		if (StringUtils.isNotEmpty(sortField) && StringUtils.isNotEmpty(sortOrder)) {
			String orderBy = sortField + " " + sortOrder;
			example.setOrderByClause(orderBy); // 设置排序信息
		}else {
			example.setOrderByClause("created DESC");// 设置排序信息
		}
		List<T> list = this.mapper.selectByExample(example);
		//List<T> list = this.queryListByWhere(record);
		return new PageInfo<T>(list);
	}

	public Integer save(T record) {
		record.setCreated(new Date());
		record.setUpdated(record.getCreated());
		return this.mapper.insert(record);
	}

	public Integer saveSelective(T record) {
		record.setCreated(new Date());
		record.setUpdated(record.getCreated());
		return this.mapper.insertSelective(record);
	}

	public Integer update(T record) {
		record.setUpdated(new Date());
		return this.mapper.updateByPrimaryKey(record);
	}

	public Integer updateSelective(T record) {
		record.setUpdated(new Date());
		return this.mapper.updateByPrimaryKeySelective(record);
	}

	public Integer deleteById(Long id) {
		return this.mapper.deleteByPrimaryKey(id);
	}

	public Integer deleteByIds(Class<T> clazz, String property, List<Object> values) {
		Example example = new Example(clazz);
		example.createCriteria().andIn(property, values);
		return this.mapper.deleteByExample(example);
	}

	public Integer deleteByWhere(T record) {
		return this.mapper.delete(record);
	}


}
