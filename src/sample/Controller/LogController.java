package sample.Controller;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;


/**
 * Created by Christian Findsen on 14-05-2017.
 */
public class LogController
{


    public void logController(String msg) throws IOException
    {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();

        Charset utf8 = StandardCharsets.UTF_8;
        List<String> lines = Arrays.asList(dtf.format(now)+msg);


        try {

            Files.write(Paths.get("Log.txt"),lines, utf8,
                    StandardOpenOption.CREATE, StandardOpenOption.APPEND);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
