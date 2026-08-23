package net.enderman999517.funnymodfortesting.mixin;

import net.irisshaders.iris.gui.element.ShaderPackSelectionList;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(net.irisshaders.iris.gui.element.ShaderPackSelectionList.class)
public interface ShaderPackSelectionListAccessor {
    @Accessor("applied")
    net.irisshaders.iris.gui.element.ShaderPackSelectionList.ShaderPackEntry iris$setApplied(ShaderPackSelectionList.ShaderPackEntry entry);
}
