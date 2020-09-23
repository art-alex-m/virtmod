package torpedo.virtmod.interfaces;

import torpedo.virtmod.interfaces.workers.IWorkerModemClient;

/**
 * Описывает интерфейс объекта, реализующего логику обработчика комманд, принятых с модема.
 */
public interface ICommandHandler {
    /**
     * Метод устанавливает принятую команду.
     * @param command Команда
     * @return ICommandHandler
     */
    public ICommandHandler setCommand(ICommandDto command);

    /**
     * Метод возвращает сохраненную команду в обработчике.
     * @return ICommandDto
     */
    public ICommandDto getCommand();

    /**
     * Метод реализует логику обработки команды.
     * @param modemClient Ссылка на главный процесс обработки соединения с клиентом.
     * @return Результат обработки команды.
     * @throws Exception Возможные исключения при выполнении логики обработчика.
     */
    public boolean handle(IWorkerModemClient modemClient) throws Exception;
}
