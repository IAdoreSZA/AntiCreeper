# AntiCreeper

A Paper plugin that makes creepers harmless: they ignore players and wander around like passive mobs, they don't hurt players, mobs, or items, and they don't destroy blocks, paintings, or item frames.

> **AI disclosure:** This plugin was written with [Claude Code](https://claude.com/claude-code), an AI coding tool. If you'd rather not use AI-made software, you may want to skip this one.

## Install

1. Download the jar for your Minecraft version from the [Releases](../../releases) page. The file name says which version it's for, e.g. `AntiCreeper-1.0.0-mc26.3.jar` is for Minecraft 26.3.
2. Stop your server and put the jar in its `plugins/` folder.
3. Start the server.

Needs a Paper server and Java 25.

## Config

`plugins/AntiCreeper/config.yml`:

```yaml
# true = creepers ignore players and wander around like passive mobs
peaceful: true

# true  = creepers never explode at all (they hiss, then calm down)
# false = creepers still explode and die, but the blast does no damage and breaks no blocks
prevent-explosion: true
```

Restart the server after changing it.

## Build

```sh
./gradlew build
```

The jar is written to `build/libs/`.
