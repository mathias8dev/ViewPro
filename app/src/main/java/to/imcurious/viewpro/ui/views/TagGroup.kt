package to.imcurious.viewpro.ui.views

import android.content.Context
import android.util.AttributeSet
import android.view.ContextThemeWrapper
import android.widget.TextView
import androidx.annotation.AnyRes
import androidx.core.content.res.ResourcesCompat
import com.google.android.flexbox.AlignItems
import com.google.android.flexbox.FlexWrap
import com.google.android.flexbox.FlexboxLayout
import com.google.android.flexbox.JustifyContent
import to.imcurious.viewpro.R
import to.imcurious.viewpro.utils.KTool
import kotlin.properties.Delegates

class TagGroup: FlexboxLayout {

    constructor(context: Context, attributeSet: AttributeSet, defStyleAttr: Int): super(context, attributeSet, defStyleAttr) {
        val attributes = context.obtainStyledAttributes(attributeSet, R.styleable.TagGroup)
        spaceWidth = attributes.getDimension(R.styleable.TagGroup_spaceWidth, KTool.pxFromDp(context, 5f))
        background = attributes.getResourceId(R.styleable.TagGroup_itemBackground, R.drawable.rounded_secondary)

        this.justifyContent = JustifyContent.FLEX_START
        this.alignItems = AlignItems.CENTER
        this.flexWrap = FlexWrap.WRAP

        attributes.recycle()
    }
    constructor(context: Context, attributeSet: AttributeSet): super(context, attributeSet, 0)
    constructor(context: Context): super(context, null)

    private var spaceWidth by Delegates.notNull<Float>()
    private var background by Delegates.notNull<@AnyRes Int>()


    fun addTag(tagText: String) {
        val textView = TextView(ContextThemeWrapper(context, R.style.tag_text_style), null, 0)
        (textView.layoutParams as MarginLayoutParams).setMargins(0, 0, spaceWidth.toInt(), 0)
        textView.background = ResourcesCompat.getDrawable(context.resources, background, null)
        textView.text = tagText

        addView(textView)
    }
}