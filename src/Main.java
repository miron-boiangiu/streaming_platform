import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.node.ArrayNode;
import input.Input;
import streamingplatform.StreamingPlatform;

import java.io.File;
import java.io.IOException;

import static java.lang.Thread.sleep;

public class Main {
    static int a = 0;
    public static void main(String[] args) throws IOException, InterruptedException {
        ObjectMapper objectMapper = new ObjectMapper();
        Input inputData = objectMapper.readValue(new File(args[0]),
                Input.class);
        a++;
        System.out.println(args[0]);

        ArrayNode output = objectMapper.createArrayNode();

        StreamingPlatform.getInstance().bootUp(inputData, output);
        ObjectWriter objectWriter = objectMapper.writerWithDefaultPrettyPrinter();
        objectWriter.writeValue(new File(args[1]), output);
        objectWriter.writeValue(new File(args[1] + a), output);
    }
}
