import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import java.util.Vector;


public class HangmanGame2 {
	
	static Random rand = new Random();
	static Scanner scanner;
	
	// Main
	public static void main(String[] args) {

		System.out.println("지금부터 행맨 게임을 시작합니다.");
		int yn = 0;	// 응답용 변수
		String word = run();	// run함수로 단어를 선택받음.
		System.out.println(word); //test용
		Blind(word);
		
		
		while (true) {
			System.out.println("다시 하시겠습니까? y/n >> ");
			while (true) {
				String a = scanner.next();
				if (a.equals("y")) {
					String word2 = run();
					System.out.println(word2);
					Blind(word2);
					break;
				} else if (a.equals("n")) {
					System.out.println("프로그램을 종료합니다.");
					yn++;
					break;
				} else
					System.out.println("잘못입력하셨습니다.");
			}
			if (yn == 1)
				break;
		}

	}
	
	// run함수
	public static String run() {
		Vector<String> wordVector = new Vector<String>();
		File wordf = new File("C:\\temp\\words.txt");
		try {
			scanner = new Scanner(new FileReader(wordf));
			while (scanner.hasNext()) {
				String word = scanner.nextLine();
				wordVector.add(word);
			} // 여기까지 단어 하나씩 넣어줬어.
				// 이제 사이즈로 랜덤 하나 추출해.
			return wordVector.get(rand.nextInt(wordVector.size()));

		} catch (FileNotFoundException e) {
			return "오류";
		}
	}
	
	
	public static void Blind(String word) {

		// System.out.println(word); 테스트용
		word = word.toLowerCase();
		scanner = new Scanner(System.in);
		int countwhile1 = 0;// 성공 했을때 반복문 빠져나가는 변수
		int countwhile2 = 0;// 실패 했을때 반복문 빠져나가는 변수
		int countwhile3 = 0;// countwhile2를 셈해주기 위한 변수
		ArrayList<Integer> count = new ArrayList<Integer>();
		//int[] count = new int[2];
		char[] Qwords = new char[word.length()];
		for (int i = 0; i < word.length(); i++)
			Qwords[i] = word.charAt(i);

		for (int i = 0; i < 2; i++) {
			// 2개만 삐처리
			int x = rand.nextInt(word.length());
			if (word.charAt(x) != '-') {
				Qwords[x] = '-';
				count.add(x);
				
			} else
				i--;
		}
		while (true) {
			for (int i = 0; i < word.length(); i++)
				System.out.print(Qwords[i]); // 출력
			System.out.println();
			System.out.println(">> "); // 출력
			String user;
			
			user = scanner.next(); // 유저에게 입력 받기
			while (true) { // 예외 처리
				if (user.length() != 1) {
					System.out.println("한글자만 입력해주세요.");
					user = scanner.next();
				} else
					break;
			}
			
			// 비교
			for (int i = 0; i < count.size(); i++) {// 만약 유저가 입력한 글자와
				// 빈곳중 한곳이랑 일치한다면? 치환
				if (user.charAt(0) == word.charAt(count.get(i))) {// 0,1순서로 돌겠지
					Qwords[count.get(i)] = word.charAt(count.get(i));
					count.remove(i);
					countwhile1++;
					System.out.println("맞히셨습니다!");
				}
			}
			if (countwhile3 == countwhile1) {
				countwhile2++;
				System.out.println("틀렸습니다.");
			} else
				countwhile3 = countwhile1;

			if (countwhile1 == 2) {
				System.out.println("성공하셨습니다!");
				break;
			} else if (countwhile2 == 5) {
				System.out.println("5번 실패하셨습니다.");
				break;
			}

		}
	}
}