package cn.linkpower.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * <p>消息队列和消息路由器的配置类</p><br >
 * 此处的配置逻辑分为以下几点：<br>
 * 1、消息生产者生产的消息是发送至指定的消息路由器(转发器)中；<br>
 * 2、消息消费者是从队列中进行消息的获取监听操作；<br>
 * 3、消息转发器和消息队列之间的绑定采取的最简单的direct类型，其他类型可以自行配置；<br>
 * @author 765199214
 *
 */
@Configuration
public class MQConfig {
	
	//配置消息转发器，接收生产者提供的消息
	@Bean(name="getDelayExchange")
	public DirectExchange getDelayExchange(){
		//DirectExchange(String name, boolean durable, boolean autoDelete)
		DirectExchange directExchange = new DirectExchange("delayExchange", true, false);
		//开启转发器推送消息至消息队列的延迟属性
		directExchange.setDelayed(true);
		return directExchange;
	}
	
	//设置消息队列的属性
	@Bean(name="getDelayQueue")
	public Queue getDelayQueue(){
		//Queue(String name, boolean durable, boolean exclusive, boolean autoDelete)
		return new Queue("delayQueue",true,false,false);
	}
	
	//将指定的消息队列和消息转发器进行绑定操作
	@Bean
	public Binding bindDelayExchangeAndQueue(
			@Qualifier(value="getDelayExchange") DirectExchange getDelayExchange,
			@Qualifier(value="getDelayQueue") Queue getDelayQueue){
		return BindingBuilder.bind(getDelayQueue).to(getDelayExchange).with("delay_key");
	}
}
