package com.journeyapps.barcodescanner;

import android.app.Activity;
import android.os.Bundle;
import androidx.annotation.NonNull;
import android.view.KeyEvent;
import android.content.Intent;

import com.google.zxing.client.android.R;

/**
 *
 */
public class CaptureActivity extends Activity {
    private CaptureManager capture;
    private DecoratedBarcodeView barcodeScannerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        barcodeScannerView = initializeContent();
        Intent intent = new Intent("com.google.zxing.client.android.SCAN");
        // Only deal with QR
        intent.putExtra("SCAN_MODE", "QR_CODE_MODE");
        capture = new CaptureManager(this, barcodeScannerView);
        capture.initializeFromIntent(intent, savedInstanceState);
        capture.decode();
    }

    /**
     * Override to use a different layout.
     *
     * @return the DecoratedBarcodeView
     */
    protected DecoratedBarcodeView initializeContent() {
        setContentView(R.layout.zxing_capture);
        return (DecoratedBarcodeView)findViewById(R.id.zxing_barcode_scanner);
    }

    @Override
    protected void onResume() {
        super.onResume();
        capture.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        capture.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        capture.onDestroy();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        capture.onSaveInstanceState(outState);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String permissions[], @NonNull int[] grantResults) {
        capture.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        return barcodeScannerView.onKeyDown(keyCode, event) || super.onKeyDown(keyCode, event);
    }
}
