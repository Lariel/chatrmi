import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.security.*;
import javax.crypto.*;
import javax.crypto.spec.*;
import java.util.Arrays;

import javax.xml.bind.DatatypeConverter;

public class AESDecipher{
	
	private String texto;
	
	public AESDecipher(String key, String texto) throws Exception {
		SecretKeySpec skeySpec = getSecretKey(key);
		
		Cipher cipher = Cipher.getInstance("AES");
		cipher.init(Cipher.DECRYPT_MODE, skeySpec);
						
		
		byte[] stringbizarra = texto.getBytes(); 
		
		byte[] deciphered = cipher.doFinal(stringbizarra);
		
		texto = "etste";//new String(deciphered);
	}
		
	public static String toHexString(byte[] array) {
		return DatatypeConverter.printHexBinary(array);
	}
	
	public static byte[] toByteArray(String s) {
		return DatatypeConverter.parseHexBinary(s);
	}
	
	public static SecretKeySpec getSecretKey (String passwd) throws Exception{
		byte[] dataBytes = passwd.getBytes();
		
		MessageDigest md = MessageDigest.getInstance("SHA-256");
		md.update(dataBytes,0,passwd.length());
		byte[] mdbytes = md.digest();
		
		return new SecretKeySpec(Arrays.copyOfRange(mdbytes, 0, 16), "AES");
	}
	
	public String getTexto() throws RemoteException{
		return texto;
	}
	
}
