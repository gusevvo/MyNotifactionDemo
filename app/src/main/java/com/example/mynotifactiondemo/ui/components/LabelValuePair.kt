package com.example.mynotifactiondemo.ui.components

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.mynotifactiondemo.R
import kotlinx.android.synthetic.main.component_label_value_pair.view.*

class LabelValuePair @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyle: Int = 0
) : ConstraintLayout(context, attrs, defStyle) {

    init {
        LayoutInflater.from(context).inflate(R.layout.component_label_value_pair, this, true)

        attrs?.let {
            val typedArray = context.obtainStyledAttributes(it, R.styleable.LabelValuePair, 0, 0)
            labelText.text = typedArray.getString(R.styleable.LabelValuePair_labelText)
            valueText.text = typedArray.getString(R.styleable.LabelValuePair_valueText)
            typedArray.recycle()
        }
    }
}