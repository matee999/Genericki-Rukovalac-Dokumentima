package app.actions;

public class ActionManager {

    private static ActionManager instance;

	private ActNodeDelete deleteNode;
	private ActNodeRename renameNode;
	private ActNewNode newNode;

	private ActSaveWorkspace saveWorkspace;
	private ActSwitchWorkspace switchWorkspace;

	private ActSaveProject saveProject;
	private ActSaveProjectAs saveProjectAs;
	private ActOpenProject openProject;

	private ActShareDocument shareDocument;

	private ActSlotsCut slotsCut;
	private ActSlotsCopy slotsCopy;
	private ActSlotsPaste slotsPaste;

	private ActDocumentCut documentCut;
	private ActDocumentCopy documentCopy;
	private ActDocumentPaste documentPaste;

	private ActUndo undo;
	private ActRedo redo;

    private ActionManager() {
    	deleteNode= new ActNodeDelete();
		renameNode = new ActNodeRename();
		newNode = new ActNewNode();
		saveWorkspace = new ActSaveWorkspace();
		switchWorkspace = new ActSwitchWorkspace();
		saveProject = new ActSaveProject();
		saveProjectAs = new ActSaveProjectAs();
		openProject = new ActOpenProject();
		shareDocument = new ActShareDocument();
		slotsCut = new ActSlotsCut();
		slotsCopy = new ActSlotsCopy();
		slotsPaste = new ActSlotsPaste();
		documentCut = new ActDocumentCut();
		documentCopy = new ActDocumentCopy();
		documentPaste = new ActDocumentPaste();
		undo = new ActUndo();
		redo = new ActRedo();
    }
    
    public static ActionManager getInstance() {
        if (instance == null)
            instance = new ActionManager();

        return instance;
    }

	public ActNodeDelete getDeleteNode() {
		return deleteNode;
	}

	public ActNodeRename getRenameNode() {
		return renameNode;
	}

	public ActNewNode getNewNode() {
		return newNode;
	}

	public ActSaveWorkspace getSaveWorkspace() {
		return saveWorkspace;
	}

	public ActSwitchWorkspace getSwitchWorkspace() {
		return switchWorkspace;
	}

	public ActSaveProject getSaveProject() {
		return saveProject;
	}

	public ActOpenProject getOpenProject() {
		return openProject;
	}

	public ActSaveProjectAs getSaveProjectAs() {
		return saveProjectAs;
	}

	public ActShareDocument getShareDocument() {
		return shareDocument;
	}

	public ActSlotsCut getSlotsCut() {
		return slotsCut;
	}

	public ActSlotsCopy getSlotsCopy() {
		return slotsCopy;
	}

	public ActSlotsPaste getSlotsPaste() {
    	return slotsPaste;
	}

	public ActDocumentCut getDocumentCut() {
    	return documentCut;
	}

	public ActDocumentCopy getDocumentCopy() {
		return documentCopy;
	}

	public ActDocumentPaste getDocumentPaste() {
		return documentPaste;
	}

	public ActUndo getUndo() {
		return undo;
	}

	public ActRedo getRedo() {
		return redo;
	}
}
