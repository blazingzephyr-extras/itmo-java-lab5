package se.ifmo.blazingzephyr.managers;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Класс-менеджер (т.е. модульный класс-функционал), управляющий вводом-выводом.
 */
public class IoManager {
    
    private final InputStreamReader isReader;   // Потоковый считыватель.
    private BufferedReader streamReader;        // Ридер буффера (инициализируется лениво).
    private String line;                        // Последняя прочитанная строка.

    /**
     * Пустой конструктор менеджера.
     * Инициализирует считыватель ввода.
     */
    public IoManager()
    {
        this.isReader = new InputStreamReader(System.in);
    }

    /**
     * Дожидается ввода пользователя и двигает строчку.
     * @return false, если пользователь ввёл пустую строку, true иначе.
     */
    public boolean waitForInput() throws IOException
    {
        if (streamReader == null)
        {
            streamReader = new BufferedReader(isReader);
        }

        this.line = this.streamReader.readLine();
        return this.line != null && !this.line.isEmpty();
    }

    /**
     * Закрывает поток чтения.
     */
    public void closeInputReader() throws IOException
    {
        this.streamReader.close();
    }

    /**
     * Последняя прочитанная строчка.
     * Не cдвигает буффер.
     * @return строка.
     */
    public String lastReadLine()
    {
        return this.line;
    }

    /**
     * Выводит сообщение в поток вывода.
     * @param message строка сообщения.
     */
    public void printToOutput(String message)
    {
        // На данном этапе выводит сообщение лишь в стандартный поток, однако, возможны расширения функционала.
        System.out.println(message);
    }
}
