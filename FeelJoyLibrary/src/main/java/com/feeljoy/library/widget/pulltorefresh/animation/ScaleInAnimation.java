package com.feeljoy.library.widget.pulltorefresh.animation;


import android.view.View;

import com.nineoldandroids.animation.Animator;
import com.nineoldandroids.animation.ObjectAnimator;


/**
 * https://github.com/CymChad/BaseRecyclerViewAdapterHelper
 */
public class ScaleInAnimation implements BaseAnimation {

  private static final float DEFAULT_SCALE_FROM = .5f;
  private final float mFrom;

  public ScaleInAnimation() {
    this(DEFAULT_SCALE_FROM);
  }

  public ScaleInAnimation(float from) {
    mFrom = from;
  }

  @Override
  public Animator[] getAnimators(View view) {


    ObjectAnimator scaleX = ObjectAnimator.ofFloat(view, "scaleX", mFrom, 1f);
    ObjectAnimator scaleY = ObjectAnimator.ofFloat(view, "scaleY", mFrom, 1f);
    return new ObjectAnimator[] { scaleX, scaleY };
  }
}
