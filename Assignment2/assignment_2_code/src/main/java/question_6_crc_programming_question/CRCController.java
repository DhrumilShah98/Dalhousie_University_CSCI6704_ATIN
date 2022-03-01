package question_6_crc_programming_question;

import java.util.Random;

/**
 * {@code CRCController} class contains the core program logic.
 *
 * @author Dhrumil Amish Shah (B00857606 | dh416386@dal.ca)
 */
public final class CRCController {
  private static final Character ONE_BIT = '1';
  private static final Character ZERO_BIT = '0';

  /**
   * Performs sender side computations.
   *
   * @param Mx Input message.
   * @param Gx Input polynomial.
   *
   * @return {@code CRCSenderModel} that contains the final answer.
   */
  public CRCSenderModel performSenderSideComputation(final String Mx, final String Gx) {
    final StringBuilder extendedZerosSB = new StringBuilder();
    for (int i = 1; i < Gx.length(); ++i) {
      extendedZerosSB.append(ZERO_BIT);
    }
    final String MDashX = Mx + extendedZerosSB.toString();

    final StringBuilder zerosSB = new StringBuilder();
    for (int i = 0; i < Gx.length(); ++i) {
      zerosSB.append(ZERO_BIT);
    }
    final String zerosString = zerosSB.toString();

    final StringBuilder Qx = new StringBuilder();

    String section = MDashX.substring(0, Gx.length());
    Qx.append(ONE_BIT);

    final StringBuilder intermediateResultSB = new StringBuilder();
    for (int x = 0; x < Gx.length(); ++x) {
      intermediateResultSB.append((section.charAt(x) ^ Gx.charAt(x)));
    }

    for (int i = section.length(); i < MDashX.length(); ++i) {
      intermediateResultSB.replace(0, 1, "");
      intermediateResultSB.append(MDashX.charAt(i));

      final char firstBit = intermediateResultSB.toString().charAt(0);
      if (firstBit == '1') {
        Qx.append(ONE_BIT);
        final String intermediateResult = intermediateResultSB.toString();
        intermediateResultSB.setLength(0);
        for (int x = 0; x < Gx.length(); ++x) {
          intermediateResultSB.append((intermediateResult.charAt(x) ^ Gx.charAt(x)));
        }
      } else {
        Qx.append(ZERO_BIT);
        final String intermediateResult = intermediateResultSB.toString();
        intermediateResultSB.setLength(0);
        for (int x = 0; x < Gx.length(); ++x) {
          intermediateResultSB.append((intermediateResult.charAt(x) ^ zerosString.charAt(x)));
        }
      }
    }

    intermediateResultSB.replace(0, 1, "");
    final String Rx = intermediateResultSB.toString();

    final String Px = Mx + Rx;

    return new CRCSenderModel(Mx, Gx, MDashX, Qx.toString(), Rx, Px);
  }

  /**
   * Performs receiver side computations.
   *
   * @param Px Input message sent to receiver.
   * @param Gx Input polynomial.
   *
   * @return {@code CRCReceiverModel} that contains the final answer.
   */
  public CRCReceiverModel performReceiverSideComputation(final String Px, final String Gx) {
    final StringBuilder zerosSB = new StringBuilder();
    for (int i = 0; i < Gx.length(); ++i) {
      zerosSB.append(ZERO_BIT);
    }
    final String zerosString = zerosSB.toString();

    final StringBuilder Qx = new StringBuilder();

    String section = Px.substring(0, Gx.length());
    Qx.append(ONE_BIT);

    final StringBuilder intermediateResultSB = new StringBuilder();
    for (int x = 0; x < Gx.length(); ++x) {
      intermediateResultSB.append((section.charAt(x) ^ Gx.charAt(x)));
    }

    for (int i = section.length(); i < Px.length(); ++i) {
      intermediateResultSB.replace(0, 1, "");
      intermediateResultSB.append(Px.charAt(i));

      final char firstBit = intermediateResultSB.toString().charAt(0);
      if (firstBit == '1') {
        Qx.append(ONE_BIT);
        final String intermediateResult = intermediateResultSB.toString();
        intermediateResultSB.setLength(0);
        for (int x = 0; x < Gx.length(); ++x) {
          intermediateResultSB.append((intermediateResult.charAt(x) ^ Gx.charAt(x)));
        }
      } else {
        Qx.append(ZERO_BIT);
        final String intermediateResult = intermediateResultSB.toString();
        intermediateResultSB.setLength(0);
        for (int x = 0; x < Gx.length(); ++x) {
          intermediateResultSB.append((intermediateResult.charAt(x) ^ zerosString.charAt(x)));
        }
      }
    }

    intermediateResultSB.replace(0, 1, "");
    final String Rx = intermediateResultSB.toString();

    String message;
    if (Long.parseLong(Rx) == 0) {
      message = "Message is error-free";
    } else {
      message = "Message contains error";
    }

    return new CRCReceiverModel(Px, Gx, Qx.toString(), Rx, message);
  }

  /**
   * Introduces random error in {@code Px}. It can be burst error or single bit error.
   *
   * @param Px Input message sent to receiver.
   *
   * @return Error message.
   */
  public String introduceRandomError(final String Px) {
    final int minErrIndex = 1;
    final int maxErrIndex = 3;
    final int randomInt = new Random().nextInt(maxErrIndex - minErrIndex + 1) + minErrIndex;
    switch (randomInt) {
      case 1:
        final StringBuilder allOnesSB = new StringBuilder();
        for (int i = 0; i < Px.length(); ++i) {
          allOnesSB.append(1);
        }
        return allOnesSB.toString();
      case 2:
        final StringBuilder allZerosSB = new StringBuilder();
        for (int i = 0; i < Px.length(); ++i) {
          allZerosSB.append(0);
        }
        return allZerosSB.toString();
      case 3:
        final int minPos = 1;
        final int maxPos = Px.length() - 1;
        final int randomPosition = new Random().nextInt(maxPos - minPos + 1) + minPos;
        final char flippedChar = (Px.charAt(randomPosition) == '0') ? '1' : '0';
        return Px.substring(0, randomPosition) + flippedChar + Px.substring(randomPosition + 1);
      default:
        return Px;
    }
  }
}