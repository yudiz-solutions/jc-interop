package com.example.interop

import android.content.Context
import android.content.res.TypedArray
import android.graphics.drawable.Drawable
import android.text.Editable
import android.text.TextWatcher
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import com.example.interop.databinding.CustomEmailValidatorBinding
import java.util.regex.Pattern

class CustomEmailValidator : LinearLayout {
    private var typed: TypedArray? = null
    lateinit var label: String
    lateinit var hint: String
    lateinit var imgRight: Drawable
    lateinit var imgWrong: Drawable
    lateinit var binding: CustomEmailValidatorBinding
    var regex =
        "([a-zA-Z0-9]+(?:[._+-][a-zA-Z0-9]+)*)@([a-zA-Z0-9]+(?:[.-][a-zA-Z0-9]+)*[.][a-zA-Z]{2,})"

    constructor(context: Context?,label:String,hint:String,imgRight:Drawable,imgWrong:Drawable) : super(context) {
        this.hint =hint
        this.imgRight=imgRight
        this.imgWrong=imgWrong
        this.label=label
        init(context, null, 0)
    }

    private fun init(context: Context?, attrs: AttributeSet?, style: Int) {
        binding = CustomEmailValidatorBinding.inflate(LayoutInflater.from(context), this, true)
        attrs?.let {
            typed =
                context?.obtainStyledAttributes(attrs, R.styleable.CustomEmailValidator, style, 0)
        }
        binding.emailValidatorTv.text = label
        binding.emailValidatorEt.hint = hint
        binding.emailValidatorEt.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                val p = Pattern.compile(regex)
                val match = p.matcher(binding.emailValidatorEt.text)
                when {
                    binding.emailValidatorEt.text.isEmpty() -> {
                        binding.emailValidatorIv.setImageDrawable(null)
                    }
                    match.matches() -> {
                        binding.emailValidatorIv.setImageDrawable(imgRight)
                    }
                    else -> {
                        binding.emailValidatorIv.setImageDrawable(imgWrong)
                    }
                }
            }

            override fun afterTextChanged(s: Editable?) {
            }

        })


    }
}
