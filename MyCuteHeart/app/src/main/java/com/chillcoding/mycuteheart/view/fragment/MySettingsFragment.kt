package com.chillcoding.mycuteheart.view.fragment

import android.app.Fragment
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.chillcoding.mycuteheart.R
import com.chillcoding.mycuteheart.extension.DelegatesExt
import kotlinx.android.synthetic.main.fragment_my_settings.*

/**
 * Created by macha on 07/09/2017.
 */
class MySettingsFragment : Fragment() {

    var isSound: Boolean by DelegatesExt.preference(this as Fragment, "PREF_SOUND", true)

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View {
        var view = inflater?.inflate(R.layout.fragment_my_settings, container, false)
        return view!!
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        settingsSoundSwitch.isChecked = isSound
    }

    override fun onPause() {
        super.onPause()
        isSound = settingsSoundSwitch.isChecked
    }
}
