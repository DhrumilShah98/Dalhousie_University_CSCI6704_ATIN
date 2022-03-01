import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * {@code BridgeProcessingSimulation} class performs bridge-processing simulation operation.
 * {@code BridgeProcessingSimulation} class execution is as below:
 * <p>
 * Step 1 - Start
 * <p>
 * Step 2 - The {@code execute()} method of this class is first called from
 * the {@code BridgeProcessingSimulationTest} class.
 * <p>
 * Step 3 - Read the content from BridgeFDB.txt file in memory (i.e. in {@code Map<String, String>)} where
 * {@code key} is MAC address of source and {@code value} is port number using method {@code readFDBDataInMemory()}.
 * <p>
 * Step 4 - Read frames one by one from file RandomFrames.txt and store the source address in {@code sourceAddress},
 * destination address in {@code destinationAddress} and port in {@code port} using the {@code readLine()} method.
 * <p>
 * Step 5 - Print the current frame under process.
 * <p>
 * Step 6 - Perform the bridge-processing algorithm using method {@code performBridgeProcessingSimulation()}
 * on the current frame under process and return the output which can be
 * {@code "Discarded"}, {@code "Broadcast"}, or {@code "Forwarded on port q"}.
 * <p>
 * Step 7 - Store the output in file BridgeOutput.txt file using method {@code storeOutput()}.
 * <p>
 * Step 8 - After all the frames are processed, store the updated Bridge FDB database from memory to file using
 * {@code storeFDBDataInFile()} method.
 * <p>
 * Step 9 - Stop
 */
public final class BridgeProcessingSimulation {
  // Path to files folder.
  private static final String PATH;

  // Path to "BridgeFDB.txt" file.
  private static final String BRIDGE_FDB_PATH;

  // Path to "RandomFrames.txt" file.
  private static final String RANDOM_FRAMES_PATH;

  // Path to "BridgeOutput.txt" file.
  private static final String BRIDGE_OUTPUT_PATH;

  static {
    PATH = "./src/main/java/files/";
    BRIDGE_FDB_PATH = PATH + "BridgeFDB.txt";
    RANDOM_FRAMES_PATH = PATH + "RandomFrames.txt";
    BRIDGE_OUTPUT_PATH = PATH + "BridgeOutput.txt";
  }

  /**
   * Stores the updated Forwarding database - FDB in file {@code BridgeFDB.txt}.
   *
   * @param fDBDatabase In-memory forwarding database.
   */
  private void storeFDBDataInFile(final Map<String, String> fDBDatabase) {
    final StringBuilder fDBStringBuilder = new StringBuilder();
    for (Map.Entry<String, String> fDBEntry : fDBDatabase.entrySet()) {
      fDBStringBuilder.append(fDBEntry.getKey());
      fDBStringBuilder.append('\n');
      fDBStringBuilder.append(fDBEntry.getValue());
      fDBStringBuilder.append('\n');
    }

    try (final FileWriter bridgeFDBFileWriter = new FileWriter(BRIDGE_FDB_PATH)) {
      bridgeFDBFileWriter.write(fDBStringBuilder.toString());
    } catch (final IOException e) {
      e.printStackTrace();
      System.err.println(e.getMessage());
    }
  }

  /**
   * Stores the output in {@code BridgeOutput.txt} file.
   *
   * @param answer answer to store in the output file.
   */
  private void storeOutput(final String answer) {
    try (final FileWriter bridgeOutputFileWriter = new FileWriter(BRIDGE_OUTPUT_PATH, true)) {
      bridgeOutputFileWriter.append(answer);
      bridgeOutputFileWriter.append('\n');
    } catch (final IOException e) {
      e.printStackTrace();
      System.err.println(e.getMessage());
    }
  }

  /**
   * Bridge-processing algorithm.
   *
   * @param sourceAddress      source MAC address.
   * @param destinationAddress destination MAC address.
   * @param port               port number
   * @param fDBDatabase        Forwarding database.
   *
   * @return {@code "Discarded"}, {@code "Broadcast"}, or {@code "Forwarded on port q"} string.
   */
  private String performBridgeProcessingSimulation(final String sourceAddress,
                                                   final String destinationAddress,
                                                   final String port,
                                                   final Map<String, String> fDBDatabase) {

    String answer;
    // Check source address X. Is there an entry for X in FDB?
    if (fDBDatabase.containsKey(sourceAddress)) {
      // Yes
      // Is the entry [X p]?
      if (!fDBDatabase.get(sourceAddress).equalsIgnoreCase(port)) {
        // No
        // Change entry to [X, p].
        fDBDatabase.put(sourceAddress, port);
      }
      // If Yes, do nothing
    } else {
      // No
      // Add entry [X p].
      fDBDatabase.put(sourceAddress, port);
    }

    // Check destination address Y. Is there an entry for Y in FDB?
    if (fDBDatabase.containsKey(destinationAddress)) {
      // Yes
      // Is there entry [Y p]?
      if (fDBDatabase.get(destinationAddress).equalsIgnoreCase(port)) {
        // Yes
        answer = "Discarded";
      } else {
        // No
        answer = "Forwarded on port " + fDBDatabase.get(destinationAddress);
      }
    } else {
      // No
      // Broadcast frame on all ports except p.
      answer = "Broadcast";
    }
    return answer;
  }

  /**
   * Reads the Forwarding database from file in memory.
   *
   * @return Map with key = MAC address and value = port number.
   */
  private Map<String, String> readFDBDataInMemory() {
    final Map<String, String> fDBDatabase = new LinkedHashMap<>();
    try (final FileReader bridgeFDBFileReader = new FileReader(BRIDGE_FDB_PATH);
         final BufferedReader bridgeFDBBufferedReader = new BufferedReader(bridgeFDBFileReader)) {
      String address;
      String port;
      while ((address = bridgeFDBBufferedReader.readLine()) != null) {
        port = bridgeFDBBufferedReader.readLine();
        fDBDatabase.put(address, port);
      }
    } catch (final IOException e) {
      e.printStackTrace();
      System.err.println(e.getMessage());
    }
    return fDBDatabase;
  }

  /**
   * Executes this program.
   */
  public void execute() {
    try (final FileReader randomFramesFileReader = new FileReader(RANDOM_FRAMES_PATH);
         final BufferedReader randomFramesBufferedReader = new BufferedReader(randomFramesFileReader)) {
      final Map<String, String> fDBDatabase = readFDBDataInMemory();
      System.out.printf("Bridge FDB Database Entries: %d%n", fDBDatabase.size());
      String sourceAddress;
      String destinationAddress;
      String port;
      while ((sourceAddress = randomFramesBufferedReader.readLine()) != null) {
        destinationAddress = randomFramesBufferedReader.readLine();
        port = randomFramesBufferedReader.readLine();
        System.out.printf("Frame: [SA = %s | DA = %s | P = %s]%n", sourceAddress, destinationAddress, port);
        final String answer = performBridgeProcessingSimulation(sourceAddress, destinationAddress, port, fDBDatabase);
        storeOutput(sourceAddress + "      " + destinationAddress + "      " + port + "      " + answer);
      }
      storeFDBDataInFile(fDBDatabase);
    } catch (final IOException e) {
      e.printStackTrace();
      System.err.println(e.getMessage());
    }
  }
}