package fr.mns.tprabbit.rabbitmq_consumer.event;

/**
 * Class to contain events
 * Used to send and recieve data with serialized json 
 * @author Alex
 *
 * @param <T>
 */
public class Event<T extends EventData> {
	private EventType eventType;
	private T data;
	
	public EventType getEventType() {
		return eventType;
	}
	public void setEventType(EventType eventType) {
		this.eventType = eventType;
	}
	public T getData() {
		return data;
	}
	public void setData(T data) {
		this.data = data;
	}
	
	@Override
	public String toString() {
		return "Event [eventType=" + eventType + ", data=" + data + "]";
	}
	
	

}
