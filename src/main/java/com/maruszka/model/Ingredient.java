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
//@MappedSuperclass
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "in_type")
public class Ingredient extends BaseEntity {

    @Column(name="in_name", unique = true)
    protected String maltName;

    @OneToMany(mappedBy = "ingredient")
    private Set<BatchIngredient> batches = new HashSet<>();

    public Ingredient(Long id) {
    }

}
