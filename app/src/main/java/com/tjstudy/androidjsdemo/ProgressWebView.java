package com.tjstudy.androidjsdemo;

import android.content.Context;
import android.util.AttributeSet;
import android.webkit.WebView;
import android.widget.ProgressBar;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * 自定义带进度的webView
 */

public class ProgressWebView extends WebView {

    private ProgressBar progressbar;

    /**
     * 进度条
     */

    public ProgressWebView(Context context, AttributeSet attrs) {
        super(context, attrs);
        // 初始化进度条
        progressbar = new ProgressBar(context, null, android.R.attr.progressBarStyleHorizontal);
        // 设置进度条风格
        progressbar.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, 5, 0, 0));
        addView(progressbar);
        setWebChromeClient(new WebChromeClient());

    }

    public class WebChromeClient extends android.webkit.WebChromeClient {
        @Override
        public void onProgressChanged(WebView view, int newProgress) {
            if (newProgress == 100) {
                progressbar.setVisibility(GONE);    // 加载完成隐藏进度条
            } else {
                if (progressbar.getVisibility() == GONE)
                    progressbar.setVisibility(VISIBLE);
                progressbar.setProgress(newProgress);
            }
            super.onProgressChanged(view, newProgress);
        }
    }

    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        LayoutParams lp = (LayoutParams) progressbar.getLayoutParams();
        lp.x = l;
        lp.y = t;
        progressbar.setLayoutParams(lp);
        super.onScrollChanged(l, t, oldl, oldt);
    }

    public String stringByEvaluatingJavaScriptFromString(String script) {
        try {
            //由webview取到webviewcore
            Field field_webviewcore = WebView.class.getDeclaredField("mWebViewCore");
            field_webviewcore.setAccessible(true);
            Object obj_webviewcore = field_webviewcore.get(this);
            //由webviewcore取到BrowserFrame
            Field field_BrowserFrame = obj_webviewcore.getClass().getDeclaredField("mBrowserFrame");
            field_BrowserFrame.setAccessible(true);
            Object obj_frame = field_BrowserFrame.get(obj_webviewcore);
            //获取BrowserFrame对象的stringByEvaluatingJavaScriptFromString方法
            Method method_stringByEvaluatingJavaScriptFromString = obj_frame.getClass().getMethod("stringByEvaluatingJavaScriptFromString", String.class);
            //执行stringByEvaluatingJavaScriptFromString方法
            Object obj_value = method_stringByEvaluatingJavaScriptFromString.invoke(obj_frame, script);
            //返回执行结果
            return String.valueOf(obj_value);
        } catch (SecurityException | NoSuchFieldException | IllegalAccessException | IllegalArgumentException | NoSuchMethodException | InvocationTargetException e) {
            e.printStackTrace();
        }
        return null;
    }
}
