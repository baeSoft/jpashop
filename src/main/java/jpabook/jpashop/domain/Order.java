package jpabook.jpashop.domain;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static javax.persistence.FetchType.*;

@Entity
@Table(name = "orders")
@Getter @Setter
public class Order {

    @Id @GeneratedValue
    @Column(name = "order_id")
    private Long id;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)   //order를 persist하면(저장할 때) cascade가 전체 persist를 전파 한다.
    private List<OrderItem> orderItems = new ArrayList<>();     //원래대로는 엔티티의 각자마다 persist를 해주어야하는데 cascade를 사용하면 편하다.

    @OneToOne(fetch = LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "delivery_id")
    private Delivery delivery;
    private LocalDateTime orderDate;
    private OrderStatus status; //주문 상태 [ORDER, CANCLE]

    //==연관관계 편의메서드==//양방향 관계일 때 사용하면 편리하다.
    public void setMember(Member member){
        this.member = new Member();
        member.getOrders().add(this);
    }

    public void setOrderItem(OrderItem orderItem){
        orderItems.add(orderItem);
        orderItem.setOrder(this);
    }

    public void setDelivery(Delivery delivery){
        this.delivery = delivery;
        delivery.setOrder(this);
    }
    /* 연관관계일 때 편의 메서드 설정을 안하게 되는 경우
    public static void main(String[] args){
        Member member = new Member();
        Order order = new Order();

        member.getOrders().add(order);
        order.getMember(member);
    }
     */
}
