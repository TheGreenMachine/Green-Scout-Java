package com.edinarobotics.scouting;

public class TeleopStack {

	private int totes;
	private boolean rc;
	private boolean noodle;

	public TeleopStack(int totes, boolean rc, boolean noodle) {
		this.totes = totes;
		this.rc = rc;
		this.noodle = noodle;
	}

	public int getTotes() {
		return this.totes;
	}

	public boolean getRC() {
		return this.rc;
	}

	public boolean getNoodle() {
		return this.noodle;
	}

	public void setTotes(int totes) {
		this.totes = totes;
	}

	public void setRC(boolean rc) {
		this.rc = rc;
	}

	public void setNoodle(boolean noodle) {
		this.noodle = noodle;
	}

}
