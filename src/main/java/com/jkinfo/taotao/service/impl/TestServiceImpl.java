package com.jkinfo.taotao.service.impl;

import org.springframework.stereotype.Service;

import com.jkinfo.taotao.pojo.Test;
import com.jkinfo.taotao.service.TestService;

@Service
public class TestServiceImpl extends BaseServiceImpl<Test> implements TestService {

	public Integer saveTest(Test item) {
		item.setId(null);// 强制设置id为null，从数据库中自增长
		return super.save(item);
	}
	
}
