
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Random;
import java.util.Scanner;
import java.util.Vector;

// 자 일단 게임 시작 멘트 치고?
// 랜덤한 단어 하나 가져 와가지고 그중에 단어 2개 숨겨야겠네?
// 그다음 입력받고 맞는지 틀린지 판단해서
// 맞으면 단어 채워넣고 아니면 그냥 계속 그대로 출력하고
// 5번 안에 맞추거나 틀리면 y/n 출력
// 이때 5번 틀리면 단어 출력.
// y 누르면 계속 n누르면 stop
// 게임을 종료합니다. 멘트.

// 멘트 치는건 하면 되고
// 랜덤 단어 가져 오는건 class만들거나 메소드 가져오고
// 가져오면 단어 2개 숨기는거에 단어 가져온거 매개변수 받는 메소드 만들고
// 메소드 만들라면 클래스 만들어야겠네?
// 메소드에서 단어 2개 랜덤으로 숨기고.
// 단어 숨긴거 출력하고
// 입력 받으면 비교하는 메소드 에다 보내고
// 또 비교하고 리턴 받아오고
// --------------애반데??????????????????
// --------------너무많찮아ㅏㅏㅏㅏㅏㅏㅏㅏㅏㅏㅏㅏㅏㅏㅏㅏㅏㅏㅏㅏㅏ
// 비교할때 if써서 숨긴거 2개중 하나 있으면 그거 치환하고
// 리턴 돌려주고
// 아니면 그냥 돌려주고
// 메인에서 받으면 그냥 출력시키고
// 다시 반복해서 입력 받고
// 또 비교 하고
// 단어 완성 되면 break시켜?? 맞지 중첩 해야 겠네
// 그럼 비교 하는거 break시키고 나오면 y/n물어봐야겠네
// y누르면 다시 위에 반복문 돌리고
// 근데 실패하는건 어케? 
// 메인에서 하나?
// count변수 하나 만들어서 ++ 시키고 위에서 하면 5에서 멈추면 5번 이잖음.
// count ==5면 break 걸면 되고

//==================================

// 정리 : word() 메소드 실행 하면 아무 영단어 하나 추출.
// 이후 2자리 삐처리 해주고 반복문 안에 넣음.
// 삐처리는 wordvoid()라고 대충 짓고 리턴을 받어.
// 일단 거기까지.

public class test04 {

	static Random rand = new Random();
	static Scanner scanner;

	public static void main(String[] args) {

		System.out.println("지금부터 행맨 게임을 시작합니다.");
		int yn = 0;

		// 단어 하나 받는데 까진 성공 했다.
		String word = run();
		System.out.println(word);
		Blind(word);// 2개 삐처리 성공.. ㅜㅜ
		// 비교까지 끝.
		while (true) {
			System.out.println("다시 하시겠습니까? y/n >> ");
			while (true) {
				String a = scanner.next();
				if (a.equals("y")) {
					String word2 = run();
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

	// 삐처리
	// 화장실 다녀와서 생각난건데 그냥 이 안에서 비교해줘도 되는거잖아?
	public static void Blind(String word) {

		// System.out.println(word); 테스트용
		word = word.toLowerCase();
		scanner = new Scanner(System.in);
		int countwhile1 = 0;// 성공 했을때 반복문 빠져나가는 변수
		int countwhile2 = 0;// 실패 했을때 반복문 빠져나가는 변수
		int countwhile3 = 0;// countwhile2를 셈해주기 위한 변수
		int[] count = new int[2];
		char[] Qwords = new char[word.length()];
		for (int i = 0; i < word.length(); i++)
			Qwords[i] = word.charAt(i);

		for (int i = 0; i < 2; i++) {
			// 2개만 삐처리
			int x = rand.nextInt(word.length());
			if (word.charAt(x) != '-') {
				Qwords[x] = '-';
				count[i] = x;// 0 , 1 순서;
				// System.out.println(x);
			} else
				i--;
		}
		while (true) {
			for (int i = 0; i < word.length(); i++)
				System.out.print(Qwords[i]); // 출력
			System.out.println();
			System.out.println(">> "); // 출력
			String user;
			user = scanner.next(); // 입력 받기
			while (true) { // 예외 처리
				if (user.length() != 1) {
					System.out.println("한글자만 입력해주세요.");
					user = scanner.next();
				} else
					break;
			}
			// user.charAt(0);

			// System.out.println(count[0]);
			// System.out.println(count[1]);
			// for(int i=0;i<2;i++) {
			// System.out.println(Qwords[count[i]]);//;
			// }
			// 비교
			for (int i = 0; i < 2; i++) {// 만약 유저가 입력한 글자와
				// 빈곳중 한곳이랑 일치한다면? 치환
				if (user.charAt(0) == word.charAt(count[i])) {// 0,1순서로 돌겠지
					Qwords[count[i]] = word.charAt(count[i]);
					countwhile1++;
					System.out.println("맞히셨습니다!");
				} // 아니면? 빠져나가.
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

		} // 여기까지 큰while.
	}
}

// 랜덤 단어 가져와서 비교하기. 아 그럼 class를 따로 만들어야겠는데?
// 생성도 여기서 하지.
// 만들어서 여따 저장?
// 그럼 words형태?
// 야그럼 만드는건 메인에서 해야돼?
// 따로 메소드 만들어?
// 만들자
// 그럼 비교 어케할래 원문 어케알고
// ??? 뇌절오네
// 워드 만들었고
// 벡터?? size만큼 랜덤값 하나 추출해서
// 넣으면 되잖아?
// 맞나?
// 야 그럼 비교 어케해?
// word형태 만들어서 매개변수 2개로 하고
// 하나는 원문 하나는 삐처리 해서 2개 보내줘? 리턴?
// 그렇게 할까?
// 비교는 어디서함?
// word.comp() 메소드 하나 만들어?
// 만들고 리턴 해주면 되잖아 미;ㄹㄴ아;ㅓㅣ랑너;ㅏㅣㅁㄹㄴ어;ㅣㅏㄹㄴ

// 일단 word()실행 시키면 단어 하나 값 받고
// compare는 words에서 비교 해주자.

/*
 * public class HangmanGame {
 * 
 * public static void main(String[] args) { Vector<String> wordVector = new
 * Vector<String>(); File wordf = new File("C:\\temp\\words.txt");
 * 
 * try { Scanner scanner = new Scanner(new FileReader(wordf));
 * while(scanner.hasNext()) { String word = scanner.nextLine();
 * wordVector.add(word); }
 * 
 * 
 * } catch (FileNotFoundException e) { // TODO Auto-generated catch block
 * e.printStackTrace(); } } }
 * 
 * class word
 */