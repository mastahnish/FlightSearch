package pl.myosolutions.flightsearch.extensions

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.view.View


fun View.gone() {
    this.visibility = View.GONE
}

fun View.visible() {
    this.visibility = View.VISIBLE
}

fun View.visibleOrGone(visible: Boolean) {
    if (visible) {
        this.visible()
    } else {
        this.gone()
    }
}

fun View.visibleAnimated() {
    this.animate()
        .alpha(1.0f)
        .scaleY(1F)
        .setListener(object : AnimatorListenerAdapter() {
            override fun onAnimationStart(animation: Animator?) {
                super.onAnimationStart(animation)
                this@visibleAnimated.clearAnimation()
                this@visibleAnimated.visible()
            }
        })
}

fun View.goneAnimated(doAfterAnimation : () -> Unit) {
    this.animate()
        .alpha(0.0f)
        .scaleY(0F)
        .setListener(object : AnimatorListenerAdapter() {
            override fun onAnimationEnd(animation: Animator?) {
                super.onAnimationEnd(animation)
                this@goneAnimated.clearAnimation()
                this@goneAnimated.gone()
                doAfterAnimation()
            }
        })
}

