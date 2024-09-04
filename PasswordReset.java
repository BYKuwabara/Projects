/* ICS 111 Assignment 06
   Brendan Kuwabara, Section 007
   Due October 17, 2022 **/
import java.util.Scanner;//imports the Scanner class into the program

public class Assignment6 {
	static String defaultPassword = "ICS111";//default password "ICS111"
	static String currentPassword = "";//scanner variable for current password: "ICS111"
	static String newPassword = "";//first new password
	static String renewPassword = "";//new password retyped
	static boolean passwords;//variable that calls the method 

	public static boolean passwordChecker (String newPassword, String renewPassword) {//passwordChecker method
		boolean value;//initializes the value variable as a boolean
		value = false;//sets the the boolean return variable to false
		
		if (newPassword.equals(renewPassword)) {
			if (renewPassword.length() >= 6) {
				if (!(renewPassword.contains(" "))) {
					if (renewPassword.contains("!") || renewPassword.contains("?") || renewPassword.contains("$")) {
						value = true;//sets boolean value to true so the main method knows the program can continue
						return value;//true
					}
					else {
						System.out.println("Your password must contain ! or $ or ?.");
						return value;//false
					}
				}
				else {
					System.out.println("Your new password can not have spaces.");
					return value;//false
				}
			}
			else {
				System.out.println("Your new password must contain 6 or more characters.");
				return value;//false
			}
		}
		else {
			System.out.println("Your new passwords do not match.");
			return value;//false
		}
			
			
	}


	public static void main(String[] args) {//main method
		Scanner input = new Scanner(System.in);//creates new scanner 
		
		System.out.print("Type your current password: ");
		currentPassword = input.nextLine();
		if (!(currentPassword.equals(defaultPassword))) {
			System.out.println("Password not recognized");
		}
		else {
			System.out.print("Type a new password: ");
			newPassword = input.nextLine();
			System.out.print("Retype the new password: ");
			renewPassword = input.nextLine();
			
			passwords = passwordChecker(newPassword, renewPassword);//calls the passwordChecker method onto the two variables
			
			if (passwords == true) {//if boolean in passwordChecker variable value is true, it makes passwords true
				System.out.println("Your password was changed to: " + renewPassword);
			}
			else {
				System.out.println("Your password was not changed.");
			}

		}
		input.close();//closes scanner
	}
}
	


