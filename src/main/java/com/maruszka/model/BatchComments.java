package com.maruszka.model;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Setter
@Getter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "batch_comments")
@EqualsAndHashCode(exclude = {"batch"}, callSuper = false)
public class BatchComments extends BaseEntity {

    @OneToOne
    private Batch batch;

    @Lob
    private String comment;

}
