package app.models.slot.content;

import java.io.File;

public class ImageSlotContent extends SlotContent {

    private File imagePath;

    public ImageSlotContent() {}

    public ImageSlotContent(ImageSlotContent content) {
        if (content.imagePath != null)
            imagePath = new File(content.imagePath.toURI());
    }

    public File getImagePath() {
        return imagePath;
    }

    public void setImagePath(File imagePath) {
        this.imagePath = imagePath;
    }

    @Override
    public SlotContent clone() {
        return new ImageSlotContent(this);
    }
}
