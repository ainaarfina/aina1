public class Client {
	private int arrivalTime = 0; 
	private int serviceTime = 0;
    private int clientNumber;
	    
	public Client(int clock, int clientArrival, int serviceTime) {
		arrivalTime = clock ;
		clientNumber = clientArrival ; 
		this.serviceTime = serviceTime ;
		
	}

	public int getServiceTime() {
        
		return serviceTime;
    }
    
	public int getArrivalTime() {
       
		return arrivalTime;
    }
	
    
    public int getCustomerNumber() {
        
    	return clientNumber;
    }
 
}