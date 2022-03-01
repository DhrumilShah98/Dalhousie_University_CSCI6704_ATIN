package question_6_crc_programming_question;

import java.util.Scanner;

/**
 * {@code CRCTest} class tests the {@code CRCController} class.
 *
 * @author Dhrumil Amish Shah (B00857606 | dh416386@dal.ca)
 */
public final class CRCTest {

  public static void main(String[] args) {
    final Scanner scanner = new Scanner(System.in);

    System.out.println("Enter your input string M(x): ");
    final String Mx = scanner.nextLine();

    System.out.println("Enter your polynomial string G(x): ");
    final String Gx = scanner.nextLine();

    final CRCController crcController = new CRCController();

    /* Output 1 */
    final CRCSenderModel crcSenderModel = crcController.performSenderSideComputation(Mx, Gx);
    System.out.println("<========== Sender side computations ==========>");
    System.out.println("Mx: " + crcSenderModel.getMx());
    System.out.println("Gx: " + crcSenderModel.getGx());
    System.out.println("MDashX: " + crcSenderModel.getMDashX());
    System.out.println("Qx: " + crcSenderModel.getQx());
    System.out.println("Rx: " + crcSenderModel.getRx());
    System.out.println("Px: " + crcSenderModel.getPx());

    /* Output 2 */
    final CRCReceiverModel crcReceiverModel = crcController.performReceiverSideComputation(crcSenderModel.getPx(), Gx);
    System.out.println("\n<========== Receiver side computations (No error) ==========>");
    System.out.println("Px: " + crcReceiverModel.getPx());
    System.out.println("Gx: " + crcReceiverModel.getGx());
    System.out.println("Qx: " + crcReceiverModel.getQx());
    System.out.println("Rx: " + crcReceiverModel.getRx());
    System.out.println("Message: " + crcReceiverModel.getMessage());

    /* Output 3 */
    final String errorPx = crcController.introduceRandomError(crcSenderModel.getPx());
    final CRCReceiverModel crcReceiverModelErr = crcController.performReceiverSideComputation(errorPx, Gx);
    System.out.println("\n<========== Receiver side computations (Errors) ==========>");
    System.out.println("Px: " + crcReceiverModelErr.getPx());
    System.out.println("Gx: " + crcReceiverModelErr.getGx());
    System.out.println("Qx: " + crcReceiverModelErr.getQx());
    System.out.println("Rx: " + crcReceiverModelErr.getRx());
    System.out.println("Message: " + crcReceiverModelErr.getMessage());
  }
}