
import 'flutter_float_button_platform_interface.dart';

class FlutterFloatButton {
  Future<String?> getPlatformVersion() {
    return FlutterFloatButtonPlatform.instance.getPlatformVersion();
  }
  Future<void> showFloatWindow() {
    return FlutterFloatButtonPlatform.instance.showFloatWindow();
  }

  Future<void> hideFloatWindow() {
    return FlutterFloatButtonPlatform.instance.hideFloatWindow();
  }

  Future<String?> checkPermission() {
    return FlutterFloatButtonPlatform.instance.checkPermission();
  }
  Future<String> requestPermission() {
    return FlutterFloatButtonPlatform.instance.requestPermission();
  }

  Future<String> openPermissionSettings() {
    return FlutterFloatButtonPlatform.instance.openPermissionSettings();
  }
}
