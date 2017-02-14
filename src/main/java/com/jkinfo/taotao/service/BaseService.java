package com.jkinfo.taotao.service;

import java.util.List;

import com.github.pagehelper.PageInfo;

public interface BaseService <T>{
	
	/**
	 * 根据id查询数据
	 * @param id:主键id
	 * @return T
	 */
	T queryById(Long id);
	
	/**
	 * 查询所有数据
	 * @return
	 */
	List<T> queryAll();
	
	/**
	 * 根据条件查询一条数据，如果有多条数据会抛出异常
	 * @param record
	 * @return
	 */
	T queryOne(T record);
	
	/**
	 * 根据条件查询数据列表
	 * @param record
	 * @return
	 */
	List<T> queryListByWhere(T record);
	
	/**
	 * 分页查询
	 * @param page
	 * 页数
	 * @param rows
	 * 每页条数
	 * @param recordl
	 * 
	 * @return
	 */
	PageInfo<T> queryPageListByWhere(Integer page, Integer rows, T record);
	
	
	/**
	 * 分页查询,并进行排序
	 * @param page
	 * @param rows
	 * @param sortField
	 * @param sortOrder
	 * @param record
	 * @return
	 */
	PageInfo<T> queryPageListByWhere(Integer page, Integer rows, String sortField, String sortOrder, T record);

	/**
	 * 
	 * @param record
	 * @return
	 */
	Integer save(T record);
	
	/**
	 * 新增数据，使用不为null的字段，返回成功的条数
	 * @param record
	 * @return
	 */
	Integer saveSelective(T record);
	
	/**
	 * 修改数据，返回成功的条数
	 * @param record
	 * @return
	 */
	Integer update(T record);
	
	/**
	 * 修改数据，使用不为null的字段进行修改，返回成功的条数
	 * @param record
	 * @return
	 */
	Integer updateSelective(T record);
	
	/**
	 * 根据id删除数据
	 * @param id
	 * @return
	 */
	Integer deleteById(Long id);
	
	/**
	 * 批量删除
	 * @param clazz
	 * @param property
	 * @param values
	 * @return
	 */
	Integer deleteByIds(Class<T> clazz, String property, List<Object> values);
	
	/**
	 * 根据条件做删除
	 * @param record
	 * @return
	 */
	Integer deleteByWhere(T record);
}
