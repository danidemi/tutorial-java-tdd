package com.danidemi.tddclass.arquillan;

import javax.inject.Inject;

public class Master {

	private Slave slave;
	
	@Inject
	public void setSlave(Slave slave) {
		this.slave = slave;
	}
	
	@Override
	public String toString() {
		return "master" + "->" + slave.toString();
	}
	
}


