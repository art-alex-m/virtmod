package torpedo.virtmod.interfaces.workers;

/**
 * Описывает интерфейс объекта, реализующего логику выхода из бесконечного цикла.
 */
public interface IWorkerAbstract {
    /**
     * Метод возвращает статус процесса.
     * @return false - если процесс должен продолжать выполнение.
     */
    public boolean isStop();

    /**
     * Метод устанавливает статус выполнения процесса.
     * @param stop Статус выполнения процесса. true - процесс должен завершиться.
     */
    public void setStop(boolean stop);
}
