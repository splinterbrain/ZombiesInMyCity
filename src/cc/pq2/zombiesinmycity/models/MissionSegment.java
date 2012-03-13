package cc.pq2.zombiesinmycity.models;

import java.io.Serializable;
import java.util.Date;

import android.text.format.Time;
import cc.pq2.zombiesinmycity.controllers.PlacesApi;

public class MissionSegment implements Serializable {
	private static final long serialVersionUID = 3728091265275077608L;
	
	private Place startPlace;
	private Place finishPlace;
	private boolean complete = false;
	//In ms
	private long elapsedTime = 0;
	//In meters
	private double elapsedDistance = 0;
	
	private long lastLocationTime;
	private Place lastLocationPlace;

	public Place getStartPlace() {
		return startPlace;
	}
	public void setStartPlace(Place startPlace) {
		this.startPlace = startPlace;
	}
	public Place getFinishPlace() {
		return finishPlace;
	}
	public void setFinishPlace(Place finishPlace) {
		this.finishPlace = finishPlace;
	}
	public boolean isComplete() {
		return complete;
	}
	public void setComplete(boolean complete) {
		this.complete = complete;
	}

	public void start(Place location){
		this.lastLocationPlace = location;
		this.lastLocationTime = (new Date()).getTime();
	}
	
	
	public void updateProgress(Place location){
		if(complete) return;
		if(lastLocationPlace == null) return;
		
		long now = (new Date()).getTime();
		elapsedTime += now-lastLocationTime;
		double distance = PlacesApi.getDistance(lastLocationPlace, location);
		elapsedDistance += distance;
		this.lastLocationPlace = location;
		this.lastLocationTime = now;
	}
	
	public void finish(Place location){
		updateProgress(location);
		complete = true;
	}

	public long getElapsedTime() {
		return elapsedTime;
	}
	public double getElapsedDistance() {
		return elapsedDistance;
	}
	public long getLastLocationTime() {
		return lastLocationTime;
	}
	public Place getLastLocationPlace() {
		return lastLocationPlace;
	}
	
	
	
}
