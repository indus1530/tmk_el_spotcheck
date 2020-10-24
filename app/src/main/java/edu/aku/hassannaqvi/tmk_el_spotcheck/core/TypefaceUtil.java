package edu.aku.hassannaqvi.tmk_el_spotcheck.core;

import android.content.Context;
import android.graphics.Typeface;
import android.util.Log;

import java.lang.reflect.Field;

/**
 * Created by hassan.naqvi on 11/30/2016.
 */
public class TypefaceUtil {
    /**
     * Using reflection to override default typeface
     * NOTICE: DO NOT FORGET TO SET TYPEFACE FOR APP THEME AS DEFAULT TYPEFACE WHICH WILL BE OVERRIDDEN
     *
     * @param context                    to work with assets
     * @param defaultFontNameToOverride  for example "monospace"
     * @param customFontFileNameInAssets file name of the font from assets
     */
    public static void overrideFont(Context context, String defaultFontNameToOverride, String customFontFileNameInAssets) {
        try {
            final Typeface customFontTypeface = Typeface.createFromAsset(context.getAssets(), customFontFileNameInAssets);

            final Field defaultFontTypefaceField = Typeface.class.getDeclaredField(defaultFontNameToOverride);
            defaultFontTypefaceField.setAccessible(true);
            defaultFontTypefaceField.set(null, customFontTypeface);
            Log.e("TypeFace", "Custom font set " + customFontFileNameInAssets + " instead of " + defaultFontNameToOverride + "\r\n");

        } catch (Exception e) {

            Log.d("TypeFace", "Can not set custom font " + customFontFileNameInAssets + " instead of " + defaultFontNameToOverride + "\r\n" + e);
        }
    }
}
