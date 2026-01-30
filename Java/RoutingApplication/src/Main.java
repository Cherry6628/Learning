import java.util.Scanner;

import auth.AuthService;
import auth.User;
import db.DB;
import log.LogService;
import validate.Validator;
public class Main {

	public final static Scanner sc = new Scanner(System.in);
	
	private static String padText(String s, int l){
        return s+(" ".repeat(Math.max(l-s.length(), 0)));
    }
    public static int displayOptions(String title, String[] options, int space, boolean clearScreen){
        int additionalGap = 2;
        int maxDigitSize;
        try{maxDigitSize = (int)Math.floor(Math.log10(options.length-1))+1;}
        catch(Exception e){maxDigitSize=1;}
        int maxLength = title.length();
        for (String s: options){
            int l=s.length();
            if(maxLength<l)maxLength=l+additionalGap+2+maxDigitSize;
        }
        float base = (maxLength-title.length()+maxDigitSize+2)/2.0f;
        int start = (int)Math.floor(base)+1;
        int end = (int)Math.ceil(base)+1;
        // System.out.println(start+" "+end);
        if(clearScreen)System.out.println("\033[H\033[2J\n");
        System.out.println("#".repeat(start)+(" ".repeat(space))+title+(" ".repeat(space))+("#".repeat(end)));
        System.out.println("#"+(" ".repeat(space+maxDigitSize+2+maxLength+space))+"#");
        
        // for (String option: options){
        for (int i=0; i<options.length; i++){
            String option = options[i];
            System.out.println("#"+(" ".repeat(space))+padText(""+(i+1), maxDigitSize)+". "+(padText(option, maxLength))+(" ".repeat(space))+"#");
        }
        System.out.println("#"+(" ".repeat(space+maxDigitSize+2+maxLength+space))+"#");
        System.out.println("#".repeat(maxLength+2+(2*space)+maxDigitSize+2));
        System.out.println("");
        int choice;
        do{
            System.out.print("Enter Your Choice >> ");
            try{
                choice = sc.nextInt();
            } catch(Exception e){
                choice=-1;
            } finally{
                sc.nextLine();
            }
            if (choice>0 && choice <= options.length)return choice;
            
            System.out.println("Invalid Option. Try Again.\n");
        } while(true);

    }

	public static void main(String[] args) throws Exception {
		LogService.debug("Log Service Loaded");
		try {
			AuthService auth = new AuthService();
			LogService.debug("Auth Model Loaded");
			main: while (true) {
				int c = displayOptions("Accounts", new String[] {"Signup","Login","Exit"}, 2, true);
				
				
				if (c==1) {
					String u,p,e;
					int chance=3;
					boolean notYetWarned=true;
					do {
						System.out.print("Username: ");
						u = sc.nextLine().strip();
						if(Validator.isValidUname(u)) {
							break;
						}
						System.out.print("Username requirements not met");
						if (notYetWarned){
							notYetWarned=false;
							System.out.println(".  Username must match the following criteria");
							System.out.println("\tMinimum 6 Characters.\n\tMaximum 100 Characters.\n\tCan have Alphanumeric characters, Underscores and Dots\n\tFirst Letter cannot be Numeric or a Dot.");
						} else 
							System.out.println(" yet.");
						chance--;
						if(chance==0) {
							System.out.println("Navigating to Accounts Tab.");
							continue main;
						}
						System.out.println(chance+" Chance(s) Left.");
					} while(chance>=0);
					
	
					
					notYetWarned=true;
					chance=3;
					do {
						System.out.print("Password: ");
						p = sc.nextLine();
						if(Validator.isValidPwd(p)) {
							System.out.print("Make sure you remember the password.  You will not be able to recover your account if you lost your password.\nRepeat Password: ");
							if(!sc.nextLine().equals(p)) {
								System.out.println("Password doesn't match. Navigating to Main Menu.");
								continue main;
							}
							break;
						}
						System.out.print("Password requirements not met");
						if (notYetWarned){
							notYetWarned=false;
							System.out.println(".  Password must match the following criteria: ");
							System.out.println("\tMinimum 6	Characters\n\tMaximum 255 Characters\n\tMust have atleast 1 lowercase letter, 1 uppercase letter, 1 symbol, 1 number"); 
						} else System.out.println(" yet.");
						chance--;
						if(chance==0) {
							System.out.println("Navigating to Accounts Tab.");
							continue main;
						}
						System.out.println(chance+" more Chance(s) Left.");
					} while(chance>=0);
	
					notYetWarned=true;
					chance=3;
					do {
						System.out.print("Email: ");
						e = sc.nextLine().strip();
						if(Validator.isValidEmail(e)) {
							break;
						}
						System.out.println("Provided email is invalid. ");
						chance--;
						if(chance==0) {
							System.out.println("Navigating to Accounts Tab.");
							continue main;
						}
						System.out.println(chance+" more Chance(s) Left.");
					} while(chance>=0);
					
					
					
					
					boolean signedup = auth.signup(u, p, e);
					if(signedup) {
						System.out.println("Signup successful");
						LogService.info("New Account Created");
					} else {
						System.out.println("User exists");
					}
				}
	
				else if (c==2) {
	
					System.out.print("Username: ");
					String u = sc.nextLine().strip();
	
					System.out.print("Password: ");
					String p = sc.nextLine();
	
					User user = auth.login(u, p);
					if (user != null) {
						System.out.println("User "+user.uname()+" has logged in");
						LogService.info(String.format("User [%s] logged in", user.userId()));
						
						new TravelApp(user).menu();
						LogService.info(String.format("User [%s] logged out", user.userId()));
					}
					else
						System.out.println("Invalid login");
				}
				else if (c==3)
					break;
	
			}
		} catch(Exception e) {
			LogService.error(e.getMessage());
		}
		finally {
			sc.close();
			LogService.debug("Closing Database Connection");
			DB.conn().close();
			LogService.debug("Closing Logger");
			LogService.closeLogger();
		}
	}

}
