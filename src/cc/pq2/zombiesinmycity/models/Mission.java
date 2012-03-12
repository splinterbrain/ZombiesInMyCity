package cc.pq2.zombiesinmycity.models;

import java.io.Serializable;
import java.util.ArrayList;

public class Mission implements Serializable{
	private static final long serialVersionUID = -1726998046032457320L;
	private final ArrayList<MissionSegment> segments = new ArrayList<MissionSegment>();
	private MissionType missionType;
	private long completionTime;
	private long completionDistance;
	
	public Mission() {
		super();
	}

	/**
	 * @return segment at index
	 */
	public MissionSegment getSegment(int index) {
		return segments.get(index);
	}

	/**
	 * @param add segment
	 */
	public void addSegment(MissionSegment segment) {
		this.segments.add(segment);
	}
	
	/**
	 * @param remove segment
	 */
	public void removeSegment(int index) {
		this.segments.remove(index);
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
