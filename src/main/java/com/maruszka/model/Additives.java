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
@Table(name="additives")
public class Additives extends BaseEntity {

    @NotBlank(message="{NotBlank.additives.additivesName}")
    @Column(name="additives_name", unique = true)
    private String additivesName;

    @ManyToMany(mappedBy="additives")
    private Set<Batch> batches;

    @Builder
    public Additives(Long id, String additivesName) {
        super(id);
        this.additivesName = additivesName;
    }
}
