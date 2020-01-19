package com.example.openflix_torrent;

import android.os.Environment;

import androidx.annotation.NonNull;

import com.github.se_bastiaan.torrentstream.StreamStatus;
import com.github.se_bastiaan.torrentstream.Torrent;
import com.github.se_bastiaan.torrentstream.TorrentOptions;
import com.github.se_bastiaan.torrentstream.TorrentStream;
import com.github.se_bastiaan.torrentstream.listeners.TorrentListener;

import java.io.File;

import io.flutter.embedding.engine.plugins.FlutterPlugin;
import io.flutter.plugin.common.EventChannel;
import io.flutter.plugin.common.MethodCall;
import io.flutter.plugin.common.MethodChannel;
import io.flutter.plugin.common.MethodChannel.MethodCallHandler;
import io.flutter.plugin.common.MethodChannel.Result;
import io.flutter.plugin.common.PluginRegistry.Registrar;

/** OpenflixTorrentPlugin */
public class OpenflixTorrentPlugin implements FlutterPlugin, MethodCallHandler {
  @Override
  public void onAttachedToEngine(@NonNull FlutterPluginBinding flutterPluginBinding) {
    final MethodChannel channel = new MethodChannel(flutterPluginBinding.getFlutterEngine().getDartExecutor(), "openflix_torrent");
    channel.setMethodCallHandler(new OpenflixTorrentPlugin());
  }

  public static void registerWith(Registrar registrar) {
    final MethodChannel channel = new MethodChannel(registrar.messenger(), "openflix_torrent");
    channel.setMethodCallHandler(new OpenflixTorrentPlugin());
  }

  @Override
  public void onMethodCall(@NonNull MethodCall call, @NonNull Result result) {
    if (call.method.equals("getPlatformVersion")) {
      init();
      try {
        Thread.sleep(1000);
        start();
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    } else {
      result.notImplemented();
    }
  }

  @Override
  public void onDetachedFromEngine(@NonNull FlutterPluginBinding binding) {
  }

  TorrentStream torrentStream;
  public void init(){
    TorrentOptions torrentOptions = new  TorrentOptions.Builder()
            .autoDownload(true)
            .saveLocation(Environment.getDownloadCacheDirectory())
            .removeFilesAfterStop(false)
            .build();
    this.torrentStream =  TorrentStream.init(torrentOptions);
    torrentStream.addListener(new TorrentListener() {

      @Override
      public void onStreamPrepared(Torrent torrent) {
        System.out.println("onStreamPrepared");
      }

      @Override
      public void onStreamStarted(Torrent torrent) {
        System.out.println("onStreamStarted");
      }

      @Override
      public void onStreamError(Torrent torrent, Exception e) {
        System.out.println("onStreamError"+ e.getMessage());
      }

      @Override
      public void onStreamReady(Torrent torrent) {
        System.out.println("onStreamReady");
      }

      @Override
      public void onStreamProgress(Torrent torrent, StreamStatus status) {
        System.out.println("onStreamProgress");
      }

      @Override
      public void onStreamStopped() {
        System.out.println("onStreamStopped");
      }
    });
    System.out.println("Initialized");
  }

  public void start(){
    this.torrentStream.startStream("https://yts.lt/torrent/download/FF495923151031A547AE14C1CA9F0DFF8EA26A0B");
  }

}

