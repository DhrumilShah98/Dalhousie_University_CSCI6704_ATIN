package question_5_bit_stuffing_program;

import java.util.HashMap;
import java.util.Map;

/**
 * {@code BitStuffingController} class contains the core program logic.
 *
 * @author Dhrumil Amish Shah (B00857606 | dh416386@dal.ca)
 */
public final class BitStuffingController {
  // String of valid hexadecimal characters.
  private static final String VALID_HEXADECIMAL_CHARACTERS;

  // Hexadecimal to binary mapping.
  private static final Map<Character, String> HEXADECIMAL_BINARY_MAP;

  // Binary to hexadecimal mapping.
  private static final Map<String, Character> BINARY_TO_HEXADECIMAL_MAP;

  // Bit stuffing delimiter.
  public static final int BIT_STUFFING_DELIMITER;

  static {
    VALID_HEXADECIMAL_CHARACTERS = "0123456789ABCDEF";

    HEXADECIMAL_BINARY_MAP = new HashMap<>();
    HEXADECIMAL_BINARY_MAP.put('0', "0000");
    HEXADECIMAL_BINARY_MAP.put('1', "0001");
    HEXADECIMAL_BINARY_MAP.put('2', "0010");
    HEXADECIMAL_BINARY_MAP.put('3', "0011");
    HEXADECIMAL_BINARY_MAP.put('4', "0100");
    HEXADECIMAL_BINARY_MAP.put('5', "0101");
    HEXADECIMAL_BINARY_MAP.put('6', "0110");
    HEXADECIMAL_BINARY_MAP.put('7', "0111");
    HEXADECIMAL_BINARY_MAP.put('8', "1000");
    HEXADECIMAL_BINARY_MAP.put('9', "1001");
    HEXADECIMAL_BINARY_MAP.put('A', "1010");
    HEXADECIMAL_BINARY_MAP.put('B', "1011");
    HEXADECIMAL_BINARY_MAP.put('C', "1100");
    HEXADECIMAL_BINARY_MAP.put('D', "1101");
    HEXADECIMAL_BINARY_MAP.put('E', "1110");
    HEXADECIMAL_BINARY_MAP.put('F', "1111");

    BINARY_TO_HEXADECIMAL_MAP = new HashMap<>();
    BINARY_TO_HEXADECIMAL_MAP.put("0000", '0');
    BINARY_TO_HEXADECIMAL_MAP.put("0001", '1');
    BINARY_TO_HEXADECIMAL_MAP.put("0010", '2');
    BINARY_TO_HEXADECIMAL_MAP.put("0011", '3');
    BINARY_TO_HEXADECIMAL_MAP.put("0100", '4');
    BINARY_TO_HEXADECIMAL_MAP.put("0101", '5');
    BINARY_TO_HEXADECIMAL_MAP.put("0110", '6');
    BINARY_TO_HEXADECIMAL_MAP.put("0111", '7');
    BINARY_TO_HEXADECIMAL_MAP.put("1000", '8');
    BINARY_TO_HEXADECIMAL_MAP.put("1001", '9');
    BINARY_TO_HEXADECIMAL_MAP.put("1010", 'A');
    BINARY_TO_HEXADECIMAL_MAP.put("1011", 'B');
    BINARY_TO_HEXADECIMAL_MAP.put("1100", 'C');
    BINARY_TO_HEXADECIMAL_MAP.put("1101", 'D');
    BINARY_TO_HEXADECIMAL_MAP.put("1110", 'E');
    BINARY_TO_HEXADECIMAL_MAP.put("1111", 'F');

    BIT_STUFFING_DELIMITER = 5;
  }

  /**
   * Converts binary input to hexadecimal output.
   *
   * @param binaryInputUnStuffed hexadecimal input to this program (e.g., 1010101111101111111111111111).
   *
   * @return binary output (e.g., ABEFFFF).
   */
  private String convertBinaryToHexadecimal(final String binaryInputUnStuffed) {
    final StringBuilder hexadecimalOutput = new StringBuilder();
    for (int i = 0; i < binaryInputUnStuffed.length(); i = i + 4) {
      final int j = i + 4;
      final String fourBitBinary = binaryInputUnStuffed.substring(i, j);
      hexadecimalOutput.append(BINARY_TO_HEXADECIMAL_MAP.get(fourBitBinary));
    }
    return hexadecimalOutput.toString();
  }

