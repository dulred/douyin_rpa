package com.example.flutter_float_button

import android.content.Context
import android.graphics.PixelFormat
import android.view.*
import android.widget.Button

import androidx.annotation.NonNull
import androidx.core.app.ActivityCompat.startActivityForResult
import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.provider.Settings

import io.flutter.embedding.engine.plugins.FlutterPlugin
import io.flutter.embedding.engine.plugins.activity.ActivityAware
import io.flutter.embedding.engine.plugins.activity.ActivityPluginBinding

import io.flutter.plugin.common.MethodCall
import io.flutter.plugin.common.MethodChannel
import io.flutter.plugin.common.MethodChannel.MethodCallHandler
import io.flutter.plugin.common.MethodChannel.Result

/** FlutterFloatButtonPlugin */
class FlutterFloatButtonPlugin: FlutterPlugin,ActivityAware, MethodCallHandler {
  /// The MethodChannel that will the communication between Flutter and native Android
  ///
  /// This local reference serves to register the plugin with the Flutter Engine and unregister it
  /// when the Flutter Engine is detached from the Activity
  private lateinit var channel : MethodChannel
  private lateinit var context: Context
  private lateinit var windowManager: WindowManager
  private var floatView: View? = null
  private var activity: Activity? = null

  override fun onAttachedToEngine(flutterPluginBinding: FlutterPlugin.FlutterPluginBinding) {
    context = flutterPluginBinding.applicationContext
    channel = MethodChannel(flutterPluginBinding.binaryMessenger, "flutter_float_button")
    channel.setMethodCallHandler(this)
  }
  override fun onAttachedToActivity(binding: ActivityPluginBinding) {
      activity = binding.activity
  }
  override fun onDetachedFromActivityForConfigChanges() {
      // Clean up any resources related to the Activity.
      activity = null
  }

  override fun onReattachedToActivityForConfigChanges(binding: ActivityPluginBinding) {
      // Reinitialize any resources related to the new Activity instance.
      activity = binding.activity
  }

  override fun onDetachedFromActivity() {
      // Clean up resources related to the Activity.
      activity = null
  }
  override fun onMethodCall(call: MethodCall, result: Result) {
    if (call.method == "getPlatformVersion") {
      result.success("Android ${android.os.Build.VERSION.RELEASE}")
    }else if(call.method == "showFloatWindow"){
      showFloatWindow()
    } else if(call.method == "checkPermission"){
      println(checkPermission())
      result.success("good idea")
    }else if(call.method == "requestPermission"){
      requestPermission()
      result.success("good idea")
    }else if(call.method == "openPermissionSettings"){
      openPermissionSettings()
      result.success("good idea")
    }else {
      result.notImplemented()
    }
  }
  override fun onDetachedFromEngine(binding: FlutterPlugin.FlutterPluginBinding) {
    channel.setMethodCallHandler(null)
    floatView = null
  }
  private fun showFloatWindow() {
    if (floatView == null) {
            // 创建悬浮窗 View
            floatView = LayoutInflater.from(context).inflate(R.layout.float_window_layout, null)
            
            // 设置悬浮窗参数
            val params = WindowManager.LayoutParams(
                WindowManager.LayoutParams.WRAP_CONTENT,
                WindowManager.LayoutParams.WRAP_CONTENT,
                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
                    WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY
                } else {
                    WindowManager.LayoutParams.TYPE_PHONE
                },
                WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE,
                PixelFormat.TRANSLUCENT
            )

            // 设置悬浮窗位置
            params.gravity = Gravity.TOP or Gravity.START
            params.x = 100
            params.y = 100

            // 添加悬浮窗到 WindowManager
            windowManager = context.getSystemService(Context.WINDOW_SERVICE) as WindowManager
            windowManager.addView(floatView, params)

            // 设置隐藏按钮点击事件
            val hideButton = floatView?.findViewById<Button>(R.id.hideButton)
            hideButton?.setOnClickListener {
                hideFloatWindow()
            }
        }
  }

  private fun hideFloatWindow() {
      floatView?.let {
          windowManager.removeView(it)
          floatView = null
      }
  }

  private fun checkPermission(): Boolean {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            return Settings.canDrawOverlays(activity)
        }
        return true  // 如果是低于Android M版本，则默认返回true
    }

    private fun requestPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (!Settings.canDrawOverlays(activity)) {
              val currentActivity = activity ?: return  // 使用安全调用操作符

              val intent = Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION,
                      Uri.parse("package:${currentActivity.packageName}"))

              startActivityForResult(currentActivity, intent, OVERLAY_PERMISSION_REQUEST_CODE, null)
            }
        }
    }

    private fun openPermissionSettings() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
           val currentActivity = activity ?: return  // 使用安全调用操作符
           val intent = Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION,
                      Uri.parse("package:${currentActivity.packageName}"))
          currentActivity.startActivity(intent)
        }
    }


    companion object {
        private const val OVERLAY_PERMISSION_REQUEST_CODE = 123 // 自定义请求码
    }

}
