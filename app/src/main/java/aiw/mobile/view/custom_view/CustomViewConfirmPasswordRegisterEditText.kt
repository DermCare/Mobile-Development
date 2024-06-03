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

class CustomViewConfirmPasswordRegisterEditText @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null
) : AppCompatEditText(context, attrs) {

    private var iconEmail: Drawable

    init {
        iconEmail = ContextCompat.getDrawable(context, R.drawable.email_icon) as Drawable

        // Mengatur background warna putih
        setBackgroundColor(ContextCompat.getColor(context, android.R.color.white))

        // Menghilangkan garis bawah
        /*background = null*/

        // Mengatur Font Family
        typeface = ResourcesCompat.getFont(context, R.font.montserrat_medium)

        // Mengatur Size
        textSize = 12f

        // Menentukan ukuran ikon (misalnya, 24dp)
        /*val iconSize = context.resources.getDimensionPixelSize(R.dimen.icon_size)
        iconEmail.setBounds(0, 0, iconSize, iconSize)*/

        // Mengatur background rounded
        background = ContextCompat.getDrawable(context, R.drawable.rounded_shape)

        // Menambahkan ikon di sebelah kanan EditText
        setCompoundDrawablesWithIntrinsicBounds(null, null, iconEmail, null)

        // Menambahkan padding agar ikon tidak terlalu dekat dengan teks
        compoundDrawablePadding = 16 // ganti dengan nilai padding yang diinginkan
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        hint = "Confirm Password"
        textAlignment = View.TEXT_ALIGNMENT_TEXT_START
    }
}