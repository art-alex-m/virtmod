package torpedo.virtmod.modems.telit;


import torpedo.virtmod.interfaces.ICommandDto;
import torpedo.virtmod.interfaces.ICommandHandlerFabric;
import torpedo.virtmod.interfaces.ICommandHandler;
import torpedo.virtmod.handlers.ErrorCommandHandler;
import torpedo.virtmod.modems.telit.handlers.AtCmgl0Handler;
import torpedo.virtmod.modems.telit.handlers.AtCusd110015Handler;
import torpedo.virtmod.modems.telit.handlers.AtEnhrst10Handler;
import torpedo.virtmod.modems.telit.interfaces.ITelitCommands;

/**
 * Класс реализует логику получения хендлеров для обработки комманд на модеме Telit.
 */
public class CommandHandlerFabric implements ICommandHandlerFabric {

    private ICommandHandler errorHandler;
    private ICommandHandler atCmgl0Handler;
    private ICommandHandler atCusd110015Handler;
    private ICommandHandler atEnhrst10Handler;

    /**
     * Метод возвращает найденный хендлер для команды.
     * @param command Комманда
     * @return Хендлер на обработку запроса
     */
    @Override
    public ICommandHandler getHandler(ICommandDto command) {

        ICommandHandler handler;

        int commandHash = command.getHash();

        if (commandHash == ITelitCommands.CMD_ALL_NEW_SMS.hashCode()) {
            handler = this.getAtCmgl0Handler();
        } else if (commandHash == ITelitCommands.CMD_GET_BALANCE.hashCode()) {
            handler = this.getAtCusd110015Handler();
        } else if (commandHash == ITelitCommands.CMD_REBOOT_NOW.hashCode()) {
            handler = this.getAtEnhrst10Handler();
        } else {
            handler = this.getErrorHandler();
        }

        return handler.setCommand(command);
    }

    /**
     * Метод возвращает хендлер для ERROR ответа.
     * @return ErrorCommandHandler
     */
    public ICommandHandler getErrorHandler() {
        if (null == this.errorHandler) {
            this.errorHandler = new ErrorCommandHandler();
        }

        return this.errorHandler;
    }

    /**
     * Метод возвращает хендлер для обработки запроса на все новые смс.
     * @return AtCmgl0Handler
     */
    public ICommandHandler getAtCmgl0Handler() {
        if (null == this.atCmgl0Handler) {
            this.atCmgl0Handler = new AtCmgl0Handler();
        }

        return this.atCmgl0Handler;
    }

    /**
     * Метод возвращает хендлер для обработки ответа запроса баланса.
     * @return AtCusd110015Handle
     */
    public ICommandHandler getAtCusd110015Handler() {
        if (null == this.atCusd110015Handler) {
            this.atCusd110015Handler = new AtCusd110015Handler();
        }

        return this.atCusd110015Handler;
    }

    /**
     * Метод возвращает хендлер для обработки запроса на перезагрузку модема.
     * @return AtEnhrst10Handler
     */
    public ICommandHandler getAtEnhrst10Handler() {
        if (null == this.atEnhrst10Handler) {
            this.atEnhrst10Handler = new AtEnhrst10Handler();
        }

        return this.atEnhrst10Handler;
    }
}
