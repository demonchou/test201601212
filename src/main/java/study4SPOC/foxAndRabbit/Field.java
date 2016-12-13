package study4SPOC.foxAndRabbit;

import study4SPOC.foxAndRabbit.ICell;
import study4SPOC.foxAndRabbit.Location;

import java.util.ArrayList;
import java.util.List;

public class Field
{
	private static final Location[] adjacent =
	{ new Location(-1, -1), new Location(-1, 0), new Location(-1, 1),
            new Location(0, -1), new Location(0, 0), new Location(0, 1),
            new Location(1, -1), new Location(1, 0), new Location(1, 1)
    };
	private int height;
	private int width;
	private ICell[][] cells;
	/*
	 * 1. adjacent为什么要将它设置为静态常量呢？
	 */

	public Field(int height, int width)
	{
		this.height = height;
		this.width = width;
		cells = new ICell[height][width];
	}

	public int getHeight()
	{
		return height;
	}

	public int getWidth()
	{
		return width;
	}

	public ICell get(int row, int col)
	{
		return cells[row][col];
	}

	public ICell place(int row, int col, ICell cell)
	{
		ICell ret = cells[row][col];
		cells[row][col] = cell;
		return ret;
	}

	public ICell[] getNeighbour(int row, int col)
	{
		ArrayList<ICell> list = new ArrayList<ICell>();
		for (Location loc : adjacent)
		{
			int r = row + loc.getRow();
			int c = col + loc.getCol();
			if (r > -1 && r < height && c > -1 && c < width && !(r == row && c == col))
			{
				list.add(cells[r][c]);
			}
		}
		return list.toArray(new ICell[list.size()]);
	}

	public Location[] getFreeNeighbour(int row, int col)
	{
		List<Location> list = new ArrayList<Location>();
		for (Location loc : adjacent)
		{
			int r = row + loc.getRow();
			int c = col + loc.getCol();
			if (r > -1 && r < height && c > -1 && c < width && (cells[r][c] == null))
			{
				list.add(new Location(r, c));
			}
		}
		return list.toArray(new Location[list.size()]);
	}

	//给指定的对象在指定的位置附近随机放置；需要返回什么吗？不需要返回什么；
	public void placeRandomAdj(int row, int col, ICell cell)
	{
		Location[] loc = this.getFreeNeighbour(row, col);
		if (loc.length > 0)
		{
			int index = (int) (Math.random() * loc.length);
			cells[loc[index].getRow()][loc[index].getCol()] = cell;
		}
	}

	public ICell remove(int row, int col)
	{
		ICell ret = cells[row][col];
		cells[row][col] = null;
		return ret;
	}

	public void remove(ICell cell)
	{
		for (int row = 0; row < height; row++)
		{
			for (int col = 0; col < width; col++)
			{
				if (cells[row][col] == cell)
				{
					cells[row][col] = null;
					break;
				}
			}
		}
	}

	//将指定的对象移动到指定的地方
	public void move(int row, int col, Location loc)
	{
		cells[loc.getRow()][loc.getCol()] = cells[row][col];
		remove(row, col);
	}
}