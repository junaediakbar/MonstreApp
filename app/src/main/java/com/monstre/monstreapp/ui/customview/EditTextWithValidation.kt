package com.monstre.monstreapp.ui.customview

import android.content.Context
import android.text.Editable
import android.text.TextWatcher
import android.util.AttributeSet
import android.util.Patterns
import androidx.appcompat.content.res.AppCompatResources.getDrawable
import androidx.appcompat.widget.AppCompatEditText
import androidx.core.content.ContextCompat
import com.monstre.monstreapp.R

open class EditTextWithValidation : AppCompatEditText {
    constructor(context: Context) : super(context) {
        init()
    }

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        init()
    }

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ) {
        init()
    }

    private var inputValidation: InputValidation? = null

    interface InputValidation {
        val errorMessage: String
        fun validate(input: String): Boolean
    }

    fun setValidationCallback(inputValidation: InputValidation) {
        this.inputValidation = inputValidation
    }

    private fun init() {
        addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                // nothing
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                // nothing
            }

            override fun afterTextChanged(s: Editable?) {
                validateInput()
            }
        })
    }

    fun validateInput(): Boolean {
        val input = text.toString()
        val isValid = inputValidation?.validate(input) ?: true

        error = if (isValid) {
            null
        } else {
            inputValidation?.errorMessage ?: ""
        }

        if(error == null){
            setCompoundDrawablesWithIntrinsicBounds(
                null,null,  ContextCompat.getDrawable(context, R.drawable.ic_checked),null
            )
        }

        return isValid
    }
}