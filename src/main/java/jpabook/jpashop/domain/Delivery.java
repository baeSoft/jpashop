package jpabook.jpashop.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

import static javax.persistence.FetchType.LAZY;

@Entity
@Getter @Setter
public class Delivery {

    @Id @GeneratedValue
    @Column(name = "delivery_id")
    private Long id;

    @OneToOne(mappedBy = "delivery", fetch = LAZY)
    private Order order;

    @Embedded
    private Address address;

    @Enumerated(EnumType.STRING)    //ORDINAL로 설정 후 사용하게 되면 서비스 중에 STATUS 값이 추가가 되면
                                    // DATA 순서가 꼬이기 때문에 STRING으로 사용할 것
    private DeliveryStatus status;  //READY, COMP
}
