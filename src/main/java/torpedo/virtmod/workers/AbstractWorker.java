package torpedo.virtmod.workers;

import torpedo.virtmod.interfaces.workers.IWorkerAbstract;

abstract public class AbstractWorker implements IWorkerAbstract, Runnable {

    private boolean stop = false;

    public synchronized boolean isStop() {
        return stop;
    }

    public synchronized void setStop(boolean stop) {
        this.stop = stop;
    }
}
