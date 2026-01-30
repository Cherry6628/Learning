package assignments.assignment007.colorCodeSaving;
import java.util.Scanner;
import java.util.HashSet;
import java.util.Objects;

class Color{
	private int code;
	private String color;
	int code() {return code;}
	String color() {return color;}
	Color(int code, String color) throws Exception{
		
		if (code<0x000000 || code>0xffffff)throw new Exception("Invalid Color Code");
		if (color.isBlank())throw new Exception("Invalid Color Name");
		this.code=code;
		this.color=color;
	}
	Color(String code, String color) throws Exception{
		this(Integer.parseInt(code, 16), color);
	}
	public String toString() {
		return color+"("+Integer.toHexString(code)+")";
	}
	public int hashCode(){return Objects.hash(this.code);}
	public boolean equals(Object c){return code()==((Color)c).code();}
}

class ColorPalette{
	private HashSet<Color> colors = new HashSet<>();
	boolean addColor(Color c){
		try {
			if(c==null)return false;
			colors.add(c);
			return true;		
		} catch(Exception e) {
			return false;
		}
	}
	public String toString(){return "ColorPalette#"+colors.toString()+"";}
	
	
}

public class Main {
	public static void main(String[]args) {
		ColorPalette cp = new ColorPalette();
		Scanner sc = new Scanner(System.in);
		while(true)
			try{
				System.out.println("\n\nCurrent Color Palette: "+cp+"\n[Type Exit to exit]");
				
				System.out.print("Enter the Color Code: ");
				String code=sc.next().strip();
				if(code.equalsIgnoreCase("exit"))break;
				sc.nextLine();
				
				
				System.out.print("Enter the Color Name: ");
				String color = sc.next().strip();
				if(color.equalsIgnoreCase("exit"))break;
				sc.nextLine();
				
				cp.addColor(new Color(code, color));
				
			} catch(Exception e) {
				System.out.println("Error: "+e.getMessage());
				
			}
		System.out.println("Final Color Palette: "+cp);
		System.out.println("Terminated...");
		sc.close();
	}
}
