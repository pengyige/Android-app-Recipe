
package  com.example.mylife.com.example.net;
import android.os.AsyncTask;

import com.example.mylife.com.example.config.Config;
import com.example.mylife.com.example.net.HttpMethod;
import com.example.mylife.com.example.config.Config;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by 彭旎 on 2017/7/14.
 */

public class NetConnection {
    /**
     * url 请求服务求的api地址
     * method 提交方式
     * successCallback 连接服务器过程中成功后回调的接口
     * failCallback 连接服务器过程中失败后回调的接口
     * kvs 附加参数
     * */
    public NetConnection(final String url, final HttpMethod method, final SuccessCallback successCallback, final FailCallback failCallback, final String ...kvs)
    {
        new AsyncTask<Void,Void,String>(){
            @Override
            protected String doInBackground(Void... voids) {
              /*获取键值对*/
                StringBuffer paramStr = new StringBuffer();
                for(int i = 0 ; i < kvs.length ; i+=2)
                {
                    paramStr.append(kvs[i]).append("=").append(kvs[i+1]).append("&");
                }
                try {
                    URLConnection uc = null;

                    switch (method)
                    {
                        case POST:
                            //POST:以流的方式写入服务器
                            uc = new URL(url).openConnection();
                            uc.setDoOutput(true);
                            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(uc.getOutputStream(), Config.CHARSET));
                            bw.write(paramStr.toString());
                            bw.flush();
                            break;
                        default:
                            //其他以URL+键值对的方式提交到服务器
                            uc = new URL(url + "？" +paramStr.toString()).openConnection();
                            break;
                    }

                    //读数据
                    BufferedReader br = new BufferedReader(new InputStreamReader(uc.getInputStream(),Config.CHARSET));
                    String line = null;
                    StringBuffer result = new StringBuffer();
                    while((line = br.readLine()) != null)
                    {
                        result.append(line);
                    }
                  /*返回数据*/
                    return  result.toString();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return null;
            }


            //doInBackground执行完后调用
            @Override
            protected void onPostExecute(String s) {
                if(s != null)
                {
                    if(successCallback != null)
                    {
                        successCallback.onSuccess(s);
                    }
                }else
                {
                    if(failCallback != null)
                    {
                        failCallback.onFail();
                    }
                }
                super.onPostExecute(s);
            }
        }.execute();
    }



    public static interface SuccessCallback
    {
        void onSuccess(String result);
    }

    public static interface FailCallback{
        void onFail();
    }
}
