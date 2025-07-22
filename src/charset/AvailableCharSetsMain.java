package charset;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class AvailableCharSetsMain {

	public static void main(String[] args) {
		
		
		// 이용 가능한 모든 Charset 자바 + OS
		Map<String, Charset> charsets = Charset.availableCharsets();
		
		for (Entry<String, Charset> entry : charsets.entrySet()) {
			String key = entry.getKey();
			System.out.println("charsetname = " + key);
			
		}
		
		System.out.println("=====");
		
		// 문자로 조회 (대소문자 구분x)
		Charset charset1 = Charset.forName("MS949");
		System.out.println("charset1 = " + charset1);
		
		
		// 별칭 조회
		Set<String> aliases = charset1.aliases();
		for (String alias : aliases) {
			System.out.println("alias = " + alias);
		}
		
		// UTF-8 이름으로 조회
		Charset charset2 = Charset.forName("utf-8");
		System.out.println("charset2 = " + charset2);
		
		// UTF-8 상수로 조회
		Charset charset3 = StandardCharsets.UTF_8;
		System.out.println("charset3 = " + charset3);
		

	}

}
