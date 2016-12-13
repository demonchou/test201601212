package study4SPOC.foxAndRabbit;

import study4SPOC.foxAndRabbit.Field;
import study4SPOC.foxAndRabbit.ICell;

import javax.swing.*;
import java.awt.*;

public class View extends JPanel
{
	private static final int GIRD_SIZE = 10;
	private Field field;

	public View(Field field)
	{
		this.field = field;
	}

	@Override
	public void paint(Graphics g)
	{
		super.paint(g);
		for (int row = 0; row < field.getHeight(); row++)
		{
			g.drawLine(0, row * GIRD_SIZE, field.getWidth() * GIRD_SIZE, row * GIRD_SIZE);
		}
		for (int col = 0; col < field.getHeight(); col++)
		{
			g.drawLine(col * GIRD_SIZE, 0, col * GIRD_SIZE, field.getHeight() * GIRD_SIZE);
		}

		for (int row = 0; row < field.getHeight(); row++)
		{
			for (int col = 0; col < field.getWidth(); col++)
			{
				ICell cell = field.get(row, col);
				if (cell != null)
				{
					cell.draw(g, col * GIRD_SIZE, row * GIRD_SIZE, GIRD_SIZE);
				}
			}
		}
	}


	@Override
	public Dimension getPreferredSize()
	{
		return new Dimension(field.getWidth() * GIRD_SIZE + 1, field.getHeight() * GIRD_SIZE + 1);
	}
}