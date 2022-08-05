public class test03 {
    public static void main(String[] args) {

        String toolName="JDK";

        //문자열과 다른 데이터타입의 + 결과는 문자열.
		System.out.println(toolName+1+0.8);
        
		//"JDK"+1 => "JDK1"+0.8 => "JDK10.8"
		System.out.println("자바"+(8+0.8));
		System.out.println(toolName+(1+0.8));// 1.8=>"JDK"+1.8 =>"JDK1.8"
		System.out.println("("+3+","+5+")");
		// "(3"=> "(3,"=> "(3,5" => "(3,5)"

    }
}
