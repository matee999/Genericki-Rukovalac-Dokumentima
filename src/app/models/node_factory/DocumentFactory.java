package app.models.node_factory;

import app.models.AbstractNode;
import app.models.document.Document;

public class DocumentFactory extends NodeFactory {

    @Override
    public AbstractNode createNode(int number) {
        return new Document(number);
    }
}
