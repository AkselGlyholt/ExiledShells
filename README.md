# Exiled Shells

A small Fabric mod that adds shulker shells to loot tables across the Overworld and Nether. Lets you get shulker boxes without having to reach the End first.

## What it does

Shulker shells have a chance to appear in the following loot chests:

| Structure | Chance | Amount |
|---|---|---|
| Ancient City | ~20% | 1-3 |
| Bastion Treasure | ~20% | 1-2 |
| Ominous Trial Vault | ~20% | 1-2 |
| Woodland Mansion | ~14% | 1-2 |
| Trial Vault (rare) | ~14% | 1 |
| Dungeon | ~10% | 1 |
| Desert Pyramid | ~10% | 1 |
| Ruined Portal | ~6.7% | 1 |
| Abandoned Mineshaft | ~5% | 1 |

The rates are intentionally low. You still have to explore and get lucky.

## Versions

- Minecraft: 26.1
- Fabric Loader: 0.18.4+
- Fabric API: required
- Java: 25

## Why no backport?

Minecraft 26.1 introduced breaking changes that make backporting impractical. The game now requires Java 25, ships only unobfuscated builds using Mojang's official mappings, and Fabric removed several long-deprecated APIs. A backport would essentially be a rewrite for a different toolchain.

## License

All rights reserved. See LICENSE.
