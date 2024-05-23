package live.smoothing.actuator.service;

import live.smoothing.actuator.config.ConditionSettings;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class ConditionSettingsService {

    private final Map<String, ConditionSettings.DeviceCondition> deviceConditions = new ConcurrentHashMap<>();

    public ConditionSettingsService(ConditionSettings settings) {
        this.deviceConditions.putAll(settings.getDevices());
    }

    public ConditionSettings.DeviceCondition getDeviceCondition(String device) {
        return deviceConditions.get(device);
    }

    public void updateDeviceCondition(String device, ConditionSettings.DeviceCondition newCondition) {
        deviceConditions.put(device, newCondition);
    }

    public Map<String, ConditionSettings.DeviceCondition> getAllDeviceConditions() {
        return deviceConditions;
    }
}
