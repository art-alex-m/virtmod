package torpedo.virtmod.interfaces.workers;

import torpedo.virtmod.interfaces.ICommandHandlerFabric;
import torpedo.virtmod.interfaces.ICommandParser;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.Closeable;

/**
 * Описывает интерфейс объекта, реализующего логику обработки принятого клиентского соединения
 */
public interface IWorkerModemClient extends IWorkerAbstract, Runnable, Closeable {
    /**
     * Метод возвращает ссылку на поток чтения.
     * @return BufferedReader
     */
    public BufferedReader getIn();

    /**
     * Метод возвращает ссылку на поток ввода.
     * @return BufferedWriter
     */
    public BufferedWriter getOut();

    /**
     * Метод устанавливает имя процесса.
     * @param name Имя процесса.
     * @return IWorkerModemClient
     */
    public IWorkerModemClient setModemName(String name);

    /**
     * Метод устанавливает фабрику обработчиков комманд.
     * @param handlerFabric Ссылка на объект фабрику обработчиков.
     * @return IWorkerModemClient
     */
    public IWorkerModemClient setHandlerFabric(ICommandHandlerFabric handlerFabric);

    /**
     * Метод устанавливает парсер принятых комманд клиента.
     * @param commandParser Ссылка на объект парсера принятых команд.
     * @return IWorkerModemClient
     */
    public IWorkerModemClient setCommandParser(ICommandParser commandParser);
}
