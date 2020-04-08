#  2020/04/08 [Android] Firebase Cloud Firestore を使ってデータを読み書きする

# はじめに

Firebase の Cloud Firestore を使ったことがありませんでした。今更な感じですがどんなものか知るために Cloud Firestore を使ってデータの追加・削除・取得をやってみたいと思います。

```
-------------------------------------------------------------------------------------------
◆ Cloud Firestore とは？ ◆
-------------------------------------------------------------------------------------------
Firebase の Cloud Firestore は クラウドホスト型の NoSQLデータベース です。
Cloud Firestore は iOS アプリや Android アプリおよびWebアプリから SDK を介して直接アクセスできます。
-------------------------------------------------------------------------------------------
```

# Cloud Firestore をセットアップする

## Firebase のプロジェクトを作成する

Firebase ではプロジェクトを作成し、そのプロジェクトで設定を行うことで Cloud Firestore を利用できるようにします。 なのでまずは次の手順で Firebase のプロジェクトを作成していきます。

1. [Firebase] にログインし、「プロジェクト」の作成を選択する。
  (FirebaseはGoogleのアカウントでログインできます。)
2. 「プロジェクト名」に任意の名称を入力して、「続行」を選択する。
3. 「Googleアナリティクス」を有効・無効どちらか選択し、「続行」を選択する
4. 「Googleアナリティクスアカウント」を選択し、「続行」を選択する
   (特に何もなければ、Default Account for Firebase でよいはず)
5. [Firebase のコンソール](https://console.firebase.google.com)に移動し、作成した Firebase プロジェクトを選択する。


## Android プロジェクトを作成する

Android プロジェクトを作成して、Cloud Firestore を利用できるようにします。
まずば Firebase プロジェクト上で次の操作でセットアップを行います。

1. [Firebase のコンソール](https://console.firebase.google.com)で　Firebase プロジェクトを選択する。
2. **アプリを追加** を選択し、**プラットフォーム** で Android を選択する。
3. **Androidパッケージ名**の入力が求められるので、Android プロジェクトのパッケージ名を入力し、次へを選択する。
4. **設定ファイルのダウンロード**で、google-service.json をダウンロードする。
5. **Firebase SDK の追加** や **アプリを実行してインストールを確認**は後で確認するので、次へを選択する。

そして Android Studio 上で Android プロジェクトのセットアップを行います。

1. ［Android Studio］Android プロジェクトを作成する。
2. ［Andriod Studio］app に google-service.json をコピーする
3. ［Android Studio］app の build.gradle で必要なライブラリを加える

```groovy
dependencies {
       ︙
    def firebase_analytics_version = "17.3.0"
    implementation "com.google.firebase:firebase-analytics:${firebase_analytics_version}"

    def firebase_fire_store_version = "21.4.2"
    implementation "com.google.firebase:firebase-firestore:${firebase_fire_store_version}"
       ︙
}
```

これで Android アプリ から Cloud Firestore にアクセスできるようになります。

# Cloud Firestore でデータを読み書きする

Cloud Firestore は NoSQLデータベースで、ドキュメントデータベースのデータモデルに従ってデータを管理します。データモデルとしてコレクション・ドキュメント・データとありこれを組み合わせてデータを管理します。

| データ | |
| ------- | ------- |
| コレクション | データの編成とクエリの作成に使用できるドキュメントのコンテナ |
| ドキュメント | 単純な文字列から数値などを格納する。<br> また複雑なネストのオブジェクトも格納できる。<br>（[サポートしているデータ・タイプ](https://firebase.google.com/docs/firestore/manage-data/data-types)) |
| データ | 文字列や数値などデータそのもの |

![data structure](https://firebase.google.com/docs/firestore/images/structure-data.png)

**NoSQLデータベースのタイプ**
[NoSQLデータベース ― 定義と解説](https://academy.datastax.com/planet-cassandra/what-is-nosql-jp)によると NoSQL データベースにはキーバリューストア・カラムストアと・ドキュメントデータベース・グラフデータベースという 4 つのタイプがあるそうです。そして Cloud Firestore はこの中のドキュメントデータベースに該当するものだそうです。

