package com.maruszka.model;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Table;

@Setter
@Getter
@NoArgsConstructor
@Entity
@ToString
@Table(name="additive")
//@DiscriminatorValue("Additive")
public class Additive extends Ingredient {

//    @NotBlank(message="{NotBlank.additive.additiveName}")
//    @Column(name="additive_name", unique = true)
//    private String additiveName;

    @Builder
    public Additive(Long id, String name) {
        super(id);
        this.name = name;
    }
}
