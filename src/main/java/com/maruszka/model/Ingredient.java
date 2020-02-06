package com.maruszka.model;

import com.maruszka.model.association.BatchIngredient;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Setter
@Getter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "type")
public class Ingredient extends BaseEntity {

    @Column(name="name")
    protected String name;

    @OneToMany(mappedBy = "ingredient")
    private Set<BatchIngredient> batches = new HashSet<>();

    //    public Ingredient(Long id) {
//    }

}
