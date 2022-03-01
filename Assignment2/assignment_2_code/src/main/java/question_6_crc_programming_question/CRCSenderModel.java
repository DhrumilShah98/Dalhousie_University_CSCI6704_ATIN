package question_6_crc_programming_question;

/**
 * {@code CRCSenderModel} class contains sender model class.
 *
 * @author Dhrumil Amish Shah (B00857606 | dh416386@dal.ca)
 */
public final class CRCSenderModel {
  private final String Mx;
  private final String Gx;
  private final String MDashX;
  private final String Qx;
  private final String Rx;
  private final String Px;

  public CRCSenderModel(final String Mx,
                        final String Gx,
                        final String MDashX,
                        final String Qx,
                        final String Rx,
                        final String Px) {
    this.Mx = Mx;
    this.Gx = Gx;
    this.MDashX = MDashX;
    this.Qx = Qx;
    this.Rx = Rx;
    this.Px = Px;
  }

  public String getMx() {
    return this.Mx;
  }

  public String getGx() {
    return this.Gx;
  }

  public String getMDashX() {
    return this.MDashX;
  }

  public String getQx() {
    return this.Qx;
  }

  public String getRx() {
    return this.Rx;
  }

  public String getPx() {
    return Px;
  }
}