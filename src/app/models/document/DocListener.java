package app.models.document;

import app.models.page.Page;

public interface DocListener {
    default void onPageCreated(Page page) {};
    default void onPageDeleted(Page page, int index) {};

    default void onDocumentSelected(Document document) {};
    default void onDocumentChangedName(Document document) {};
}
