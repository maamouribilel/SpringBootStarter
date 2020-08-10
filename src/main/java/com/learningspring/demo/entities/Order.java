package com.learningspring.demo.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.springframework.hateoas.RepresentationModel;

import javax.persistence.*;

@Entity
@Data
@Table(name = "orders")
public class Order extends RepresentationModel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long orderId;
    private String orderDescription;
    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    private User user;

    public Order() {
    }


}
