package app.models.page;

public interface PageObserver {
    void addObserver(PageListener listener);
    void removeObserver(PageListener listener);

    void notifyPageSelected(Page page);
    void notifyPageChangedName(String name);
    void notifySlotChanged();
}
