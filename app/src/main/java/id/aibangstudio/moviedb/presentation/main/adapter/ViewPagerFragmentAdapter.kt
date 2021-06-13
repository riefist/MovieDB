package id.aibangstudio.moviedb.presentation.main.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import id.aibangstudio.moviedb.presentation.main.fragments.favorite.FavoriteFragment
import id.aibangstudio.moviedb.presentation.main.fragments.home.HomeFragment
import id.aibangstudio.moviedb.presentation.main.fragments.popular.PopularFragment

class ViewPagerFragmentAdapter(fa: FragmentActivity): FragmentStateAdapter(fa) {

    override fun getItemCount(): Int = 3

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> HomeFragment()
            1 -> PopularFragment()
            2 -> FavoriteFragment()
            else -> throw IllegalArgumentException("position not implemented")
        }

    }
}