package tarsila.costalonga.searchwordapp.ui.about

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import tarsila.costalonga.searchwordapp.R
import tarsila.costalonga.searchwordapp.ui.about.frags.AboutFragment
import tarsila.costalonga.searchwordapp.ui.about.frags.TutorialFragment

class PagerAdapter(fm: FragmentManager) :
    FragmentPagerAdapter(fm, FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    override fun getCount(): Int {

        return 2
    }

    override fun getItem(position: Int): Fragment {
        return when (position) {
            0 -> TutorialFragment()
            else -> AboutFragment()
        }
    }

    override fun getPageTitle(position: Int): CharSequence? {

        return when (position) {
            0 -> "Como usar"
            else -> "Sobre"

        }

    }
}