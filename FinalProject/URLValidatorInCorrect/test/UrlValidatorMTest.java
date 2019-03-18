import java.util.Scanner;

public class UrlValidatorMTest {

    public static void main(String[] args) {
    	var v = new UrlValidator();
    	Scanner scan = new Scanner(System.in);
    	String str;
    	do {
        	System.out.println("Enter a URL (Or q to quit): ");
        	str = scan.nextLine();
        	if(v.isValid(str))
        		System.out.println("PASS");
        	else
        		System.out.println("FAIL");
    	} while(!str.equals("q"));
    }
}