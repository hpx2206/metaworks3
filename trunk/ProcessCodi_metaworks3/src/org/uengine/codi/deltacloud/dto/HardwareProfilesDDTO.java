package org.uengine.codi.deltacloud.dto;

public class HardwareProfilesDDTO {

	private String id;
	private String name;
	private HardwareProfilesCDTO[] hardwareProfilesCDTOs;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public HardwareProfilesCDTO[] getHardwareProfilesCDTOs() {
		return hardwareProfilesCDTOs;
	}
	public void setHardwareProfilesCDTOs(HardwareProfilesCDTO[] hardwareProfilesCDTOs) {
		this.hardwareProfilesCDTOs = hardwareProfilesCDTOs;
	}
	
	
}
