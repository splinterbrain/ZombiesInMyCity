package cc.pq2.zombiesinmycity.models;

import java.io.Serializable;
import java.util.ArrayList;

public class Mission implements Serializable{
	private static final long serialVersionUID = -1726998046032457320L;
	private final ArrayList<Double[]> points = new ArrayList<Double[]>();;
	private MissionType missionType;
	private long completionTime;
	private long completionDistance;
	
	public Mission() {
		super();
	}

	/**
	 * @return point at index
	 */
	public Double[] getPoint(int index) {
		return points.get(index);
	}

	/**
	 * @param add point
	 */
	public void addPoint(Double[] point) {
		this.points.add(point);
	}
	
	/**
	 * @param remove point
	 */
	public void removePoint(int index) {
		this.points.remove(index);
	}
	
	


	/**
	 * @return the missionType
	 */
	public MissionType getMissionType() {
		return missionType;
	}

	/**
	 * @param missionType the missionType to set
	 */
	public void setMissionType(MissionType missionType) {
		this.missionType = missionType;
	}

	/**
	 * @return the completionTime
	 */
	public long getCompletionTime() {
		return completionTime;
	}

	/**
	 * @param completionTime the completionTime to set
	 */
	public void setCompletionTime(long completionTime) {
		this.completionTime = completionTime;
	}

	/**
	 * @return the completionDistance
	 */
	public long getCompletionDistance() {
		return completionDistance;
	}

	/**
	 * @param completionDistance the completionDistance to set
	 */
	public void setCompletionDistance(long completionDistance) {
		this.completionDistance = completionDistance;
	}
}
