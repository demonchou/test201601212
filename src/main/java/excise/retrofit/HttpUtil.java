package excise.retrofit;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 *
 * @author hzzhouhongfei
 * @version $$ HttpUtil, 2018/2/5 hzzhouhongfei $$
 */
public class HttpUtil
{
	public static void request(String url) {

		//步骤4:创建Retrofit对象
		Retrofit retrofit = new Retrofit.Builder()
				// 设置 网络请求 Url
				.baseUrl(url)
				//设置使用Gson解析(记得加入依赖)
				.addConverterFactory(GsonConverterFactory.create())
				.build();

		// 步骤5:创建 网络请求接口 的实例
		GetRequest_Interface request = retrofit.create(GetRequest_Interface.class);

		//对 发送请求 进行封装
		Call<Translation> call = request.getCall();

		//同步
		try
		{
			Response<Translation> response = call.execute();
			System.out.println("-----同步开始");
			System.out.println(response.body());
			response.body().show();
			System.out.println("-----同步结束");
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}

		//步骤6:发送网络请求(异步)
		//		call.enqueue(new Callback<Translation>() {
		//			//请求成功时回调
		//			@Override
		//			public void onResponse(Call<Translation> call, Response<Translation> response) {
		//				// 步骤7：处理返回的数据结果
		//				response.body().show();
		//			}
		//
		//			//请求失败时回调
		//			@Override
		//			public void onFailure(Call<Translation> call, Throwable throwable) {
		//				System.out.println("连接失败");
		//			}
		//		});
	}

	public static void main(String[] args)
	{
		String url = "http://fy.iciba.com/";
		request(url);
	}
}
