package charset;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

import static java.nio.charset.StandardCharsets.*;

public class EncodingMain1 {
	
	private static final Charset EUC_KR = Charset.forName("EUC-KR");
	private static final Charset MS_949 = Charset.forName("MS949");
	
	
	public static void main(String[] args) {
		System.out.println("== ASCII 영문 처리");
		encoding("A", US_ASCII);
		encoding("A", ISO_8859_1);
		encoding("A", EUC_KR);
		encoding("A", UTF_8);
		encoding("A", UTF_16BE);

		System.out.println("== 한글 지원 ==");
		encoding("가", EUC_KR);
	}

	
	private static void encoding(String text, Charset charset) {
		byte[] bytes = text.getBytes(charset);
		System.out.printf("%s -> [%s] 인코딩 -> %s %sbyte\n", text, charset.name(), Arrays.toString(bytes), bytes.length);
	}
}
