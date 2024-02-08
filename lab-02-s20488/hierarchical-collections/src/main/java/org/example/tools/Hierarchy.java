package org.example.tools;

import org.example.model.abstraction.IHaveHierarchicalStructure;

import java.util.LinkedList;
import java.util.Queue;

public class Hierarchy<TItem extends IHaveHierarchicalStructure> {
    private TItem root;

    public void setRootElement(TItem tItem) {
        root = tItem;
    }

    public TItem findElementById(int index) {
        Queue<TItem> queue = new LinkedList<>(root.getChildren());
        TItem result = null;

        while (!queue.isEmpty() && result == null) {
            TItem pulled = queue.poll();

            if (pulled.getId() == index) {
                result = pulled;
            } else {
                queue.addAll(pulled.getChildren());
            }
        }

        return result;
    }
}
