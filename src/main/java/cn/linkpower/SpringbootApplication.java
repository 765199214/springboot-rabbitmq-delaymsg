package cn.linkpower;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 延迟消息---demo启动类
 * @author 765199214
 *
 */
@SpringBootApplication
public class SpringbootApplication {
	private static Logger log = LoggerFactory.getLogger(SpringApplication.class);
	public static void main(String[] args) {
		try {
			SpringApplication.run(SpringbootApplication.class, args);
		} catch (Exception e) {
			log.info("springboot Application Rabbitmq Dealy demo --{}",String.valueOf(e));
		}
	}
}
