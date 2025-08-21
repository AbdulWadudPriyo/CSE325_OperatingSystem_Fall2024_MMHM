package ProcessScheduling;

import java.util.*;

public class SJFPreemptive extends Algorithm {
	
	public SJFPreemptive(Process[] processes) {
		super(processes);
		// TODO Auto-generated constructor stub
	}
	
	
	@Override
	protected void sort() {
		 // No need for this method
	}

	

	@Override
	protected void apply() {
		ArrayList<Process> list = new ArrayList<>(Arrays.asList(processes));
		GrantChart grantchart = new GrantChart();
		time =0;
		total_waiting_time = 0;
		average_waiting_time = 0;
		Process process = null;
		
		while(list.size()>0) {
			process = nextProcess(list, time);
			grantchart.schedule(process);
			process.remaining_burst_time--;
			if(process.remaining_burst_time == 0) {
				list.remove(process);
			}
			
			time ++;
		}
		grantchart.claculateWaitingTime();
		grantchart.print();
		average_waiting_time = grantchart.getTotalWaitingTime() / processes.length;
		
	}
	
	protected Process nextProcess(ArrayList<Process> list, int time) {
		Process process = list.get(0);
		for(int i=1; i<list.size(); ++i) {
			if((list.get(i).getArrivingTime() <= time && list.get(i).remaining_burst_time < process.remaining_burst_time) ||
					(list.get(i).getArrivingTime() <= time && process.getArrivingTime() > time)) {
				process = list.get(i);
			}
		}
		return process;
	}


	protected void printResult() {
		if(time == 0) {
			apply();
		}
		
		System.out.println();
		System.out.println(Process.getHeader());
		
		for(Process process : processes) {
			 System.out.println(process);
		}
		
	    System.out.printf("%s \nAverage waiting time: %.2f\n", getName(), average_waiting_time);
		
	}


	 
}
