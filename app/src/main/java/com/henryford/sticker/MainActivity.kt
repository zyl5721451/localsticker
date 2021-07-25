package com.henryford.sticker

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.alibaba.android.arouter.launcher.ARouter
import com.allen.commlib.arouter.ARouterPage
import com.google.android.material.navigation.NavigationView
import com.henryford.sticker.main.MainStickerFragment
import com.henryford.sticker.mine.MineFragment
import com.henryford.ui.bottomnavigation.BottomNavigationBar
import com.henryford.ui.bottomnavigation.BottomNavigationItemView


class MainActivity : BaseActivity() {
    private lateinit var leftNavagation: NavigationView
    private lateinit var bottomNavigationBar: BottomNavigationBar
    private var currentFragment: Fragment? = null

    override fun setListener() {
        leftNavagation.setNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.nav_share -> {

                    true
                }
                R.id.nav_rate -> {

                    true
                }
                R.id.nav_terms -> {

                    true
                }
                R.id.nav_privacy -> {

                    true
                }
                else -> {

                    false
                }
            }
        }

        bottomNavigationBar.onNavigationItemSelectedListener =
            object : BottomNavigationBar.OnNavigationItemSelectedListener {
                override fun onSelect(type: BottomNavigationItemView.BottomItemType) {
                    var selectedFragment: Fragment? = null
                    when (type) {
                        BottomNavigationItemView.BottomItemType.Sticker -> {
                            selectedFragment =
                                supportFragmentManager.findFragmentByTag(MainStickerFragment.TAG)
                        }
                        BottomNavigationItemView.BottomItemType.Make -> {
                            ARouter.getInstance().build(ARouterPage.MAKE_ACTIVITY).navigation(this@MainActivity)
                        }
                        BottomNavigationItemView.BottomItemType.Mine -> {
                            selectedFragment =
                                supportFragmentManager.findFragmentByTag(MineFragment.TAG)
                        }
                    }
                    if (currentFragment != null && selectedFragment != null) {
                        supportFragmentManager.beginTransaction().show(selectedFragment)
                            .hide(currentFragment!!).commitAllowingStateLoss()
                        currentFragment = selectedFragment
                    }

                }

            }
    }

    override fun initData() {
        initializeFragments()
    }

    override fun initView() {
        bottomNavigationBar = findViewById(R.id.nav_view)
        leftNavagation = findViewById<NavigationView>(R.id.left_view)
        leftNavagation.setItemTextColor(null)
        leftNavagation.setItemIconTintList(null)
    }

    override fun getLayoutResId(): Int {
        return R.layout.activity_main
    }

    private fun initializeFragments() {
        addAndHideFragment(MineFragment(), MineFragment.TAG)

        val homeFragment = MainStickerFragment()
        supportFragmentManager.beginTransaction()
            .add(R.id.container, homeFragment, MainStickerFragment.TAG)
            .commitAllowingStateLoss()
        currentFragment = homeFragment
    }

    private fun addAndHideFragment(fragment: Fragment, tag: String) {
        supportFragmentManager.beginTransaction()
            .add(R.id.container, fragment, tag)
            .hide(fragment)
            .commit()
    }
}