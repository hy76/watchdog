// Generated code from Butter Knife. Do not modify!
package bitman.ay27.watchdog.ui.activity;

import android.view.View;
import butterknife.ButterKnife.Finder;

public class KeyguardImgActivity$$ViewInjector {
  public static void inject(Finder finder, final bitman.ay27.watchdog.ui.activity.KeyguardImgActivity target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131492928, "field 'dCanvas'");
    target.dCanvas = (bitman.ay27.watchdog.ui.DrawingCanvas) view;
    view = finder.findRequiredView(source, 2131492929, "field 'changeModeBtn' and method 'changeClick'");
    target.changeModeBtn = (android.widget.Button) view;
    view.setOnClickListener(
      new android.view.View.OnClickListener() {
        @Override public void onClick(
          android.view.View p0
        ) {
          target.changeClick(p0);
        }
      });
  }

  public static void reset(bitman.ay27.watchdog.ui.activity.KeyguardImgActivity target) {
    target.dCanvas = null;
    target.changeModeBtn = null;
  }
}