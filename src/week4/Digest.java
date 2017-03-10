package week4;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Digest {
	public static void main(String[] args) throws NoSuchAlgorithmException{
		String str = "this is a sentence.";
		byte[] byte_array = str.getBytes();
		MessageDigest md = MessageDigest.getInstance("MD5");
		md.update(byte_array);
		byte[] digest = md.digest();
		System.out.println(digest.toString());
	}
}