  /**
   * Performs bit unstuffing on the binary stuffed input.
   *
   * @param binaryInputStuffed binary stuffed input to this program (e.g., 10101011111001111101111101111101).
   *
   * @return Binary output (e.g., 1010101111101111111111111111).
   */
  private String performBitUnStuffing(final String binaryInputStuffed) {
    final StringBuilder binaryInputUnStuffedSB = new StringBuilder();
    int consecutive1sOccurrence = 0;
    for (int i = 0; i < binaryInputStuffed.length(); ++i) {
      final char currentBinary = binaryInputStuffed.charAt(i);
      if (currentBinary == '1') {
        consecutive1sOccurrence++;
        if (consecutive1sOccurrence == BIT_STUFFING_DELIMITER) {
          binaryInputUnStuffedSB.append(currentBinary);
          ++i; // Skip the next bit
          consecutive1sOccurrence = 0;
        } else {
          binaryInputUnStuffedSB.append(currentBinary);
        }
      } else {
        binaryInputUnStuffedSB.append(currentBinary);
        consecutive1sOccurrence = 0;
      }
    }

    /* Output 4 */
    System.out.println("After bit unstuffing: " + binaryInputUnStuffedSB.toString());

    return binaryInputUnStuffedSB.toString();
  }

  /**
   * Performs bit stuffing on the binary input.
   *
   * @param binaryInput binary input to this program (e.g., 1010101111101111111111111111).
   *
   * @return Stuffed binary output (e.g., 10101011111001111101111101111101).
   */
  private String performBitStuffing(final String binaryInput) {
    final StringBuilder binaryInputStuffedSB = new StringBuilder();
    int consecutive1sOccurrence = 0;
    for (int i = 0; i < binaryInput.length(); ++i) {
      final char currentBinary = binaryInput.charAt(i);
      if (currentBinary == '1') {
        consecutive1sOccurrence++;
        if (consecutive1sOccurrence == BIT_STUFFING_DELIMITER) {
          binaryInputStuffedSB.append(currentBinary).append(0);
          consecutive1sOccurrence = 0;
        } else {
          binaryInputStuffedSB.append(currentBinary);
        }
      } else {
        binaryInputStuffedSB.append(currentBinary);
        consecutive1sOccurrence = 0;
      }
    }

    /* Output 3 */
    System.out.println("After bit stuffing: " + binaryInputStuffedSB.toString());

    return binaryInputStuffedSB.toString();
  }

  /**
   * Converts hexadecimal input to binary output.
   *
   * @param hexadecimalInput hexadecimal input to this program (e.g., ABEFFFF).
   *
   * @return Binary output (e.g., 1010101111101111111111111111).
   */
  private String convertHexadecimalInputToBinary(final String hexadecimalInput) {
    final String hexadecimalInputCaps = hexadecimalInput.toUpperCase();
    final StringBuilder hexadecimalToBinarySB = new StringBuilder();
    for (int i = 0; i < hexadecimalInputCaps.length(); ++i) {
      hexadecimalToBinarySB.append(HEXADECIMAL_BINARY_MAP.get(hexadecimalInputCaps.charAt(i)));
    }

    /* Output 2 */
    System.out.println("Conversion to binary: " + hexadecimalToBinarySB.toString());

    return hexadecimalToBinarySB.toString();
  }

  /**
   * Validates {@code hexadecimalInput} parameter.
   *
   * @param hexadecimalInput hexadecimal input to this program (e.g., ABEFFFF).
   *
   * @throws BitStuffingException if input is not valid.
   */
  private void validateHexString(final String hexadecimalInput) throws BitStuffingException {
    final String hexadecimalInputCaps = hexadecimalInput.toUpperCase();
    for (int i = 0; i < hexadecimalInputCaps.length(); ++i) {
      final char currentHexadecimalInputChar = hexadecimalInputCaps.charAt(i);
      if (VALID_HEXADECIMAL_CHARACTERS.indexOf(currentHexadecimalInputChar) == -1) {
        throw new BitStuffingException("Invalid hexadecimal string!");
      }
    }
  }

  /**
   * Executes this bit stuffing program.
   *
   * @param hexadecimalInput hexadecimal input to this program (e.g., ABEFFFF).
   *
   * @throws BitStuffingException .
   */
  public void executeBitStuffing(final String hexadecimalInput) throws BitStuffingException {
    validateHexString(hexadecimalInput);

    /* Output 1 */
    System.out.println("Input: " + hexadecimalInput.toUpperCase());

    final String hexadecimalToBinary = convertHexadecimalInputToBinary(hexadecimalInput);
    final String binaryInputStuffed = performBitStuffing(hexadecimalToBinary);
    final String binaryInputUnStuffed = performBitUnStuffing(binaryInputStuffed);
    final String hexadecimalOutput = convertBinaryToHexadecimal(binaryInputUnStuffed);

    /* Output 5 */
    System.out.println("Output: " + hexadecimalOutput.toUpperCase());
  }
}