package net.fettlol.coloredslime.mixin;

import com.llamalad7.mixinextras.injector.v2.WrapWithCondition;
import net.minecraft.block.HoneyBlock;
import net.minecraft.block.TransparentBlock;
import net.minecraft.entity.Entity;
import net.minecraft.particle.BlockStateParticleEffect;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(HoneyBlock.class)
public abstract class HoneyBlockMixin extends TransparentBlock {
	public HoneyBlockMixin(Settings settings) {
		super(settings);
	}

	@WrapWithCondition(
		method = "onLandedUpon",
		at = @At(value = "INVOKE", target = "Lnet/minecraft/world/World;sendEntityStatus(Lnet/minecraft/entity/Entity;B)V")
	)
	public boolean spawnRichColoredHoneyParticles(World instance, Entity entity, byte status) {
		if (instance instanceof ServerWorld serverWorld) {
			serverWorld.spawnParticles(new BlockStateParticleEffect(ParticleTypes.BLOCK, getDefaultState()), entity.getX(), entity.getY(), entity.getZ(), 10, 0, 0, 0, 0);
			return false;
		} else {
			return true;
		}
	}

	@WrapWithCondition(
		method = "addCollisionEffects",
		at = @At(value = "INVOKE", target = "Lnet/minecraft/world/World;sendEntityStatus(Lnet/minecraft/entity/Entity;B)V")
	)
	public boolean spawnRegularColoredHoneyParticles(World instance, Entity entity, byte status) {
		if (instance instanceof ServerWorld serverWorld) {
			serverWorld.spawnParticles(new BlockStateParticleEffect(ParticleTypes.BLOCK, getDefaultState()), entity.getX(), entity.getY(), entity.getZ(), 5, 0, 0, 0, 0);
			return false;
		} else {
			return true;
		}
	}
}
