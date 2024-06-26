import 'package:flutter_test/flutter_test.dart';
import 'package:flutter_float_button/flutter_float_button.dart';
import 'package:flutter_float_button/flutter_float_button_platform_interface.dart';
import 'package:flutter_float_button/flutter_float_button_method_channel.dart';
import 'package:plugin_platform_interface/plugin_platform_interface.dart';

class MockFlutterFloatButtonPlatform
    with MockPlatformInterfaceMixin
    implements FlutterFloatButtonPlatform {

  @override
  Future<String?> getPlatformVersion() => Future.value('42');
  
  @override
  Future<void> hideFloatWindow() {
    // TODO: implement hideFloatWindow
    throw UnimplementedError();
  }
  
  @override
  Future<void> showFloatWindow() {
    // TODO: implement showFloatWindow
    throw UnimplementedError();
  }
  
  @override
  Future<String> checkPermission() {
    // TODO: implement checkPermission
    throw UnimplementedError();
  }
  
  @override
  Future<String> openPermissionSettings() {
    // TODO: implement openPermissionSettings
    throw UnimplementedError();
  }
  
  @override
  Future<String> requestPermission() {
    // TODO: implement requestPermission
    throw UnimplementedError();
  }
}

void main() {
  final FlutterFloatButtonPlatform initialPlatform = FlutterFloatButtonPlatform.instance;

  test('$MethodChannelFlutterFloatButton is the default instance', () {
    expect(initialPlatform, isInstanceOf<MethodChannelFlutterFloatButton>());
  });

  test('getPlatformVersion', () async {
    FlutterFloatButton flutterFloatButtonPlugin = FlutterFloatButton();
    MockFlutterFloatButtonPlatform fakePlatform = MockFlutterFloatButtonPlatform();
    FlutterFloatButtonPlatform.instance = fakePlatform;

    expect(await flutterFloatButtonPlugin.getPlatformVersion(), '42');
  });
}
