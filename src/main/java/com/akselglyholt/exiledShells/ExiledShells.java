package com.akselglyholt.exiledShells;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.loot.v3.LootTableEvents;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.storage.loot.BuiltInLootTables;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.EmptyLootItem;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;
import net.minecraft.world.level.storage.loot.providers.number.NumberProvider;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;

public class ExiledShells implements ModInitializer {

    public static final String MOD_ID = "exiled-shells";

    @Override
    public void onInitialize() {
        LootTableEvents.MODIFY.register((key, tableBuilder, source, registries) -> {
            if (!source.isBuiltin()) return;

            addShulkerShell(key, tableBuilder, BuiltInLootTables.SIMPLE_DUNGEON,                     1, 9,  1, 1);   // ~10%, always 1
            addShulkerShell(key, tableBuilder, BuiltInLootTables.ABANDONED_MINESHAFT,                1, 17, 1, 1);   // ~5%, always 1
            addShulkerShell(key, tableBuilder, BuiltInLootTables.ANCIENT_CITY,                       1, 4,  1, 3);   // ~20%, 1-3 (2-3 rare)
            addShulkerShell(key, tableBuilder, BuiltInLootTables.BASTION_TREASURE,                   1, 4,  1, 2);   // ~20%, always 1
            addShulkerShell(key, tableBuilder, BuiltInLootTables.DESERT_PYRAMID,                     1, 9,  1, 1);   // ~10%, always 1
            addShulkerShell(key, tableBuilder, BuiltInLootTables.RUINED_PORTAL,                      1, 14, 1, 1);   // ~6.7%, always 1
            addShulkerShell(key, tableBuilder, BuiltInLootTables.WOODLAND_MANSION,                   1, 6,  1, 2);   // ~14%, rarely 2
            addShulkerShell(key, tableBuilder, BuiltInLootTables.TRIAL_CHAMBERS_REWARD_OMINOUS_COMMON,1, 4,  1, 2);   // ~20%, rarely 2
            addShulkerShell(key, tableBuilder, BuiltInLootTables.TRIAL_CHAMBERS_REWARD_RARE,         1, 7,  1, 1);   // ~14%, always 1
        });
    }

    private void addShulkerShell(ResourceKey<LootTable> key, LootTable.Builder tableBuilder,
                                 ResourceKey<LootTable> table, int itemWeight, int emptyWeight,
                                 int minCount, int maxCount) {
        if (!table.equals(key)) return;

        NumberProvider count = minCount == maxCount
                ? ConstantValue.exactly(minCount)
                : UniformGenerator.between(minCount, maxCount);

        tableBuilder.withPool(LootPool.lootPool()
                .setRolls(ConstantValue.exactly(1.0f))
                .add(LootItem.lootTableItem(Items.SHULKER_SHELL)
                        .setWeight(itemWeight)
                        .apply(SetItemCountFunction.setCount(count))
                )
                .add(EmptyLootItem.emptyItem()
                        .setWeight(emptyWeight))
        );
    }
}
