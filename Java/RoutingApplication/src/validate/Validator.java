package validate;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.ResolverStyle;

public class Validator {
	public static boolean isNumeric(char c) {return 47<c && c<58;}
	public static boolean isAlphabetic(char c) {return (64<c && c<91) || (96<c && c<123);}
	public static boolean isAlphaNumeric(char c){return isNumeric(c) || isAlphabetic(c);}
    public static boolean isValidEmail(String mailId){
        String[] parts = mailId.split("@");
        if (parts.length!=2) return false;
        String uname = parts[0];
        String url = parts[1];
        if((!isAlphaNumeric(uname.charAt(0)))||(uname.charAt(uname.length()-1)=='.'))return false;
        if(uname.contains(".."))return false;
        String[] urlParts=url.split("\\.");
        if(urlParts.length<=1)return false;
        for (String part : urlParts){
            if (part.isEmpty()) return false;
            if (part.startsWith("-") || part.endsWith("-")) return false;
            for (char ch: part.toCharArray()){
                if (! (isAlphaNumeric(ch)||ch=='-'))return false;
            }
        }
        String tld=urlParts[urlParts.length-1];
        if(tld.length()<2)return false;
        for(String part:urlParts)if(part.length()<1||part.length()>63)return false;
        if((!isAlphaNumeric(url.charAt(0))) || (!isAlphaNumeric(url.charAt(url.length()-1))))return false;
        return true;
    }
    public static boolean isValidUname(String uname) {
    	if(uname.length()<6||uname.length()>100||isNumeric(uname.charAt(0))||uname.charAt(0)=='.'||uname.isBlank())return false;
    	for (char ch:uname.toCharArray()) {
    		if(isAlphaNumeric(ch)||ch=='_'||ch=='.')continue;
    		return false;
    	}
    	return true;
    }
    public static boolean isValidPwd(String pwd) {
    	if(pwd.length()<6||pwd.length()>255||pwd.isBlank())return false;
    	return pwd.matches("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[~!@#$%^&*()_+\\-={}\\[\\]|:;\"'<>,.?\\/]).+$");
    }
    public static LocalDateTime parseDate(String input) {
        if (input == null || input.isBlank())
            return null;

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("uuuu-MM-dd HH:mm").withResolverStyle(ResolverStyle.STRICT);;

        try {
        	 LocalDateTime ldt = LocalDateTime.parse(input, formatter);

             if (ldt.isBefore(LocalDateTime.now()))
                 return null;

             return ldt;
        } catch (DateTimeParseException e) {
            return null;
        }
    }

}
