package modules.brainBreak;

import javafx.concurrent.Task;

public class timerTask extends Task<String> {
	public boolean once;
	public long time;
	public long end;
	public long display = 0;
	public boolean counting; 
	
	public timerTask(Long time) {
		this.time = time;
		this.end = System.currentTimeMillis() + time;
	}
	
	@Override
	protected String call() throws Exception {
		while (isCancelled() == false) {
			if (counting == true) {
				if (once == true) {
					once = false;
				}
				display = end - System.currentTimeMillis() + time;
				updateMessage(""+display);
			} else {
				if (once == false) {
					time += end - System.currentTimeMillis() ;
					once = true;
				}
			}
		}
		return null;
	}
}
