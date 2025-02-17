package dev.dubhe.anvilcraft.block;

import dev.dubhe.anvilcraft.api.chargecollector.ChargeCollectorManager;
import dev.dubhe.anvilcraft.api.chargecollector.ChargeCollectorManager.Entry;
import dev.dubhe.anvilcraft.api.hammer.IHammerRemovable;
import dev.dubhe.anvilcraft.block.entity.ChargeCollectorBlockEntity;
import java.util.Collection;
import javax.annotation.Nonnull;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.NotNull;

public class PiezoelectricCrystalBlock extends Block implements IHammerRemovable {
    public static VoxelShape SHAPE = Shapes.or(
        Block.box(0, 14, 0, 16, 16, 16),
        Block.box(2, 2, 2, 14, 14, 14),
        Block.box(0, 0, 0, 16, 2, 16)
    );

    public PiezoelectricCrystalBlock(Properties properties) {
        super(properties);
    }

    @SuppressWarnings("deprecation")
    @Override
    public @Nonnull RenderShape getRenderShape(@Nonnull BlockState state) {
        return RenderShape.MODEL;
    }

    @SuppressWarnings("deprecation")
    @Override
    public @NotNull VoxelShape getShape(
        @NotNull BlockState state, @NotNull BlockGetter level, @NotNull BlockPos pos, @NotNull CollisionContext context
    ) {
        return SHAPE;
    }

    /**
     * 被铁砧砸事件
     */
    public void onHitByAnvil(float fallDistance, Level level, BlockPos blockPos) {
        int distance = (int) Math.min(3, fallDistance);
        int chargeNum = (int) Math.pow(2, distance);
        this.charge(chargeNum, blockPos);
        pressureConduction(level, blockPos, chargeNum / 2, 4);
    }

    private void charge(int chargeNum, BlockPos blockPos) {
        Collection<Entry> chargeCollectorCollection = ChargeCollectorManager
               .getNearestChargeCollect(blockPos);
        double surplus = chargeNum;
        for (Entry entry : chargeCollectorCollection) {
            ChargeCollectorBlockEntity chargeCollectorBlockEntity = entry.getBlockEntity();
            if (!ChargeCollectorManager.canCollect(chargeCollectorBlockEntity, blockPos)) return;
            surplus = chargeCollectorBlockEntity.incomingCharge(surplus);
            if (surplus == 0) return;
        }
    }

    private void pressureConduction(Level level, BlockPos blockPos, int chargeNum, int count) {
        int c = count - 1;
        if (c <= 0) return;
        BlockPos pos = blockPos.below();
        if (level.getBlockState(pos).getBlock() instanceof PiezoelectricCrystalBlock block) {
            if (chargeNum == 0) return;
            this.charge(chargeNum, blockPos);
            block.pressureConduction(level, pos, chargeNum / 2, c);
        }
    }
}
