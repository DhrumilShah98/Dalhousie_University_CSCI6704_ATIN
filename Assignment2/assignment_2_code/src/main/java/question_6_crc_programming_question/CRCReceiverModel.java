package question_6_crc_programming_question;

/**
 * {@code CRCReceiverModel} class contains receiver model class.
 *
 * @author Dhrumil Amish Shah (B00857606 | dh416386@dal.ca)
 */
public final class CRCReceiverModel {
  private final String Px;
  private final String Gx;
  private final String Qx;
  private final String Rx;
  private final String message;

  public CRCReceiverModel(final String Px,
                          final String Gx,
                          final String Qx,
                          final String Rx,
                          final String message) {
    this.Px = Px;
    this.Gx = Gx;
    this.Qx = Qx;
    this.Rx = Rx;
    this.message = message;
  }

  public String getPx() {
    return Px;
  }

  public String getGx() {
    return Gx;
  }

  public String getQx() {
    return Qx;
  }

  public String getRx() {
    return Rx;
  }

  public String getMessage() {
    return message;
  }
}