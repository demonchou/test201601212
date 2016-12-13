package thinkInJava;

import java.util.Arrays;

import static com.sun.org.apache.xalan.internal.xsltc.compiler.util.Util.println;

/**
 * Description: 策略模式
 * Created by zhouhongfei on 2016/11/16 16:39.
 */
class Processor
{
    public String name()
    {
        return getClass().getSimpleName();
    }

    Object process(Object input)
    {
        return input;
    }
}

class Upcase extends Processor
{
    String process(Object input)
    {
        return ((String) input).toUpperCase();
    }
}

class Downcase extends Processor
{
    String process(Object input)
    {
        return ((String) input).toLowerCase();
    }
}

class Spliter extends Processor
{
    String process(Object input)
    {
        return Arrays.toString(((String) input).split(" "));
    }
}


public class Apply
{
    public static void process(Processor p, Object s)
    {
        println("Using Processor " + p.name());
        println(p.process(s).toString());
    }

    public static String s = "Hello World";

    public static void main(String[] args)
    {
        process(new Upcase(), s);
        process(new Downcase(), s);
        process(new Spliter(), s);
    }

}
