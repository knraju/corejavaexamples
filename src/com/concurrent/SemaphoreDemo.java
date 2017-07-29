package com.concurrent;

import java.util.concurrent.Semaphore;

public class SemaphoreDemo {
	public static void main(String[] args) {
		Task t = new Task();
		Thread t1 = new Thread(t, "t1");
		Thread t2 = new Thread(t, "t2");
		Thread t3 = new Thread(t, "t3");
		t1.start();
		t2.start();
		t3.start();
		
	}
	

}

class Task implements Runnable {

	Semaphore sem = new Semaphore(1);
	int t = 0;
	@Override
	public void run() {
		
		try {
			sem.acquire();
			executeMethod();
//		9951028801	
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			sem.release();
		}
		
	}
	private void executeMethod() throws Exception {
		System.out.println("inside executeMethod==="+Thread.currentThread().getName());
		Thread.sleep(5000);
		t++;
		System.out.println(t);
	}
	
}
