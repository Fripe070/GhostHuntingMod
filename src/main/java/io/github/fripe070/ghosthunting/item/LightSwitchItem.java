package io.github.fripe070.ghosthunting.item;

import io.github.fripe070.ghosthunting.block.LightswitchBlockEntity;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.item.BlockItem;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtIntArray;
import net.minecraft.util.ActionResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;

import java.util.List;
import java.util.Objects;

public class LightSwitchItem extends BlockItem {
    public LightSwitchItem(Block block, Settings settings) {
        super(block, settings);
    }

    BlockPos target_block = null;

    @Override
    public ActionResult useOnBlock(ItemUsageContext context) {
        ActionResult actionResult = ActionResult.PASS;
        if (!context.getWorld().isClient()) {
            if (target_block == null) {
                target_block = context.getBlockPos();
                context.getWorld().setBlockState(target_block, Blocks.GRAY_CONCRETE.getDefaultState());

                actionResult = ActionResult.SUCCESS;
            } else {
                actionResult = super.useOnBlock(context);

                BlockPos clickPos = context.getBlockPos();

                switch (context.getSide()) {
                    case NORTH -> clickPos = clickPos.north(1);
                    case SOUTH -> clickPos = clickPos.south(1);
                    case EAST -> clickPos = clickPos.east(1);
                    case WEST -> clickPos = clickPos.west(1);
                }

                BlockEntity blockEntity = (context.getWorld()).getBlockEntity(clickPos);

                if (blockEntity == null) {
                    return ActionResult.FAIL;
                }


                ((LightswitchBlockEntity) blockEntity).activate_position = new int[]{target_block.getX(), target_block.getY(), target_block.getZ()};

                target_block = null;
            }
        }

        return actionResult;
    }
}
