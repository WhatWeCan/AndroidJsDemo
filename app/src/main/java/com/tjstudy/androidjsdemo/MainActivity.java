package com.tjstudy.androidjsdemo;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.webkit.CookieManager;
import android.webkit.CookieSyncManager;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class MainActivity extends AppCompatActivity {

    private WebView mWebView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        CookieSyncManager cookieSyncMngr =
                CookieSyncManager.createInstance(this);
        CookieManager cookieManager = CookieManager.getInstance();
        cookieManager.removeAllCookie();
        mWebView = (WebView) findViewById(R.id.webView_goods);
//        mWebView.loadUrl("http://www.baidu.com");
        mWebView.setLayerType(WebView.LAYER_TYPE_SOFTWARE,new android.graphics.Paint());
        final WebSettings settings = mWebView.getSettings();
        settings.setJavaScriptCanOpenWindowsAutomatically(true);
        settings.setPluginState(WebSettings.PluginState.ON);
        settings.setAllowFileAccess(true);
        settings.setLoadWithOverviewMode(true);
        settings.setDomStorageEnabled(true);
        settings.setUseWideViewPort(true);
        settings.setJavaScriptEnabled(true);//设置是否支持与js互相调用
        settings.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);//不使用网络缓存，开启的话容易导致app膨胀导致卡顿
//        mWebView.loadUrl("https://www.huzerui.com/vue2.0-demo/#/login");
//        mWebView.loadUrl("http://www.baidu.com");
//        mWebView.loadUrl("http://api.5bai.net/api/web/index#/anlixiangqing/v/1.0/token/4589dfa7bda856ecfc9b571d6b2f1731/article_id/26");
        mWebView.loadUrl("http://api.5bai.net/api/web/index#/details/goods_id/771/v/1.0/token/4589dfa7bda856ecfc9b571d6b2f1731");
        mWebView.setWebViewClient(new WebViewClient() {//设置webviewclient,使其不会由第三方浏览器打开新的url

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;//设置为true才有效哦
            }

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
            }
        });


//        mWebView = (CommWebView) findViewById(R.id.webView_goods);
//        mWebView.setLayerType(WebView.LAYER_TYPE_SOFTWARE, new android.graphics.Paint());
//        //mWebView.loadUrl("http://api.tsls.cn/api/web/index#/details/goods_id/97/v/1.0.1/token/bce721dbfba31af552fbba3c668b5ec8/cat_id/452");
//        mWebView.setCurWebUrl("http://api.5bai.net/api/web/index#/details/goods_id/771/v/1.0/token/4589dfa7bda856ecfc9b571d6b2f1731")
//                .addJavascriptInterface(new JSCallJava(), "chinahr")
//                .addJavascriptInterface(new JSCallJava(), "moremerchant")
//                .addJavascriptInterface(new JSCallJava(), "ordersave")
//                .startCallback(new WebViewCallback() {
//                    @Override
//                    public void onStart() {
//                    }
//
//                    @Override
//                    public void onProgress(int curProgress) {
////                        ELog.e(curProgress);
////                        if (curProgress > 80) {//加载完成80%以上就可以隐藏了，防止部分网页不能
////                            mProgressDialog.dismiss();
////                            tvTitle.setText(wv_main.getWebTitle());
////                        }
//                    }
//
//                    @Override
//                    public void onError(int errorCode, String description, String failingUrl) {
//                        super.onError(errorCode, description, failingUrl);
////                        ELog.e(errorCode + " \t" + description + "\t" + failingUrl);
//                    }
//                });

    }

//    public void getchinahr(View view) {
//        Log.e("tjs", "点击事件");
//        mWebView.post(new Runnable() {
//            @Override
//            public void run() {
//                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
//                    Log.e("tjs", "19以上");
//                    mWebView.evaluateJavascript("javascript:liantian();", new ValueCallback<String>() {
//                        @Override
//                        public void onReceiveValue(String value) {
//                            Log.e("tjs", "chinahr=" + value);
//                        }
//                    });
//                } else {
//                    Log.e("tjs", "19以下");
////                    String ret = mWebView.stringByEvaluatingJavaScriptFromString("javascript:chinahr();");
////                    Log.e("tjs", "chinahr=" + ret);
//                }
//            }
//        });
//    }
//
//    class JSCallJava {
//        @JavascriptInterface
//        public void postMessage(String str) {
//            /**
//             * 4.4以上的webview，需要在子线程中调用js与java互相调用的代码
//             */
//            Log.e("tjs", "chinahr=" + str);
//        }
//
////        @JavascriptInterface
////        public void moremerchant(String supplier_id) {
////            /**
////             * 4.4以上的webview，需要在子线程中调用js与java互相调用的代码
////             */
////            Log.e("tjs", "moremerchant");
////        }
////
////        @JavascriptInterface
////        public void ordersave(String v, String token, String goods_id, String goods_num, String start_time, String end_time, String supservice) {
////            /**
////             * 4.4以上的webview，需要在子线程中调用js与java互相调用的代码
////             */
////            Log.e("tjs", "ordersave");
////        }
//    }
//
//    @Override
//    protected void onPause() {
//        super.onPause();
//        // 加载空白页
//        mWebView.loadUrl("about:blank");
//    }
}