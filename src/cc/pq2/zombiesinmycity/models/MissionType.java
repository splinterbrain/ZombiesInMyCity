package cc.pq2.zombiesinmycity.models;

import java.io.Serializable;

public class MissionType implements Serializable {
	private static final long serialVersionUID = -5806709537122682056L;
	private final String name;
	
	
	public MissionType(String name) {
		super();
		this.name = name;
	}


	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

}
