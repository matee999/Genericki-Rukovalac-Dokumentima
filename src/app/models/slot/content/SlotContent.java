package app.models.slot.content;

import java.io.Serializable;

public abstract class SlotContent implements Serializable, Cloneable {

    public abstract SlotContent clone();
}
