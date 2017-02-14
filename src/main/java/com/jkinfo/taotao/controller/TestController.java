package com.jkinfo.taotao.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.jkinfo.taotao.pojo.Test;
import com.jkinfo.taotao.service.TestService;

@RequestMapping("test")
@Controller
public class TestController {
	
	@Autowired
	TestService testService;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(TestController.class);
	
	@RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Void> saveTest(Test item) {
        try {
            if (LOGGER.isDebugEnabled()) {
                LOGGER.info("新增========开始=========== {}", item.getDescp());
                LOGGER.info("新增========开始=========== {}", item);
            }
            Integer num = this.testService.saveTest(item);
            if (null != num) {
                // 新增成功 201
                if (LOGGER.isInfoEnabled()) {
                    LOGGER.info("新增成功! itemId = {}", item.getId());
                }
                return ResponseEntity.status(HttpStatus.CREATED).build();
            }
            if (LOGGER.isInfoEnabled()) {
                LOGGER.info("新增失败，但是没有异常抛出！ item = {}", item);
            }
        } catch (Exception e) {
            LOGGER.error("新增失败! item  = " + item, e);
        }
        // 500
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }

}
