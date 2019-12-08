public class Queue {
	private QueueInterface<Client> q;   
	private QueueInterface<Client> q2;   
	private int clientArrival; 
	private int numberServed;  
	private int totalTimeWaiting ;
	
	public Queue() {
	 
	} // end default constructor
	
	public void simulation(int duration, int client, int server) {
		
		q = new LinkedQueue<>();
		q2 = new LinkedQueue<>();
		
		int[] serviceTimeLeft = new int[server]; 
		
		
	//if the clock not equal and exceeding duration
	for (int clock = (int)(30 + Math.random() * 16); clock <= duration ; clock++) {
		
		int serviceTime = (int)(120 + Math.random() * 481);
        
		
	        //when new client arrives
			//the first clock or while clientArrival is less than number of clients
			if (clientArrival < client ) {
			
			//enqueue client and divide 2 queues 
			if(clientArrival % 2 == 0 ) {
		        Client nextArrival = new Client(clock, clientArrival, serviceTime);
		        q.enqueue(nextArrival);
		        
		        System.out.println("[Queue 1]: Client " + clientArrival + " enters line at time " + nextArrival.getArrivalTime() + ". The service time is " + serviceTime);
			      
			}
			if(clientArrival % 2 != 0 ) {
		        Client nextArrival = new Client(clock, clientArrival, serviceTime);
		        q2.enqueue(nextArrival);
		        
		        System.out.println("[Queue 2]: Client " + clientArrival + " enters line at time " + nextArrival.getArrivalTime() + ". The service time is " + serviceTime);
			      
			}
			//increment the clientArrival
			clientArrival++;
			
			
			
		} // end if
		
		for (int i = 0 ; i < server ; i++ ) {
			//when the client is still being served
			if (serviceTimeLeft[i] > 0) {    
	            serviceTimeLeft[i]--;
			}
			
			//when the client has ended service time
			//dequeue the client if queue is not empty and serviceTimeLeft has ended
			else if (!q.isEmpty() && !q2.isEmpty()) {
				
				Client nextClient = q.dequeue();  
				serviceTimeLeft[i] = nextClient.getServiceTime() - 1;
				int waitingTime = (clock-1) - nextClient.getArrivalTime();
				
				
				numberServed++;
				
				System.out.println();
				System.out.println("Queue 1: Client " + nextClient.getCustomerNumber() + " begins service at time " + (clock-1) + ". Time waited is " + waitingTime);
			   
				Client nextClient2 = q2.dequeue();
				serviceTimeLeft[i] = nextClient2.getServiceTime() - 1;
				int waitingTime2 = clock - nextClient2.getArrivalTime();
			
				
				totalTimeWaiting = totalTimeWaiting + waitingTime + waitingTime2;
			
				numberServed++;
				
				System.out.println();
				System.out.println("Queue 2:Client " + nextClient2.getCustomerNumber() + " begins service at time " + clock + ". Time waited is " + waitingTime2);
			     

			} // end if
			
			
		} //end inner loop  
		
		
	} // end outer loop

} // end simulate

	   // Displays summary results of the simulation
		public void result() {
			
			System.out.println();
	      
			System.out.println("Number served = " + numberServed);
	     
			System.out.println("Total time waited = " + totalTimeWaiting);
	      
			double averageTimeWaited = ((double)totalTimeWaiting) / numberServed;
	      
			System.out.println("Average time waited = " + averageTimeWaited);
	      
			int leftInLine = clientArrival - numberServed;
	      
			System.out.println("Number left in line = " + leftInLine);
	   
	} // end displayResults


}// end WaitLine