package com.example.athena.Models;

import android.graphics.Bitmap;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.journeyapps.barcodescanner.BarcodeEncoder;

/**
 * The {@code QRCode} class provides functionality to generate a QR code bitmap
 * based on an event ID.
 * This class uses the ZXing library to encode the event ID into a QR code bitmap.
 * The generated QR code can be displayed in the application.
 * Currently, the class provides a method to create the QR code and a getter to retrieve the bitmap.
 */
public class QRCode {
    private Bitmap bitmap;

    /**
     * Default constructor for the QRCode class.
     */
    public QRCode() {
    }

    /**
     * Creates a QR code based on the given event ID.
     * The QR code is encoded as a bitmap image.
     *
     * @param eventID The ID of the event that will be encoded into the QR code.
     * @throws WriterException If an error occurs during the QR code generation.
     */
    public void createQR(String eventID) throws WriterException {
        BarcodeEncoder barcodeEncoder = new BarcodeEncoder();
        this.bitmap = barcodeEncoder.encodeBitmap(eventID, BarcodeFormat.QR_CODE, 400, 400);
    }

    /**
     * Retrieves the generated QR code bitmap.
     *
     * @return The bitmap representing the QR code.
     */
    public Bitmap getQRCode() {
        return bitmap;
    }
}

