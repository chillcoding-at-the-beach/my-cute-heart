package com.chillcoding.mycuteheart.model

import android.os.Parcel
import android.os.Parcelable

/**
 * Created by macha on 21/07/2017.
 */
class GameData : Parcelable {

    var score = 0
    var level = 1
    var nbLife = 3

    constructor()

    constructor(parcel: Parcel) {
        score = parcel.readInt()
        level = parcel.readInt()
        nbLife = parcel.readInt()
    }

    override fun writeToParcel(dest: Parcel?, flags: Int) {
        dest?.writeInt(score)
        dest?.writeInt(level)
        dest?.writeInt(nbLife)
    }

    override fun describeContents(): Int {
        return 0
    }

    val CREATOR: Parcelable.Creator<GameData> = object : Parcelable.Creator<GameData> {
        override fun createFromParcel(`in`: Parcel): GameData {
            return GameData(`in`)
        }

        override fun newArray(size: Int): Array<GameData?> {
            return arrayOfNulls<GameData>(size)
        }
    }

}