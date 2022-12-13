package modules.notePad;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;

public class NotePadInstances {

	@XmlElement(name="instance") 
	private List<NotePadInstance> instances = new ArrayList<>();

	public List<NotePadInstance> getInstances() {
		return instances;
	}
	
}
