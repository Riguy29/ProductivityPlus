package modules.weeklyPlanner;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;

public class WeeklyPlannerInstances {

	@XmlElement(name="instance")
	private List<WeeklyPlannerInstance> instances = new ArrayList<>();
	
	public List<WeeklyPlannerInstance> getInstances() {
		return instances;
	}
}
