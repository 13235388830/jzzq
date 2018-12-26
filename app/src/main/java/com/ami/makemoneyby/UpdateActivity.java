package com.ami.makemoneyby;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;

import com.ami.makemoneyby.downloadapp.DownloadService;
import com.ami.makemoneyby.downloadapp.NumberProgressBar;
import com.ami.makemoneyby.downloadapp.VersionUpdate;
import com.ami.makemoneyby.downloadapp.VersionUpdateImpl;

import org.xutils.view.annotation.ContentView;

/**
 * Created by Administrator on 2018/10/15.
 */
public class UpdateActivity extends BaseActivity implements VersionUpdateImpl {
    NumberProgressBar bnp;
    private String url;
    private boolean isBindService;

    private ServiceConnection conn = new ServiceConnection() {

        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            DownloadService.DownloadBinder binder = (DownloadService.DownloadBinder) service;
            DownloadService downloadService = binder.getService();

            //接口回调，下载进度
            downloadService.setOnProgressListener(new DownloadService.OnProgressListener() {
                @Override
                public void onProgress(float fraction) {
//                    LogUtil.i(TAG, "下载进度：" + fraction);
                    bnp.setProgress((int) (fraction * 100));
                    //判断是否真的下载完成进行安装了，以及是否注册绑定过服务
                    if (fraction == DownloadService.UNBIND_SERVICE && isBindService) {
                        unbindService(conn);
                        isBindService = false;
                    }
                }
            });
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };
    @Override
    protected int getLayoutID() {
        return R.layout.activity_update;
    }

    @Override
    protected void initView() {
        super.initView();
        url=getIntent().getStringExtra("url");
        bnp= (NumberProgressBar) findViewById(R.id.number_bar);
        VersionUpdate.checkVersion(UpdateActivity.this,url);
    }

    @Override
    protected void initListener() {
        super.initListener();
    }

    @Override
    public void bindService(String apkUrl) {
        Intent intent = new Intent(this, DownloadService.class);
        intent.putExtra(DownloadService.BUNDLE_KEY_DOWNLOAD_URL, apkUrl);
        isBindService = bindService(intent, conn, BIND_AUTO_CREATE);
    }
}
