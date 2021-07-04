package com.henryford.sticker

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.henryford.sticker.mine.MineFragment
import com.henryford.ui.bottomnavigation.BottomNavigationBar


class MainActivity : BaseActivity() {
    private var currentFragment: Fragment? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val bottomNavigationBar: BottomNavigationBar = findViewById(R.id.nav_view)


        val fragmentManager: FragmentManager = supportFragmentManager
        bottomNavigationBar.setOnNavigationItemSelectedListener { item ->
            var selectedFragment: Fragment? = null
            val itemId = item.itemId
            if (itemId == R.id.menu_sticker) {
                selectedFragment = fragmentManager.findFragmentByTag(MainStickerFragment.TAG)
            } else if (itemId == R.id.menu_mine) {
                selectedFragment = fragmentManager.findFragmentByTag(MineFragment.TAG)
            }
            if (currentFragment != null && selectedFragment != null) {
                fragmentManager.beginTransaction().show(selectedFragment)
                    .hide(currentFragment!!).commitAllowingStateLoss()
                currentFragment = selectedFragment
            }
        }
        val actionView: View = bottomNavigationBar.actionView
        val addButton: ImageView = actionView.findViewById(R.id.button_add)
        addButton.setOnClickListener {

        }
        initializeFragments()
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