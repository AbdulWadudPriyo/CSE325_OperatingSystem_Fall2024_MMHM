package ProcessScheduling;

import java.util.*;

public class IdComparator implements Comparator<Process> {

	@Override
	public int compare(Process o1, Process o2) {
		return o1.getId() - o2.getId();
	
	}
	
	
}
