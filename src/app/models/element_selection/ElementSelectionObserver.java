package app.models.element_selection;

/**
 * Created by Qwerasdzxc on 29/12/2019.
 */
public interface ElementSelectionObserver {

    void addObserver(ElementSelectionListener listener);
    void removeObserver(ElementSelectionListener listener);

    void notifySelectionChanged();
}
