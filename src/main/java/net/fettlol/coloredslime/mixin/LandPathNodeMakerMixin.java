package net.fettlol.coloredslime.mixin;

import com.llamalad7.mixinextras.expression.Definition;
import com.llamalad7.mixinextras.expression.Expression;
import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.block.BlockState;
import net.minecraft.entity.ai.pathing.LandPathNodeMaker;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

import static net.fettlol.coloredslime.util.Helpers.isColoredHoney;

@Mixin(LandPathNodeMaker.class)
public abstract class LandPathNodeMakerMixin {
	@Definition(id = "blockState", local = @Local(type = BlockState.class))
	@Definition(id = "isOf", method = "net/minecraft/block/BlockState.isOf(Lnet/minecraft/block/Block;)Z")
	@Definition(id = "HONEY_BLOCK", field = "Lnet/minecraft/block/Blocks;HONEY_BLOCK:Lnet/minecraft/block/Block;")
	@Expression("blockState.isOf(HONEY_BLOCK)")
	@ModifyExpressionValue(method = "getCommonNodeType", at = @At(value = "MIXINEXTRAS:EXPRESSION"))
	private static boolean isColoredHoneyBlock(boolean original, @Local BlockState blockState) {
		return original || isColoredHoney(blockState);
	}
}
