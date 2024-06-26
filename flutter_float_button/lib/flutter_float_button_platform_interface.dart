import 'package:plugin_platform_interface/plugin_platform_interface.dart';

import 'flutter_float_button_method_channel.dart';

abstract class FlutterFloatButtonPlatform extends PlatformInterface {
  /// Constructs a FlutterFloatButtonPlatform.
  FlutterFloatButtonPlatform() : super(token: _token);

  static final Object _token = Object();

  static FlutterFloatButtonPlatform _instance = MethodChannelFlutterFloatButton();

  /// The default instance of [FlutterFloatButtonPlatform] to use.
  ///
  /// Defaults to [MethodChannelFlutterFloatButton].
  static FlutterFloatButtonPlatform get instance => _instance;

  /// Platform-specific implementations should set this with their own
  /// platform-specific class that extends [FlutterFloatButtonPlatform] when
  /// they register themselves.
  static set instance(FlutterFloatButtonPlatform instance) {
    PlatformInterface.verifyToken(instance, _token);
    _instance = instance;
  }

  Future<void> showFloatWindow() {
    throw UnimplementedError('showFloatWindow() has not been implemented.');
  }

  Future<void> hideFloatWindow() {
    throw UnimplementedError('hideFloatWindow() has not been implemented.');
  }

  Future<String?> getPlatformVersion() {
    throw UnimplementedError('platformVersion() has not been implemented.');
  }

  Future<String> checkPermission() {
    throw UnimplementedError('showFloatWindow() has not been implemented.');
  }

  Future<String> requestPermission() {
    throw UnimplementedError('hideFloatWindow() has not been implemented.');
  }

  Future<String> openPermissionSettings() {
    throw UnimplementedError('platformVersion() has not been implemented.');
  }
}
