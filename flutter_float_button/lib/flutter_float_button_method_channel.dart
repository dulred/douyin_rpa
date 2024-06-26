import 'package:flutter/foundation.dart';
import 'package:flutter/services.dart';

import 'flutter_float_button_platform_interface.dart';

/// An implementation of [FlutterFloatButtonPlatform] that uses method channels.
class MethodChannelFlutterFloatButton extends FlutterFloatButtonPlatform {
  /// The method channel used to interact with the native platform.
  @visibleForTesting
  final methodChannel = const MethodChannel('flutter_float_button');

  @override
  Future<String?> getPlatformVersion() async {
    final version = await methodChannel.invokeMethod<String>('getPlatformVersion');
    return version;
  }

  @override
  Future<void> showFloatWindow() async {
    await methodChannel.invokeMethod('showFloatWindow');
  }

  @override
  Future<void> hideFloatWindow() async {
    await methodChannel.invokeMethod('hideFloatWindow');
  }

  @override
  Future<String> checkPermission() async {
    final result = await methodChannel.invokeMethod('checkPermission');
    return result;
  }

  @override
  Future<String> requestPermission() async {
    final result = await methodChannel.invokeMethod('requestPermission');
    return result;
  }

  @override
  Future<String> openPermissionSettings() async {
    final result = await methodChannel.invokeMethod('openPermissionSettings');
    return result;
  }
}
