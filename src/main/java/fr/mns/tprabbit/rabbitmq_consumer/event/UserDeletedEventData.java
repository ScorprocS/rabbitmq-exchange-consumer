package fr.mns.tprabbit.rabbitmq_consumer.event;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
/**
 * Data sent when a user is deleted
 * @author Alex
 *
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserDeletedEventData extends EventData{
	private Long id;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "UserDeletedEventData [id=" + id + "]";
	}
	
	

}
