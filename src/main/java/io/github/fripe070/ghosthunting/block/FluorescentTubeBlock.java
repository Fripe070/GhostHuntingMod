package io.github.fripe070.ghosthunting.block;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.RedstoneLampBlock;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;

public class FluorescentTubeBlock extends RedstoneLampBlock {
    private boolean flickering = false;

    public FluorescentTubeBlock(Settings settings) {
        super(settings
                .luminance(Blocks.createLightLevelFromLitBlockState(15))
                .ticksRandomly()
        );
    }

    @Override
    @SuppressWarnings("deprecation")
    public void randomTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        if (state.get(LIT) && !flickering) {
            world.setBlockState(pos, state.with(LIT, false));

            // Schedule a tick to turn on again
            flickering = true;
            world.scheduleBlockTick(pos, this, 2);
        }
    }

    public void scheduledTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        super.scheduledTick(state, world, pos, random);
        if (flickering && !state.get(LIT) && world.isReceivingRedstonePower(pos)) {
            world.setBlockState(pos, state.with(LIT, true));
            flickering = false;
        }
    }
}
