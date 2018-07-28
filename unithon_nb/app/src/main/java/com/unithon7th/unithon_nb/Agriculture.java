package com.unithon7th.unithon_nb;

/**
 * Created by len on 29/07/2018.
 */

public class Agriculture {
    private static final Agriculture ourInstance = new Agriculture();
    private String whatKind;
    private String detailKind;
    private String CurrentAgriculturing;
    private String howmanyArg;
    private String unit;

    static Agriculture getInstance() {
        return ourInstance;
    }

    private Agriculture() {
        whatKind = "";
        detailKind = "";
        CurrentAgriculturing = "";
        howmanyArg = "";

        unit = "";
    }

    public void setWhatKind(String whatKind) {
        this.whatKind = whatKind;
    }

    public void setDetailKind(String detailKind) {
        this.detailKind = detailKind;
    }

    public void setCurrentAgriculturing(String a) {
        CurrentAgriculturing = a;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public void setHowmanyArg(String howmanyArg) {
        this.howmanyArg = howmanyArg;
    }
}
