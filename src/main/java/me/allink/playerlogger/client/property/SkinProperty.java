package me.allink.playerlogger.client.property;

public class SkinProperty {
    String value;
    String signature;

    public SkinProperty() {

    }

    public SkinProperty(String value, String signature) {
        this.value = value;
        this.signature = signature;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }
}
