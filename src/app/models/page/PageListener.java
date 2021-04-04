package app.models.page;

public interface PageListener {
    default void onPageSelected(Page page) {}
    default void onPageChangedName(String name) {}
    default void onSlotChanged() {}
}
