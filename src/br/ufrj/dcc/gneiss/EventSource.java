package br.ufrj.dcc.gneiss;

import java.io.IOException;
import java.io.OutputStream;


public class EventSource {
	StringBuffer buffer;

	public EventSource() {
		buffer = new StringBuffer(128);
	}

	public boolean isEmpty() {
		return buffer.length() == 0;
	}
	
	public String save() {
		String result = "{}\n";
		
		if (!isEmpty()) result = "{ " + buffer.toString() + " }\n";
		
		buffer.setLength(0);
		
		return result;
	}
	
	private void appendSeparator() {
		if (!isEmpty()) {
			buffer.append(", ");
		}
	}
	
	public void set(String key, String value) {
		appendSeparator();
		buffer.append("\"" + key + "\": ");
		buffer.append("\"" + value + "\"");
	}

	public void set(String key, int value) {
		appendSeparator();
		buffer.append("\"" + key + "\": ");
		buffer.append(value);
	}

	public void set(String key, boolean value) {
		appendSeparator();
		buffer.append("\"" + key + "\": ");
		buffer.append(value);
	}
	
	public boolean flush(OutputStream out) {
		try {
			out.write(save().getBytes());
			return true;
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return false;
	}
}
