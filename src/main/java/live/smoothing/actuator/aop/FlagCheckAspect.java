package live.smoothing.actuator.aop;

import com.fasterxml.jackson.databind.ObjectMapper;
import live.smoothing.actuator.dto.ControlRequest;
import live.smoothing.actuator.dto.DataDTO;
import live.smoothing.actuator.entity.ControlElement;
import live.smoothing.actuator.exception.NotManualException;
import live.smoothing.actuator.prop.ConditionProperties;
import live.smoothing.actuator.service.ControlElementService;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Aspect
@Component
@RequiredArgsConstructor
public class FlagCheckAspect {

    private final ConditionProperties conditionProperties;
    private final RedisTemplate<String, Boolean> redisTemplate;
    private final ControlElementService elementService;
    private final ObjectMapper objectMapper;

    @Pointcut("execution(* live.smoothing.actuator.controller.DeviceController.turnDevice(..))")
    public void deviceControllerPointcut() {}

    @Pointcut("execution(* live.smoothing.actuator.listener.mq.*.receiveMessage(..))")
    public void mqListenerPointcut() {}

    @Around("deviceControllerPointcut() && args(request)")
    public Object checkAuto(ProceedingJoinPoint joinPoint, ControlRequest request) throws Throwable {

        String eui= request.getEui();
        Boolean isManual = redisTemplate.opsForValue().get(String.join("-", eui, conditionProperties.getFlagKey()));

        if (Boolean.FALSE.equals(isManual)) {
            throw new NotManualException(HttpStatus.BAD_REQUEST, "수동 모드가 아닙니다.");
        }

        return joinPoint.proceed();
    }

    @Around("mqListenerPointcut() && args(message)")
    public Object checkManual(ProceedingJoinPoint joinPoint, String message) throws Throwable {
        DataDTO data = objectMapper.readValue(message, DataDTO.class);
        String place = data.getLocation();
        String device = data.getDevice();
        String event = data.getEvent();

        ControlElement element = elementService.findByPlaceAndDeviceAndEvent(place, device, event);
        Boolean isManual = redisTemplate.opsForValue().get(String.join("-", element.getControlDevice().getEui(), conditionProperties.getFlagKey()));

        if (Objects.isNull(isManual) || Boolean.TRUE.equals(isManual)) {
            return null;
        }

        return joinPoint.proceed();
    }
}
