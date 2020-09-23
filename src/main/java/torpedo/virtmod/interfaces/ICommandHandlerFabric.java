package torpedo.virtmod.interfaces;

/**
 * Описывает интерфейс объекта, реализующего фабрику по обработке комманд.
 */
public interface ICommandHandlerFabric {
    /**
     * Метод возвращает обработчика комманды.
     *
     * @param command Комманда для обработки.
     * @return ICommandHandler Обработчик комманды.
     */
    public ICommandHandler getHandler(ICommandDto command);
}
