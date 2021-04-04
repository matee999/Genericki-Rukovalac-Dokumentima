package app.models.slot.content;

import java.awt.*;
import java.io.Serializable;
import java.util.ArrayList;

public class TextSlotContent extends SlotContent {

    private String content;
    private ArrayList<FormatType> formats;
    private Color color;
    private String fontStyle;
    private int fontSize;

    public TextSlotContent() {
        this.content = "";
        this.formats = new ArrayList<>();
        this.color = Color.BLACK;
        this.fontStyle = "Arial";
        this.fontSize = 14;
    }

    public TextSlotContent(TextSlotContent content) {
        this.content = content.content;
        this.formats = new ArrayList<>(content.formats);
        this.color = content.color;
        this.fontStyle = content.fontStyle;
        this.fontSize = content.fontSize;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public ArrayList<FormatType> getFormats() {
        return formats;
    }

    public void setFormats(ArrayList<FormatType> formats) {
        this.formats = formats;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public String getFontStyle() {
        return fontStyle;
    }

    public void setFontStyle(String fontStyle) {
        this.fontStyle = fontStyle;
    }

    public int getFontSize() {
        return fontSize;
    }

    public void setFontSize(int fontSize) {
        this.fontSize = fontSize;
    }

    @Override
    public SlotContent clone() {
        return new TextSlotContent(this);
    }

    public enum FormatType {
        BOLD, ITALIC
    }
}
