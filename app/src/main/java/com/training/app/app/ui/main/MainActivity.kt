package com.training.app.app.ui.main

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Parcelable
import com.training.app.app.ui.base.BaseActivity
import com.training.app.databinding.ActivityMainBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : BaseActivity(), MainListener {

    private val binding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(
            layoutInflater
        ).apply {
            lifecycleOwner = this@MainActivity
        }
    }

    private val viewModel: MainViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(binding.root)

        val menuOption = intent.extras?.getParcelable(EXTRA_OPTION) ?: MainMenuOptions.HOME
        binding.bottomNavView.selectedItemId = menuOption.menuId
        changeFragment(menuOption)

        binding.bottomNavView.setOnItemSelectedListener { item ->
            val option = MainMenuOptions.findByMenuId(item.itemId)

            option?.let {
                changeFragment(it)
                true
            } ?: false
        }

        viewModel.isPrivacyPolicyAccepted.observe(
            this,
            getResultObjectObserver(action = {})
        )
    }

    override fun changeFragment(option: MainMenuOptions) {
        supportFragmentManager.beginTransaction().replace(binding.flMain.id, option.fragment()).commit()
    }

    companion object {
        const val EXTRA_OPTION = "EXTRA_OPTION"
        fun getCallingIntent(context: Context): Intent =
            Intent(context, MainActivity::class.java)

        fun getCallingIntent(context: Context, option: MainMenuOptions) =
            Intent(context, MainActivity::class.java).putExtra(EXTRA_OPTION, option as Parcelable)
    }
}
