package me.levi;

import me.levi.pojo.Receiver;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;

/**
 * Spring Boot automatically creates a connection factory and a RabbitTemplate,
 * reducing the amount of code you have to write.
 * */

@SpringBootApplication
public class MessagingRabbitmqApplication {
    static final String topicExchangeName = "spring-boot-exchange";

    static final String queueName = "spring-boot";

    public static void main(String[] args) {
        SpringApplication.run(MessagingRabbitmqApplication.class, args).close();
    }

    @Bean
    Queue queue(){
        return new Queue(queueName, false);
    }

    @Bean
    TopicExchange exchange(){
        return new TopicExchange(topicExchangeName);
    }

    //使用主题交换，并且队列由foo.bar.＃的路由键绑定，这意味着任何以foo.bar开头的路由键发送的消息能被路由到队列。
    @Bean
    Binding binding(Queue queue, TopicExchange exchange){
        return BindingBuilder.bind(queue).to(exchange).with("foo.bar.#");
    }

    @Bean
    SimpleMessageListenerContainer container(ConnectionFactory factory, MessageListenerAdapter listenerAdapter){
        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
        container.setConnectionFactory(factory);
        container.setQueueNames(queueName);
        container.setMessageListener(listenerAdapter);
        return container;

    }

    //包装receiver
    @Bean
    MessageListenerAdapter listenerAdapter(Receiver receiver){
        return new MessageListenerAdapter(receiver, "receiveMessage");
    }


}
