package org.uengine.processmarket;

import org.metaworks.annotation.Face;

@Face(
	ejsPath="genericfaces/Window.ejs",
	options={"hideAddBtn", "hideLabels"},
	values={"true", "true"},
	displayName="$Category"
)
public class MarketCategoryPanel {

    ICategory category;

    public ICategory getCategory() {
        return category;
    }

    public void setCategory(ICategory category) {
        this.category = category;
    }
}
