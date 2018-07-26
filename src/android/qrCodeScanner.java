package cn.x1ongzhu.qrCodeScanner;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.text.TextUtils;

import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaArgs;
import org.apache.cordova.CordovaPlugin;
import org.json.JSONException;

/**
 * Created by xiongzhu on 2017/12/14.
 */

public class qrCodeScanner extends CordovaPlugin {
    private static final int REQUEST_SCAN = 1;
    private CallbackContext callbackContext;

    @Override
    public boolean execute(String action, CordovaArgs args, final CallbackContext callbackContext) throws JSONException {
        this.callbackContext = callbackContext;
        if (action.equals("start")) {
            if (cordova.hasPermission(Manifest.permission.CAMERA)) {
                this.cordova.startActivityForResult(this, new Intent(this.cordova.getActivity(), ScanActivity.class), REQUEST_SCAN);
            } else {
                cordova.requestPermission(this, 1, Manifest.permission.CAMERA);
            }
        }
        return true;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);
        if (resultCode == Activity.RESULT_OK) {
            String result = intent.getStringExtra("result");
            if (!TextUtils.isEmpty(result)) {
                callbackContext.success(result);
            }
        }
    }
}
