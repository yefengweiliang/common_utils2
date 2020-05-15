package com.utils.rabbitmq;

import org.springframework.amqp.core.*;

/**
 * rabbitMq工具类
 *
 * @author wanghohgnshen
 * @date 2019-03-01
 */
@SuppressWarnings("all")
public class RabbitMqUtil {
    /**
     * 交换机
     */
    private static Exchange exchange;


    /**
     * 获取不同类型的exchange
     *
     * @param exchangeType 交换机类型
     * @param exchangeName 交换机名称
     * @return 交换机
     */
    public static Exchange getExchenge(String exchangeType, String exchangeName){

        switch (exchangeType){
            case "direct" :
                exchange = new DirectExchange(exchangeName);
                break;
            case "topic" :
                exchange = new TopicExchange(exchangeName);
                break;
            case "fanout" :
                exchange = new FanoutExchange(exchangeName);
        }
        return exchange;
    }


}
