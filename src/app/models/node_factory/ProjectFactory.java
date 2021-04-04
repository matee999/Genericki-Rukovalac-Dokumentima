package app.models.node_factory;

import app.models.AbstractNode;
import app.models.project.Project;

public class ProjectFactory extends NodeFactory {

    @Override
    public AbstractNode createNode(int number) {
        return new Project(number);
    }
}
