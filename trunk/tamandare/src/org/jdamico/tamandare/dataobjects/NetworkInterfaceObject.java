package org.jdamico.tamandare.dataobjects;

public class NetworkInterfaceObject {
	private String ifaceName;
	private String ifaceIPv6;
	private String ifaceIPv4;
	
	public NetworkInterfaceObject(String ifaceName, String ifaceIPv6,
			String ifaceIPv4) {
		super();
		this.ifaceName = ifaceName;
		this.ifaceIPv6 = ifaceIPv6;
		this.ifaceIPv4 = ifaceIPv4;
	}
	
	public String getIfaceName() {
		return ifaceName;
	}
	public void setIfaceName(String ifaceName) {
		this.ifaceName = ifaceName;
	}
	public String getIfaceIPv6() {
		return ifaceIPv6;
	}
	public void setIfaceIPv6(String ifaceIPv6) {
		this.ifaceIPv6 = ifaceIPv6;
	}
	public String getIfaceIPv4() {
		return ifaceIPv4;
	}
	public void setIfaceIPv4(String ifaceIPv4) {
		this.ifaceIPv4 = ifaceIPv4;
	}
	
	
}
