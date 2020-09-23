package torpedo.virtmod.interfaces;

/**
 * Описывает интерфейс объекта, реализующего распарсивание полученной от клиента комманды.
 */
public interface ICommandParser {
    /**
     * Метод возвращиет объект-комманду для использования.
     *
     * @param command Строковая коммада из потока ввода.
     * @return Объект комманда для внутреннего использования
     */
    public ICommandDto create(String command);
}
