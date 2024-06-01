package com.example.menusdialogsandtouch

import android.app.AlertDialog
import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.ImageView
import androidx.fragment.app.DialogFragment

class CaptchaDialogFragment : DialogFragment() {
    private var isTrafficLightSelected: Boolean = false

    interface CaptchaListener {
        fun onCaptchaDone(isTrafficLightClicked: Boolean)
    }

    companion object {
        fun newInstance(): CaptchaDialogFragment {
            return CaptchaDialogFragment()
        }
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialogView = LayoutInflater.from(requireContext()).inflate(R.layout.dialog_captcha, null)


        val trafficLightImageView = dialogView.findViewById<ImageView>(R.id.imageView4)
        val otherImageView = dialogView.findViewById<ImageView>(R.id.imageView5)
        val otherImageView2 = dialogView.findViewById<ImageView>(R.id.imageView6)
        val otherImageView3 = dialogView.findViewById<ImageView>(R.id.imageView7)

        val builder = AlertDialog.Builder(requireContext())
        builder.setTitle("Captcha")
            .setView(dialogView)
            .setPositiveButton("Done") { _, _ ->
                (activity as? CaptchaListener)?.onCaptchaDone(isTrafficLightSelected)
            }
            .setNegativeButton("Cancel") { _, _ ->
                (activity as? CaptchaListener)?.onCaptchaDone(false)
            }

        trafficLightImageView.setOnClickListener {
            isTrafficLightSelected = true
        }

        otherImageView.setOnClickListener {
            isTrafficLightSelected = false
        }

        otherImageView2.setOnClickListener {
            isTrafficLightSelected = false
        }
        otherImageView3.setOnClickListener {
            isTrafficLightSelected = false
        }

        return builder.create()
    }

    override fun onCancel(dialog: DialogInterface) {
        super.onCancel(dialog)
        (activity as? CaptchaListener)?.onCaptchaDone(false)
    }

}
