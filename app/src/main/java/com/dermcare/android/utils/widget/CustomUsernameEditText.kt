package com.dermcare.android.utils.widget

import android.content.Context
import android.graphics.Canvas
import android.text.Editable
import android.text.TextWatcher
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import androidx.appcompat.widget.AppCompatEditText
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat
import com.dermcare.android.R

class CustomUsernameEditText @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null
) : AppCompatEditText(context, attrs), View.OnTouchListener {


    init {

        setBackgroundColor(ContextCompat.getColor(context, android.R.color.white))
        typeface = ResourcesCompat.getFont(context, R.font.montserrat_medium)
        textSize = 12f
        background = ContextCompat.getDrawable(context, R.drawable.rounded_shape)

        compoundDrawablePadding = 16
        setHintTextColor(ContextCompat.getColor(context, R.color.hintColor))

        setOnTouchListener(this)

        addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, after: Int) {
            }

            override fun afterTextChanged(s: Editable?) {
            }
        })
    }
    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        hint = "Username"
        textAlignment = View.TEXT_ALIGNMENT_TEXT_START
//        textAlignment = View.TEXT_ALIGNMENT_VIEW_START
    }

    override fun onTouch(p0: View?, p1: MotionEvent?): Boolean {
        return false
    }
}