package cn.linkpower.controller;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cn.linkpower.service.IMessageServcie;

@RestController
public class TestController {
	private static Logger log = LoggerFactory.getLogger(TestController.class);
	
	@Autowired
	private IMessageServcie messageServiceImpl;
	
	@RequestMapping("/test1")
	public String testDelay1(String msg){
		messageServiceImpl.sendDelayMessage("delayExchange", "delay_key", msg, 6000);
		log.info("成功发送消息");
		return "成功发送消息  ";
	}
}
