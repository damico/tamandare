package org.jdamico.tamandare.urlreader;

public class Timer implements Runnable {

	private int time = 0;
	
	public Timer(int time){
		System.err.println("Thread will be terminated in:");
		this.time = time;
	}
	
	@Override
	public void run() {
		System.err.println("oooooooooooooo");
		int max = 0;
		while(max < time){
			try {
				
				Thread.sleep(1000);
				System.out.println("Thread will be terminated in: "+max);
				max++;
				
				
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}

	}

}
