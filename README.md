# task-management-api
ユーザーのタスク管理情報管理用のAPI

## 必要な環境
- JDK 11
- Groovy 3.0
- Gradle

※ IntelliJならIDE上で全てインストール可能

## API仕様書(Swagger UI)
- [git hub pages](https://dev-fjk.github.io/task-management-api/)
- [ローカル(ビルドが必要)](http://localhost:8080/swagger-ui/index.html)

### API仕様書の更新方法
~~~
(1) ローカルでアプリケーションを起動しSwaggerのリンクを開く
(2) 検索欄に『/v3/api-docs.yaml』 と入力して検索する
(3) 左上の/v3/api-docs.yamlというリンクを押下すると api-docs.yamlがダウンロードされるので 
元々置いてある docs/specs/api-doc.yamlと差し替える

※ masterブランチのapi-docs.ymlの記載内容が反映されるので他のブランチで差し替えてもmasterにマージするまで反映されない
※ git hub actionsを入れた後はmasterマージ時に自動で最新化されるようにしたい
~~~

## MySQL起動方法
- Docker Desktopをインストールしてローカル環境でDockerの環境構築を行う([Docker Desktop](https://www.docker.com/products/docker-desktop) )
- プロジェクトのルートディレクトリで 以下のコマンドを実行して mysqlのコンテナを立ち上げる(Win/Mac共通)
~~~
$ cd docker
$ docker-compose up -d --build

※ コンテナ起動時に initdb.dディレクトリ配下に置かれたsqlファイルを読み込む設定となっているので
コンテナ起動の度にデータは初期化されます。
~~~

## checkStyle
- configディレクトリ配下に置いてあるcheckstyle.xmlをIDEに読み込ませる
    - [IntelliJでの手順](https://qiita.com/kent-hamaguchi/items/f4d2a5594c3c4d3195ab)

## 参考
- [Java11+Spring Boot+Dockerで作るWebアプリケーション](https://blog.mmmcorp.co.jp/blog/2019/01/19/java11_springboot_docker/)
