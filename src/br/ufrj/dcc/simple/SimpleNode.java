package br.ufrj.dcc.simple;

import javax.microedition.midlet.MIDlet;
import javax.microedition.midlet.MIDletStateChangeException;

import br.ufrj.dcc.gneiss.EventSource;

public class SimpleNode extends MIDlet implements Runnable {
	private EventSource event;
	private Thread background;
	
	public SimpleNode() {
		event = new EventSource();
		background = new Thread(this);
	}

	protected void startApp() throws MIDletStateChangeException {
		event.set("address", System.getProperty("IEEE_ADDRESS"));	
		event.set("event", "startApp");
		event.flush(System.out);
		background.start();
	}
	
	protected void pauseApp() {
		event.set("address", System.getProperty("IEEE_ADDRESS"));
		event.set("event", "pauseApp");
		event.flush(System.out);
	}
	
	protected void destroyApp(boolean arg) throws MIDletStateChangeException {
		event.set("address", System.getProperty("IEEE_ADDRESS"));
		event.set("event", "destroyApp");
		event.set("arg", arg);
		event.flush(System.out);
	}

	private boolean step() {
		event.set("address", System.getProperty("IEEE_ADDRESS"));
		event.set("event", "step");
		event.flush(System.out);
		
		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {}
		
		return true;
	}
	
	public void run() {
		while (step());
	}
}
