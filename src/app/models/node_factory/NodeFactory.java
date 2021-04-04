package app.models.node_factory;

import app.models.AbstractNode;
import app.models.document.Document;
import app.models.project.Project;

public abstract class NodeFactory {

    public AbstractNode deliverNode(AbstractNode parent) {
        AbstractNode node = createNode(parent.getChildCount() + 1);
        parent.addChild(node);

        if (node instanceof Document) {
            Document document = (Document) node;
            document.addParent((Project) parent);
        }

        return node;
    }

    public abstract AbstractNode createNode(int number);
}
