package torpedo.virtmod.workers;

import torpedo.virtmod.interfaces.ICommandHandlerFabric;
import torpedo.virtmod.interfaces.ICommandParser;
import torpedo.virtmod.interfaces.workers.IWorkerModemClient;
import torpedo.virtmod.interfaces.workers.IWorkerModemServer;
import torpedo.virtmod.models.CommandParser;
import torpedo.virtmod.modems.telit.CommandHandlerFabric;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Класс реализует логику слушаеющего соединения сервера.
 */
public class ModemServer extends AbstractWorker implements IWorkerModemServer {

    private int port;
    private IWorkerModemClient clientWorker;

    public ModemServer setPort(int port) {
        this.port = port;
        return this;
    }

    @Override
    public void run() {

        ICommandHandlerFabric handlerFabric = new CommandHandlerFabric();
        ICommandParser commandParser = new CommandParser();

        String modemName = Thread.currentThread().getName();
        System.out.printf("Running %s (%s)%n", modemName, ProcessHandle.current().pid());

        try (ServerSocket serverSocket = new ServerSocket(this.port, 1)) {
            serverSocket.setSoTimeout(500);
            while (!this.isStop()) {
                try {
                    Socket clientSocket = serverSocket.accept();

                    if (null != this.clientWorker) {
                        if (!this.clientWorker.isStop()) {
                            System.out.printf("%s client interrupted%n", modemName);
                            this.clientWorker.setStop(true);
                        }
                        this.clientWorker = null;
                    }

                    System.out.printf("%s new client accepted%n", modemName);

                    this.clientWorker = new ModemClient(clientSocket)
                            .setHandlerFabric(handlerFabric)
                            .setCommandParser(commandParser)
                            .setModemName(modemName);

                    Thread worker = new Thread(this.clientWorker);
                    worker.setDaemon(true);
                    worker.start();

                } catch (InterruptedIOException e) {
                    /// do noting
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
