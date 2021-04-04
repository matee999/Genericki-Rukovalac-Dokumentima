package app.models.node_factory;

import app.models.AbstractNode;
import app.models.page.Page;

public class PageFactory extends NodeFactory {

    @Override
    public AbstractNode createNode(int number) {
        return new Page(number);
    }
}