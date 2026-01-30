// import java.lang.Math;
import java.lang.System;

class PrimitiveDataTypes {
    public static void main(String args[]) {

        // ================================
        // Java Primitive Data Types
        // ================================

        // ----------- Integer Types -----------
        // byte: 8-bit signed integer (-128 to 127)
        int byteSize = Byte.SIZE;       // 8 bits
        byte byteMin = Byte.MIN_VALUE;  // -128
        byte byteMax = Byte.MAX_VALUE;  //  127

        System.out.println("byte:");
        System.out.println("  Size (bits): " + byteSize);
        System.out.println("  Min Value  : " + byteMin);
        System.out.println("  Max Value  : " + byteMax);
        System.out.println();

        // short: 16-bit signed integer (-32,768 to 32,767)
        int shortSize = Short.SIZE;        // 16 bits
        short shortMin = Short.MIN_VALUE;  // -32768
        short shortMax = Short.MAX_VALUE;  //  32767

        System.out.println("short:");
        System.out.println("  Size (bits): " + shortSize);
        System.out.println("  Min Value  : " + shortMin);
        System.out.println("  Max Value  : " + shortMax);
        System.out.println();

        // int: 32-bit signed integer (-2^31 to 2^31 - 1)
        int intSize = Integer.SIZE;      // 32 bits
        int intMin = Integer.MIN_VALUE;  // -2147483648L
        int intMax = Integer.MAX_VALUE;  //  2147483647L

        System.out.println("int:");
        System.out.println("  Size (bits): " + intSize);
        System.out.println("  Min Value  : " + intMin);
        System.out.println("  Max Value  : " + intMax);
        System.out.println();

        // long: 64-bit signed integer (-2^63 to 2^63 - 1)
        int longSize = Long.SIZE;       // 64 bits
        long longMin = Long.MIN_VALUE;  // -9223372036854775808
        long longMax = Long.MAX_VALUE;  //  9223372036854775807

        System.out.println("long:");
        System.out.println("  Size (bits): " + longSize);
        System.out.println("  Min Value  : " + longMin);
        System.out.println("  Max Value  : " + longMax);
        System.out.println();

        // ----------- Floating-Point Types -----------
        // float: 32-bit IEEE 754 floating point
        int floatSize = Float.SIZE;                   // 32 bits
        float floatMin = Float.MIN_VALUE;             // 1.4E-45F (smallest positive *non-zero* float)
        float floatMax = Float.MAX_VALUE;             // 3.4028235E38F (largest finite float)
        float floatNegInf = Float.NEGATIVE_INFINITY;  // -∞
        float floatPosInf = Float.POSITIVE_INFINITY;  // +∞
        float floatNaN = Float.NaN;                   // Not-a-Number

        System.out.println("float:");
        System.out.println("  Size (bits): " + floatSize);
        System.out.println("  Min Value  : " + floatMin);
        System.out.println("  Max Value  : " + floatMax);
        System.out.println("  Positive Infinity: " + floatPosInf);
        System.out.println("  Negative Infinity: " + floatNegInf);
        System.out.println("  Not A Number     : " + floatNaN);
        System.out.println();

        // double: 64-bit IEEE 754 floating point
        int doubleSize = Double.SIZE;                    // 64 bits
        double doubleMin = Double.MIN_VALUE;             // 4.9E-324 (smallest positive *non-zero* double)
        double doubleMax = Double.MAX_VALUE;             // 1.7976931348623157E308 (largest finite double)
        double doubleNegInf = Double.NEGATIVE_INFINITY;  // -∞
        double doublePosInf = Double.POSITIVE_INFINITY;  // +∞
        double doubleNaN = Double.NaN;                   // Not-a-Number

        System.out.println("double:");
        System.out.println("  Size (bits): " + doubleSize);
        System.out.println("  Min Value  : " + doubleMin);
        System.out.println("  Max Value  : " + doubleMax);
        System.out.println("  Positive Infinity: " + doublePosInf);
        System.out.println("  Negative Infinity: " + doubleNegInf);
        System.out.println("  Not A Number     : " + doubleNaN);
        System.out.println();

        // ----------- Character Type -----------
        // char: 16-bit unsigned UTF-16 code unit (0 to 65535)
        int charSize = Character.SIZE;       // 16 bits
        char charMin = Character.MIN_VALUE;  // '\u0000' (NUL)
        char charMax = Character.MAX_VALUE;  // '\uffff' (Unicode code point 65535, reserved noncharacter)

        System.out.println("char:");
        System.out.println("  Size (bits): " + charSize);System.out.println("  Min Value  : " + charMin + " (NUL, no visible glyph) UNICODE: " + (int) charMin);
        System.out.println("  Max Value  : " + charMax + " (noncharacter, may show tofu □) UNICODE: " + (int) charMax);

        System.out.println();

        // ----------- Boolean Type -----------
        // boolean: logical type with values true/false
        // • Logically 1 bit, but JVM storage varies.
        // • In arrays: 1 byte per element.
        // • As locals: usually stored as int (32 bits) in registers.
        // • As fields: may use 1 byte, but aligned/padded (often 4 bytes).
        // • Wrapper Boolean adds object overhead from 12 to 16 bytes based on the JVM 32 bit JVM vs 64 bit JVM
        boolean boolTrue = Boolean.TRUE;    // true
        boolean boolFalse = Boolean.FALSE;  // false

        System.out.println("boolean:");
        System.out.println("  Possible Values: " + boolTrue + ", " + boolFalse);
    }
}
