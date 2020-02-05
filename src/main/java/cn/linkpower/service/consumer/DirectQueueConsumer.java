package cn.linkpower.service.consumer;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import com.rabbitmq.client.Channel;

@Component
public class DirectQueueConsumer {
	
	private static Logger log = LoggerFactory.getLogger(DirectQueueConsumer.class);
	
	@RabbitListener(queues="delayQueue")
	@RabbitHandler
	public void delayMessage(String msg,Channel channel, Message message) throws IOException{
		
		try {
			/**
			 * 确认一条消息：<br>
			 * channel.basicAck(deliveryTag, false); <br>
			 * deliveryTag:该消息的index <br>
			 * multiple：是否批量.true:将一次性ack所有小于deliveryTag的消息 <br>
			 */
			channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
			//打印获取到的消息的时间
			log.info("成功收到消息--{}", String.valueOf(msg));
		} catch (Exception e) {
			/**
			 * 拒绝确认消息:<br>
			 * channel.basicNack(long deliveryTag, boolean multiple, boolean requeue) ; <br>
			 * deliveryTag:该消息的index<br>
			 * multiple：是否批量.true:将一次性拒绝所有小于deliveryTag的消息。<br>
			 * requeue：被拒绝的是否重新入队列 <br>
			 */
			channel.basicNack(message.getMessageProperties().getDeliveryTag(),
					false, true);
			log.error("收到消息异常--{}",String.valueOf(e));
		}
	}
}
