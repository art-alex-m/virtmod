package torpedo.virtmod.modems.telit.handlers;

import torpedo.virtmod.exceptions.ModemEmitRebootException;
import torpedo.virtmod.handlers.AbstractCommandHandler;
import torpedo.virtmod.interfaces.ICommandResponseDto;
import torpedo.virtmod.interfaces.workers.IWorkerModemClient;

/**
 * Класс реализует логику обработки команды запроса на немедленную перезагрузку модема.
 */
public class AtEnhrst10Handler extends AbstractCommandHandler {

    @Override
    public boolean handle(IWorkerModemClient modemClient) throws Exception {
        modemClient.getOut().write(this.getResponse().asString());
        modemClient.getOut().flush();
        Thread.sleep(1000L);
        throw new ModemEmitRebootException("Modem emit reboot");
    }

    public ICommandResponseDto getResponse() {
        return () -> "\r\nOK\r\n\r\n";
    }
}
