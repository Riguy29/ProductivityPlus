package modules.contacts;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Comparator;

public class ContactComparator implements Comparator<Contact> {
	private String compareMethod;
	@Override
	public int compare(Contact o1, Contact o2) {
		if(compareMethod.equals("Name")) {
			return o1.getName().compareTo(o2.getName());
		}
		else if(compareMethod.equals("DOB")) {
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
			LocalDate o1DOB = LocalDate.parse(o1.getDOB(), formatter);
			LocalDate o2DOB = LocalDate.parse(o2.getDOB(), formatter);
			return o1DOB.compareTo(o2DOB);
		}
		else {
			return o1.getGroup().compareTo(o2.getGroup());
		}
		
	}
	
	public void SetCompareMethod(String _compareMethod) {
		compareMethod = _compareMethod;
	}

}
