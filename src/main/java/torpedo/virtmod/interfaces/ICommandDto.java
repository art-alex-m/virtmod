package torpedo.virtmod.interfaces;

/**
 * Интерфейс реализует описание методов доступа к данным объектов CommandDto
 */
public interface ICommandDto {
    /**
     * Метод возвращает строковое представление полученной команды.
     *
     * @return Строковое представление полученной команды.
     */
    public String getCommand();

    /**
     * Метод возвращает хеш команды.
     * @return Хеш команды.
     */
    public int getHash();

    /**
     * Метод реализует присвоения полученной команды.
     *
     * @param command Строковая комманда.
     * @return Возвращает текущий объект.
     */
    public ICommandDto setCommand(String command);
}
