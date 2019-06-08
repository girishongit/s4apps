package com.cg.pbs.customerprofile.models;
/**
 * 
 * @author gbangalo
 *
 */
public class ProfileText {
	
	private int client;
	private int id;
	private String language;
	private String type;
	private String text;
	
	public ProfileText(int client, int id, String type, String text)
	{
		this.setClient(client);
		this.setId(id);
		this.setType(type);
		this.setText(text);
		this.setLanguage("EN");
	}
	
	public int getClient() {
		return client;
	}
	public void setClient(int client) {
		this.client = client;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getLanguage() {
		return language;
	}
	public void setLanguage(String language) {
		this.language = language;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}

	
}
