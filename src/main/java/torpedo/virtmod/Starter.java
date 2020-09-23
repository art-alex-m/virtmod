package torpedo.virtmod;

/**
 * Класс реализует логику инициации программы.
 */
public class Starter {
    public static void main(String[] args) {
        int[] ports = {13200, 13201, 13202, 13203};
        new App().setPorts(ports).run();
    }
}
