# task-management-api
ユーザーのタスク管理情報管理用のAPI

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
