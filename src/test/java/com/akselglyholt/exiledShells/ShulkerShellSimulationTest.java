package com.akselglyholt.exiledShells;

import java.util.Random;

import org.junit.jupiter.api.Test;

/**
 * Simulates shulker shell drops across all modified structures.
 *
 * Each structure has an estimated chest count (based on typical world generation)
 * and the loot pool parameters from ExiledShells. The test rolls every chest
 * many times and reports the average shells per structure visit.
 */
public class ShulkerShellSimulationTest {

    private static final int SIMULATIONS = 100_000;
    private static final Random RNG = new Random(42);

    record Structure(String name, int estimatedChests, int itemWeight, int emptyWeight, int minCount, int maxCount) {
        double dropChance() {
            return (double) itemWeight / (itemWeight + emptyWeight);
        }
    }

    private static final Structure[] STRUCTURES = {
        new Structure("Ancient City",                    30, 1, 1,  2, 4),
        new Structure("Bastion Treasure",                 4, 2, 3,  2, 3),
        new Structure("Bastion Other",                    6, 1, 4,  1, 2),
        new Structure("Bastion Bridge",                   2, 1, 4,  1, 2),
        new Structure("Bastion Hoglin Stable",            4, 1, 6,  1, 1),
        new Structure("TC Reward Ominous Rare",           3, 1, 1,  2, 3),
        new Structure("TC Reward Ominous Common",        12, 1, 2,  1, 2),
        new Structure("TC Reward Rare",                   6, 1, 3,  1, 2),
        new Structure("TC Reward Common",                12, 1, 4,  1, 1),
        new Structure("Woodland Mansion",                20, 1, 3,  1, 2),
        new Structure("Simple Dungeon",                   2, 1, 6,  1, 1),
        new Structure("Desert Pyramid",                   4, 1, 6,  1, 1),
        new Structure("Ruined Portal",                    1, 1, 9,  1, 1),
        new Structure("Abandoned Mineshaft",              8, 1, 9,  1, 1),
    };

    @Test
    void simulateAllStructures() {
        System.out.println("=== Shulker Shell Drop Simulation ===");
        System.out.printf("Simulations per structure: %,d%n%n", SIMULATIONS);
        System.out.printf("%-25s | %6s | %10s | %8s | %8s | %8s%n",
                "Structure", "Chests", "Drop Chance", "Avg/Run", "Min/Run", "Max/Run");
        System.out.println("-".repeat(85));

        for (Structure s : STRUCTURES) {
            simulateStructure(s);
        }
    }

    private void simulateStructure(Structure s) {
        long totalShells = 0;
        int minShells = Integer.MAX_VALUE;
        int maxShells = Integer.MIN_VALUE;

        for (int sim = 0; sim < SIMULATIONS; sim++) {
            int shells = 0;
            for (int chest = 0; chest < s.estimatedChests(); chest++) {
                shells += rollChest(s);
            }
            totalShells += shells;
            minShells = Math.min(minShells, shells);
            maxShells = Math.max(maxShells, shells);
        }

        double avg = (double) totalShells / SIMULATIONS;

        System.out.printf("%-25s | %6d | %9.1f%% | %7.2f | %8d | %8d%n",
                s.name(), s.estimatedChests(), s.dropChance() * 100, avg, minShells, maxShells);
    }

    private int rollChest(Structure s) {
        int totalWeight = s.itemWeight() + s.emptyWeight();
        int roll = RNG.nextInt(totalWeight);

        if (roll < s.itemWeight()) {
            if (s.minCount() == s.maxCount()) {
                return s.minCount();
            }
            return s.minCount() + RNG.nextInt(s.maxCount() - s.minCount() + 1);
        }
        return 0;
    }
}
