package net.coloredslime.mixin;

import net.coloredslime.ColoredSlime;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.block.entity.PistonBlockEntity;
import net.minecraft.block.piston.PistonBehavior;
import net.minecraft.entity.Entity;
import net.minecraft.network.packet.s2c.play.EntityVelocityUpdateS2CPacket;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Boxes;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Iterator;
import java.util.List;

@Mixin(value = PistonBlockEntity.class, priority = 450)
public abstract class PistonBlockEntityMixin extends BlockEntity {

    @Shadow
    private BlockState pushedBlock;

    @Shadow
    private float progress;

    @Shadow
    public abstract Direction getFacing();

    @Shadow
    protected abstract BlockState getHeadBlockState();

    @Shadow
    protected abstract Box offsetHeadBox(Box box);

    @Shadow
    public abstract Direction getMovementDirection();

    @Shadow protected abstract float getAmountExtended(float progress);

    @Shadow private float lastProgress;

    @Shadow public abstract boolean isExtending();

    public PistonBlockEntityMixin(BlockEntityType<?> type) {
        super(type);
    }

    @Inject(method = {"isPushingHoneyBlock"}, at = {@At(value = "HEAD")}, cancellable = true)
    public void isPushingHoneyBlock(CallbackInfoReturnable<Boolean> ci) {
        if (ColoredSlime.isColoredSlimeBlock(this.pushedBlock.getBlock())) {
            ci.setReturnValue(false);
        }
    }

    @Inject(method = {"pushEntities"}, at = {@At(value = "HEAD")}, cancellable = true)
    public void pushEntities(float nextProgress, CallbackInfo ci) {
        Direction direction = this.getMovementDirection();
        double d = nextProgress - progress;
        VoxelShape voxelShape = this.getHeadBlockState().getCollisionShape(this.world, this.getPos());

        if (!voxelShape.isEmpty()) {
            Box box = this.offsetHeadBox(voxelShape.getBoundingBox());
            List list = this.world.getOtherEntities((Entity)null, Boxes.stretch(box, direction, d).union(box));

            if (!list.isEmpty()) {
                List list2 = voxelShape.getBoundingBoxes();
                boolean bl = ColoredSlime.isColoredSlimeBlock(this.pushedBlock.getBlock());

                for (Object o : list) {
                    Entity entity = (Entity) o;

                    if (entity.getPistonBehavior() == PistonBehavior.NORMAL || !bl) {
                        Vec3d vec3d = entity.getVelocity();
                        double x = vec3d.x;
                        double y = vec3d.y;
                        double z = vec3d.z;

                        switch (direction.getAxis()) {
                            case X:
                                x = direction.getOffsetX();
                                break;
                            case Y:
                                y = direction.getOffsetY();
                                break;
                            case Z:
                                z = direction.getOffsetZ();
                                break;
                        }

                        entity.setVelocity(x, y, z);

                        if (entity instanceof ServerPlayerEntity) {
                            ((ServerPlayerEntity) entity).networkHandler.sendPacket(new EntityVelocityUpdateS2CPacket(entity));
                        }

                    }
                }
            }
        }

    }

}
