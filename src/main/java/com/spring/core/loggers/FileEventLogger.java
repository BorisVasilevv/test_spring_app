package com.spring.core.loggers;

import com.spring.core.beans.Event;
import org.apache.commons.io.FileUtils;
import org.springframework.util.FileCopyUtils;
import org.springframework.util.FileSystemUtils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.Charset;

public class FileEventLogger implements EventLogger{

    private String filename;

    private File file;

    public FileEventLogger(String filename){
        this.filename=filename;

    }

    @Override
    public void logEvent(Event event) {
        /*try (BufferedWriter bWriter=new BufferedWriter(new FileWriter(filename))){
            bWriter.append(event.getMessage());
            bWriter.append('\n');

        } catch (IOException e) {
            throw new RuntimeException(e);
        }*/
        try {
            FileUtils.writeStringToFile(file,event.toString(), "UTF-8", true);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }


    }

    @Override
    public String getName() {
        return "FileEventLogger";
    }

    protected void init() throws IOException{
        this.file=new File(filename);
        if(file.exists()&&!file.canWrite())
            throw new IOException("Can't write to file " + filename);
        else if (!file.exists())
            file.createNewFile();
    }

}
