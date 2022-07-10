package com.moringaschool.medicare.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Medicare {
    @SerializedName("showMedicare")
    @Expose
    private Boolean showMedicare;
    @SerializedName("medicareData")
    @Expose
    private MedicareData medicareData;

    /**
     * No args constructor for use in serialization
     *
     */
    public Medicare() {
    }

    /**
     *
     * @param medicareData
     * @param showMedicare
     */
    public Medicare(Boolean showMedicare, MedicareData medicareData) {
        super();
        this.showMedicare = showMedicare;
        this.medicareData = medicareData;
    }

    public Boolean getShowMedicare() {
        return showMedicare;
    }

    public void setShowMedicare(Boolean showMedicare) {
        this.showMedicare = showMedicare;
    }

    public MedicareData getMedicareData() {
        return medicareData;
    }

    public void setMedicareData(MedicareData medicareData) {
        this.medicareData = medicareData;
    }
}
