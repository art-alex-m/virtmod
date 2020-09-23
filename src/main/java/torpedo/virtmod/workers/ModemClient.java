package torpedo.virtmod.workers;

import torpedo.virtmod.exceptions.ModemEmitRebootException;
import torpedo.virtmod.interfaces.ICommandDto;
import torpedo.virtmod.interfaces.ICommandHandler;
import torpedo.virtmod.interfaces.ICommandHandlerFabric;
import torpedo.virtmod.interfaces.ICommandParser;
import torpedo.virtmod.interfaces.workers.IWorkerModemClient;

import java.io.*;
import java.net.Socket;
import java.net.SocketException;

/**
 * Класс реализует логику сервера, принимающего комманды клиента.
 */
public class ModemClient extends AbstractWorker implements IWorkerModemClient {

    private final Socket clientSocket;
    private final BufferedReader in;
    private final BufferedWriter out;
    private ICommandHandlerFabric handlerFabric;
    private ICommandParser commandParser;
    private String modemName;

    ModemClient(Socket clientSocket) throws IOException {
        this.in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        this.out = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()));
        this.clientSocket = clientSocket;
    }

    public ModemClient setModemName(String name) {
        this.modemName = name;
        return this;
    }

    public ModemClient setHandlerFabric(ICommandHandlerFabric handlerFabric) {
        this.handlerFabric = handlerFabric;
        return this;
    }

    public ModemClient setCommandParser(ICommandParser commandParser) {
        this.commandParser = commandParser;
        return this;
    }

    public BufferedReader getIn() {
        return in;
    }

    public BufferedWriter getOut() {
        return out;
    }

    @Override
    public void run() {
        try {
            String message = "";
            while (!this.isStop() && this.clientSocket.isConnected()) {
                try {
                    message = this.readLine();
                    System.out.printf("%s got message: %s%n", this.modemName, message);
                    ICommandDto commandDto = this.commandParser.create(message);
                    ICommandHandler handler = this.handlerFabric.getHandler(commandDto);
                    handler.handle(this);

                } catch (ModemEmitRebootException | SocketException e) {
                    System.out.printf("%s ignoring exception: %s%n", this.modemName, e.getMessage());
                    e.printStackTrace();
                    this.setStop(true);
                }
            }
            this.close();
            System.out.printf("%s client is disconnected%n", this.modemName);
        } catch (Exception e) {
            System.out.printf("%s ignoring exception: %s%n", this.modemName, e.getMessage());
            e.printStackTrace();
        }
    }

    @Override
    public void close() throws IOException {
        this.in.close();
        this.out.close();
        this.clientSocket.close();
    }

    private String readLine() throws IOException {
        String message;
        do {
            message = this.in.readLine();
        } while (
                !this.isStop() &&
                        !this.clientSocket.isInputShutdown() &&
                        message != null &&
                        message.isBlank()
        );
        return message;
    }
}
