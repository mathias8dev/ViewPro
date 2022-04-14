package to.imcurious.viewpro.ui.views

import android.content.Context
import android.util.AttributeSet
import android.view.ContextThemeWrapper
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.AnyRes
import androidx.annotation.DrawableRes
import androidx.core.content.res.ResourcesCompat
import androidx.core.view.size
import androidx.core.widget.ImageViewCompat
import com.google.android.flexbox.AlignItems
import com.google.android.flexbox.FlexWrap
import com.google.android.flexbox.FlexboxLayout
import com.google.android.flexbox.JustifyContent
import com.squareup.picasso.Picasso
import to.imcurious.viewpro.R
import to.imcurious.viewpro.utils.KTool
import kotlin.properties.Delegates

class AvatarGroup: FlexboxLayout {


    constructor(context: Context, attributeSet: AttributeSet, defStyleAttr: Int): super(context, attributeSet, defStyleAttr) {
        val attributes = context.obtainStyledAttributes(attributeSet, R.styleable.AvatarGroup)

        spaceWidth = attributes.getDimension(R.styleable.AvatarGroup_spaceWidth, KTool.pxFromDp(context, 5f))
        background = attributes.getResourceId(R.styleable.AvatarGroup_itemBackground, R.drawable.circle_white)
        mode = attributes.getInt(R.styleable.AvatarGroup_mode, Mode.OVERLAP)

        avatarSize = attributes.getDimension(R.styleable.AvatarGroup_avatarSize, KTool.pxFromDp(context, 50f))

        this.setPadding(spaceWidth.toInt(), 0, 0, 0)
        this.justifyContent = JustifyContent.FLEX_START
        this.alignItems = AlignItems.CENTER
        this.flexWrap = FlexWrap.WRAP

        attributes.recycle()
    }

    constructor(context: Context, attributeSet: AttributeSet): super(context, attributeSet, 0)
    constructor(context: Context): super(context, null)

    private var spaceWidth by Delegates.notNull<Float>()
    private var mode by Delegates.notNull<Int>()
    private var background by Delegates.notNull<@AnyRes Int>()
    private var avatarSize by Delegates.notNull<Float>()


    private fun addAvatar(avatarURL: String ) {
       // Create an imageView
        val imageView = ImageView(ContextThemeWrapper(context, R.style.circle50_image_view), null, 0)
        imageView.setImageDrawable(ResourcesCompat.getDrawable(resources, background, null))
        imageView.layoutParams.height = avatarSize.toInt()
        imageView.layoutParams.width = avatarSize.toInt()

        val p = imageView.layoutParams as MarginLayoutParams
        p.setMargins(-spaceWidth.toInt(), 0, 0, 0)

        if (mode == SPACED) {
            val p = imageView.layoutParams as MarginLayoutParams
            p.setMargins(0, 0, spaceWidth.toInt(), 0)
        }
        addView(imageView)
        // Set it's style
        // Use picasso to download the avatar image
        Picasso.get()
            .load(avatarURL)
            .resize(size, size)
            .centerCrop()
            .into(imageView)
        // Insert the image
    }

    fun addAvatars(vararg avatarsURL: String) {
        if (avatarsURL.size < 4)
        avatarsURL.forEach {
            addAvatar(it)
        }

        else {
            for (i in 0..4) {
                addAvatar(avatarsURL[i])
            }
            // Add remaining participants
            val moreParticipantText = TextView(ContextThemeWrapper(context, R.style.more_participants), null, 0)
            moreParticipantText.text = "+${avatarsURL.size - 4} participants"
            addView(moreParticipantText)
        }


    }

    private companion object Mode {
        val OVERLAP = 1
        val SPACED = 2
    }
}