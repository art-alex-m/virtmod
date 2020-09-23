package torpedo.virtmod.handlers;

import torpedo.virtmod.interfaces.ICommandDto;
import torpedo.virtmod.interfaces.ICommandHandler;
import torpedo.virtmod.interfaces.ICommandResponseDto;
import torpedo.virtmod.interfaces.workers.IWorkerModemClient;

abstract public class AbstractCommandHandler implements ICommandHandler {

    private ICommandDto commandDto;

    abstract public ICommandResponseDto getResponse();

    @Override
    public AbstractCommandHandler setCommand(ICommandDto command) {
        this.commandDto = command;
        return this;
    }

    @Override
    public ICommandDto getCommand() {
        return this.commandDto;
    }

    @Override
    public boolean handle(IWorkerModemClient modemClient) throws Exception {
        modemClient.getOut().write(this.getResponse().asString());
        modemClient.getOut().flush();
        return true;
    }
}
