package study4SPOC.foxAndRabbit;

import study4SPOC.foxAndRabbit.*;
import study4SPOC.foxAndRabbit.Animal;

import java.awt.*;

public class Rabbit extends Animal implements ICell
{
	public Rabbit()
	{
		super(10, 2);
	}

	@Override
	public void draw(Graphics g, int x, int y, int size)
	{
		int alpha = (int) ((1 - this.getAgePercent()) * 255);
		g.setColor(new Color(255, 0, 0, alpha));
		g.fillRect(x, y, size, size);
	}

	@Override
	public Animal breed()
	{
		Animal ret = null;
		if (isbreedable() && Math.random() < 0.12)
		{
			ret = new Rabbit();
		}
		return ret;
	}

	@Override
	public String toString()
	{
		return "Rabbit: " + super.toString();
	}


}