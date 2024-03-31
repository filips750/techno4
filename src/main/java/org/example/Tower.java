package org.example;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
public class Tower {
    @Id @Setter @Getter
    private String name;
    @Setter @Getter
    private int height;
    @OneToMany
    @Setter @Getter
    private List<Mage> mages;
}
