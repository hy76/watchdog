// Generated code from Butter Knife. Do not modify!
package bitman.ay27.watchdog.ui.activity;

import android.view.View;
import butterknife.ButterKnife.Finder;

public class MainActivity$$ViewInjector {
  public static void inject(Finder finder, final bitman.ay27.watchdog.ui.activity.MainActivity target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131558504, "field 'toolbar'");
    target.toolbar = (android.support.v7.widget.Toolbar) view;
    view = finder.findRequiredView(source, 2131558492, "field 'bootLoaderSwitch' and method 'bootLoaderCheckChanged'");
    target.bootLoaderSwitch = (com.kyleduo.switchbutton.SwitchButton) view;
    ((android.widget.CompoundButton) view).setOnCheckedChangeListener(
      new android.widget.CompoundButton.OnCheckedChangeListener() {
        @Override public void onCheckedChanged(
          android.widget.CompoundButton p0,
          boolean p1
        ) {
          target.bootLoaderCheckChanged(p0, p1);
        }
      });
    view = finder.findRequiredView(source, 2131558509, "field 'sdTitle'");
    target.sdTitle = (android.widget.TextView) view;
    view = finder.findRequiredView(source, 2131558510, "field 'sdSummer'");
    target.sdSummer = (android.widget.TextView) view;
    view = finder.findRequiredView(source, 2131558493, "field 'keyguardSwitch' and method 'keyguardCheckChanged'");
    target.keyguardSwitch = (com.kyleduo.switchbutton.SwitchButton) view;
    ((android.widget.CompoundButton) view).setOnCheckedChangeListener(
      new android.widget.CompoundButton.OnCheckedChangeListener() {
        @Override public void onCheckedChanged(
          android.widget.CompoundButton p0,
          boolean p1
        ) {
          target.keyguardCheckChanged(p0, p1);
        }
      });
    view = finder.findRequiredView(source, 2131558494, "field 'usbSwitch' and method 'usbCheckChanged'");
    target.usbSwitch = (com.kyleduo.switchbutton.SwitchButton) view;
    ((android.widget.CompoundButton) view).setOnCheckedChangeListener(
      new android.widget.CompoundButton.OnCheckedChangeListener() {
        @Override public void onCheckedChanged(
          android.widget.CompoundButton p0,
          boolean p1
        ) {
          target.usbCheckChanged(p0, p1);
        }
      });
    view = finder.findRequiredView(source, 2131558507, "field 'userSummer'");
    target.userSummer = (android.widget.TextView) view;
    view = finder.findRequiredView(source, 2131558505, "method 'loginClick'");
    view.setOnClickListener(
      new android.view.View.OnClickListener() {
        @Override public void onClick(
          android.view.View p0
        ) {
          target.loginClick(p0);
        }
      });
    view = finder.findRequiredView(source, 2131558508, "method 'sdPanelClick'");
    view.setOnClickListener(
      new android.view.View.OnClickListener() {
        @Override public void onClick(
          android.view.View p0
        ) {
          target.sdPanelClick(p0);
        }
      });
    view = finder.findRequiredView(source, 2131558495, "method 'drawPanelClick'");
    view.setOnClickListener(
      new android.view.View.OnClickListener() {
        @Override public void onClick(
          android.view.View p0
        ) {
          target.drawPanelClick(p0);
        }
      });
    view = finder.findRequiredView(source, 2131558514, "method 'setPasswdPanelClick'");
    view.setOnClickListener(
      new android.view.View.OnClickListener() {
        @Override public void onClick(
          android.view.View p0
        ) {
          target.setPasswdPanelClick(p0);
        }
      });
    view = finder.findRequiredView(source, 2131558515, "method 'watchClick'");
    view.setOnClickListener(
      new android.view.View.OnClickListener() {
        @Override public void onClick(
          android.view.View p0
        ) {
          target.watchClick(p0);
        }
      });
    view = finder.findRequiredView(source, 2131558517, "method 'nfcClick'");
    view.setOnClickListener(
      new android.view.View.OnClickListener() {
        @Override public void onClick(
          android.view.View p0
        ) {
          target.nfcClick(p0);
        }
      });
  }

  public static void reset(bitman.ay27.watchdog.ui.activity.MainActivity target) {
    target.toolbar = null;
    target.bootLoaderSwitch = null;
    target.sdTitle = null;
    target.sdSummer = null;
    target.keyguardSwitch = null;
    target.usbSwitch = null;
    target.userSummer = null;
  }
}
