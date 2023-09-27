package io.github.fripe070.ghosthunting.block;

import net.minecraft.block.BlockEntityProvider;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.LeverBlock;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.event.GameEvent;
import org.jetbrains.annotations.Nullable;

public class LightSwitchBlock extends LeverBlock implements BlockEntityProvider {
    public LightSwitchBlock(Settings settings) {
        super(settings);
    }

    @Nullable
    @Override
    public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new LightswitchBlockEntity(pos, state);
    }

    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        if (world.isClient) return ActionResult.SUCCESS;

        BlockState blockState = this.togglePower(state, world, pos);
        BlockEntity blockEntity = world.getBlockEntity(pos);

        if (!(blockEntity instanceof LightswitchBlockEntity)) return ActionResult.FAIL;

        int[] activate_coordinates = ((LightswitchBlockEntity) blockEntity).activate_position;
        BlockPos activate_position = new BlockPos(
                activate_coordinates[0], 
                activate_coordinates[1], 
                activate_coordinates[2]
        );

        BlockState set_to_blockstate;
        if (blockState.get(POWERED)) {
            set_to_blockstate = Blocks.REDSTONE_BLOCK.getDefaultState();
        }
        else {
            set_to_blockstate = Blocks.GRAY_CONCRETE.getDefaultState();
        }

        world.setBlockState(activate_position, set_to_blockstate);


        float pitch = blockState.get(POWERED) ? 0.6F : 0.5F;

        world.playSound(null, pos, SoundEvents.BLOCK_LEVER_CLICK, SoundCategory.BLOCKS, 0.3F, pitch);
        world.emitGameEvent(player, blockState.get(POWERED) ? GameEvent.BLOCK_ACTIVATE : GameEvent.BLOCK_DEACTIVATE, pos);

        return ActionResult.CONSUME;

    }

    @Override
    public void onPlaced(World world, BlockPos pos, BlockState state, @Nullable LivingEntity placer, ItemStack itemStack) {

    }
}
