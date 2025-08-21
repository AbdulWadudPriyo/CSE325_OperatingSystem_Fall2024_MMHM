package ProcessScheduling;

import java.util.*;

public class Priority extends Algorithm {
	private GrantChart grantchart;

	public Priority(Process[] processes) {
		super(processes);
		 
	}

	@Override
	protected void sort() {
		 
		if(sorted_processes == null) {
			sorted_processes = new TreeSet<>(new PriorityComparator());
		} else {
			sorted_processes.clear();
		}
		for(Process process : processes) {
			if(process.getArrivingTime() <= time && !grantchart.isProcessStarted(process)) {
				sorted_processes.add(process);
			}
		}
		
	}

	@Override
	protected void apply() {
		 grantchart = new GrantChart();
		 time = 0;
		 total_waiting_time = 0;
		 average_waiting_time = 0;
		 sort();
		 
		 do {
			 grantchart.schedule(sorted_processes.first());
			 grantchart.passTime(sorted_processes.first().getBurstTime());
			 time = grantchart.getTime();
			 sort();
		 } while(sorted_processes.size() > 0);
		 
		 grantchart.claculateWaitingTime();
	     grantchart.print();
	     average_waiting_time = grantchart.getTotalWaitingTime() / processes.length;
		
	}

	@Override
	protected void printResult() {
		if(sorted_processes == null) {
			apply();
		}
		
		System.out.println();
		System.out.print(Process.getHeader());
		System.out.println("\tPriority");
		
		
		for(Process process : processes) {
			 System.out.print(process);
			 System.out.printf("\t%d\n", process.getPriority());
		}
		
	    System.out.printf("%s \nAverage waiting time: %.2f\n", getName(), average_waiting_time); 
		
	}
  
}
