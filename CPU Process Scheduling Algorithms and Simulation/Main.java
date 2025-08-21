package ProcessScheduling;

public class Main {

	public static void main(String[] args) {
		Process[] processes = new Process[4];
		processes[0] = new Process(1, 10, 0, 1);
		processes[1] = new Process(2, 2, 1, 2);
		processes[2] = new Process(3, 3, 2, 1);
		processes[3] = new Process(4, 2, 5, 3);
        
		
		//First Come First Serve Algorithm
		System.out.println("-----------<First Come First Serve Algorithm>------------");
		FCFS fcfs = new FCFS(processes);
		fcfs.printResult(); 
		
		// Shortest Job First Algorithm
		System.out.println("\n------------<Shortest Job First Algorithm>-----------");
		SJF sjf = new SJF(processes);
		sjf.printResult();
		
		// Shortest Job First Algorithm Preemptive
		System.out.println("\n--------<Shortest Job First Algorithm Preemptive>-------------");
		SJFPreemptive sjfp = new SJFPreemptive(processes);
		sjfp.printResult();
		
		// Round Robin Algorithm
		System.out.println("\n--------<Round Robin Algorithm>-------------");
		RoundRobin rrb = new RoundRobin(processes, 2);
		rrb.printResult();
		
		// Priority Algorithm
		System.out.println("\n--------<Priority Algorithm>-------------");
		Priority prt = new Priority(processes);
		prt.printResult();
	}

}
