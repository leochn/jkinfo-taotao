package com.jkinfo.taotao.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.jkinfo.taotao.pojo.JsonResult;
import com.jkinfo.taotao.service.ItemService;


@RequestMapping("item")
@Controller
public class ItemController {

	@Autowired
	private ItemService itemService;

	private static final Logger LOGGER = LoggerFactory.getLogger(ItemController.class);

/*	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<JsonResult> queryList(
			@RequestParam(value = "page", defaultValue = "1") Integer page,
			@RequestParam(value = "rows", defaultValue = "30") Integer rows) {

		try {
			if (LOGGER.isDebugEnabled()) {
                LOGGER.debug("queryList=============start==============");
            }
			JsonResult result = this.itemService.queryPageList(page, rows);
			if (null == result) {
				if (LOGGER.isDebugEnabled()) {
	                LOGGER.debug("queryList=============fail==============查询失败,但是没有抛异常");
	            }
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
			}
			if (LOGGER.isDebugEnabled()) {
                LOGGER.debug("queryList=============ok==============");
            }
			return ResponseEntity.ok(result);
		} catch (Exception e) {
			LOGGER.error("queryList=========fail===========查询失败,抛异常", e);
		}
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
	}*/
	
	@RequestMapping(value = "/queryList",method = RequestMethod.GET)
	public ResponseEntity<JsonResult> queryListByWhereOrderBy(
			@RequestParam(value = "page", defaultValue = "1") Integer page,
			@RequestParam(value = "rows", defaultValue = "30") Integer rows,
			@RequestParam(defaultValue = "created") String sortField,
			@RequestParam(defaultValue = "desc") String sortOrder) {
		
		try {
			if (LOGGER.isDebugEnabled()) {
				LOGGER.debug("queryList=============start==============");
			}
			JsonResult result = this.itemService.queryPageListOrderBy(page, rows,sortField,sortOrder);
			if (null == result) {
				if (LOGGER.isDebugEnabled()) {
					LOGGER.debug("queryList=============fail==============查询失败,但是没有抛异常");
				}
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
			}
			if (LOGGER.isDebugEnabled()) {
				LOGGER.debug("queryList=============ok==============");
			}
			return ResponseEntity.ok(result);
		} catch (Exception e) {
			LOGGER.error("queryList=========fail===========查询失败,抛异常", e);
		}
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
	}

}
