package live.smoothing.smoothingactuator.listener;

import com.fasterxml.jackson.databind.ObjectMapper;
import live.smoothing.smoothingactuator.checker.ConditionChecker;
import live.smoothing.smoothingactuator.config.ConditionSettings;
import live.smoothing.smoothingactuator.dto.DataDTO;
import live.smoothing.smoothingactuator.service.ConditionSettingsService;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.ApplicationContext;


@RequiredArgsConstructor
public abstract class BaseListener {

    protected final RabbitTemplate rabbitTemplate;
    protected final ApplicationContext applicationContext;
    protected final ConditionSettingsService conditionSettingsService;

    protected void handleMessage(String message, String conditionCheckerBeanName) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            DataDTO data = mapper.readValue(message, DataDTO.class);

            ConditionChecker checker = (ConditionChecker) applicationContext.getBean(conditionCheckerBeanName);
            ConditionSettings.DeviceCondition settings = conditionSettingsService.getDeviceCondition(data.getDevice());

            if(checker.checkCondition(data, settings)) {
                String controlMessage =  createControlMessage(data, settings);
                rabbitTemplate.convertAndSend("actuator-queue", controlMessage);
            }

        } catch(Exception e) {}
    }

    protected abstract String createControlMessage(DataDTO data, ConditionSettings.DeviceCondition settings);
}
