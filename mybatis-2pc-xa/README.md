Spring BootでMyBatisを利用して、2PCを行うサンプル
====
JTA/XAとして、Atomikosを利用。  
仕様：OrdererとProductの2つのDB（TBL名も同じ）をINSERT、SELECTします。

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
