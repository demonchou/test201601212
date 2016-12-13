package study4SPOC.foxAndRabbit;

import java.awt.*;

/**
 * Description:这个接口只定义了一个方法，就是把让实现了这个接口的子类自己把自己给画出来；
 * Created by zhouhongfei on 2016/11/26 10:56.
 */
interface ICell
{
	void draw(Graphics g, int x, int y, int size);
}
