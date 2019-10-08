package com.maruszka.model;

import java.io.Serializable;

public class IngredientsId implements Serializable {

    private long maltId;
    private long batchId;

    public int hashCode() {
        return (int)(maltId + batchId);
    }

    public boolean equals(Object object) {
        if (object instanceof IngredientsId) {
            IngredientsId otherId = (IngredientsId) object;
            return (otherId.maltId == this.maltId) && (otherId.batchId == this.batchId);
        }
        return false;
    }

}
