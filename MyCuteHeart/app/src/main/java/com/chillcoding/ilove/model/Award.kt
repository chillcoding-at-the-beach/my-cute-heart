package com.chillcoding.ilove.model

import com.chillcoding.ilove.R

/**
 * Created by macha on 21/09/2017.
 */
data class Award(val img: Int = R.drawable.ic_menu_awards, val name: CharSequence = "Bonus",
                 var score: Int = 0, val level: Int = -1)
