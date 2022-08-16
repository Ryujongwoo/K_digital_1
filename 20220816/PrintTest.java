
public class PrintTest {

	public static void main(String[] args) {
		
//		자바는 "+"이 두 가지 용도로 사용된다.
//		"+" 양쪽이 모두 숫자일 경우 덧셈이 실행된다.
//		"+"의 한쪽이라도 문자열이 나오면 문자열을 이어붙이는 문자열 연결 연산자로 사용된다.
		System.out.println(5 + " + " + 3 + " = " + (5 + 3));
		System.out.println(5 + " - " + 3 + " = " + (5 - 3));
		System.out.println(5 + " * " + 3 + " = " + 5 * 3);
//		자바는 정수와 정수의 연산은 결과도 정수다.
		System.out.println(5 + " / " + 3 + " = " + 5 / 3); // 몫
		System.out.println(5. + " / " + 3 + " = " + 5. / 3);
		System.out.println(5 + " / " + 3. + " = " + 5 / 3.);
		System.out.println(5. + " / " + 3. + " = " + 5. / 3.);
		System.out.println(5 + " % " + 3 + " = " + 5 % 3); // 나머지
		
//		자바가 제공하는 기본 자료형
//		boolean: 1바이트, 논리값(true, false)
//		byte: 1바이트, 2진 데이터 전송에 사용한다.
//		char: 2바이트, 문자 1개
//		short: 2바이트, -32768 ~ 32767 사이의 정수
//		int: 4바이트, -2147483648 ~ 2147483647 사이의 정수
//		long: 8바이트, -2의 63승 ~ 2의 63승 - 1 사이의 정수
//		float: 4바이트, 단정도 실수, 소수점 아래 약 6자리 정도의 실수
//		double: 8바이트, 배정도 실수, 소수점 아래 약 16자리 정도의 실수
		
//		묵시적(암시적) 형변환
//		정수는 실수로, 자료형의 크기가 서로 다른 자료의 연산 결과는 크기가 큰 자료형으로 자동 변환된다.
//		byte => short => int => long => float => double
//		        char  =>
		System.out.println(5. + " / " + 3 + " = " + 5. / 3);
		System.out.println('A' + " + " + 32 + " = " + ('A' + 32));
		System.out.println('a' + " - " + 32 + " = " + ('a' - 32));
		
//		명시적 형변환 => casting
//		프로그래머가 그 순간의 자료형을 지정할 수 있다.
		System.out.println(5 + " / " + 3 + " = " + (double) 5 / 3);
		System.out.println('A' + " + " + 32 + " = " + (char) ('A' + 32));
		System.out.println('a' + " - " + 32 + " = " + (char) ('a' - 32));
		System.out.println("==========================================");
		
//		서식있는 출력
//		printf("출력 서식", 출력할 데이터)
//		출력 서식은 서식 문자를 제외한 나머지 내용은 입력한 그대로 출력된다.
//		출력 서식 문자: d(정수), f(실수), s(문자열), c(문자)
//		출력 서식의 형식: %[-][0][n][.m]서식문자
//		n: 출력할 전체 자리수, 전체 자리수가 지정되면 오른쪽에 맞춰 출력한다.
//		-: 전체 자리수가 지정된 경우 왼쪽에 맞춰 출력한다.
//		0: 전체 자리수가 지정된 경우 남는 자리를 "0"을 채워 출력한다.
//		.m: 소수점 아래 자리수, 잘리는 자리에서 반올림해서 화면에 표시한다.
		
		System.out.println(100);
		System.out.println(1);
		System.out.println(10000);
		
		System.out.printf("%d\n", 100);
		System.out.printf("%d\n", 1);
		System.out.printf("%d\n", 10000);
		
		System.out.printf("%5d\n", 100);
		System.out.printf("%5d\n", 1);
		System.out.printf("%-5d\n", 1);
		System.out.printf("%05d\n", 1);
		System.out.printf("%5d\n", 10000);
		System.out.println("==========================================");
		
		System.out.println("asd");
		System.out.println("a");
		System.out.println("asdfg");
		
		System.out.printf("%s\n", "asd");
		System.out.printf("%s\n", "a");
		System.out.printf("%s\n", "asdfg");
		
		System.out.printf("%5s\n", "asd");
		System.out.printf("%5s\n", "a");
		System.out.printf("%-5s\n", "a");
		System.out.printf("%5s\n", "asdfg");
		
//		문자열 출력시 "0"을 사용하면 FormatFlagsConversionMismatchException이 발생되니 주의한다.
//		System.out.printf("%05s\n", "a"); // 예외 발생
		System.out.println("==========================================");
		
//		출력 서식 문자와 출력할 데이터의 타입이 다르면 IllegalFormatConversionException이 
//		발생되니 주의한다.
//		System.out.printf("%f\n", 100); // 예외 발생
		System.out.printf("%f\n", 100.);
		System.out.printf("%6.2f\n", 65.12);
		System.out.printf("%6.2f\n", 65.1);
		System.out.printf("%6.2f\n", 65.125);
		System.out.printf("%6.0f\n", 59.5);
		System.out.println("==========================================");
		
		System.out.println('A' + 32);
		System.out.printf("%d\n", 'A' + 32);
		System.out.println((char) ('A' + 32));
		System.out.printf("%c\n", 'A' + 32);
		System.out.println("==========================================");
		
		System.out.printf("%d + %d = %d\n", 5, 3, 5 + 3);
		System.out.printf("%d - %d = %d\n", 5, 3, 5 - 3);
		System.out.printf("%d * %d = %d\n", 5, 3, 5 * 3);
		System.out.printf("%d / %d = %d\n", 5, 3, 5 / 3);
		System.out.printf("%d / %d = %f\n", 5, 3, 5 / 3.);
//		출력 서식에 "%" 자체를 출력하려면 "%%"와 같이 입력해야 한다.
		System.out.printf("%d %% %d = %d\n", 5, 3, 5 % 3);
		System.out.println("==========================================");
		
//		출력 서식의 개수보다 데이터가 많으면 남는 데이터는 무시된다.
		System.out.printf("%d + %d \n", 5, 3, 5 + 3);
//		출력 서식의 개수보다 데이터가 적으면 예외가 발생된다.
//		System.out.printf("%d + %d + %d = %d\n", 5, 3, 5 + 3); // 예외 발생
		
	}
	
}















