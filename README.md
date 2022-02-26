# task-management-api

ユーザーのタスク管理情報管理用のAPI

## 必要な環境

- JDK 11
- Groovy 3.0
- Gradle
- IDE(javaはテキストエディタでの開発は少々厳しいので IntelliJ IDEA(有料版) か Eclipseを推奨)
- Docker(自前でMySQL用意するなら不要)

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
~~~

### DB初期化手順

- MySQLに直接以下のファイルに配置された DDLとDMLを投入する or コンテナ再作成を行う

~~~
(1) docker/initdb.d/1_schema.sql を直接MySQLに投入する
(2) docker/initdb.d/2_initdata.sql　をDBに投入してデータを投入
~~~

## UTについて
- src/test/javaパッケージ配下では java + junit5でテストを作成する
- src/test/groovyパッケージ配下では groovy + Spockでテストを作成する
    - junitで無いと対応できないケース以外では spockで書くことを推奨
    
■ テストクラス 命名規則
~~~
■ Junit5
~Test.java
■ Spock
~Spec.groovy
~~~

## checkStyle

- configディレクトリ配下に置いてあるcheckstyle.xmlをIDEに読み込ませる
    - [IntelliJでの手順](https://qiita.com/kent-hamaguchi/items/f4d2a5594c3c4d3195ab)

## 参考

- [Java11+Spring Boot+Dockerで作るWebアプリケーション](https://blog.mmmcorp.co.jp/blog/2019/01/19/java11_springboot_docker/)
