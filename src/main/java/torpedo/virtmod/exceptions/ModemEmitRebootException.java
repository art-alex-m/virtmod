package torpedo.virtmod.exceptions;

/**
 * Класс реализует представление исключения, когда модем требует перезагрузки и разрывает соединение.
 */
public class ModemEmitRebootException extends RuntimeException {
    public ModemEmitRebootException(String message) {
        super(message);
    }
}
