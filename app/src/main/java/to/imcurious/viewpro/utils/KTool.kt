package to.imcurious.viewpro.utils

import android.content.Context
import android.util.TypedValue

object KTool {

    /*fun dpToPx(value: Float, context: Context): Float {
        val res = context.resources
        return TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_DIP,
            value,
            res.displayMetrics
        )
    }

    fun pxToDp(value: Float, context: Context): Float {
        val res = context.resources
        return TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_PX,
            value,
            res.displayMetrics
        )
    }*/

    fun dpFromPx(context: Context, px: Float): Float {
        return px / context.resources.displayMetrics.density
    }

    fun pxFromDp(context: Context, dp: Float): Float {
        return dp * context.resources.displayMetrics.density
    }

}