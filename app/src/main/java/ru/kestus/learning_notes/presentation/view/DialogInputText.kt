package ru.kestus.learning_notes.presentation.view

import android.app.Dialog
import android.content.Context
import android.view.LayoutInflater
import androidx.core.widget.doOnTextChanged
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LifecycleRegistry
import androidx.lifecycle.MutableLiveData
import ru.kestus.learning_notes.databinding.DialogInputTextBinding

class DialogInputText(
    context: Context,
    title: String? = null,
    hint: String? = null,
    submitListener: ((DialogInputText) -> Unit)? = null
) : Dialog(context), LifecycleOwner {

    private val dialogLifecycle = LifecycleRegistry(this)
    override val lifecycle: Lifecycle get() = dialogLifecycle

    private val binding: DialogInputTextBinding by lazy {
        DialogInputTextBinding.inflate(LayoutInflater.from(context))
    }
    private var submitListener: ((DialogInputText) -> Unit)? = null
    private val errorMessage = MutableLiveData<String?>(null)
    var input: String? = null


    init {
        dialogLifecycle.currentState = Lifecycle.State.CREATED
        setupView(title, hint, submitListener)
        setOnClickListeners()
        observeError()
        setOnTextChangeListener()
    }

    private fun setupView(
        title: String?,
        hint: String?,
        submitListener: ((DialogInputText) -> Unit)?
    ) {
        this.setContentView(binding.root)
        title?.let { binding.dialogTitle.text = it }
        hint?.let { binding.inputEditText.hint = it }
        submitListener?.let {
            this.submitListener = it
        }
    }

    private fun setOnClickListeners() {
        binding.dialogButtonNegative.setOnClickListener { this.cancel() }
        binding.dialogButtonPositive.setOnClickListener {
            submitListener?.invoke(this)
            if (errorMessage.value == null) {
                dismiss()
            }
        }
    }

    private fun observeError() {
        errorMessage.observe(this) {
            binding.inputLayout.error = null
            it?.let {
                binding.inputLayout.error = it
            }
        }
    }

    private fun setOnTextChangeListener() {
        binding.inputEditText.doOnTextChanged { text, _, _, _ ->
            errorMessage.value = null
            input = text.toString()
        }
    }

    // lifecycle management
    override fun onAttachedToWindow() {
        dialogLifecycle.currentState = Lifecycle.State.STARTED
        dialogLifecycle.currentState = Lifecycle.State.RESUMED
    }

    override fun onDetachedFromWindow() {
        dialogLifecycle.currentState = Lifecycle.State.DESTROYED
    }

    // setters
    fun setErrorMessage(message: String) {
        errorMessage.value = message
    }
}