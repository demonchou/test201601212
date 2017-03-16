package excise.weatherInfoSpider;

/**
 * Description:空气质量实体类
 * Created by zhouhongfei on 2017/3/10 10:49.
 * ||E-mail:zhouhfsix@foxmail.com
 */
public class WeatherInfo
{
    private String id;
    private String station;


    private String date_time;

    private String pm2;
    private String so2;
    private String o3;
    private String pm10;
    private String no2;
    private String co;

    private String now;
    private String aqi;
    private String grade;
    private String date_f;
    private String first;

    @Override
    public String toString()
    {
        return "excise.weatherInfoSpider.WeatherInfo{" +
                "id='" + id + '\'' +
                ", station='" + station + '\'' +
                ", date_time='" + date_time + '\'' +
                ", pm2='" + pm2 + '\'' +
                ", so2='" + so2 + '\'' +
                ", o3='" + o3 + '\'' +
                ", pm10='" + pm10 + '\'' +
                ", no2='" + no2 + '\'' +
                ", co='" + co + '\'' +
                '}';
    }

    public String getId()
    {
        return id;
    }

    public void setId(String id)
    {
        this.id = id;
    }

    public String getPm2()
    {
        return pm2;
    }

    public void setPm2(String pm2)
    {
        this.pm2 = pm2;
    }

    public String getCo()
    {
        return co;
    }

    public void setCo(String co)
    {
        this.co = co;
    }

    public String getSo2()
    {
        return so2;
    }

    public void setSo2(String so2)
    {
        this.so2 = so2;
    }

    public String getO3()
    {
        return o3;
    }

    public void setO3(String o3)
    {
        this.o3 = o3;
    }

    public String getNow()
    {
        return now;
    }

    public void setNow(String now)
    {
        this.now = now;
    }

    public String getNo2()
    {
        return no2;
    }

    public void setNo2(String no2)
    {
        this.no2 = no2;
    }

    public String getAqi()
    {
        return aqi;
    }

    public void setAqi(String aqi)
    {
        this.aqi = aqi;
    }

    public String getGrade()
    {
        return grade;
    }

    public void setGrade(String grade)
    {
        this.grade = grade;
    }

    public String getPm10()
    {
        return pm10;
    }

    public void setPm10(String pm10)
    {
        this.pm10 = pm10;
    }

    public String getDate_f()
    {
        return date_f;
    }

    public void setDate_f(String date_f)
    {
        this.date_f = date_f;
    }

    public String getFirst()
    {
        return first;
    }

    public void setFirst(String first)
    {
        this.first = first;
    }

    public String getStation()
    {
        return station;
    }

    public void setStation(String station)
    {
        this.station = station;
    }

    public String getDate_time()
    {
        return date_time;
    }

    public void setDate_time(String date_time)
    {
        this.date_time = date_time;
    }
}
