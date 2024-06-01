package live.smoothing.actuator.entity;

import lombok.Getter;

import javax.persistence.*;

@Getter
@Entity
@Table(name = "control_elements")
public class ControlElement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "element_id")
    private Long id;

    private String place;

    private String device;

    private String event;

    @OneToOne
    @JoinColumn(name = "device_id")
    private ControlDevice controlDevice;
}