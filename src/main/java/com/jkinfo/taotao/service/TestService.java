package com.jkinfo.taotao.service;

import com.jkinfo.taotao.pojo.Test;

public interface TestService extends BaseService<Test> {
	Integer saveTest(Test item);
}
