package cc.pq2.zombiesinmycity.models;

import java.io.Serializable;

public class Base implements Serializable {
	private static final long serialVersionUID = -3392586280426604421L;
	private String name;
	private Place place;

	
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	public Place getPlace() {
		return place;
	}
	public void setPlace(Place place) {
		this.place = place;
	}
}
