package com.unithon7th.unithon_nb;

/**
 * Created by len on 29/07/2018.
 */

public class LiveStock {

    private static final LiveStock ourInstance = new LiveStock();

    private String whatKind;
    private String detailKind;
    private String countTotalunit;

    static LiveStock getInstance() {
        return ourInstance;
    }

    private LiveStock() {
        whatKind = "";
        detailKind = "";
        countTotalunit = "";
    }

    public void setWhatKind(String whatKind) {
        this.whatKind = whatKind;
    }

    public void setDetailKind(String detailKind) {
        this.detailKind = detailKind;
    }

    public void setCountTotalunit(String countTotalunit) {
        this.countTotalunit = countTotalunit;
    }
}
