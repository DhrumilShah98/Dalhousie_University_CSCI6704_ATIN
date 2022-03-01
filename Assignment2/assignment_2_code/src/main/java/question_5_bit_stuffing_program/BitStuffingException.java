package question_5_bit_stuffing_program;

/**
 * {@code BitStuffingException} class contains the exception logic.
 *
 * @author Dhrumil Amish Shah (B00857606 | dh416386@dal.ca)
 */
public final class BitStuffingException extends Exception {
  private final String errorMessage;

  public BitStuffingException(final String errorMessage) {
    this.errorMessage = errorMessage;
  }

  @Override
  public String getMessage() {
    return this.errorMessage;
  }

  @Override
  public String toString() {
    return "BitStuffingException{" +
        "message='" + this.errorMessage + '\'' +
        '}';
  }
}