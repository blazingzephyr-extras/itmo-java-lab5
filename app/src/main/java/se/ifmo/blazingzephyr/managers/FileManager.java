package se.ifmo.blazingzephyr.managers;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

/**
 * Менеджер, единолично осуществляющий управление файловой системой.
 * Отвечает за логику открытия-закрытия файлов, а также их чтения и записи.
 */
public class FileManager {
    
    private String filePath;                // Путь искомого файла базы данных.
    private FileInputStream inputStream;    // 
    private InputStreamReader reader;
    private BufferedReader bufferReader;

    private FileOutputStream outputStream;  // 
    private OutputStreamWriter writer;
    private BufferedWriter bufferWriter;

    /**
     * 
     * @return возвращает
     */
    public String getFilePath()
    {
        return this.filePath;
    }

    /**
     * Новый путь
     * @param path
     * @return
     */
    public FileManager setFilePath(String path)
    {
        this.filePath = path;
        return this;
    }

    public void openForReading() throws FileNotFoundException
    {
        this.inputStream = new FileInputStream(this.filePath);
        this.reader = new InputStreamReader(this.inputStream);
        this.bufferReader = new BufferedReader(this.reader);
    }

    public void openForWriting() throws FileNotFoundException
    {
        this.outputStream = new FileOutputStream(this.filePath);
        this.writer = new OutputStreamWriter(this.outputStream);
        this.bufferWriter = new BufferedWriter(this.writer);
    }

    public void closeAfterReading() throws IOException
    {
        this.bufferReader.close();
        this.reader.close();
        this.inputStream.close();
    }

    public void closeAfterWriting() throws IOException
    {
        this.bufferWriter.close();
        this.writer.close();
        this.outputStream.close();
    }

    public Iterable<String> readAllLines() throws IOException
    {
        ArrayList<String> lines = new ArrayList<>();
        String line;

        while ((line = this.bufferReader.readLine()) != null)
        {
            lines.add(line);
        }
        
        return lines;
    }

    public void writeAllLines(Iterable<String> lines) throws IOException
    {
        for (String line : lines)
        {
            this.bufferWriter.write(line);
            this.bufferWriter.write('\n');
        }
    }

    public void write(String line) throws IOException
    {
        this.bufferWriter.write(line);
    }
}
