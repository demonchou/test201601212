package thinkInJava;

import java.util.Arrays;
import java.util.Random;

import static com.sun.org.apache.xalan.internal.xsltc.compiler.util.Util.println;

/**
 * Description: 验证static的使用
 * Created by zhouhongfei on 2016/11/10 22:18.
 */
class Sup
{
    private int i;
    Sup()
    {
        println("sup class");
    }
    void f(){
        println("sup fun");
    }

}

class Base extends Sup
{
    private int radious;
    Base()
    {
        println("base class");
    }
    Base(int r){
        radious = r;
        println("radious = " + radious);
    }
    void f (){
        println("base fun");
    }
}

class Cup
{
    Cup()
    {
        println("cup(0)");
    }

    Cup(int marker)
    {
        println("cup(" + marker + ")");
    }

    static void f(int marker)
    {
        println("f(" + marker + ")");
    }
}

class Cups
{
    static Cup cup1;

    //only once
    static
    {
        cup1 = new Cup(1);
    }

    Cups()
    {
        println("cups");
    }
}


public class StaticIni
{
    public static void main(String[] args)
    {

        println("main begin");
        Cups.cup1.f(99);
        Cup.f(33);
        Base base = new Base(5);
        base.f();

        int[] a;
        Random rand = new Random(47);
        a = new int[rand.nextInt(33)];
        println("a.length = " + a.length);
        println(Arrays.toString(a));
    }

    static Cups cups1 = new Cups();
    static Cups cups2 = new Cups();
}
