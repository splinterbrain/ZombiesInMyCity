package cc.pq2.zombiesinmycity.models;

import java.io.Serializable;

import cc.pq2.zombiesinmycity.controllers.PlacesApi;

import android.text.format.Time;

public class MissionSegment implements Serializable {
	private static final long serialVersionUID = 3728091265275077608L;
	
	private Place startPlace;
	private Place finishPlace;
	private boolean complete = false;
	//In ms
	private long elapsedTime = 0;
	//In meters
	private long elapsedDistance = 0;
	
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
		this.lastLocationTime = (new Time()).toMillis(false);
	}
	
	
	public void updateProgress(Place location){
		if(complete) return;
		long now = (new Time()).toMillis(false);
		elapsedTime += now-lastLocationTime;
		elapsedDistance += PlacesApi.getDistance(lastLocationPlace, location);
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
	public long getElapsedDistance() {
		return elapsedDistance;
	}
	public long getLastLocationTime() {
		return lastLocationTime;
	}
	public Place getLastLocationPlace() {
		return lastLocationPlace;
	}
	
	
	
}
