package com.chillcoding.ilove.view.dialog

import android.app.AlertDialog
import android.app.Dialog
import android.app.DialogFragment
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import com.chillcoding.ilove.App
import com.chillcoding.ilove.MainActivity
import com.chillcoding.ilove.R
import com.chillcoding.ilove.SecondActivity
import com.chillcoding.ilove.extension.DelegatesExt
import com.chillcoding.ilove.extension.playGame
import com.chillcoding.ilove.extension.setUpNewGame
import com.chillcoding.ilove.model.FragmentId
import com.chillcoding.ilove.model.GameData
import com.chillcoding.ilove.view.GameView
import com.chillcoding.ilove.view.activity.AwardsActivity
import kotlinx.android.synthetic.main.dialog_end_game.view.*
import org.jetbrains.anko.share
import org.jetbrains.anko.startActivity
import java.util.*

/**
 * Created by macha on 01/08/2017.
 */
class EndGameDialog : DialogFragment() {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        var bestScore: Int by DelegatesExt.preference(activity, App.PREF_BEST_SCORE, 0)
        val builder = AlertDialog.Builder(activity)
        val successString = resources.getStringArray(R.array.word_success)
        val endGameView = (LayoutInflater.from(activity)).inflate(R.layout.dialog_end_game, null)
        val data = arguments.getParcelable<GameData>(App.STATE_GAME_DATA)
        endGameView.dialogTitleTextView.text = "${successString[Random().nextInt(successString.size)].toUpperCase()}!"
        endGameView.dialogScoreTextView.text = "${data.score}"
        endGameView.dialogLevelTextView.text = "${resources.getString(R.string.word_level)} ${data.level}"

        when {
            data.score < GameView.TAPS_PER_LEVEL -> {
                endGameView.dialogTitleTextView.text = getString(R.string.failure).toUpperCase()
                endGameView.dialogLevelTextView.text = getString(R.string.you_can_better_text)
                endGameView.starImageView.setColorFilter(App.sColors[1])
                endGameView.middleStarImageView.setColorFilter(App.sColors[1])
                endGameView.endStarImageView.setColorFilter(App.sColors[1])
            }
            data.score > bestScore -> {
                bestScore = data.score
                endGameView.dialogBestTextView.visibility = View.VISIBLE
                endGameView.starImageView.setColorFilter(App.sColors[7])
                endGameView.middleStarImageView.setColorFilter(App.sColors[7])
                endGameView.endStarImageView.setColorFilter(App.sColors[7])
                if (data.awardUnlocked) {
                    endGameView.dialogAwardTextView.visibility = View.VISIBLE
                    builder.setNegativeButton(R.string.action_see_awards, { _, _ -> startActivity<AwardsActivity>() })
                } else
                    builder.setNegativeButton(R.string.action_see_top, { _, _ -> startActivity<SecondActivity>(SecondActivity.FRAGMENT_ID to FragmentId.TOP.ordinal) })
            }
        }
        val activity = (activity as MainActivity)
        val score = data.score
        activity.setUpNewGame()
        builder.setView(endGameView)
                .setPositiveButton(R.string.action_play, { _, _ -> activity.playGame(true) })
                .setNeutralButton(R.string.action_share, { _, _ ->
                    share("${getString(R.string.text_share_score)} ${score} <3 \n" +
                            "${getString(R.string.text_from)} ${getString(R.string.url_app)}", "I Love")
                })
        return builder.create()
    }
}
