package aiw.mobile.view.custom_view

import aiw.mobile.testonboardingpage.R
import android.content.Context
import android.graphics.Canvas
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.view.View
import androidx.appcompat.widget.AppCompatEditText
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat

class CustomViewPasswordLoginEditText @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null
) : AppCompatEditText(context, attrs) {

    private var iconPassword: Drawable

    init {
        iconPassword = ContextCompat.getDrawable(context, R.drawable.eyes_close_icon) as Drawable

        // Mengatur background warna putih
        setBackgroundColor(ContextCompat.getColor(context, android.R.color.white))

        // Mengatur Font Family
        typeface = ResourcesCompat.getFont(context, R.font.montserrat_medium)

        // Mengatur Size
        textSize = 12f

        // Mengatur background rounded
        background = ContextCompat.getDrawable(context, R.drawable.rounded_shape)

        // Menambahkan ikon di sebelah kanan EditText
        setCompoundDrawablesWithIntrinsicBounds(null, null, iconPassword, null)

        // Menambahkan padding agar ikon tidak terlalu dekat dengan teks
        compoundDrawablePadding = 16

        // Mengatur warna hint
        setHintTextColor(ContextCompat.getColor(context, R.color.hintColor))
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        hint = "Password"
        textAlignment = View.TEXT_ALIGNMENT_TEXT_START
    }
}