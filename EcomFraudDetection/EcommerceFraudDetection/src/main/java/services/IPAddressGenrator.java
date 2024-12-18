package services;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Vector;

import org.apache.jasper.tagplugins.jstl.core.Catch;

import JavaBeans.JavaFuns;
public class IPAddressGenrator {
	public static boolean main1(String userid){
		String ipaddr="NA",macaddr="NA";
	try {
		InetAddress ip;
		ip = InetAddress.getLocalHost();
		System.out.println("Current IP address : " + ip.getHostAddress());
		ipaddr=ip.getHostAddress();
		NetworkInterface network = NetworkInterface.getByInetAddress(ip);
			
		byte[] mac = network.getHardwareAddress();
			
		System.out.print("Current MAC address : ");
			
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < mac.length; i++) {
			sb.append(String.format("%02X%s", mac[i], (i < mac.length - 1) ? "-" : ""));		
		}
		System.out.println(sb.toString());
		macaddr=sb.toString();
			
	} catch (UnknownHostException e) {
		 
		e.printStackTrace();
		
	} catch (SocketException e){
		 	
		e.printStackTrace();
			
	}
	try {
	JavaFuns jf=new JavaFuns();
	Vector v=jf.getValue("select userid from clientMachineDetails where userid='"+userid.trim()+"' and ipaddr='"+ipaddr.trim()+"'", 1);
	if(v.size()>0)
	{
		return true;
	}
	else
		return false;
	}  
	catch (Exception e) {
		// TODO: handle exception
		return false;
	} 
	}
	public static String getIPAddr(){
		String ipaddr="NA",macaddr="NA";
	try {
		InetAddress ip;
		ip = InetAddress.getLocalHost();
		System.out.println("Current IP address : " + ip.getHostAddress());
		ipaddr=ip.getHostAddress();
		NetworkInterface network = NetworkInterface.getByInetAddress(ip);
			
		byte[] mac = network.getHardwareAddress();
			
		System.out.print("Current MAC address : ");
			
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < mac.length; i++) {
			sb.append(String.format("%02X%s", mac[i], (i < mac.length - 1) ? "-" : ""));		
		}
		System.out.println(sb.toString());
		macaddr=sb.toString();
			
	} catch (UnknownHostException e) {
		 
		e.printStackTrace();
		
	} catch (SocketException e){
		 	
		e.printStackTrace();
			
	}
	 return ipaddr;
	}
	public static String getMacAddr(){
		String ipaddr="NA",macaddr="NA";
	try {
		InetAddress ip;
		ip = InetAddress.getLocalHost();
		System.out.println("Current IP address : " + ip.getHostAddress());
		ipaddr=ip.getHostAddress();
		NetworkInterface network = NetworkInterface.getByInetAddress(ip);
			
		byte[] mac = network.getHardwareAddress();
			
		System.out.print("Current MAC address : ");
			
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < mac.length; i++) {
			sb.append(String.format("%02X%s", mac[i], (i < mac.length - 1) ? "-" : ""));		
		}
		System.out.println(sb.toString());
		macaddr=sb.toString();
			
	} catch (UnknownHostException e) {
		 
		e.printStackTrace();
		
	} catch (SocketException e){
		 	
		e.printStackTrace();
			
	}
	 return macaddr;
	}
	public static void main(String userid){
		String ipaddr="NA",macaddr="NA";
	try {
		InetAddress ip;
		ip = InetAddress.getLocalHost();
		System.out.println("Current IP address : " + ip.getHostAddress());
		ipaddr=ip.getHostAddress();
		NetworkInterface network = NetworkInterface.getByInetAddress(ip);
			
		byte[] mac = network.getHardwareAddress();
			
		System.out.print("Current MAC address : ");
			
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < mac.length; i++) {
			sb.append(String.format("%02X%s", mac[i], (i < mac.length - 1) ? "-" : ""));		
		}
		System.out.println(sb.toString());
		macaddr=sb.toString();
			
	} catch (UnknownHostException e) {
		 
		e.printStackTrace();
		
	} catch (SocketException e){
		 	
		e.printStackTrace();
			
	}
	try {
	JavaFuns jf=new JavaFuns();
	Vector v=jf.getValue(" select ifnull(max(ipId),1000)+1 as mxid from clientMachineDetails ;", 1);
	String qr="insert into clientMachineDetails values("+v.elementAt(0).toString().trim()+",'"+userid+"','"+ipaddr+"','"+macaddr+"')";
	if(jf.execute(qr))
	{}
	}  
	catch (Exception e) {
		// TODO: handle exception
	} 
	}
}
