package study4SPOC.foxAndRabbit;

/**
 * Description:为什么创建Location呢？
 * 因为根据游戏规则，狐狸和兔子会向周围空的位置移动，因此需要一个类来记录周围位置的坐标；
 * Created by zhouhongfei on 2016/11/26 10:51.
 */
public class Location
{
	private int row;
	private int col;

	public Location(int row, int col)
	{
		super();
		this.row = row;
		this.col = col;
	}

	public int getRow()
	{
		return row;
	}

	public int getCol()
	{
		return col;
	}
}
