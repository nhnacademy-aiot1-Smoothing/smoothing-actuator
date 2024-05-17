package live.smoothing.smoothingactuator.device;

public interface Device {
    boolean connect();
    void sendCommand();
}
