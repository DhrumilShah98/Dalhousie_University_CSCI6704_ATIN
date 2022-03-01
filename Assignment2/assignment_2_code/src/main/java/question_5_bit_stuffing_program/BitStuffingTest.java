package question_5_bit_stuffing_program;

import java.util.Scanner;

/**
 * {@code BitStuffingTest} class tests the {@code BitStuffingController} class.
 *
 * @author Dhrumil Amish Shah (B00857606 | dh416386@dal.ca)
 */
public final class BitStuffingTest {

  public static void main(String[] args) {
    final Scanner scanner = new Scanner(System.in);
    System.out.println("\nEnter hexadecimal string.");
    final String hexadecimalInput = scanner.nextLine();
    try {
      final BitStuffingController bitStuffingController = new BitStuffingController();
      System.out.println("\n<========== Output ==========>\n");
      bitStuffingController.executeBitStuffing(hexadecimalInput);
    } catch (final BitStuffingException e) {
      System.err.println(e.getMessage());
    }
  }
}
