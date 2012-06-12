package com.danidemi.tutorial.tdd.coverage;

public class Illusion {

	public void illude(int a, int b){
		
		if(a < 0){
			a = -a;
		}else{
			a = 0;
		}
		
		if(b >= 0){
			b = 2*b;
		}else{
			b = b/a;
		}
		
		System.out.println("a=" + a);
		System.out.println("b=" + b);
		
	}
		
}
