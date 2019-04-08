package be.witspirit.pubnub.exp;

public class ExpMessage {
    private String type;
    private String text;

    public ExpMessage(String type, String text) {
        this.type = type;
        this.text = text;
    }

    public String getType() {
        return type;
    }

    public String getText() {
        return text;
    }

    @Override
    public String toString() {
        return type+":"+text;
    }
}
