package study4SPOC.foxAndRabbit;

import study4SPOC.foxAndRabbit.*;
import study4SPOC.foxAndRabbit.Animal;
import study4SPOC.foxAndRabbit.Fox;
import study4SPOC.foxAndRabbit.ICell;
import study4SPOC.foxAndRabbit.Location;
import study4SPOC.foxAndRabbit.Rabbit;
import study4SPOC.foxAndRabbit.View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class FoxAndRabbit
{
	private Field theField;
	private View theView;
    JFrame frame = new JFrame();

	public FoxAndRabbit(int size)
	{
		theField = new Field(size, size);
		for (int row = 0; row < theField.getHeight(); row++)
		{
			for (int col = 0; col < theField.getWidth(); col++)
			{
				double probability = Math.random();
				if (probability < 0.05)
				{
					theField.place(row, col, new Fox());
				}
				else if (probability < 0.15)
				{
					theField.place(row, col, new Rabbit());
				}
			}
		}
		theView = new View(theField);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setTitle("FoxAndRabbit");
        frame.add(theView);
        JButton jbt = new JButton();
        jbt.setName("单击！");
        jbt.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                start(1);
            }
        });
        frame.add(jbt, BorderLayout.NORTH);
//        JPanel jPanel = new JPanel();
//        jPanel.setLayout(jbt, BorderLayout.NORTH);
        frame.pack();
		frame.setVisible(true);
	}

	public void start(int steps)
	{
		for (int i = 0; i < steps; i++)
		{
			step();
			theView.repaint();
			try
			{
				Thread.sleep(200);
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}
		}
	}

	public void step()
	{
		for (int row = 0; row < theField.getHeight(); row++)
		{
			for (int col = 0; col < theField.getWidth(); col++)
			{
				ICell cell = theField.get(row, col);
				if (cell != null)
				{
					Animal animal = (Animal) cell;
					animal.grow();
					System.out.println(animal);
					if (animal.isAlive())
					{
						//move
						Location[] freeAdj = theField.getFreeNeighbour(row, col);
						if (freeAdj.length > 0)
						{
							Location moveloc = animal.move(row, col, freeAdj);
							if (moveloc != null)
							{
								theField.move(row, col, moveloc);
							}
						}
						//feed，注意，吃掉的animal一定要remove出来；
						ICell[] neighbour = theField.getNeighbour(row, col);
						ArrayList<Animal> listofRabbit = new ArrayList<>();
						if (neighbour.length > 0)
						{
							for (ICell c : neighbour)
							{
								if (c instanceof Rabbit)
								{
									listofRabbit.add((Rabbit) c);
								}
							}
							if (!listofRabbit.isEmpty())
							{
								Animal fed = animal.feed(row, col, listofRabbit);
								if (fed != null)
								{
									theField.remove((ICell) fed);
								}
							}

						}
						//breed
						Animal baby = animal.breed();
						if (baby != null)
						{
							theField.placeRandomAdj(row, col, (ICell) baby);
						}
					}
					else
					{
						theField.remove(row, col);
					}
				}
			}
		}
	}

	public static void main(String[] args)
	{
        FoxAndRabbit fnr = new FoxAndRabbit(60);
	}
}
