package live.smoothing.actuator.entity;

import lombok.Getter;

import javax.persistence.*;

@Getter
@Entity
@Table(name = "control_devices")
public class ControlDevice {

    @Id
    @Column(name = "device_id")
    private String eui;

    private String name;

    @OneToOne(mappedBy = "controlDevice")
    private ControlElement controlElement;
}
