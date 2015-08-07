package utils;

import com.google.common.base.Charsets;
import com.google.common.hash.HashCode;
import com.google.common.hash.HashFunction;
import com.google.common.hash.Hashing;

public class HashUtil {
	public static String md5(String input){
		HashFunction hf = Hashing.md5();
		HashCode hc = hf.newHasher()
		       .putString(input, Charsets.UTF_8)
		       .hash();
		return hc.toString();
	}

}
