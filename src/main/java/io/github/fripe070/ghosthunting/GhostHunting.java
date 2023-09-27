package io.github.fripe070.ghosthunting;

import io.github.fripe070.ghosthunting.block.FluorescentTubeBlock;
import io.github.fripe070.ghosthunting.block.LightSwitchBlock;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.piston.PistonBehavior;
import net.minecraft.item.BlockItem;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.state.property.Properties;
import net.minecraft.util.Identifier;

public class GhostHunting implements ModInitializer {
    public static final String MOD_ID = "ghosthunting";

    public static final Block FLUORESCENT_TUBE = registerBlockWithItem(
            "fluorescent_tube",
            new FluorescentTubeBlock(FabricBlockSettings.create()
            .strength(0.3F)
            .sounds(BlockSoundGroup.GLASS)
            .luminance(state -> state.get(Properties.LIT) ? 15 : 0)
            .pistonBehavior(PistonBehavior.DESTROY)
    ));

    public static final Block LIGHT_SWITCH_BLOCK = registerBlockWithItem(
            "light_switch",
            new LightSwitchBlock(FabricBlockSettings.copyOf(Blocks.LEVER))
    );


    @Override
    public void onInitialize() {

    }

    public static <T extends Block> T registerBlockWithItem(String name, T block) {
        Registry.register(Registries.BLOCK, new Identifier(MOD_ID, name), block);
        Registry.register(Registries.ITEM, new Identifier(MOD_ID, name), new BlockItem(block, new FabricItemSettings()));
        return block;
    }
}
