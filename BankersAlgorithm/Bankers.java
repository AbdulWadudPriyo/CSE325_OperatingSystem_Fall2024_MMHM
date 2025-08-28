package BankersAlgorithm;

import java.util.*;

public class Bankers {
	private int need[][];
	private int allocate[][];
	private int max[][];
	private int avail[][];
	private int np;
	private int nr;
	
	public void input() {
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter number of process and resources: ");
		np = sc.nextInt();
		nr = sc.nextInt();
		need = new int[np][nr];
		max = new int[np][nr];
		allocate = new int[np][nr];
		avail = new int[1][nr];
		
		System.out.println("Enter allocation matrix: ");
		for(int i=0; i<np; i++) {
			for(int j=0; j<nr; j++) {
				allocate[i][j] = sc.nextInt();
			}
		}
		
		System.out.println("Enter max matrix: ");
		for(int i=0; i<np; i++) {
			for(int j=0; j<nr; j++) {
				max[i][j] = sc.nextInt();
			}
		}
		
		System.out.println("Enter available matrix: ");
		for(int j=0; j<nr; j++) {
			avail[0][j] = sc.nextInt();
		}
		
		sc.close();
		
		
	}
	
	private int[][] calcNeed(){
		for(int i=0; i<np; i++) {
			for(int j=0; j<nr; j++) {
				need[i][j] = max[i][j] - allocate[i][j];
			}
		}
		return need;
	}
	
	public boolean check(int i) {
		// checking if all resources of i th process cab be allocated
		for(int j=0; j<nr; j++) {
			if(avail[0][j]<need[i][j]) {
				return false;
			}
		}
		return true;
	}
	
	public void isSafe() {
		input();
		calcNeed();
		boolean done[] = new boolean[np];
		int j =0;
		
		while(j<np) {
			boolean allocated = false;
			for(int i=0; i<np; i++) {
				if(!done[i] && check(i)) {
					for(int k=0; k<nr; k++) {
						avail[0][k] = avail[0][k] -need[i][k] + max[i][k];
					}
					System.out.println("Allocated Process: "+i);
					allocated = done[i] = true;
					j++;
				}
				if(!allocated) {
					break;
				}
			}
			if(j==np) {
				System.out.println("\nSafely Allocated");
			} else {
				System.out.println("\nNot Safely Allocated");
			}
		}
	}
}
