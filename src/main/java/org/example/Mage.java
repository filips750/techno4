package org.example;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
public class Mage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    private Long id;
    @Setter @Getter
    private String name;
    @Setter @Getter
    private int level;
    @ManyToOne
    @Setter @Getter
    private Tower tower;
}
