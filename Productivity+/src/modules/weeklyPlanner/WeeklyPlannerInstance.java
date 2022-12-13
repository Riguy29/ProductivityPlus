package modules.weeklyPlanner;

public class WeeklyPlannerInstance {

	private String text;
	
	public WeeklyPlannerInstance() {
		this("");
	}
	
	public WeeklyPlannerInstance(String text) {
		this.text = text;
	}
	
	public String getText() {
		return text;
	}
	
	public void setText(String text) {
		this.text = text;
	}
}
