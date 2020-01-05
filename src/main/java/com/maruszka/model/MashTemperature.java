package com.maruszka.model;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Table;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table
public class MashTemperature extends BaseEntity {

    // https://byo.com/article/the-science-of-step-mashing/
    // https://grainfather.com/step-mashing-what-is-it-and-why-do-it-2/
    // https://www.wiki.piwo.org/Zacieranie_ziarna
    // http://braukaiser.com/wiki/index.php/The_Theory_of_Mashing?fbclid=IwAR0cQZKfFTbYAKWmGOIyQ5NwLvP8f7G5g2e6kebNkF9uDz6VekIWo0s7ZRA

    private String restName;
    private Integer temp1;
    private Integer temp2;
    private String description;

//    @OneToMany(mappedBy = "maltConversionRest")
//    private Set<BatchMaltConversionRest> batches = new HashSet<>();
//
//    public MaltConversionRest(Long id, Set<BatchMaltConversionRest> batches) {
//        this.batches = batches;
//    }

}
