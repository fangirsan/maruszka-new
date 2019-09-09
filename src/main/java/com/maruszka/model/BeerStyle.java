package com.maruszka.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.*;

import com.maruszka.constraints.bigDecimal.BigDecimalNotNullConstraint;
import com.maruszka.constraints.integer.IntegerNotNullConstraint;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;
import java.math.MathContext;

@Setter
@Getter
@NoArgsConstructor
@Entity
@ToString
@Table(name="beer_style")
public class BeerStyle extends BaseEntity {

    @NotBlank
    @Column(name="beer_style_name", unique = true)
    private String beerStyleName;

    @DecimalMin(value = "0", inclusive = false)
    @Digits(integer=3, fraction=1)
    @BigDecimalNotNullConstraint
    @Column(name="original_blg_1", precision = 3, scale = 1)
    private BigDecimal originalBLG1;

    @DecimalMin(value = "0", inclusive = false)
    @Digits(integer=3, fraction=1)
    @BigDecimalNotNullConstraint
    @Column(name="final_blg_1", precision = 3, scale = 1)
    private BigDecimal finalBLG1;

    @DecimalMin(value = "0", inclusive = false)
    @Digits(integer=3, fraction=1)
    @BigDecimalNotNullConstraint
    @Column(name="original_blg_2", precision = 3, scale = 1)
    private BigDecimal originalBLG2;

    @DecimalMin(value = "0", inclusive = false)
    @Digits(integer=3, fraction=1)
    @BigDecimalNotNullConstraint
    @Column(name="final_blg_2", precision = 3, scale = 1)
    private BigDecimal finalBLG2;

    @Column(name="original_gravity_1", precision = 4, scale = 3)
    private BigDecimal originalGravity1;

    @Column(name="final_gravity_1", precision = 4, scale = 3)
    private BigDecimal finalGravity1;

    @Column(name="original_gravity_2", precision = 4, scale = 3)
    private BigDecimal originalGravity2;

    @Column(name="final_gravity_2", precision = 4, scale = 3)
    private BigDecimal finalGravity2;

    @Min(1)
    @IntegerNotNullConstraint
    @Column(name="ebc1")
    private Integer ebc1;

    @Min(1)
    @IntegerNotNullConstraint
    @Column(name="ebc2")
    private Integer ebc2;

    @Column(name="srm1")
    private Integer srm1;

    @Column(name="srm2")
    private Integer srm2;

    @DecimalMin(value = "0", inclusive = false)
    @Digits(integer=3, fraction=1)
    @BigDecimalNotNullConstraint
    @Column(name="abv1", precision = 3, scale = 1)
    private BigDecimal abv1;

    @DecimalMin(value = "0", inclusive = false)
    @Digits(integer=3, fraction=1)
    @BigDecimalNotNullConstraint
    @Column(name="abv2", precision = 3, scale = 1)
    private BigDecimal abv2;

    @Min(1)
    @IntegerNotNullConstraint
    @Column(name = "ibu1")
    private Integer ibu1;

    @Min(1)
    @IntegerNotNullConstraint
    @Column(name = "ibu2")
    private Integer ibu2;

    public BigDecimal getOriginalGravity1() {
        return this.originalGravity1 = calculateBallingToGravity(originalBLG1).setScale(3, BigDecimal.ROUND_HALF_UP);
    }

    public BigDecimal getFinalGravity1() {
        return this.finalGravity1 = calculateBallingToGravity(finalBLG1).setScale(3, BigDecimal.ROUND_HALF_UP);
    }

    public BigDecimal getOriginalGravity2() {
        return this.originalGravity2 = calculateBallingToGravity(originalBLG2).setScale(3, BigDecimal.ROUND_HALF_UP);
    }

    public BigDecimal getFinalGravity2() {
        return this.finalGravity2 = calculateBallingToGravity(finalBLG2).setScale(3, BigDecimal.ROUND_HALF_UP);
    }

    public Integer getSrm1() {
        return this.srm1 = calculateEbcToSrm(ebc1);
    }

    public Integer getSrm2() {
        return this.srm2 = calculateEbcToSrm(ebc2);
    }

    public Integer calculateEbcToSrm(Integer ebc) {
        // SRM = EBC x 0.508
        double srm = Math.round(ebc * 0.508);
        return Integer.valueOf((int) srm);
    }

    private BigDecimal calculateBallingToGravity(BigDecimal blg) {
        // SG = 1+ (plato / (258.6 – ( (plato/258.2) *227.1) ) )
        Double doubleGravity = 1 + ( blg.doubleValue() / (258.6 - ( (blg.doubleValue() / 258.2) * 227.1)));
        return new BigDecimal(doubleGravity, MathContext.DECIMAL64);
    }

    @Builder
    public BeerStyle(Long id, String beerStyleName, BigDecimal originalBLG1, BigDecimal finalBLG1,
                     BigDecimal originalBLG2, BigDecimal finalBLG2, Integer ebc1, Integer ebc2,
                     BigDecimal abv1, BigDecimal abv2, Integer ibu1 , Integer ibu2) {
        super(id);
        this.beerStyleName = beerStyleName;
        if (originalBLG1 != null) {
            this.originalBLG1 = originalBLG1.setScale(1, BigDecimal.ROUND_HALF_UP);
            this.originalGravity1 = calculateBallingToGravity(originalBLG1).setScale(3, BigDecimal.ROUND_HALF_UP);
        }
        if (finalBLG1 != null) {
            this.finalBLG1 = finalBLG1.setScale(1, BigDecimal.ROUND_HALF_UP);
            this.finalGravity1 = calculateBallingToGravity(finalBLG1).setScale(3, BigDecimal.ROUND_HALF_UP);
        }
        if (originalBLG2 != null) {
            this.originalBLG2 = originalBLG2.setScale(1, BigDecimal.ROUND_HALF_UP);
            this.originalGravity2 = calculateBallingToGravity(originalBLG2).setScale(3, BigDecimal.ROUND_HALF_UP);
        }
        if (finalBLG2 != null) {
            this.finalBLG2 = finalBLG2.setScale(1, BigDecimal.ROUND_HALF_UP);
            this.finalGravity2 = calculateBallingToGravity(finalBLG2).setScale(3, BigDecimal.ROUND_HALF_UP);
        }
        if (ebc1 != null) {
            this.srm1 = calculateEbcToSrm(ebc1);
        }
        if (ebc2 != null) {
            this.srm2 = calculateEbcToSrm(ebc2);
        }
        if (abv1 != null) {
            this.abv1 = abv1.setScale(1, BigDecimal.ROUND_HALF_UP);
        }
        if (abv2 != null) {
            this.abv2 = abv2.setScale(1, BigDecimal.ROUND_HALF_UP);
        }
        this.ebc1 = ebc1;
        this.ebc2 = ebc2;
        this.ibu1 = ibu1;
        this.ibu2 = ibu2;
    }

}