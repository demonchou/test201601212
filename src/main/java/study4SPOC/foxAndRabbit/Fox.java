package study4SPOC.foxAndRabbit;

import study4SPOC.foxAndRabbit.Animal;
import study4SPOC.foxAndRabbit.ICell;

import java.awt.*;
import java.util.ArrayList;

public class Fox extends Animal implements ICell
{
	public Fox()
	{
		super(20, 4);
	}
	@Override
	public void draw(Graphics g, int x, int y, int size)
	{
		int alpha = (int) ((1 - getAgePercent()) * 255);
		g.setColor(new Color(0, 0, 0, alpha));
		g.fillRect(x, y, size, size);
	}

	@Override
	public Animal breed()
	{
		Animal ret = null;
		if (isbreedable() && Math.random() < 0.05)
		{
			ret = new Fox();
		}
		return ret;
	}


	//捕食，返回一个Animal.传入该点坐标，周围可捕食的对象；年龄上限加2；
	public Animal feed(int row, int col, ArrayList<Animal> neighbour)
    {
		Animal ret = null;
		if (Math.random() < 0.05)
		{
			ret = neighbour.get((int) (Math.random() * neighbour.size()));
			this.longerLife(2);
		}
		return ret;
	}

	@Override
	public String toString()
	{
		return "Fox " + super.toString();
	}

}