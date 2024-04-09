package dev.dubhe.anvilcraft.init;

import com.tterrag.registrate.util.entry.BlockEntityEntry;
import dev.dubhe.anvilcraft.block.entity.AutoCrafterBlockEntity;
import dev.dubhe.anvilcraft.block.entity.ChuteBlockEntity;

import static dev.dubhe.anvilcraft.AnvilCraft.REGISTRATE;

public class ModBlockEntities {
    public static final BlockEntityEntry<AutoCrafterBlockEntity> AUTO_CRAFTER = REGISTRATE
            .blockEntity("auto_crafter", AutoCrafterBlockEntity::createBlockEntity)
            .onRegister(AutoCrafterBlockEntity::onBlockEntityRegister)
            .validBlock(ModBlocks.AUTO_CRAFTER)
            .register();

    public static final BlockEntityEntry<ChuteBlockEntity> CHUTE = REGISTRATE
            .blockEntity("chute", ChuteBlockEntity::createBlockEntity)
            .onRegister(ChuteBlockEntity::onBlockEntityRegister)
            .validBlock(ModBlocks.CHUTE)
            .register();

    public static void register() {
    }
}
