package net.enderman999517.funnymodfortesting.screen;

import com.mojang.blaze3d.systems.RenderSystem;
import net.enderman999517.funnymodfortesting.FunnyModForTesting;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.ingame.HandledScreen;
import net.minecraft.client.gui.tooltip.Tooltip;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.render.GameRenderer;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class CompactingScreen extends HandledScreen<CompactingScreenHandler> {
    public static final Identifier TEXTURE = new Identifier(FunnyModForTesting.MOD_ID, "textures/gui/compactor_gui.png");

    public CompactingScreen(CompactingScreenHandler handler, PlayerInventory inventory, Text title) {
        super(handler, inventory, title);
    }

    //public ButtonWidget button;
//
    //@Override
    //protected void init() {
    //    button = ButtonWidget.builder(Text.literal("Button"), button -> {
    //        FunnyModForTesting.LOGGER.info("button pressed");
    //    })
    //            .dimensions(width / 2 - 85, height/2, 20, 20)
    //            .tooltip(Tooltip.of(Text.literal("button tooltip")))
    //            .build();
    //    addDrawableChild(button);
    //}

    @Override
    protected void drawBackground(DrawContext context, float delta, int mouseX, int mouseY) {
        RenderSystem.setShader(GameRenderer::getPositionTexProgram);
        RenderSystem.setShaderColor(1f, 1f, 1f, 1f);
        RenderSystem.setShaderTexture(0, TEXTURE);
        int x = (width - backgroundWidth) / 2;
        int y = (height - backgroundHeight) / 2;

        context.drawTexture(TEXTURE, x, y, 0, 0, backgroundWidth, backgroundHeight);

        renderProgressArrow(context, x, y);
    }

    private void renderProgressArrow(DrawContext context, int x, int y) {
        if (handler.isCrafting()) {
            context.drawTexture(TEXTURE, x + 85, y + 30, 176, 0, 8, handler.getScaledProgress()); /* change */
        }
    }

    @Override
    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
        renderBackground(context);
        super.render(context, mouseX, mouseY, delta);
        drawMouseoverTooltip(context, mouseX, mouseY);
    }
}
