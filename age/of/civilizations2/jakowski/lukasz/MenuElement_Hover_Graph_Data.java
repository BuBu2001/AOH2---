/*
 * Decompiled with CFR 0.152.
 */
package age.of.civilizations2.jakowski.lukasz;

class MenuElement_Hover_Graph_Data {
    private int iCivID;
    private String sText;

    protected MenuElement_Hover_Graph_Data(int iCivID, String sText) {
        this.iCivID = iCivID;
        this.sText = sText;
    }

    protected final int getCivID() {
        return this.iCivID;
    }

    protected final void setCivID(int iCivID) {
        this.iCivID = iCivID;
    }

    protected final String getText() {
        return this.sText;
    }

    protected final void setText(String sText) {
        this.sText = sText;
    }
}

