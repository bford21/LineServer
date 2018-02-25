package CircleLineServer;

import java.util.HashMap;

public class Lines {
    private static HashMap<Integer, String> allLines = new HashMap<>();

    public static String getLine(Integer lineNum){
      return allLines.get(lineNum);
    }

    public static void insertLine(Integer lineNum, String line){
        allLines.put(lineNum, line);
    }
}
