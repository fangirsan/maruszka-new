package com.maruszka.model;

import java.util.*;

import javax.persistence.*;

import com.maruszka.model.enums.IngredientType;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@NoArgsConstructor
//@AllArgsConstructor
//@Builder
@Entity
//@ToString
@Table(name="batch")
public class Batch extends BaseEntity {

    @Column(name="batch_number")
    private Integer batchNumber;

    @ManyToOne(fetch=FetchType.EAGER)
    @JoinColumn(name="beer_style_id")
    private BeerStyle beerStyle;

//    @ManyToOne(fetch=FetchType.EAGER)
//    @JoinColumn(name="yeast_id")
//    private Yeast yeast;

//    @ManyToMany(fetch=FetchType.LAZY)
//    @JoinTable(name = "batch_hop",
//            joinColumns = @JoinColumn(name = "batch_id"),
//            inverseJoinColumns = @JoinColumn(name = "hop_id"))
//    private Set<Hop> hops = new HashSet<>();

//    @ManyToMany(cascade= {CascadeType.PERSIST, CascadeType.MERGE,
//            CascadeType.DETACH, CascadeType.REFRESH})
//    @JoinTable(name="batch_malt",
//            joinColumns = @JoinColumn(name="batch_id"),
//            inverseJoinColumns = @JoinColumn(name="malt_id"))
//    private Set<Malt> malts = new HashSet<>();

//    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE,
//            CascadeType.DETACH, CascadeType.REFRESH})
//    @JoinTable(name="batch_additive",
//            joinColumns = @JoinColumn(name="batch_id"),
//            inverseJoinColumns = @JoinColumn(name="additive_id"))
//    private Set<Additive> additives = new HashSet<>();

//    @ManyToMany(fetch=FetchType.LAZY)
//    @JoinTable(name = "ingredients",
//            joinColumns = @JoinColumn(name = "batch_id"),
//            inverseJoinColumns = @JoinColumn(name = "ingredient_id"))
//    private Set<Ingredients> ingredients = new HashSet<>();

    /*
    @OneToMany(mappedBy="project")
    private List<ProjectAssociation> employees;
     */
    @OneToMany(mappedBy = "batch")
//    private Set<Ingredients> malts = new HashSet<>();
    private Set<Ingredients> malts;

    /*
    public void addEmployee(Employee employee, boolean teamLead) {
      ProjectAssociation association = new ProjectAssociation();
      association.setEmployee(employee);
      association.setProject(this);
      association.setEmployeeId(employee.getId());
      association.setProjectId(this.getId());
      association.setIsTeamLead(teamLead);
      if(this.employees == null)
        this.employees = new ArrayList<>();

      this.employees.add(association);
      // Also add the association object to the employee.
      employee.getProjects().add(association);
     */

    public void addMalt(Malt malt, int amount) {
        Ingredients ingredients = new Ingredients();
        ingredients.setMalt(malt);
        ingredients.setBatch(this);
        ingredients.setMaltId(malt.getId());
//        ingredients.setBatchId(this.getId());
        ingredients.setBatchId(1L);
        ingredients.setAmount(amount);
        if(this.malts == null) {
            this.malts = new HashSet<>();
        }

        this.malts.add(ingredients);
        malt.getBatches().add(ingredients);
    }

    @Builder
//    public Batch(Long id, Integer batchNumber, BeerStyle beerStyle, Yeast yeast, Set<Hop> hops, Set<Malt> malts, Set<Additive> additives) {
    public Batch(Long id, Integer batchNumber, BeerStyle beerStyle) {
        super(id);
        this.batchNumber = batchNumber;
        this.beerStyle = beerStyle;
//        this.yeast = yeast;
//        this.hops = hops;
//        this.malts = malts;
//        this.additives = additives;
    }

    @Override
    public String toString() {

//        Set<String> hopsName = new HashSet<>();
//        for (Hop tempHop: hops) {
//            hopsName.add(tempHop.getHopName());
//        }

//        Set<String> maltsName = new HashSet<>();
//        for (Malt tempMalt: malts) {
//            maltsName.add(tempMalt.getMaltName());
//        }

//        Set<String> additivesName = new HashSet<>();
//        for (Additive tempAdditive: additives) {
//            additivesName.add(tempAdditive.getAdditiveName());
//        }

        return "Batch{" +
                "batchNumber=" + batchNumber +
                ", beerStyle=" + beerStyle.getBeerStyleName() +
//                ", yeast=" + yeast.getYeastName() +
//                ", hops=" + hopsName +
//                ", malts=" + maltsName +
//                ", additives=" + additivesName +
                '}';
    }

}
