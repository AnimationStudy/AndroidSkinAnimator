package skin.support.animator.SingleAnimator;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.animation.LinearInterpolator;

import skin.support.animator.Action;
import skin.support.animator.SkinAnimator;

/**
 * Created by erfli on 2/25/17.
 */

public class TranslationHintAnimator2 extends SingleAnimatorImpl {

    private ObjectAnimator animator;

    private TranslationHintAnimator2() {
    }


    public static TranslationHintAnimator2 getInstance() {
        return new TranslationHintAnimator2();
    }

    @Override
    public SkinAnimator apply(@NonNull final View view, @Nullable final Action action) {
        animator = ObjectAnimator.ofPropertyValuesHolder(view,
                PropertyValuesHolder.ofFloat("alpha", 1, 0),
                PropertyValuesHolder.ofFloat("translationX", view.getLeft(),
                        view.getLeft() - view.getWidth() / 4, view.getLeft() + view.getWidth() / 2));
        animator.setDuration(PRE_DURATION);
        animator.setInterpolator(new LinearInterpolator());
        animator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                view.setVisibility(View.GONE);
                if (action != null) {
                    action.action();
                }
            }
        });
        return this;
    }

    @Override
    public void start() {
        animator.start();
    }
}