package com.maruszka.model;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import java.util.Set;

@Setter
@Getter
@NoArgsConstructor
@Entity
@ToString
@Table(name="additive")
public class Additive extends BaseEntity {

    @NotBlank(message="{NotBlank.additive.additiveName}")
    @Column(name="additive_name", unique = true)
    private String additiveName;

    @ManyToMany(mappedBy="additives")
    private Set<Batch> batches;

    @Builder
    public Additive(Long id, String additiveName) {
        super(id);
        this.additiveName = additiveName;
    }
}