package org.mule.examples.snaplights;

import org.codehaus.jackson.annotate.JsonAutoDetect;

@JsonAutoDetect
public class MMSMessage {

	private String event; // "message-in"
	private String type; // "command_mms"
	private String campaign_id; //"54667"
	private String msisdn; // "14152901974"
	private String shortcode; //"343434"
	private String carrier; //"AT&T"
	private String carrier_id; //"3"
	private String message; //"Mule"
	private String subject; //empty string
	private ImageRef[] images;
	
	public String getEvent() {
		return event;
	}
	public void setEvent(String event) {
		this.event = event;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getCampaign_id() {
		return campaign_id;
	}
	public void setCampaign_id(String campaign_id) {
		this.campaign_id = campaign_id;
	}
	public String getMsisdn() {
		return msisdn;
	}
	public void setMsisdn(String msisdn) {
		this.msisdn = msisdn;
	}
	public String getShortcode() {
		return shortcode;
	}
	public void setShortcode(String shortcode) {
		this.shortcode = shortcode;
	}
	public String getCarrier() {
		return carrier;
	}
	public void setCarrier(String carrier) {
		this.carrier = carrier;
	}
	public String getCarrier_id() {
		return carrier_id;
	}
	public void setCarrier_id(String carrier_id) {
		this.carrier_id = carrier_id;
	}
	public String getMessage() {
		
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public ImageRef[] getImages() {
		return images;
	}
	public void setImages(ImageRef[] images) {
		this.images = images;
	}

}
