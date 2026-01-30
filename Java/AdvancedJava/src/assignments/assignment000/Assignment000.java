package assignments.assignment000;

public class Assignment000 {
	public static void main(String[] args) {
		System.out.println("Byte: ");
		{
	        Byte byteObj = Byte.valueOf((byte) 5);
	        byte byteVal = byteObj.byteValue();                 // 5
	        byte parsedByte = Byte.parseByte("10");             // 10
	        String byteStr = Byte.toString((byte) 15);          // 15
	
	        System.out.println(byteVal);       // 5
	        System.out.println(parsedByte);    // 10
	        System.out.println(byteStr);       // 15
		}
		
		System.out.println("\n\nShort: ");
		{	
	        Short shortObj = Short.valueOf((short) 10);
	        short shortVal = shortObj.shortValue();             // 10
	        short parsedShort = Short.parseShort("25");         // 25
	        String shortStr = Short.toString((short) 30);       // 30
	
	        System.out.println(shortVal);      // 10
	        System.out.println(parsedShort);   // 25
	        System.out.println(shortStr);      // 30
		}
		
		System.out.println("\n\nInteger: ");
		{	
	        Integer intObj = Integer.valueOf(42);
	        int intVal = intObj.intValue();                      // 42
	        int parsedInt = Integer.parseInt("123");             // 123
	        String intStr = Integer.toString(456);               // 456
	        int compareInt = Integer.compare(5, 10);             // -1
	        int maxInt = Integer.max(7, 12);                     // 12
	        int minInt = Integer.min(7, 12);                     // 7
	
	        System.out.println(intVal);      // 42
	        System.out.println(parsedInt);   // 123
	        System.out.println(intStr);      // 456
	        System.out.println(compareInt);  // -1
	        System.out.println(maxInt);      // 12
	        System.out.println(minInt);      // 7
		}
		
		System.out.println("\n\nLong: ");
		{	
	        Long longObj = Long.valueOf(5000L);
	        long longVal = longObj.longValue();                  // 5000
	        long parsedLong = Long.parseLong("123456");          // 123456
	        String longStr = Long.toString(9999L);               // 9999
	        long maxLong = Long.max(100L, 200L);                 // 200
	        long minLong = Long.min(100L, 200L);                 // 100
	
	        System.out.println(longVal);       // 5000
	        System.out.println(parsedLong);    // 123456
	        System.out.println(longStr);       // 9999
	        System.out.println(maxLong);       // 200
	        System.out.println(minLong);       // 100
		}
		
		System.out.println("\n\nFloat: ");
		{	
	        Float floatObj = Float.valueOf(1.5f);
	        float floatVal = floatObj.floatValue();             // 1.5
	        float parsedFloat = Float.parseFloat("2.25");       // 2.25
	        String floatStr = Float.toString(3.75f);            // 3.75
	        int compareFloat = Float.compare(1.2f, 2.3f);       // -1
	
	        System.out.println(floatVal);        // 1.5
	        System.out.println(parsedFloat);     // 2.25
	        System.out.println(floatStr);        // 3.75
	        System.out.println(compareFloat);    // -1
		}
		
		System.out.println("\n\nDouble: ");
		{	
	        Double doubleObj = Double.valueOf(3.14);
	        double doubleVal = doubleObj.doubleValue();          // 3.14
	        double parsedDouble = Double.parseDouble("2.718");   // 2.718
	        String doubleStr = Double.toString(5.55);            // 5.55
	        int compareDouble = Double.compare(1.5, 1.2);        // 1
	
	        System.out.println(doubleVal);        // 3.14
	        System.out.println(parsedDouble);     // 2.718
	        System.out.println(doubleStr);        // 5.55
	        System.out.println(compareDouble);    // 1
		}
		
		System.out.println("\n\nBoolean: ");
		{	
	        Boolean boolObj = Boolean.valueOf(true);
	        boolean boolVal = boolObj.booleanValue();             // true
	        boolean parsedBool = Boolean.parseBoolean("false");   // false
	        String boolStr = Boolean.toString(true);              // true
	
	        System.out.println(boolVal);      // true
	        System.out.println(parsedBool);   // false
	        System.out.println(boolStr);      // true
		}
		
		System.out.println("\n\nCharacter: ");
		{	
	        Character charObj = Character.valueOf('G');
	        char charVal = charObj.charValue();                 // G
	        boolean isDigit = Character.isDigit('7');           // true
	        boolean isLetter = Character.isLetter('Z');         // true
	        char upper = Character.toUpperCase('m');            // M
	        char lower = Character.toLowerCase('X');            // x
	
	        System.out.println(charVal);     // G
	        System.out.println(isDigit);     // true
	        System.out.println(isLetter);    // true
	        System.out.println(upper);       // M
	        System.out.println(lower);       // x
		}
    }
}