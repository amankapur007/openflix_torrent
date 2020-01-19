import 'package:flutter/services.dart';
import 'package:flutter_test/flutter_test.dart';
import 'package:openflix_torrent/openflix_torrent.dart';

void main() {
  const MethodChannel channel = MethodChannel('openflix_torrent');

  TestWidgetsFlutterBinding.ensureInitialized();

  setUp(() {
    channel.setMockMethodCallHandler((MethodCall methodCall) async {
      return '42';
    });
  });

  tearDown(() {
    channel.setMockMethodCallHandler(null);
  });

  test('getPlatformVersion', () async {
    expect(await OpenflixTorrent.platformVersion, '42');
  });
}
