package com.teamx.raseef.ui.fragments.stripe

import android.app.AlertDialog
import android.os.Bundle
import android.view.View
import androidx.lifecycle.lifecycleScope
import com.github.kittinunf.fuel.httpPost
import com.github.kittinunf.fuel.json.responseJson
import com.stripe.android.PaymentConfiguration
import com.stripe.android.model.ConfirmPaymentIntentParams
import com.stripe.android.payments.paymentlauncher.PaymentLauncher
import com.stripe.android.payments.paymentlauncher.PaymentResult
import com.stripe.android.paymentsheet.PaymentSheet
import com.stripe.android.paymentsheet.PaymentSheetResult
import com.teamx.raseef.R
import com.teamx.raseef.baseclasses.BaseFragment
import com.github.kittinunf.result.Result
import com.teamx.raseef.constants.AppConstants
import com.teamx.raseef.databinding.FragmentStripeBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch


@AndroidEntryPoint
class StripeFragment() : BaseFragment<FragmentStripeBinding, StripeFragmentViewModel>() {

    override val layoutId: Int
        get() = R.layout.fragment_stripe
    override val viewModel: Class<StripeFragmentViewModel>
        get() = StripeFragmentViewModel::class.java
    override val bindingVariable: Int
        get() = 1

    companion object {
        fun newInstance() = StripeFragment()
    }

    lateinit var customerConfig: PaymentSheet.CustomerConfiguration
    lateinit var paymentIntentClientSecret: String
    lateinit var paymentSheet: PaymentSheet
    private lateinit var paymentLauncher: PaymentLauncher

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        paymentSheet = PaymentSheet(this, ::onPaymentSheetResult)
        val paymentConfiguration = PaymentConfiguration.getInstance(requireContext())
        paymentLauncher = PaymentLauncher.Companion.create(
            requireActivity(),
//            paymentConfiguration.publishableKey,
            "pk_test_UGqCEQSFSmhogt9N3hvKS7UA00eopc2Bd4",
//            "sk_test_iZjWnmMmtQ0KV8TGFZxm7Den00Wrv7GOBU",
            paymentConfiguration.stripeAccountId,
            ::onPaymentResult
        )
        "${AppConstants.ApiConfiguration.BASE_URL2}stripe/charge".httpPost()
            .responseJson { _, _, result ->
                if (result is Result.Success) {
                    val responseJson = result.get().obj()
//                    paymentIntentClientSecret = "pi_3M7L93G5yM3A5WD31fF4JY7e_secret_YsTgM0h5Vt0zlcqEoNIQ00sSn"
//                    paymentIntentClientSecret = responseJson.getString("client_secret")
                    customerConfig = PaymentSheet.CustomerConfiguration(
                        responseJson.getString("customer"),
                        responseJson.getString("ephemeralKey")
                    )
                    val publishableKey = paymentConfiguration.publishableKey
//                        "pk_test_TYooMQauvdEDq54NiTphI7jx"/*responseJson.getString("publishableKey")*/
                    PaymentConfiguration.init(requireContext(), publishableKey)
                }
            }
        startCheckout()

    }

    private fun displayAlert(
        title: String,
        message: String
    ) {
        requireActivity().runOnUiThread {
            val builder = AlertDialog.Builder(requireActivity())
                .setTitle(title)
                .setMessage(message)

            builder.setPositiveButton("Ok", null)
            builder
                .create()
                .show()
        }
    }

    private fun startCheckout() {

        /*  val params: ConfirmPaymentIntentParams = ConfirmPaymentIntentParams.builder()
              .setAmount(1099L)
              .setCurrency("usd")
              .build()*/


        // Create a PaymentIntent by calling the sample server's /create-payment-intent endpoint.
        ApiClient().createPaymentIntent(
            "pm_card_visa_debit",
            completion = { paymentIntentClientSecret, error ->
                run {
                    paymentIntentClientSecret?.let {
                        this.paymentIntentClientSecret = it
                    }
                    error?.let {
                        displayAlert(
                            "Failed to load page",
                            "Error: $error"
                        )
                    }
                }
            })

        // Confirm the PaymentIntent with the card widget
        mViewDataBinding.payButton.setOnClickListener {
            mViewDataBinding.cardInputWidget.paymentMethodCreateParams?.let { params ->
                val confirmParams = ConfirmPaymentIntentParams.createWithPaymentMethodCreateParams(
                    params,
                    paymentIntentClientSecret
                )
                lifecycleScope.launch {
                    paymentLauncher.confirm(confirmParams)
                }
            }
        }
    }


    fun presentPaymentSheet() {
        paymentSheet.presentWithPaymentIntent(
            paymentIntentClientSecret,
            PaymentSheet.Configuration(
                merchantDisplayName = "My merchant name",
                customer = customerConfig,
                // Set `allowsDelayedPaymentMethods` to true if your business
                // can handle payment methods that complete payment after a delay, like SEPA Debit and Sofort.
                allowsDelayedPaymentMethods = true
            )
        )
    }

    private fun onPaymentSheetResult(paymentSheetResult: PaymentSheetResult) {
        // implemented in the next steps

        when (paymentSheetResult) {
            is PaymentSheetResult.Canceled -> {
                print("Canceled")
            }
            is PaymentSheetResult.Failed -> {
                print("Error: ${paymentSheetResult.error}")
            }
            is PaymentSheetResult.Completed -> {
                // Display for example, an order confirmation screen
                print("Completed")
            }
        }
    }

    private fun onPaymentResult(paymentResult: PaymentResult) {
        val message = when (paymentResult) {
            is PaymentResult.Completed -> {
                "Completed!"
            }
            is PaymentResult.Canceled -> {
                "Canceled!"
            }
            is PaymentResult.Failed -> {
                // This string comes from the PaymentIntent's error message.
                // See here: https://stripe.com/docs/api/payment_intents/object#payment_intent_object-last_payment_error-message
                "Failed: " + paymentResult.throwable.message
            }
        }
        displayAlert(
            "Payment Result:",
            message,
//            restartDemo = true
        )
    }
}