package io.github.itzispyder.clickcrystals.gui.elements.cc.settings;

import io.github.itzispyder.clickcrystals.gui.GuiElement;
import io.github.itzispyder.clickcrystals.gui.GuiScreen;
import io.github.itzispyder.clickcrystals.gui.GuiTextures;
import io.github.itzispyder.clickcrystals.gui.TextAlignment;
import io.github.itzispyder.clickcrystals.gui.elements.design.AbstractElement;
import io.github.itzispyder.clickcrystals.gui.elements.design.TextElement;
import io.github.itzispyder.clickcrystals.modules.settings.KeybindSetting;
import io.github.itzispyder.clickcrystals.util.DrawableUtils;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.util.Identifier;
import org.lwjgl.glfw.GLFW;

import static io.github.itzispyder.clickcrystals.ClickCrystals.mc;

public class KeybindSettingElement extends GuiElement {

    private final KeybindSetting setting;
    private String display;

    public KeybindSettingElement(KeybindSetting setting, int x, int y, int width, int height) {
        super(x, y, width, height);
        this.setting = setting;
        this.display = null;

        TextElement title = new TextElement(setting.getName(), TextAlignment.LEFT, 0.5F, x + 105, y);
        TextElement desc = new TextElement("§7" + setting.getDescription(), TextAlignment.LEFT, 0.45F, title.x, title.y + 5);
        this.addChild(title);
        this.addChild(desc);

        AbstractElement reset = new AbstractElement(x + 92, y, height, height, (context, mouseX, mouseY, button) -> {
            context.drawTexture(GuiTextures.RESET, button.x, button.y, 0, 0, button.width, button.height, button.width, button.height);
        }, (button) -> {
            setting.setKey(setting.getDefKey());
        });
        this.addChild(reset);
    }

    @Override
    public void onRender(DrawContext context, int mouseX, int mouseY) {
        if (mc.currentScreen instanceof GuiScreen screen) {
            Identifier texture = screen.selected == this ? GuiTextures.SETTING_KEYBIND_SELECTED : GuiTextures.SETTING_KEYBIND;
            context.drawTexture(texture, x, y, 0, 0, width, height, width, height);

            updateDisplay();
            DrawableUtils.drawCenteredText(context, display, x + width / 2, y + (int)(height * 0.28), 0.75F, true);
        }
    }

    @Override
    public void onClick(double mouseX, double mouseY, int button) {
        if (mc.currentScreen instanceof GuiScreen screen) {
            screen.selected = this;
        }
    }

    public void onKey(int key, int scanCode) {
        if (mc.currentScreen instanceof GuiScreen screen) {
            setting.setKey(key);
            screen.selected = null;
        }
    }

    public String getDisplay() {
        return display;
    }

    public void updateDisplay() {
        int key = setting.getKey();
        String name = GLFW.glfwGetKeyName(key, 0);

        name = name != null ? "[" + name.toUpperCase() + "]" : "NONE";
        display = name;
    }
}
