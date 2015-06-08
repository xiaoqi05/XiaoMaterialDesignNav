package com.demo.materialdesign.factories;

import android.content.Context;
import android.graphics.Typeface;

/**
 * Typeface creation.
 *
 * @author Sotti https://plus.google.com/+PabloCostaTirado/about
 */
public class FactoryTypeface
{
    public static Typeface createTypeface(Context context, int typeface)
    {
        return Typeface.createFromAsset(context.getAssets(), String.format("fonts/%s.ttf", context.getString(typeface)));
    }
}
