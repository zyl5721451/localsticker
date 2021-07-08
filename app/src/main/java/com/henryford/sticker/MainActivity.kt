package com.henryford.sticker

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.google.android.material.navigation.NavigationView
import com.henryford.sticker.main.MainStickerFragment
import com.henryford.sticker.mine.MineFragment
import com.henryford.ui.bottomnavigation.BottomNavigationBar
import com.henryford.ui.bottomnavigation.BottomNavigationItemView


class MainActivity : BaseActivity() {
    private var currentFragment: Fragment? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val bottomNavigationBar: BottomNavigationBar = findViewById(R.id.nav_view)
        var leftNavagation:NavigationView = findViewById(R.id.left_view)
        leftNavagation.setItemTextColor(null)
        leftNavagation.setItemIconTintList(null)
        leftNavagation.setNavigationItemSelectedListener {
            when(it.itemId){
                R.id.nav_share ->{

                    true
                }
                R.id.nav_rate ->{

                    true
                }
                R.id.nav_terms ->{

                    true
                }
                R.id.nav_privacy ->{

                    true
                }
                else ->{

                    false
                }
            }


        }


        val fragmentManager: FragmentManager = supportFragmentManager
        bottomNavigationBar.onNavigationItemSelectedListener =  object :BottomNavigationBar.OnNavigationItemSelectedListener{
            override fun onSelect(type: BottomNavigationItemView.BottomItemType) {
                var selectedFragment: Fragment? = null
                when(type){
                    BottomNavigationItemView.BottomItemType.Sticker ->{
                        selectedFragment = fragmentManager.findFragmentByTag(MainStickerFragment.TAG)
                    }
                    BottomNavigationItemView.BottomItemType.Make ->{

                    }
                    BottomNavigationItemView.BottomItemType.Mine ->{
                        selectedFragment = fragmentManager.findFragmentByTag(MineFragment.TAG)
                    }
                }
                if (currentFragment != null && selectedFragment != null) {
                    fragmentManager.beginTransaction().show(selectedFragment)
                        .hide(currentFragment!!).commitAllowingStateLoss()
                    currentFragment = selectedFragment
                }

            }

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