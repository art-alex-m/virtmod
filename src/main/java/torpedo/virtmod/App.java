package torpedo.virtmod;

import sun.misc.Signal;
import torpedo.virtmod.interfaces.workers.IWorkerModemServer;
import torpedo.virtmod.workers.ModemServer;

import java.util.List;
import java.util.ArrayList;

/**
 * Класс реализует логику основного процесса приложения.
 */
public class App implements Runnable {

    /**
     * Список активных "модемов".
     */
    private final List<IWorkerModemServer> modemServerCollection;
    /**
     * Список портов для инициации модемов.
     */
    private int[] ports;

    /**
     * Базовый конструктор класса App.
     */
    App() {
        this.modemServerCollection = new ArrayList<>();
        this.addTermSignalHandler();
    }

    @Override
    public void run() {
        System.out.println("Welcome in virtual modem pool.");
        for (var p : this.ports) {
            IWorkerModemServer modemServer = new ModemServer().setPort(p);
            Thread worker = new Thread(modemServer, "modem-" + p);
            this.modemServerCollection.add(modemServer);
            worker.start();
        }
    }

    /**
     * Метод устанавливает список портов для прослушивания.
     *
     * @param ports Массив портов для прикрепления модемов.
     * @return App
     */
    public App setPorts(int[] ports) {
        this.ports = ports;
        return this;
    }

    /**
     * Метод устанавливает обработчик на сигнал TERM остановки процесса
     */
    private void addTermSignalHandler() {
        Signal.handle(new Signal("TERM"), (signal) -> {
            System.out.println(signal.getName() + " (" + signal.getNumber() + ")");
            this.modemServerCollection.forEach(item -> item.setStop(true));
        });
    }
}
