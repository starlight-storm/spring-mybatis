Spring BootでMyBatisを利用して、2PCを行うサンプル
====
JTA/XAとして、Atomikosを利用。  
仕様：OrdererとProductの2つのDB（TBL名も同じ）をINSERT、SELECTします。 
※ H2に依存している部分もあり、その辺を今後修正予定。 

## 使い方
(1)Spring Bootを起動  
(2)Advanced REST Clientを使って、以下のURLをGET。  
http://localhost:8080/v1/buydata  
(3)データが２件取得できることを確認。  
(4)Advanced REST Clientを使って、上記のURLをPOST。データ例は以下の通り。  
{"orderer":{"id":"8","firstName":"A","lastName":"Z", "shippingCode":"999"},"product":{"code":"999","name":"Note"}}    
(5)(2)を再度行い、データが３件になっていることを確認。あわせて、コンソールに出力されているログで、XAトランザクションが正常に動作していることを確認。

## 参照
こちらも参照してください。　　
### AdvocateのJosh Longのサンプル
https://github.com/joshlong/spring-dist-tx

### Dave Syerのサンプル
#### XAの利用（atomikos-db）
https://github.com/dsyer/dist-tx/tree/master/atomikos-db
#### 独自TMの実装（best-db-db）
https://github.com/dsyer/dist-tx/tree/master/best-db-db

## 注意
XAを推奨している訳ではありません。  
Spring開発チームの基本方針は「XAを使うな」、「Never XA（代わりに一意IDなど重複登録を検出できるような仕組みを入れて２PCをしなくて済むように検討しろ）」。  
もし、Spring Bootで2PCが必要な場合は「何か間違っているかも」と考えるのが妥当。  
ACIDのチェックは自己責任です。
