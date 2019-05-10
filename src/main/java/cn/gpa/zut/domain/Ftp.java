package cn.gpa.zut.domain;

/**
 * ftp
 *
 */
public class Ftp {

	private static String ipAddr;
	
	private Integer port;//
	
	private static String userName;//
	
	private static String pwd;//
	
	private static String path;//

	public static String getIpAddr() {
		ipAddr="119.23.12.86";
		return ipAddr;
	}

	public void setIpAddr(String ipAddr) {
		this.ipAddr = ipAddr;
	}

	public Integer getPort() {
		port=21;
		return port;
	}

	public void setPort(Integer port) {
		this.port = port;
	}

	public static String getUserName() {
		userName="test";
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public static String getPwd() {
		pwd="123456";
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public static String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}
	
	
}
