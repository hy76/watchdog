package bitman.ay27.watchdog.net;

import android.support.annotation.NonNull;
import android.util.Log;
import bitman.ay27.watchdog.PrefUtils;
import bitman.ay27.watchdog.WatchdogApplication;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.tencent.android.tpush.XGPushManager;
import org.apache.http.Header;
import org.apache.http.entity.StringEntity;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;
import java.net.CookieHandler;
import java.net.CookieManager;

/**
 * Proudly to user Intellij IDEA.
 * Created by ay27 on 15/3/28.
 */
public class NetManager {

    public static final String SIGN_UP = "signup";
    public static final String SIGN_IN = "signin";
    public static final String ONLINE = "online";
    public static final String OFFLINE = "offline";
    public static final String HEARTBEAT = "heartbeat";
    public static final String BIND = "bind";
    public static final String UPLOAD = "callback/fileupload";

    public static final String KEY_USERNAME = "username";
    public static final String KEY_PASSWORD = "password";
    public static final String KEY_EMAIL = "email";
    public static final String KEY_DEVICE_ID = "deviceid";
    public static final String KEY_LATI = "latitude";
    public static final String KEY_LONI = "longitude";
    public static final String GPS = "gps";
    public static final String ALARM = "alarm";
    public static final String DISALARM = "disalarm";
    public static final String LOCK = "lock";
    public static final String UNLOCK = "unlock";
    public static final String ERASE = "erase";
    public static final String KEY_STATE = "state";
    public static final String STATE = "state";
    public static final String KEY_FILE_LIST = "fileList";
    public static final String FILE_LIST = "callback/filelist";
    public static final String KEY_USERID = "uid";
    public static final String KEY_FILE_PATH = "path";
    public static final String KEY_FILE_NAME = "fileName";
    public static final String KEY_FILE = "file";
    private static final String TAG = "NetManager";
    private static final String KEY_DEVICE_NAME = "deviceName";

    private NetManager() {

    }

