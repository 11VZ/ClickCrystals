package io.github.itzispyder.clickcrystals.modules.modules.rendering;

import io.github.itzispyder.clickcrystals.modules.Categories;
import io.github.itzispyder.clickcrystals.modules.Module;

public class NoOverlay extends Module {

    public NoOverlay() {
        super("NoGameOverlay", Categories.RENDERING,"Stops various overlays from rendering.");
    }

    @Override
    protected void onEnable() {

    }

    @Override
    protected void onDisable() {

    }
}
