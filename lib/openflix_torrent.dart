import 'dart:async';

import 'package:flutter/services.dart';
import 'package:permission_handler/permission_handler.dart';

class OpenflixTorrent {
  static const MethodChannel _channel =
      const MethodChannel('openflix_torrent');
  static Map<PermissionGroup, PermissionStatus> permissions;

  static Future<String> get platformVersion async {
    permissions = await PermissionHandler().requestPermissions([
      PermissionGroup.location,
      PermissionGroup.camera,
      PermissionGroup.locationAlways,
      PermissionGroup.phone,
      PermissionGroup.sensors,
      PermissionGroup.storage,
      PermissionGroup.microphone,
    ]);
    final String version = await _channel.invokeMethod('getPlatformVersion');
    return version;
  }
}
