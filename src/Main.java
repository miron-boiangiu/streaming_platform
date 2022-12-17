import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.node.ArrayNode;
import input.Input;
import streamingplatform.StreamingPlatform;

import java.io.File;
import java.io.IOException;

public final class Main {
    private Main() {

    }

    /**
     * Prepares everything and runs the platform.
     * @param args The JSON file to be turned into input for
     *             the platform and the name of the output JSON file.
     * @throws IOException
     */
    public static void main(final String[] args) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        Input inputData = objectMapper.readValue(new File(args[0]), Input.class);

        ArrayNode output = objectMapper.createArrayNode();

        StreamingPlatform.getINSTANCE().bootUp(inputData, output);
        ObjectWriter objectWriter = objectMapper.writerWithDefaultPrettyPrinter();
        objectWriter.writeValue(new File(args[1]), output);
    }
}
