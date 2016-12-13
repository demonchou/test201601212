package study4SPOC.foxAndRabbit;

import study4SPOC.foxAndRabbit.Location;

import java.util.ArrayList;

/**
 * Description:
 * 1. 为什么Animal是抽象类；
 * Animal是狐狸和兔子的父类，实现了共同的属性和方法；例如年龄，年龄上限，移动，捕食，繁殖；
 * 繁殖需要每个子类重写（overridde）,因为狐狸和兔子的繁殖概率不一样。所以将它定义成抽象方法；
 * 所以Animal是抽象类；
 * Created by zhouhongfei on 2016/11/26 10:45.
 */
public abstract class Animal {
    private int age;
    private int ageLimit;
    private int breedableAge;
    private boolean isAlive = true;
    //给子类设定年龄上限和生育年龄
    public Animal(int ageLimit, int breedableAge) {
        this.ageLimit = ageLimit;
        this.breedableAge = breedableAge;
    }
    public int getAge() {
        return age;
    }
    public boolean isAlive() {
        return isAlive;
    }
    //该方法用来实现颜色的渐变；
    public double getAgePercent() {
        return (double)age / ageLimit;
    }

    public boolean isbreedable() {
        return age >= breedableAge;
    }
    //生长
    public void grow() {
        age ++;
        if(age > ageLimit) {
            this.die();
        }
    }
    //死亡
    public void die() {
        this.isAlive = false;
    }
    //随机繁殖；返回的一定是一个Animal啊...
    public abstract Animal breed();
    //随机移动,返回的是一个位置(确定移动到那个点的位置)。传入某点坐标和周围空的位置；
    public Location move(int row, int col, Location[] freeAdj) {
        Location ret = null;
        if(freeAdj.length > 0 && Math.random() < 0.02) {
            ret = freeAdj[(int)(Math.random() * freeAdj.length)];
        }
        return ret;
    }
    /*
     * 随机捕食；返回的是一个Animal对象，传入的是某点坐标和周围所有（意味着这是容器）的对象；
     * 需要狐狸重写，因为只有狐狸才捕食；
     */
    public Animal feed(int row, int col, ArrayList<Animal> fed) {
        return null;
    }
    //根据规则：狐狸每捕食一次年龄上限加一次；
    public void longerLife(int inc) {
        ageLimit += inc;
    }

    @Override
    public String toString() {
        return "" + age + " : " + (this.isAlive ? "isAlive" : "dead");
    }
}