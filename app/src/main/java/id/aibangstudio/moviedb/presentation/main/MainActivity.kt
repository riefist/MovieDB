package id.aibangstudio.moviedb.presentation.main

import android.os.Bundle
import id.aibangstudio.moviedb.R
import id.aibangstudio.moviedb.databinding.ActivityMainBinding
import id.aibangstudio.moviedb.presentation.base.BaseViewBindingActivity
import id.aibangstudio.moviedb.presentation.main.adapter.ViewPagerFragmentAdapter

class MainActivity : BaseViewBindingActivity<ActivityMainBinding>(ActivityMainBinding::inflate) {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.viewPager.adapter = ViewPagerFragmentAdapter(this)
        binding.viewPager.isUserInputEnabled = false

        binding.bottomNav.setOnNavigationItemSelectedListener {
            when(it.itemId){
                R.id.home -> {
                    binding.viewPager.currentItem = 0
                    setToolbarTitle(getString(R.string.app_name))
                }
                R.id.popular -> {
                    binding.viewPager.currentItem = 1
                    setToolbarTitle("My Movie")
                }
                R.id.favorite -> {
                    binding.viewPager.currentItem = 2
                    setToolbarTitle("Favorite Movie")
                }

            }
            true
        }

    }

    fun setToolbarTitle(title: String){
        supportActionBar?.title = title
    }

}
