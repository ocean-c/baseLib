package com.white.luckcloud.framelibrary.base;

import android.content.Intent;

import com.white.luckcloud.baselibrary.base.ExceptionCrashHandler;
import com.white.luckcloud.framelibrary.service.LogService;


/**
 * 全局异常捕获类
 * Created by fa on 2018/10/17.
 */

public class DefaultExceptionCrashHandler extends ExceptionCrashHandler {
    private static final String TAG = DefaultExceptionCrashHandler.class.getSimpleName();
    private static final ExceptionCrashHandler instance = new DefaultExceptionCrashHandler();


    public static ExceptionCrashHandler getInstance() {
        return instance;
    }

    @Override
    protected void logUpload() {
//        String logPath = LogUtils.getCrashLogPath();
//        String logName = LogUtils.getCrashLogName();
//        LogUtils.e(TAG, "崩溃日志文件路径：" + logPath + File.separator + logName);
//        CrashLogUtils.getInstance().uploadNowCrash(mContext);
        Intent intent = new Intent(mContext, LogService.class);
        mContext.startService(intent);
    }
}
