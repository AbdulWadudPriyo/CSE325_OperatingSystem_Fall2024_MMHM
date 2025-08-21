package ProcessScheduling;

import java.util.*;

public class RoundRobin extends FCFS {

	private int quantam;
	
	public RoundRobin(Process[] processes, int quantam) {
		super(processes);
		this.quantam = quantam;
		
	}

	@Override
	protected void apply() {
		GrantChart grantchart = new GrantChart();
		ArrayList<Process> list;
		time = 0;
		total_waiting_time = 0;
		average_waiting_time = 0;
		sort();
		list = new ArrayList<>(sorted_processes);
		
		int index = 0;
		while(list.size()>0) {
			Process process = list.get(index);
			grantchart.schedule(process);
			int processed_time = Math.min(process.remaining_burst_time, quantam);
			grantchart.passTime(processed_time);
			process.remaining_burst_time -= processed_time;
			if(process.remaining_burst_time == 0) {
				list.remove(index);
				if(list.size()>0) {
					index = index % list.size();
				}
			} else {
				index = (index + 1) % list.size();
			}
		}
		grantchart.claculateWaitingTime();
		grantchart.print();
		average_waiting_time = grantchart.getTotalWaitingTime() / processes.length;
	}
	
	
	
}
