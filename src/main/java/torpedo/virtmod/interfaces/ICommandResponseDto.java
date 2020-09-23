package torpedo.virtmod.interfaces;

/**
 * Описывает интерфейс объекта, реализующего ответ обработчика комманды.
 */
public interface ICommandResponseDto {
    /**
     * Метод возвращает строкоевое представление ответа на принятую команду.
     * @return Ответ
     */
    public String asString();
}
