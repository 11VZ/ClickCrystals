package io.github.itzispyder.clickcrystals.gui_beta.elements.client.module;

import io.github.itzispyder.clickcrystals.gui.GuiElement;
import io.github.itzispyder.clickcrystals.gui_beta.misc.Gray;
import io.github.itzispyder.clickcrystals.modules.ModuleSetting;
import io.github.itzispyder.clickcrystals.util.RenderUtils;
import io.github.itzispyder.clickcrystals.util.StringUtils;
import net.minecraft.client.gui.DrawContext;

public abstract class SettingElement<T extends ModuleSetting<?>> extends GuiElement {

    protected final T setting;
    protected boolean shouldUnderline;

    public SettingElement(T setting, int x, int y, int width, int height) {
        super(x, y, width, height);
        this.shouldUnderline = false;
        this.setting = setting;
    }

    public void renderSettingDetails(DrawContext context) {
        int caret = y + 5;
        RenderUtils.drawText(context, setting.getName(), x + 5, caret, 0.6F, false);
        for (String line : StringUtils.wrapLines(setting.getDescription(), 50, true)) {
            caret += 8;
            RenderUtils.drawText(context, "§7" + line, x + 5, caret, 0.5F, false);
        }
        caret += 5;

        this.setHeight(caret - y);

        if (shouldUnderline) {
            caret += 10;
            RenderUtils.drawHorizontalLine(context, x + 5, caret, width - 10, 1, Gray.DARK_GRAY.argb);
        }
    }

    public void setShouldUnderline(boolean shouldUnderline) {
        this.shouldUnderline = shouldUnderline;
    }
}
