package torpedo.virtmod.modems.telit.handlers;

import torpedo.virtmod.handlers.AbstractCommandHandler;
import torpedo.virtmod.interfaces.ICommandResponseDto;
import torpedo.virtmod.interfaces.workers.IWorkerModemClient;

/**
 * Класс реализует логику обработки команды запроса баланса.
 */
public class AtCusd110015Handler extends AbstractCommandHandler {

    @Override
    public boolean handle(IWorkerModemClient modemClient) throws Exception {
        modemClient.getOut().write(this.getResponse(1).asString());
        modemClient.getOut().flush();
        Thread.sleep(1000L);
        modemClient.getOut().write(this.getResponse(2).asString());
        modemClient.getOut().flush();
        return true;
    }

    public  ICommandResponseDto getResponse() {
        return () -> getResponse(1).asString() + getResponse(2).asString();
    }

    public ICommandResponseDto getResponse(int step) {
        String message;

        if (1 == step) {
            message = "\r\n" +
                    "OK" +
                    "\r\n\r\n";
        } else {
            message = "+CUSD: 2,\"002D00310034003600380034002E0033003200200440002E000A002000000000EE2C0200000000F00000250040060200AA020A00EC180030\",72" +
                    "\r\n\r\n";
        }

        return () -> message;
    }
}
