package torpedo.virtmod.handlers;

import torpedo.virtmod.interfaces.ICommandResponseDto;

public class ErrorCommandHandler extends AbstractCommandHandler {

    public ICommandResponseDto getResponse() {
        return () -> "\r\nERROR\r\n\r\n";
    }
}
