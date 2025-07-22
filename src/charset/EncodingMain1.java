package charset;

import java.nio.charset.Charset;

public class EncodingMain1 {
	
	private static final Charset EUC_KR = Charset.forName("EUC-KR");
	private static final Charset MS_949 = Charset.forName("MS949");
	
	
	public static void main(String[] args) {
		
	}
	
	
	private static void encoding(String text, Charset charset) {
		byte[] bytes = text.getBytes(charset);
		System.out.printf();
	}
}
