package org.example.tools;

import org.example.model.abstraction.IHaveHierarchicalStructure;

import java.util.List;

public class HierarchyBuilder<TItem extends IHaveHierarchicalStructure> {

    private List<TItem> elements;
    private TItem root;

    public void setElements(List<TItem> elements) {
        this.elements = elements;
    }

    public void buildHierarchy() {
        for (TItem element:elements) {
            Integer parentId = element.getParentId();
            if (parentId != null){
                TItem parent = findParent(parentId);
                element.setParent(parent); //ustawi znalezionego rodzica jako rodzica bieżącego elementu
                parent.getChildren().add(element); //dodanie bieżącego elementu do listy elementów potomnych rodzica
            } else {
                root = element; //jeśli parentId ma wartość null, bieżący element jest uważany jako root
            }
        }
    }

    private TItem findParent(Integer index) {
        for (TItem element: elements) {
            if (index == element.getId()) {
                return element;
            }
        }
        return null;
    }

    public TItem getRootElement() {
        return root;
    }
}
