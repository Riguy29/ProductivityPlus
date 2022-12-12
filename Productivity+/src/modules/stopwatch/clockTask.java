package modules.stopwatch;

import javafx.concurrent.Task;

public class clockTask extends Task<String> {
	
	public boolean on;
	public boolean once;
	public long time = 0;
	public long start = System.currentTimeMillis();
	public long display = 0; 
	
	@Override
	protected String call() throws Exception {
		while (isCancelled() == false) {
			if (on == true) {
				if (once == true) {
					start = System.currentTimeMillis();
					once = false;
				}
				display = System.currentTimeMillis() - start + time;
				updateMessage(String.format("%04d.%03d",(display) / 1000,(display) % 1000));
			} else {
				if (once == false) {
					time += System.currentTimeMillis() - start;
					once = true;
				}
			}
		}
		return null;
	}

}