    public static void signIn(final String username, String password, final NetCallback callback) {
        RequestParams params = new RequestParams();
        params.put(KEY_USERNAME, username);
        params.put(KEY_PASSWORD, password);
//        callback.onSuccess(0, "");
        WatchServerRestClient.post(SIGN_IN, params, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int i, Header[] headers, byte[] bytes) {

                // set cookie, keep login
                CookieManager cookieManager = new CookieManager();
                CookieHandler.setDefault(cookieManager);

                if (bytes != null)
                    callback.onSuccess(i, new String(bytes));
                else
                    callback.onSuccess(i, null);
            }

            @Override
            public void onFailure(int i, Header[] headers, byte[] bytes, Throwable throwable) {
                if (bytes != null)
                    callback.onError(i, new String(bytes));
                else
                    callback.onError(i, null);
            }
        });
    }

    public static void signUp(String username, String email, String password, NetCallback callback) {
        RequestParams params = new RequestParams();
        params.put(KEY_USERNAME, username);
        params.put(KEY_EMAIL, email);
        params.put(KEY_PASSWORD, password);
//        callback.onSuccess(0, "");
        WatchServerRestClient.post(SIGN_UP, params, generateDefaultHandler(callback));
    }

    public static void online() {
        final RequestParams params = new RequestParams();
        params.put(KEY_DEVICE_ID, WatchdogApplication.DeviceId);
        LocationManager.getLocation(new LocationManager.GetLocationCallback() {
            @Override
            public void onSuccess(double latitude, double longitude) {
                params.put(KEY_LATI, latitude);
                params.put(KEY_LONI, longitude);
                WatchServerRestClient.get(ONLINE, params, null);
            }
        });
    }

    public static void offline() {
        final RequestParams params = new RequestParams();
        params.put(KEY_DEVICE_ID, WatchdogApplication.DeviceId);
        LocationManager.getLocation(new LocationManager.GetLocationCallback() {
            @Override
            public void onSuccess(double latitude, double longitude) {
                params.put(KEY_LATI, latitude);
                params.put(KEY_LONI, longitude);
                WatchServerRestClient.get(OFFLINE, params, null);
            }
        });
    }


    public static void heartbeat() {
        final RequestParams params = new RequestParams();
        params.put(KEY_DEVICE_ID, WatchdogApplication.DeviceId);
//        WatchServerRestClient.get(HEARTBEAT, params, null);
        if (!PrefUtils.isPhoneSafe() && PrefUtils.isGpsAutoUpload()) {
            LocationManager.getLocation(new LocationManager.GetLocationCallback() {
                @Override
                public void onSuccess(double latitude, double longitude) {
                    params.put(KEY_LATI, latitude);
                    params.put(KEY_LONI, longitude);
                    WatchServerRestClient.get(HEARTBEAT, params, null);
                }
            });
        }
        else {
            WatchServerRestClient.get(HEARTBEAT, params, null);
        }
    }

    public static void bind(final NetCallback cb) {
        final RequestParams params = new RequestParams();
        params.put(KEY_DEVICE_ID, WatchdogApplication.DeviceId);
        params.put(KEY_USERID, PrefUtils.getUserId());
        params.put(KEY_DEVICE_NAME, PrefUtils.getDeviceName());
        params.put("phone_number", "12345678910");
        LocationManager.getLocation(new LocationManager.GetLocationCallback() {
            @Override
            public void onSuccess(double latitude, double longitude) {
                params.put(KEY_LONI, longitude);
                params.put(KEY_LATI, latitude);
                WatchServerRestClient.post(BIND, params, generateDefaultHandler(cb));
            }
        });
    }

    public static void gps(double latitude, double longitude) {
        RequestParams params = new RequestParams();
        params.put(KEY_LATI, latitude);
        params.put(KEY_LONI, longitude);
        WatchServerRestClient.get(GPS, params, null);
    }

    public static void alarm() {
        WatchServerRestClient.get(ALARM, null, null);
    }

    public static void disalarm() {
        WatchServerRestClient.get(DISALARM, null, null);
    }

    public static void lock() {
        WatchServerRestClient.get(LOCK, null, null);
    }

    public static void unlock() {
        WatchServerRestClient.get(UNLOCK, null, null);
    }

    public static void erase() {
        WatchServerRestClient.get(ERASE, null, null);
    }

    public static void state(String type) {
        RequestParams params = new RequestParams(KEY_STATE, type);
        WatchServerRestClient.get(STATE, params, null);
    }

    public static void fileList(String json, long task_id, NetCallback cb) {
        RequestParams params = new RequestParams();
        params.put(KEY_DEVICE_ID, WatchdogApplication.DeviceId);
        params.put(KEY_FILE_LIST, json);
        WatchServerRestClient.post(FILE_LIST+"?task_id="+task_id+"&&deviceid="+WatchdogApplication.DeviceId, params, generateDefaultHandler(cb));
//        try {
//            StringEntity entity = new StringEntity(json);
//            entity.setContentType("application/json");
//            WatchServerRestClient.post(FILE_LIST+"?task_id="+task_id+"&&deviceid="+WatchdogApplication.DeviceId, entity, "application/json", generateDefaultHandler(cb));
////            WatchServerRestClient.post(FILE_LIST, new StringEntity(json), "application/json", generateDefaultHandler(cb));
//        } catch (UnsupportedEncodingException e) {
//            e.printStackTrace();
//        }
    }

    public static void fileUpload(File file, String userId, String filePath, String fileName, long task_id, NetCallback cb) throws FileNotFoundException {
        RequestParams params = new RequestParams();
        params.put(KEY_USERID, userId);
        params.put(KEY_FILE_PATH, filePath);
        params.put(KEY_FILE_NAME, fileName);
        params.put(KEY_DEVICE_ID, WatchdogApplication.DeviceId);
        params.put(KEY_FILE, file);
        WatchServerRestClient.post(UPLOAD+"?task_id="+task_id+"&&deviceid="+WatchdogApplication.DeviceId, params, generateDefaultHandler(cb));
    }


    private static AsyncHttpResponseHandler generateDefaultHandler(final NetCallback callback) {

        return new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int i, Header[] headers, byte[] bytes) {
                Log.i(TAG, "code: " + i);
                if (callback == null) {
                    return;
                }
                if (bytes != null) {
                    Log.i(TAG, "content: " + new String(bytes));
                    callback.onSuccess(i, new String(bytes));
                } else {
                    callback.onSuccess(i, "");
                }
            }

            @Override
            public void onFailure(int i, Header[] headers, byte[] bytes, Throwable throwable) {
                Log.i(TAG, "code: " + i);
                if (callback == null) {
                    return;
                }
                if (bytes != null) {
                    Log.i(TAG, "content: " + new String(bytes));
                    callback.onError(i, new String(bytes));
                } else {
                    callback.onError(i, null);
                }
            }
        };
    }

    public static interface NetCallback {
        public void onSuccess(int code, String recv);

        public void onError(int code, String recv);
    }


}
