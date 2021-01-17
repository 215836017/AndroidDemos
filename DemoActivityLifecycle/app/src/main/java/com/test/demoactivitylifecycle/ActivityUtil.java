package com.test.demoactivitylifecycle;

import android.app.ActivityManager;
import android.content.ComponentName;
import android.content.Context;

public class ActivityUtil {

    /**
     * 桌面
     *  pkg:com.hikvision.ezviz.simplehome_kw
     *  cls:com.hikvision.ezviz.simplehome_kw.Launcher
     */

    /**
     * 电话联系人
     * pkg:com.android.contacts
     * cls:com.android.contacts.activities.PeopleActivity
     */
    /**
     * 打电话
     * pkg:com.android.dialer
     * cls:com.android.incallui.InCallActivity
     */

    /**
     * 闹钟
     * pkg:com.android.deskclock
     * cls:com.ezviz.alarm.AlarmListActivity
     */
    /**
     * 音视频聊天
     * pkg:com.ezviz.ezvizvideochat
     * cls:com.ezviz.ezvizvideochat.ui.ContactsActivity
     */
    /**
     * 拍照 or 录像
     * pkg:com.ezviz.camera
     * cls:com.ezviz.camera.MainActivity
     */
    /**
     * 拍照识物
     * pkg:com.ezviz.identifypicture
     * cls:com.ezviz.identifypicture.ui.MainActivity
     */
    /**
     * 智能语音
     * pkg:com.ezviz.ezvizsmartvoice
     * cls:com.ezviz.ezvizsmartvoice.ui.CameraActivity
     */
    private static final String tag = "ActivityUtil.java";

    // -------------- 应用包名 ----------------
    /*** 桌面 */
    public static final String LAUNCHER_APP_PACKAGE = "com.hikvision.ezviz.simplehome_kw";
    /*** 这个是打电话的包名，不是电话联系人的包名 */
    public static final String PHONETELE_APP_PACKAGE = "com.android.dialer";
    /*** 闹钟 */
    public static final String ALARM_APP_PACKAGE = "com.android.deskclock";
    /*** 视频通话 */
    private static final String VIDEOCHAT_APP_PACKAGE = "com.ezviz.ezvizvideochat";
    /*** 本地拍照或录像 */
    private static final String CAMERA_APP_PACKAGE = "com.ezviz.camera";
    /*** 拍照识物 */
    private static final String IDENTIFY_APP_PACKAGE = "com.ezviz.identifypicture";
    /*** 智能语音 */
    private static final String SMART_VOICE_APP_PACKAGE = "com.ezviz.ezvizsmartvoice";

    public static String[] getPackageAndActivityName(Context context) {
        ActivityManager am = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        ComponentName cn = am.getRunningTasks(1).get(0).topActivity;
        LogUtil.d(tag, "pkg:" + cn.getPackageName());
        LogUtil.d(tag, "cls:" + cn.getClassName());

        String[] names = {cn.getPackageName(), cn.getClassName()};
        return names;
    }

}
