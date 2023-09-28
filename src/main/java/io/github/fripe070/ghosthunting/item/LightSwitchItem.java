package io.github.fripe070.ghosthunting.item;

import io.github.fripe070.ghosthunting.block.LightswitchBlockEntity;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.item.BlockItem;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.util.ActionResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class LightSwitchItem extends BlockItem {
    public LightSwitchItem(Block block, Settings settings) {
        super(block, settings);
    }

    BlockPos targetBlock = null;

    @Override
    public ActionResult useOnBlock(ItemUsageContext context) {
        ActionResult actionResult;
        World world = context.getWorld();
        if (world.isClient()) return ActionResult.PASS;

        if (targetBlock == null) {
            targetBlock = context.getBlockPos();
            world.setBlockState(targetBlock, Blocks.GRAY_CONCRETE.getDefaultState());

            actionResult = ActionResult.SUCCESS;
        } else {
            // Place lightswitch
            actionResult = super.useOnBlock(context);

            ItemPlacementContext placementContext = this.getPlacementContext(new ItemPlacementContext(context));
            if (placementContext == null) return ActionResult.FAIL;
            BlockEntity lightswitchBlockEntity = world.getBlockEntity(placementContext.getBlockPos());
            if (!(lightswitchBlockEntity instanceof LightswitchBlockEntity)) return ActionResult.FAIL;

            // Update the nbt data of the lightswitch block entity
            ((LightswitchBlockEntity) lightswitchBlockEntity).activate_position = new int[]{
                    targetBlock.getX(),
                    targetBlock.getY(),
                    targetBlock.getZ(),
            };
            targetBlock = null;
        }

        return actionResult;
    }
}
