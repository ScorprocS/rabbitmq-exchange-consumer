package fr.mns.tprabbit.rabbitmq_consumer;

import java.io.IOException;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;

import fr.mns.tprabbit.rabbitmq_consumer.event.Event;
import fr.mns.tprabbit.rabbitmq_consumer.event.EventType;
import fr.mns.tprabbit.rabbitmq_consumer.event.UserDeletedEventData;
import fr.mns.tprabbit.rabbitmq_consumer.event.UserEventData;

/**
 * Used to listent and consume events recieved in queue
 * @author Alex
 *
 */
@Component
public class QueueListener {
	@Autowired
	private Queue myQueue;

	@Autowired
	private ObjectMapper objectMapper;

	/**
	 * Listen the message from the queue name defined in application.properties and consume it
	 * Use the header to know what kind of data is inside and what to do with it
	 * @param message
	 * @throws StreamReadException
	 * @throws DatabindException
	 * @throws IOException
	 */
	@RabbitListener(queues = "${queue.name}")
	public void listen(Message message) throws StreamReadException, DatabindException, IOException {
		//get eventType from header
		String et = message.getMessageProperties().getHeader("eventType");

		//decide what to do with the data thanks to eventType
		if (EventType.USER_CREATED.name().equals(et) || EventType.USER_UPDATED.name().equals(et)) {
			//convert the json event to Event
			TypeReference<Event<UserEventData>> ref = new TypeReference<Event<UserEventData>>() {
			};
			Event<UserEventData> event = objectMapper.readValue(message.getBody(), ref);
			
			//use the event to alter your user data

			System.out.println("Message read from " + myQueue.getName() + " : " + event.toString());

		} else if (EventType.USER_DELETED.name().equals(et)) {
			//convert the json event to Event

			TypeReference<Event<UserDeletedEventData>> ref = new TypeReference<Event<UserDeletedEventData>>() {
			};
			Event<UserDeletedEventData> event = objectMapper.readValue(message.getBody(), ref);
			//use the event to alter your user data

			System.out.println("Message read from " +  myQueue.getName() + " : " + event.toString());
		} else {
			System.out.println("Unmannaged event");
		}

	}
}
