package www.project.bean;

import java.sql.Timestamp;

public class Messages {

	private int mes_id;
	private int sender;

	public int getMes_id() {
		return mes_id;
	}

	public void setMes_id(int mes_id) {
		this.mes_id = mes_id;
	}

	private int receiver;
	private String msg;
	private Timestamp timestamp;

	public int getSender() {
		return sender;
	}

	public void setSender(int sender) {
		this.sender = sender;
	}

	public int getReceiver() {
		return receiver;
	}

	public void setReceiver(int receiver) {
		this.receiver = receiver;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Timestamp getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Timestamp timestamp) {
		this.timestamp = timestamp;
	}
}
