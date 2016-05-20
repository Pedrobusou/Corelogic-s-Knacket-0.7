package uk.co.ribot.Knacket.event;

public class AdSavedEvent {
    public static final String PARAM_CREATE = "create";
    public static final String PARAM_DELETE = "delete";
    public static final String PARAM_EDIT = "edit";
    String mode;

    public AdSavedEvent(String mode) {
        this.mode = mode;
    }

    public AdSavedEvent() {

    }

    public String getMode() {
        return mode;
    }
}
