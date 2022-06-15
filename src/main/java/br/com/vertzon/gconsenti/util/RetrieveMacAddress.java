package br.com.vertzon.gconsenti.util;

import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

public class RetrieveMacAddress {
	
	public static String getMacAddress() throws SocketException {
	    String firstInterface = null;   
	    @SuppressWarnings("unused")
		String macAddress = null;
	    Map<String, String> addressByNetwork = new HashMap<>();
	    Enumeration<NetworkInterface> networkInterfaces = NetworkInterface.getNetworkInterfaces();

	    while(networkInterfaces.hasMoreElements()){
	        NetworkInterface network = networkInterfaces.nextElement();

	        byte[] bmac = network.getHardwareAddress();
	        if(bmac != null){
	            StringBuilder sb = new StringBuilder();
	            for (int i = 0; i < bmac.length; i++){
	                sb.append(String.format("%02X%s", bmac[i], (i < bmac.length - 1) ? "-" : ""));        
	            }

	            if(sb.toString().isEmpty()==false){
	                addressByNetwork.put(network.getName(), sb.toString());
	            }

	            if(sb.toString().isEmpty()==false && firstInterface == null){
	                firstInterface = network.getName();
	            }
	        }
	    }

	    if(firstInterface != null){
	        return macAddress = addressByNetwork.get(firstInterface);
	    }

	    return null;
	}

}
