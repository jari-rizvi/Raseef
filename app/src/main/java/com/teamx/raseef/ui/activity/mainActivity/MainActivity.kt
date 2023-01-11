package com.teamx.raseef.ui.activity.mainActivity

import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.ui.setupWithNavController
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.teamx.raseef.BR
import com.teamx.raseef.MainApplication
import com.teamx.raseef.R
import com.teamx.raseef.baseclasses.BaseActivity
import com.teamx.raseef.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding, MainViewModel>() {


    override val viewModel: Class<MainViewModel>
        get() = MainViewModel::class.java

    override val layoutId: Int
        get() = R.layout.activity_main

    override val bindingVariable: Int
        get() = BR.viewModel

    val RC_SIGN_IN: Int = 1

    private var mFbHelper = null
    private var navController: NavController? = null


    lateinit var progress_bar: ProgressBar
//    private var mFbHelper: FacebookHelper? = null
    lateinit var mGoogleSignInClient: GoogleSignInClient
    lateinit var mGoogleSignInOptions: GoogleSignInOptions

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initialising()

        bottomNav = findViewById<BottomNavigationView>(R.id.bottomnavigationbar)

        navController = Navigation.findNavController(this, R.id.nav_host_fragment)

        setupBottomNavMenu(navController!!)

        navController!!.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {

                R.id.homeFragment -> {
                    bottomNav?.visibility = View.VISIBLE
                }
                R.id.cartFragment -> {
                    bottomNav?.visibility = View.VISIBLE
                }
                R.id.userProfileFragment -> {
                    bottomNav?.visibility = View.VISIBLE
                }
                R.id.notificationFragment -> {
                    bottomNav?.visibility = View.VISIBLE
                }
                else -> {
                    bottomNav?.visibility = View.GONE
                }
            }

        }

//
//        mGoogleSignInOptions = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
//            .requestEmail()
//            .build()
//
//        mGoogleSignInClient = GoogleSignIn.getClient(this, mGoogleSignInOptions)


//        mFbHelper = FacebookHelper(
//            this,
//            "id,name,email,gender,birthday,picture",
//            this
//        )


    }

//    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
//        super.onActivityResult(requestCode, resultCode, data)
//
//        if (requestCode == RC_SIGN_IN) {
//            val task: Task<GoogleSignInAccount> = GoogleSignIn.getSignedInAccountFromIntent(data)
//            try {
//                val account = task.getResult(ApiException::class.java)
//                firebaseAuthWithGoogle(account!!)
//            } catch (e: ApiException) {
//                Log.d("error", e.toString())
//            }
//        } else {
//            mFbHelper!!.onActivityResult(requestCode, resultCode, data)
//        }
//
//    }


//    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
//        super.onActivityResult(requestCode, resultCode, data)
//
//        // Result returned from launching the Intent from GoogleSignInClient.getSignInIntent(...);
//        if (requestCode == 1000) {
//            // The Task returned from this call is always completed, no need to attach
//            // a listener.
//            val task: Task<GoogleSignInAccount> = GoogleSignIn.getSignedInAccountFromIntent(data)
//            handleSignInResult(task)
//        }
//    }
//
//    private fun handleSignInResult(completedTask: Task<GoogleSignInAccount>)    {
//        try {
//            val account = completedTask.getResult(ApiException::class.java)
//
//            // Signed in successfully, show authenticated UI.
//        } catch (e: ApiException) {
//            // The ApiException status code indicates the detailed failure reason.
//            // Please refer to the GoogleSignInStatusCodes class reference for more information.
//            Log.w(ContentValues.TAG, "signInResult:failed code=" + e.statusCode)
//
//        }
//    }

    private fun initialising() {
        progress_bar = findViewById(R.id.progress_bar)
    }

    open fun showProgressBar() {
        progress_bar.visibility = View.VISIBLE
    }


    open fun hideProgressBar() {
        progress_bar.visibility = View.GONE
    }

    fun googleSignIn() {
        val signInIntent = mGoogleSignInClient.signInIntent
        startActivityForResult(signInIntent, RC_SIGN_IN)
    }

    override fun onBackPressed() {
        super.onBackPressed()

    }

    override fun attachBaseContext(newBase: Context?) =
        super.attachBaseContext(MainApplication.localeManager!!.setLocale(newBase!!))
}

var bottomNav: BottomNavigationView? = null

private fun setupBottomNavMenu(navController: NavController) {
    bottomNav?.setupWithNavController(navController)
    bottomNav?.setOnItemSelectedListener {
        when (it.itemId) {
            R.id.profile -> {
                navController.navigate(R.id.userProfileFragment, null)
            }
            R.id.home -> {
                navController.navigate(R.id.homeFragment, null)
            }
            R.id.cart -> {
                navController.navigate(R.id.cartFragment, null)
            }
            R.id.notification -> {
                navController.navigate(R.id.notificationFragment, null)
            }
        }
        return@setOnItemSelectedListener true
    }

}