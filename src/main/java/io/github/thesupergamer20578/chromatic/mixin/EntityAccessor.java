package io.github.thesupergamer20578.chromatic.mixin;

import net.minecraft.entity.Entity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

@SuppressWarnings("BooleanMethodNameMustStartWithQuestion")
@Mixin(Entity.class)
public interface EntityAccessor {
    @Invoker("isBeingRainedOn")
    boolean invokeIsBeingRainedOn();
}
