package ru.bikbulatov.pureWave

import androidx.fragment.app.FragmentManager
import androidx.fragment.app.commit
import ru.bikbulatov.pureWave.mainWindow.FragmentPicker

object Navigator {
    fun openFragmentPicker(fragmentManager: FragmentManager) {
        if (fragmentManager.findFragmentByTag(FragmentPicker.TAG) == null) {
            fragmentManager.commit {
                setReorderingAllowed(true)
                add(R.id.flContainer, FragmentPicker())
                addToBackStack(FragmentPicker.TAG)
            }
        }
    }
}