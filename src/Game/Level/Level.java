package Game.Level;

import Game.Game;
import Graphics.*;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Mocki on 02.11.2016.
 */

public class Level {

	public static final int TILE_SCALE 			= 8;
	public static final int	TILE_IN_GAMN_SCALE 	= 2;
	public static final int SCALE_TILE_SIZE		= TILE_SCALE * TILE_IN_GAMN_SCALE;
	public static final int TILE_IN_WIDTH 		= Game.WIDTH / SCALE_TILE_SIZE;
	public static final int TILE_IN_HEIGTH 		= Game.HEIGHT / SCALE_TILE_SIZE;

	private int [][] 			tileMap;
	private Map<TileType, Tile>	tiles;

	public Level(TextureAtlas atlas) {

		tiles = new HashMap<TileType, Tile>();
		tiles.put(TileType.BRICK, new Tile(atlas.cut(32 * TILE_SCALE, 0 * TILE_SCALE,
				TILE_SCALE, TILE_SCALE), TILE_IN_GAMN_SCALE, TileType.BRICK));
		tiles.put(TileType.METAL, new Tile(atlas.cut(32 * TILE_SCALE, 2 * TILE_SCALE,
				TILE_SCALE, TILE_SCALE), TILE_IN_GAMN_SCALE, TileType.METAL));
		tiles.put(TileType.WATER, new Tile(atlas.cut(32 * TILE_SCALE, 4 * TILE_SCALE,
				TILE_SCALE, TILE_SCALE), TILE_IN_GAMN_SCALE, TileType.WATER));
		tiles.put(TileType.GRASS, new Tile(atlas.cut(34 * TILE_SCALE, 4 * TILE_SCALE,
				TILE_SCALE, TILE_SCALE), TILE_IN_GAMN_SCALE, TileType.GRASS));
		tiles.put(TileType.ICE, new Tile(atlas.cut(36 * TILE_SCALE, 4 * TILE_SCALE,
				TILE_SCALE, TILE_SCALE), TILE_IN_GAMN_SCALE, TileType.ICE));
		tiles.put(TileType.EMPTY, new Tile(atlas.cut(36 * TILE_SCALE, 6 * TILE_SCALE,
				TILE_SCALE, TILE_SCALE), TILE_IN_GAMN_SCALE, TileType.EMPTY));

		tileMap = new int[TILE_IN_HEIGTH][TILE_IN_WIDTH];

		tileMap[10][10] = TileType.BRICK.numeric();
		tileMap[10][11] = TileType.BRICK.numeric();
		tileMap[10][12] = TileType.BRICK.numeric();
		tileMap[10][13] = TileType.BRICK.numeric();
		tileMap[11][10] = TileType.BRICK.numeric();


	}

	public void update() {

	}

	public void render(Graphics2D g) {
		for (int i = 0; i < tileMap.length; i++) {
			for (int j = 0; j <tileMap[i].length; j++) {
				tiles.get(TileType.fromNumeric(tileMap[i][j])).render(g, j * SCALE_TILE_SIZE, i * SCALE_TILE_SIZE);
			}
		}

	}



}
