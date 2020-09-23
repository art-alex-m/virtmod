package torpedo.virtmod.interfaces.workers;

/**
 * Описывает интерфейс объекта, реализующего логику слушающего сервера.
 */
public interface IWorkerModemServer extends IWorkerAbstract, Runnable {
    /**
     * Метод устанадливает номер порта для прослушивания соединений.
     *
     * @param port Порт для прослушивания входящих соединений.
     * @return IWorkerModemServer
     */
    public IWorkerModemServer setPort(int port);
}
