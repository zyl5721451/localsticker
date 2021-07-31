package com.henryford.sticker

import android.R
import android.app.Activity
import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import androidx.annotation.StringRes
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.DialogFragment
import com.alibaba.android.arouter.facade.callback.NavigationCallback
import com.alibaba.android.arouter.launcher.ARouter

abstract class BaseActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getLayoutResId())
        ARouter.getInstance().inject(this)
        initView()
        initData()
        initToolbar()
        setListener()
    }

    abstract fun initToolbar()

    protected abstract fun setListener()

    protected abstract fun initData()

    protected abstract fun initView()

    protected abstract fun getLayoutResId(): Int

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    class MessageDialogFragment : DialogFragment() {
        override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
            @StringRes val title = requireArguments().getInt(ARG_TITLE_ID)
            val message = requireArguments().getString(ARG_MESSAGE)
            val dialogBuilder = AlertDialog.Builder(requireActivity())
                .setMessage(message)
                .setCancelable(true)
                .setPositiveButton(
                    R.string.ok
                ) { dialog: DialogInterface?, which: Int -> dismiss() }
            if (title != 0) {
                dialogBuilder.setTitle(title)
            }
            return dialogBuilder.create()
        }

        companion object {
            private const val ARG_TITLE_ID = "title_id"
            private const val ARG_MESSAGE = "message"
            fun newInstance(@StringRes titleId: Int, message: String?): DialogFragment {
                val fragment: DialogFragment = MessageDialogFragment()
                val arguments = Bundle()
                arguments.putInt(ARG_TITLE_ID, titleId)
                arguments.putString(ARG_MESSAGE, message)
                fragment.arguments = arguments
                return fragment
            }
        }
    }

    fun Activity.toActivity(url:String,bundle: Bundle? = null){
        ARouter.getInstance().build(url)
            .with(bundle)
            .navigation(this@BaseActivity)
    }
    fun Activity.toActivity(url:String,bundle: Bundle? = null,call:NavigationCallback){
        ARouter.getInstance().build(url)
            .with(bundle)
            .navigation(this@BaseActivity,call)
    }


}