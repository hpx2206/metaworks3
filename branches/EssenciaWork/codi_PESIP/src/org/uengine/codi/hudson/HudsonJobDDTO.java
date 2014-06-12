package org.uengine.codi.hudson;

public class HudsonJobDDTO {

	private String action;
	
	private String description;
	private String displayName;
	private String name;
	private String url;
	private String buildable;
	private String inQueue;
	private String keepDependencies;
	private String nextBuilderNumber;
	private String property;
	private String concurrentBuild;
	private String scm;
	private BuildDDTO[] buildDDTOs;
	private BuildDDTO firstBuildDDTO;
	private BuildDDTO lastBuildDDTO;
	private BuildDDTO lastCompletedBuild;
	private BuildDDTO lastFailedBuild;
	private BuildDDTO lastStableBuild;
	private BuildDDTO lastSuccessfulBuild;
	private BuildDDTO lastUnSuccessfulBuild;
	private HealthReportDDTO healthReportDDTO;
	
	public String getAction() {
		return action;
	}
	public void setAction(String action) {
		this.action = action;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getDisplayName() {
		return displayName;
	}
	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getBuildable() {
		return buildable;
	}
	public void setBuildable(String buildable) {
		this.buildable = buildable;
	}
	public String getInQueue() {
		return inQueue;
	}
	public void setInQueue(String inQueue) {
		this.inQueue = inQueue;
	}
	public String getKeepDependencies() {
		return keepDependencies;
	}
	public void setKeepDependencies(String keepDependencies) {
		this.keepDependencies = keepDependencies;
	}
	public String getNextBuilderNumber() {
		return nextBuilderNumber;
	}
	public void setNextBuilderNumber(String nextBuilderNumber) {
		this.nextBuilderNumber = nextBuilderNumber;
	}
	public String getProperty() {
		return property;
	}
	public void setProperty(String property) {
		this.property = property;
	}
	public String getConcurrentBuild() {
		return concurrentBuild;
	}
	public void setConcurrentBuild(String concurrentBuild) {
		this.concurrentBuild = concurrentBuild;
	}
	public String getScm() {
		return scm;
	}
	public void setScm(String scm) {
		this.scm = scm;
	}
	public BuildDDTO[] getBuildDDTOs() {
		return buildDDTOs;
	}
	public void setBuildDDTOs(BuildDDTO[] buildDDTOs) {
		this.buildDDTOs = buildDDTOs;
	}
	public BuildDDTO getFirstBuildDDTO() {
		return firstBuildDDTO;
	}
	public void setFirstBuildDDTO(BuildDDTO firstBuildDDTO) {
		this.firstBuildDDTO = firstBuildDDTO;
	}
	public BuildDDTO getLastBuildDDTO() {
		return lastBuildDDTO;
	}
	public void setLastBuildDDTO(BuildDDTO lastBuildDDTO) {
		this.lastBuildDDTO = lastBuildDDTO;
	}
	public BuildDDTO getLastCompletedBuild() {
		return lastCompletedBuild;
	}
	public void setLastCompletedBuild(BuildDDTO lastCompletedBuild) {
		this.lastCompletedBuild = lastCompletedBuild;
	}
	public BuildDDTO getLastFailedBuild() {
		return lastFailedBuild;
	}
	public void setLastFailedBuild(BuildDDTO lastFailedBuild) {
		this.lastFailedBuild = lastFailedBuild;
	}
	public BuildDDTO getLastStableBuild() {
		return lastStableBuild;
	}
	public void setLastStableBuild(BuildDDTO lastStableBuild) {
		this.lastStableBuild = lastStableBuild;
	}
	public BuildDDTO getLastSuccessfulBuild() {
		return lastSuccessfulBuild;
	}
	public void setLastSuccessfulBuild(BuildDDTO lastSuccessfulBuild) {
		this.lastSuccessfulBuild = lastSuccessfulBuild;
	}
	public BuildDDTO getLastUnSuccessfulBuild() {
		return lastUnSuccessfulBuild;
	}
	public void setLastUnSuccessfulBuild(BuildDDTO lastUnSuccessfulBuild) {
		this.lastUnSuccessfulBuild = lastUnSuccessfulBuild;
	}
	public HealthReportDDTO getHealthReportDDTO() {
		return healthReportDDTO;
	}
	public void setHealthReportDDTO(HealthReportDDTO healthReportDDTO) {
		this.healthReportDDTO = healthReportDDTO;
	}
	
}
