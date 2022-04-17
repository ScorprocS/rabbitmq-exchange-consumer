package fr.mns.tprabbit.rabbitmq_consumer.config;


import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Configure rabbitMQ
 * @author Alex
 *
 */
@EnableRabbit
@Configuration
public class RabbitConfiguration {
	/**
	 * Get the value queue.name from application.properties
	 */
    @Value("${queue.name}")
    private String queueName;
    
    /**
	 * Get the value user.event-key from application.properties
	 */
    @Value("${user.event-key}")
    private String userEventKey;
    
    /**
	 * Get the value exchange.name from application.properties
	 */
    @Value("${exchange.name}")
    private String exchangeName;

	/**
	 * Create if not exists a new persisted queue in rabbitMQ
	 * @return
	 */
	@Bean
	public Queue myQueue() {
	    return new Queue(queueName, true); //enregistre la queue persistante si elle n'existe pas dans rabbitMQ
	}

	 /**
     * Create if not exists a new direct exchange in rabbitMQ
     * @return
     */
    @Bean
    DirectExchange exchange() {
        return new DirectExchange(exchangeName); //créer un exchange si il n'existe pas dans rabbitMQ
    }

    /**
     * Bind the queue to exchange with userEventKey as routing key
     * It allow to manage bound from the consumer
     * The sender doesn't know anything about the consumer
     * @param myQueue
     * @param exchange
     * @return
     */
    @Bean
    Binding testeBinding(Queue myQueue, DirectExchange exchange) {
        return BindingBuilder.bind(myQueue).to(exchange).with(userEventKey); 	//lie la queue avec l’exchange lorsqu’on utilise la clé de routage « user-event »
    }

    /**
     * Instanciate an ObjectMapper bean to use it everywhere with @Autowired
     * @return
     */
    @Bean
    ObjectMapper objectMapper() {
    	return new ObjectMapper(); 
    }
}
