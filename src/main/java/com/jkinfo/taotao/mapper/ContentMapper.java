package com.jkinfo.taotao.mapper;

import java.util.List;

import com.github.abel533.mapper.Mapper;
import com.jkinfo.taotao.pojo.Content;

public interface ContentMapper extends Mapper<Content> {
	List<Content> queryListBycategoryId(Long categoryId);
}
