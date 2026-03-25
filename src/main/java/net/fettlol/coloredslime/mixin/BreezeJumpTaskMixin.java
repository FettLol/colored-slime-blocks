package net.fettlol.coloredslime.mixin;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.monster.breeze.Breeze;
import net.minecraft.world.entity.monster.breeze.LongJump;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

import static net.fettlol.coloredslime.util.Helpers.isColoredHoney;

@Mixin(LongJump.class)
public abstract class BreezeJumpTaskMixin {
	@ModifyExpressionValue(method = "canJumpFromCurrentPosition", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/level/block/state/BlockState;is(Ljava/lang/Object;)Z"))
	private static boolean isColoredHoneyBlock(boolean original, @Local(argsOnly = true) ServerLevel world, @Local(argsOnly = true) Breeze breeze) {
		return original || isColoredHoney(world.getBlockState(breeze.blockPosition()));
	}
}
