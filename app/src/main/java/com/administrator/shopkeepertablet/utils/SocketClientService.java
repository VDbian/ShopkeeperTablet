package com.administrator.shopkeepertablet.utils;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.support.annotation.NonNull;
import android.util.Log;
import android.widget.Toast;

import com.administrator.shopkeepertablet.AppApplication;
import com.administrator.shopkeepertablet.repository.BaseRepertoryImpl;
import com.vilyever.socketclient.SocketClient;
import com.vilyever.socketclient.helper.SocketClientDelegate;
import com.vilyever.socketclient.helper.SocketPacket;
import com.vilyever.socketclient.helper.SocketResponsePacket;
import com.vilyever.socketclient.util.CharsetUtil;

import org.json.JSONObject;

import java.io.UnsupportedEncodingException;

/**
 * Description:
 * Author chencheng
 * Time 2018/9/13
 */

public class SocketClientService extends Service {
    /*socket*/
    private SocketClient socketClient;

    private SocketBinder socketBinder = new SocketBinder();

    private String ip;
    private int duankou;
    private String shopId;

    @Override
    public IBinder onBind(Intent intent) {
        return socketBinder;
    }

    public class SocketBinder extends Binder {

        /*返回SocketService 在需要的地方可以通过ServiceConnection获取到SocketService  */
        public SocketClientService getService() {
            return SocketClientService.this;
        }
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        ip = intent.getStringExtra("ip");
        duankou = intent.getIntExtra("duankou", 0);
        shopId = intent.getStringExtra("id");
        initSocket();
        return super.onStartCommand(intent, flags, startId);
    }

    private void initSocket() {
        socketClient = new SocketClient();
        //设置ip和端口
        socketClient.getAddress().setRemoteIP(ip); // 远程端IP地址
        socketClient.getAddress().setRemotePort(duankou); // 远程端端口号
        socketClient.getAddress().setConnectionTimeout(15 * 1000); // 连接超时时长，单位毫秒
        socketClient.setCharsetName("GBK");
        //设置心跳包
//        socketClient.getHeartBeatHelper().setDefaultReceiveData(CharsetUtil.stringToData("", CharsetUtil.UTF_8));
        socketClient.getHeartBeatHelper().setHeartBeatInterval(10 * 1000); // 设置自动发送心跳包的间隔时长，单位毫秒
//        socketClient.getHeartBeatHelper().setSendHeartBeatEnabled(true); // 设置允许自动发送心跳包，此值默认为false

        // 对应removeSocketClientDelegate
        socketClient.registerSocketClientDelegate(delegate);
        socketClient.connect();
    }

    SocketClientDelegate delegate = new SocketClientDelegate() {
        /**
         * 连接上远程端时的回调
         */
        @Override
        public void onConnected(SocketClient client) {
//                SocketPacket packet = socketClient.sendData(new byte[]{0x02}); // 发送消息
            SocketPacket packet = socketClient.sendString("A*" + shopId + "*X*AS01#"); // 发送消息
            Handler handler = new Handler(Looper.getMainLooper());
            handler.post(new Runnable() {
                public void run() {
                    AppApplication.get(getApplicationContext()).setAlias(shopId,"PHONE");
                }
            });
//                socketClient.cancelSend(packet); // 取消发送，若在等待发送队列中则从队列中移除，若正在发送则无法取消
        }

        /**
         * 与远程端断开连接时的回调
         */
        @Override
        public void onDisconnected(SocketClient client) {
            // 可在此实现自动重连
            socketClient.connect();
        }

        /**
         * 接收到数据包时的回调
         */
        @Override
        public void onResponse(final SocketClient client, @NonNull SocketResponsePacket responsePacket) {
//            byte[] data = responsePacket.getData(); // 获取接收的byte数组，不为null
//            String value = new String(data);
            final String message = responsePacket.getMessage(); // 获取按默认设置的编码转化的String，可能为null
            Log.e("vd", message);

            Handler handler = new Handler(Looper.getMainLooper());
            handler.post(new Runnable() {
                public void run() {
//                    Toast.makeText(getApplicationContext(), message, Toast.LENGTH_LONG).show();
                    new Thread(() -> {
                        BaseRepertoryImpl baseRepertory = new BaseRepertoryImpl(AppApplication.get(getApplicationContext()).getAppComponent().getApiSource(), AppApplication.get(getApplicationContext()).getAppComponent().getPreferenceSource());
                        new Print(baseRepertory).socketDataArrivalHandler(message);
                    }).start();
                }
            });
        }
    };


    public interface ProgressListener {
        void onProgress(String progress);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (socketClient != null) {
            socketClient.removeSocketClientDelegate(delegate);
            socketClient.disconnect();
        }
    }

//    Intent intent = new Intent(this,SocketClientService.class);
//    startService(intent);

}
