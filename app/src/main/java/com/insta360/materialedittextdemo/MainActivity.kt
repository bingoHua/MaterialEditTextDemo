package com.insta360.materialedittextdemo

import android.animation.ObjectAnimator
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    private val view by lazy {
        View(this)
    }
    private var floatingLabelFraction = 0f

    private val animator: ObjectAnimator by lazy {
        ObjectAnimator.ofFloat(this, "floatingLabelFraction", 0f, 1f)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        view.visibility = View.GONE
        animator.start()
        setContentView(R.layout.activity_main)

        /*val editText = findViewById<MaterialEditTextDemo>(R.id.edit_text)
        editText.postDelayed({
            editText.useFloatLabel = false
        }, 3000)*/
    }
}