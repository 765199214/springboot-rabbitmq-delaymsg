package cn.linkpower.service;

public interface IMessageServcie {
	public void sendDelayMessage(String exchange,String routingKey,Object msg,Integer delayTimes);
}
