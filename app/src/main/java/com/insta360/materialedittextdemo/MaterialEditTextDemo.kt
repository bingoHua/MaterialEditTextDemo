package com.insta360.materialedittextdemo

import android.animation.Animator
import android.animation.ObjectAnimator
import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View
import androidx.appcompat.widget.AppCompatEditText

private val TEXT_SIZE = 12.dp()
private val HORIZONTAL_OFFSET = 5.dp()
private val VERTICAL_OFFSET = 23.dp()
private val EXTRA_VERTICAL_OFFSET = 10.dp()

class MaterialEditTextDemo(context: Context, attrs: AttributeSet) :
    AppCompatEditText(context, attrs) {

    private val paint = Paint(Paint.ANTI_ALIAS_FLAG)
    private var floatLabelShown = false

    private var floatingLabelFraction = 0f
        set(value) {
            field = value
            invalidate()
        }

    private val animator: ObjectAnimator by lazy {
        ObjectAnimator.ofFloat(this, "floatingLabelFraction", 0f, 1f)
    }

    private var useFloatLabel = false
        set(value) {
            if (field != value) {
                field = value
                if (field) {
                    setPadding(
                        paddingLeft,
                        (paddingTop + TEXT_SIZE).toInt(),
                        paddingRight,
                        paddingBottom
                    )
                } else {
                    setPadding(
                        paddingLeft,
                        (paddingTop - TEXT_SIZE).toInt(),
                        paddingRight,
                        paddingBottom
                    )
                }
            }
        }

    init {
        paint.textSize = TEXT_SIZE
        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.MaterialEditTextDemo)
        useFloatLabel =
            typedArray.getBoolean(R.styleable.MaterialEditTextDemo_useFloatingLabel, true)
        typedArray.recycle()
    }

    override fun onTextChanged(
        text: CharSequence?,
        start: Int,
        lengthBefore: Int,
        lengthAfter: Int
    ) {
        if (floatLabelShown && !text.isNullOrEmpty()) {
            floatLabelShown = false
            animator.reverse()
        } else if (!floatLabelShown && text.isNullOrEmpty()) {
            floatLabelShown = true
            animator.start()
        }
    }

    override fun draw(canvas: Canvas) {
        super.draw(canvas)
        paint.alpha = (floatingLabelFraction * 0xff).toInt()
        canvas.drawText(
            hint.toString(),
            HORIZONTAL_OFFSET,
            VERTICAL_OFFSET + EXTRA_VERTICAL_OFFSET * (1 - floatingLabelFraction),
            paint
        )

    }

}