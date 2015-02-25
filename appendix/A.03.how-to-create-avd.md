---
title: 仮想デバイスの作成
description: この章では、Android のエミュレータで動作させる仮想デバイスの作成について解説します。
keywords: ["android","training", "基礎", "スキル", "開発", "developer", "プログラミング", "avd", "Android Virtual Device Manager"]

---

## 仮想デバイスの管理

Android Virtual Device Manager で、仮想デバイスの管理を行います。

Android Virtual Device Manager は、Eclipse のメニューから起動するか、またはコマンドラインから`android avd`で起動します。

![AVD Manager]({{site.baseurl}}/assets/04-03/avd-manager.png)

### 仮想デバイスの作成

AVD Manager の画面右のメニューから、`New`を選択します。

![AVD Manager]({{site.baseurl}}/assets/04-03/new-avd.png)

入力する項目は以下のとおりです。

項目 | 内容
------|------
AVD Name | 仮想デバイスの名前
Device | 画面サイズと解像度の組の選択。組み込みで実機の設定も選択可能
Target | API Level と Google API の有無の選択
CPU/ABI | エミュレートする CPU の種類。ARM / MIPS / Intel x86 のうちどれか
RAM | 主記憶装置の容量
VM Heap | Dalvik VM が確保するヒープのサイズ
Internal Storage | 内部記憶装置の容量
SD Card | サイズ指定する場合は、容量を指定。既にファイルに書きだされている場合は、それを指定する
Emulation Options | Snapshot を有効にすると、エミュレータを終了した時の状態を保存し、次回起動時にそれから復帰する。Use Host GPU を有効にすると、PC の GPU リソースを使ってエミュレートする

エミュレートする CPU に、Intel x86 を選択する場合は、下記の手順でセットアップが必要です。<br />
現在の最新のリビジョンは 3 です。2 までのものでは、Retina MacBook Pro でカーネルパニックを引き起こしますので、最新のリビジョンのものをインストールしてください。

1. SDK Manager で、Intel x86 Emulator Accelerator(HAXM) をインストールします
  ![HAXM from SDK Manager]({{site.baseurl}}/assets/04-03/haxm-from-sdk-manager.png)
2. SDK のディレクトリから、`extras/intel/Hardware_Accelerated_Execution_Manager/` を辿り、`haxm-macosx_r03.dmg`を実行します(Mac の場合)
  ![HAXM dmg]({{site.baseurl}}/assets/04-03/dir-haxm.png)
3. mpkg ファイルを実行し、インストールを行います
