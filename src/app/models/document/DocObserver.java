package app.models.document;

import app.models.page.Page;

public interface DocObserver {
    void addObserver(DocListener listener);
    void removeObserver(DocListener listener);

    void notifyPageCreated(Page document);
    void notifyPageDeleted(Page document, int index);

    void notifyDocumentSelected();
    void notifyDocumentChangedName();
}
