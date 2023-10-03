# Build your own World

This program is a world generator and interactive game where you can explore procedurally generated 2D worlds. You control an avatar and interact with objects, solve puzzles, and explore unique worlds. Key features include using Object Oriented Principles to optimzie procedureally generated maps. 

## Overview

### Phase 1 - World Generation
The program generates 2D worlds consisting of rooms and hallways. Each world is unique based on a provided seed. The world is displayed using a tile rendering engine, and you can customize the appearance of tiles. 

Key Features:
- Procedurally generated worlds.
- Diverse tilesets for rooms, hallways, walls, and more.
- Generation of rooms and hallways.
- Deterministic behavior based on seeds.

### Phase 2 - Interactivity
In this phase, you control an avatar within the generated world using the W, A, S, and D keys. The avatar can interact with objects, solve puzzles, and achieve objectives. The program ensures that the same sequence of inputs leads to the same results, allowing you to save and load your progress.

Key Features:
- Avatar control with movement keys.
- Interactions with objects and puzzles.
- Deterministic behavior for replaying actions.
- Save and load game progress.

## Code Details

- The `MapGen` class generates worlds based on the provided seed. It creates rooms, hallways, and connects adjacent rooms with hallways.
- Rooms are represented by the `Room` class, which includes methods to check for room overlaps.
- The program uses a tile-based rendering engine to display the world.
- The code ensures that rooms and hallways are generated within the bounds of the game window.
- The avatar's movement and interactions are handled in the interactive phase, allowing you to explore the generated world.

## How to Use

1. Run the program by executing the `Main` class.
2. You'll be prompted to provide an input string, which can include commands like "N" for a new world, followed by a seed, "S" to save, and more.
3. Once the world is generated, use the W, A, S, and D keys to control your avatar.
4. Explore the world, solve puzzles, and achieve objectives.

## Saving and Loading

You can save and load your game progress using the following commands:

- `:Q` or `:q`: Save and quit the game.
- `L`: Load the last saved game state.

The program ensures that loading a game state returns the exact same world as when it was saved.
