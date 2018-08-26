レッツゴーデベロッパー 平成ジェネレーションズ FINAL 〜 TDDチーム
================================================================

2018/08/25に仙台で開催された[レッツゴーデベロッパー 平成ジェネレーションズ FINAL](https://connpass.com/event/95619/)のTDDチームの成果物です。

## (お題) レッツゴーデベロッパーモバイルのスマートフォン利用料金計算

https://github.com/5000dai/free-style-battle

かなりめんどくさい感じです。


## 仕様に明示されてないけど想定してること

- このプログラムはライブラリとして他のプログラムから使うこと
- 締め日は毎月月末であること
- 契約情報ややユーザの利用状況など必要なデータは別のシステムなどから入手し、今回のプログラムへの入力とすること
- 契約情報は別のシステムで矛盾がないようにきちんと管理されていること
    - (例) 「あんしんパック」に入っているのに「あんしん補償サービス」にも入っているような契約情報が入力されないこと


## TODO

- 基本プランの通話料金計算
- SMS
- 長期契約割引
- MNP割引
- オプションの残り
- 事務手数料
- 消費税
- すべての合計のパターン網羅


## 現状でのテスト実行したレポートの一覧

Spockのデータ駆動テストとメソッド名の`@Unroll`展開を駆使して、レポートのそれぞれの1行が仕様に対する具体的な料金計算例を表すように頑張ってます。

- WIP: すべてを合計した月額請求金額(税抜き)を計算する
- 基本プランのレッツゴーデベロッパー THE NEXT プランを2018-04-01に新規契約したとき、2018-08-31を締め日とした月額料金は4500円になる
- 基本プランのレッツゴーデベロッパー 変真 プランを2018-04-01に新規契約したとき、2018-08-31を締め日とした月額料金は3500円になる
- 基本プランのレッツゴーデベロッパー X プランを2018-04-01に新規契約したとき、2018-08-31を締め日とした月額料金は2500円になる
- 基本プランのレッツゴーデベロッパー THE NEXT プランを2018-08-01に新規契約したとき、2018-08-31を締め日とした月額料金は日割りされて4500円になる
- 基本プランのレッツゴーデベロッパー THE NEXT プランを2018-08-02に新規契約したとき、2018-08-31を締め日とした月額料金は日割りされて4355円になる
- 基本プランのレッツゴーデベロッパー THE NEXT プランを2018-08-31に新規契約したとき、2018-08-31を締め日とした月額料金は日割りされて145円になる
- 基本プランのレッツゴーデベロッパー THE NEXT プランを2018-04-01に新規契約して2018-08-01に解約したとき、2018-08-31を締め日とした月額料金は日割りされて145円になる
- 基本プランのレッツゴーデベロッパー THE NEXT プランを2018-04-01に新規契約して2018-08-02に解約したとき、2018-08-31を締め日とした月額料金は日割りされて290円になる
- 基本プランのレッツゴーデベロッパー THE NEXT プランを2018-04-01に新規契約して2018-08-31に解約したとき、2018-08-31を締め日とした月額料金は日割りされて4500円になる
- 基本プランのレッツゴーデベロッパー THE NEXT プランを2018-08-15に新規契約して2018-08-15に解約したとき、2018-08-31を締め日とした月額料金は日割りされて145円になる
- 基本プランのレッツゴーデベロッパー THE NEXT プランを2018-08-15に新規契約して2018-08-16に解約したとき、2018-08-31を締め日とした月額料金は日割りされて290円になる
- 基本プランのレッツゴーデベロッパー 変真 プランを2018-08-01に新規契約したとき、2018-08-31を締め日とした月額料金は日割りされて3500円になる
- 基本プランのレッツゴーデベロッパー 変真 プランを2018-08-02に新規契約したとき、2018-08-31を締め日とした月額料金は日割りされて3387円になる
- 基本プランのレッツゴーデベロッパー 変真 プランを2018-08-31に新規契約したとき、2018-08-31を締め日とした月額料金は日割りされて113円になる
- 基本プランのレッツゴーデベロッパー 変真 プランを2018-04-01に新規契約して2018-08-01に解約したとき、2018-08-31を締め日とした月額料金は日割りされて113円になる
- 基本プランのレッツゴーデベロッパー 変真 プランを2018-04-01に新規契約して2018-08-02に解約したとき、2018-08-31を締め日とした月額料金は日割りされて226円になる
- 基本プランのレッツゴーデベロッパー 変真 プランを2018-04-01に新規契約して2018-08-31に解約したとき、2018-08-31を締め日とした月額料金は日割りされて3500円になる
- 基本プランのレッツゴーデベロッパー 変真 プランを2018-08-15に新規契約して2018-08-15に解約したとき、2018-08-31を締め日とした月額料金は日割りされて113円になる
- 基本プランのレッツゴーデベロッパー 変真 プランを2018-08-15に新規契約して2018-08-16に解約したとき、2018-08-31を締め日とした月額料金は日割りされて226円になる
- 基本プランのレッツゴーデベロッパー X プランを2018-08-01に新規契約したとき、2018-08-31を締め日とした月額料金は日割りされて2500円になる
- 基本プランのレッツゴーデベロッパー X プランを2018-08-02に新規契約したとき、2018-08-31を締め日とした月額料金は日割りされて2419円になる
- 基本プランのレッツゴーデベロッパー X プランを2018-08-31に新規契約したとき、2018-08-31を締め日とした月額料金は日割りされて81円になる
- 基本プランのレッツゴーデベロッパー X プランを2018-04-01に新規契約して2018-08-01に解約したとき、2018-08-31を締め日とした月額料金は日割りされて81円になる
- 基本プランのレッツゴーデベロッパー X プランを2018-04-01に新規契約して2018-08-02に解約したとき、2018-08-31を締め日とした月額料金は日割りされて161円になる
- 基本プランのレッツゴーデベロッパー X プランを2018-04-01に新規契約して2018-08-31に解約したとき、2018-08-31を締め日とした月額料金は日割りされて2500円になる
- 基本プランのレッツゴーデベロッパー X プランを2018-08-15に新規契約して2018-08-15に解約したとき、2018-08-31を締め日とした月額料金は日割りされて81円になる
- 基本プランのレッツゴーデベロッパー X プランを2018-08-15に新規契約して2018-08-16に解約したとき、2018-08-31を締め日とした月額料金は日割りされて161円になる
- 基本プランのレッツゴーデベロッパー THE NEXT プランを2018-04-01に新規契約したとき、2018-08-31を締め日としたインターネット接続料金は300円になる
- 基本プランのレッツゴーデベロッパー 変真 プランを2018-04-01に新規契約したとき、2018-08-31を締め日としたインターネット接続料金は300円になる
- 基本プランのレッツゴーデベロッパー X プランを2018-04-01に新規契約したとき、2018-08-31を締め日としたインターネット接続料金は300円になる
- 基本プランのレッツゴーデベロッパー THE NEXT プランを2018-08-01に新規契約したとき、2018-08-31を締め日としたインターネット接続料金は日割りされて300円になる
- 基本プランのレッツゴーデベロッパー THE NEXT プランを2018-08-02に新規契約したとき、2018-08-31を締め日としたインターネット接続料金は日割りされて290円になる
- 基本プランのレッツゴーデベロッパー THE NEXT プランを2018-08-31に新規契約したとき、2018-08-31を締め日としたインターネット接続料金は日割りされて10円になる
- 基本プランのレッツゴーデベロッパー THE NEXT プランを2018-04-01に新規契約して2018-08-01に解約したとき、2018-08-31を締め日としたインターネット接続料金は日割りされて10円になる
- 基本プランのレッツゴーデベロッパー THE NEXT プランを2018-04-01に新規契約して2018-08-02に解約したとき、2018-08-31を締め日としたインターネット接続料金は日割りされて19円になる
- 基本プランのレッツゴーデベロッパー THE NEXT プランを2018-04-01に新規契約して2018-08-31に解約したとき、2018-08-31を締め日としたインターネット接続料金は日割りされて300円になる
- 基本プランのレッツゴーデベロッパー THE NEXT プランを2018-08-15に新規契約して2018-08-15に解約したとき、2018-08-31を締め日としたインターネット接続料金は日割りされて10円になる
- 基本プランのレッツゴーデベロッパー THE NEXT プランを2018-08-15に新規契約して2018-08-16に解約したとき、2018-08-31を締め日としたインターネット接続料金は日割りされて19円になる
- 基本プランのレッツゴーデベロッパー 変真 プランを2018-08-01に新規契約したとき、2018-08-31を締め日としたインターネット接続料金は日割りされて300円になる
- 基本プランのレッツゴーデベロッパー 変真 プランを2018-08-02に新規契約したとき、2018-08-31を締め日としたインターネット接続料金は日割りされて290円になる
- 基本プランのレッツゴーデベロッパー 変真 プランを2018-08-31に新規契約したとき、2018-08-31を締め日としたインターネット接続料金は日割りされて10円になる
- 基本プランのレッツゴーデベロッパー 変真 プランを2018-04-01に新規契約して2018-08-01に解約したとき、2018-08-31を締め日としたインターネット接続料金は日割りされて10円になる
- 基本プランのレッツゴーデベロッパー 変真 プランを2018-04-01に新規契約して2018-08-02に解約したとき、2018-08-31を締め日としたインターネット接続料金は日割りされて19円になる
- 基本プランのレッツゴーデベロッパー 変真 プランを2018-04-01に新規契約して2018-08-31に解約したとき、2018-08-31を締め日としたインターネット接続料金は日割りされて300円になる
- 基本プランのレッツゴーデベロッパー 変真 プランを2018-08-15に新規契約して2018-08-15に解約したとき、2018-08-31を締め日としたインターネット接続料金は日割りされて10円になる
- 基本プランのレッツゴーデベロッパー 変真 プランを2018-08-15に新規契約して2018-08-16に解約したとき、2018-08-31を締め日としたインターネット接続料金は日割りされて19円になる
- 基本プランのレッツゴーデベロッパー X プランを2018-08-01に新規契約したとき、2018-08-31を締め日としたインターネット接続料金は日割りされて300円になる
- 基本プランのレッツゴーデベロッパー X プランを2018-08-02に新規契約したとき、2018-08-31を締め日としたインターネット接続料金は日割りされて290円になる
- 基本プランのレッツゴーデベロッパー X プランを2018-08-31に新規契約したとき、2018-08-31を締め日としたインターネット接続料金は日割りされて10円になる
- 基本プランのレッツゴーデベロッパー X プランを2018-04-01に新規契約して2018-08-01に解約したとき、2018-08-31を締め日としたインターネット接続料金は日割りされて10円になる
- 基本プランのレッツゴーデベロッパー X プランを2018-04-01に新規契約して2018-08-02に解約したとき、2018-08-31を締め日としたインターネット接続料金は日割りされて19円になる
- 基本プランのレッツゴーデベロッパー X プランを2018-04-01に新規契約して2018-08-31に解約したとき、2018-08-31を締め日としたインターネット接続料金は日割りされて300円になる
- 基本プランのレッツゴーデベロッパー X プランを2018-08-15に新規契約して2018-08-15に解約したとき、2018-08-31を締め日としたインターネット接続料金は日割りされて10円になる
- 基本プランのレッツゴーデベロッパー X プランを2018-08-15に新規契約して2018-08-16に解約したとき、2018-08-31を締め日としたインターネット接続料金は日割りされて19円になる
- データ定額プランの仮面データパックLLを2018-04-01に新規契約したとき、2018-08-31を締め日とした月額料金は7000円になる
- データ定額プランの仮面データパックLを2018-04-01に新規契約したとき、2018-08-31を締め日とした月額料金は6000円になる
- データ定額プランの仮面データパックMを2018-04-01に新規契約したとき、2018-08-31を締め日とした月額料金は4500円になる
- データ定額プランの仮面データパックSを2018-04-01に新規契約したとき、データ通信量が0バイトの場合、2018-08-31を締め日とした月額料金は2900円になる
- データ定額プランの仮面データパックSを2018-04-01に新規契約したとき、データ通信量が1000000001バイトの場合、2018-08-31を締め日とした月額料金は4000円になる
- データ定額プランの仮面データパックSを2018-04-01に新規契約したとき、データ通信量が3000000001バイトの場合、2018-08-31を締め日とした月額料金は5000円になる
- データ定額プランの仮面データパックSを2018-04-01に新規契約したとき、データ通信量が0バイトの場合、2018-08-31を締め日とした月額料金は2900円になる
- データ定額プランの仮面データパックSを2018-04-01に新規契約したとき、データ通信量が1000000001バイトの場合、2018-08-31を締め日とした月額料金は4000円になる
- データ定額プランの仮面データパックSを2018-04-01に新規契約したとき、データ通信量が3000000001バイトの場合、2018-08-31を締め日とした月額料金は5000円になる
- データ定額プランの仮面データパックSを2018-04-01に新規契約したとき、データ通信量が5000000001バイトの場合、2018-08-31を締め日とした月額料金は7000円になる
- データ定額プランの仮面データパックSを2018-04-01に新規契約したとき、データ通信量が20000000001バイトの場合、2018-08-31を締め日とした月額料金は7000円になる
- データ定額プランの仮面データパックSを2018-04-01に新規契約したとき、データ通信量が9223372036854775807バイトの場合、2018-08-31を締め日とした月額料金は7000円になる
- データ定額プランの仮面データパックLLを2018-08-01に新規契約したとき、2018-08-31を締め日とした月額料金は日割りされて7000円になる
- データ定額プランの仮面データパックLLを2018-08-02に新規契約したとき、2018-08-31を締め日とした月額料金は日割りされて6774円になる
- データ定額プランの仮面データパックLLを2018-08-31に新規契約したとき、2018-08-31を締め日とした月額料金は日割りされて226円になる
- データ定額プランの仮面データパックLLを2018-04-01に新規契約して2018-08-01に解約したとき、2018-08-31を締め日とした月額料金は日割りされて226円になる
- データ定額プランの仮面データパックLLを2018-04-01に新規契約して2018-08-02に解約したとき、2018-08-31を締め日とした月額料金は日割りされて452円になる
- データ定額プランの仮面データパックLLを2018-04-01に新規契約して2018-08-31に解約したとき、2018-08-31を締め日とした月額料金は日割りされて7000円になる
- データ定額プランの仮面データパックLLを2018-08-15に新規契約して2018-08-15に解約したとき、2018-08-31を締め日とした月額料金は日割りされて226円になる
- データ定額プランの仮面データパックLLを2018-08-15に新規契約して2018-08-16に解約したとき、2018-08-31を締め日とした月額料金は日割りされて452円になる
- データ定額プランの仮面データパックLを2018-08-01に新規契約したとき、2018-08-31を締め日とした月額料金は日割りされて6000円になる
- データ定額プランの仮面データパックLを2018-08-02に新規契約したとき、2018-08-31を締め日とした月額料金は日割りされて5806円になる
- データ定額プランの仮面データパックLを2018-08-31に新規契約したとき、2018-08-31を締め日とした月額料金は日割りされて194円になる
- データ定額プランの仮面データパックLを2018-04-01に新規契約して2018-08-01に解約したとき、2018-08-31を締め日とした月額料金は日割りされて194円になる
- データ定額プランの仮面データパックLを2018-04-01に新規契約して2018-08-02に解約したとき、2018-08-31を締め日とした月額料金は日割りされて387円になる
- データ定額プランの仮面データパックLを2018-04-01に新規契約して2018-08-31に解約したとき、2018-08-31を締め日とした月額料金は日割りされて6000円になる
- データ定額プランの仮面データパックLを2018-08-15に新規契約して2018-08-15に解約したとき、2018-08-31を締め日とした月額料金は日割りされて194円になる
- データ定額プランの仮面データパックLを2018-08-15に新規契約して2018-08-16に解約したとき、2018-08-31を締め日とした月額料金は日割りされて387円になる
- データ定額プランの仮面データパックMを2018-08-01に新規契約したとき、2018-08-31を締め日とした月額料金は日割りされて4500円になる
- データ定額プランの仮面データパックMを2018-08-02に新規契約したとき、2018-08-31を締め日とした月額料金は日割りされて4355円になる
- データ定額プランの仮面データパックMを2018-08-31に新規契約したとき、2018-08-31を締め日とした月額料金は日割りされて145円になる
- データ定額プランの仮面データパックMを2018-04-01に新規契約して2018-08-01に解約したとき、2018-08-31を締め日とした月額料金は日割りされて145円になる
- データ定額プランの仮面データパックMを2018-04-01に新規契約して2018-08-02に解約したとき、2018-08-31を締め日とした月額料金は日割りされて290円になる
- データ定額プランの仮面データパックMを2018-04-01に新規契約して2018-08-31に解約したとき、2018-08-31を締め日とした月額料金は日割りされて4500円になる
- データ定額プランの仮面データパックMを2018-08-15に新規契約して2018-08-15に解約したとき、2018-08-31を締め日とした月額料金は日割りされて145円になる
- データ定額プランの仮面データパックMを2018-08-15に新規契約して2018-08-16に解約したとき、2018-08-31を締め日とした月額料金は日割りされて290円になる
- データ定額プランの仮面データパックSを2018-08-01に新規契約したとき、データ通信量が0バイトの場合、2018-08-31を締め日とした月額料金は日割りされて2900円になる
- データ定額プランの仮面データパックSを2018-08-02に新規契約したとき、データ通信量が0バイトの場合、2018-08-31を締め日とした月額料金は日割りされて2806円になる
- データ定額プランの仮面データパックSを2018-08-31に新規契約したとき、データ通信量が0バイトの場合、2018-08-31を締め日とした月額料金は日割りされて94円になる
- データ定額プランの仮面データパックSを2018-04-01に新規契約して2018-08-01に解約したとき、データ通信量が0バイトの場合、2018-08-31を締め日とした月額料金は日割りされて94円になる
- データ定額プランの仮面データパックSを2018-04-01に新規契約して2018-08-02に解約したとき、データ通信量が0バイトの場合、2018-08-31を締め日とした月額料金は日割りされて187円になる
- データ定額プランの仮面データパックSを2018-04-01に新規契約して2018-08-31に解約したとき、データ通信量が0バイトの場合、2018-08-31を締め日とした月額料金は日割りされて2900円になる
- データ定額プランの仮面データパックSを2018-08-15に新規契約して2018-08-15に解約したとき、データ通信量が0バイトの場合、2018-08-31を締め日とした月額料金は日割りされて94円になる
- データ定額プランの仮面データパックSを2018-08-15に新規契約して2018-08-16に解約したとき、データ通信量が0バイトの場合、2018-08-31を締め日とした月額料金は日割りされて187円になる
- データ定額プランの仮面データパックSを2018-08-01に新規契約したとき、データ通信量が1000000001バイトの場合、2018-08-31を締め日とした月額料金は日割りされて4000円になる
- データ定額プランの仮面データパックSを2018-08-02に新規契約したとき、データ通信量が1000000001バイトの場合、2018-08-31を締め日とした月額料金は日割りされて3871円になる
- データ定額プランの仮面データパックSを2018-08-31に新規契約したとき、データ通信量が1000000001バイトの場合、2018-08-31を締め日とした月額料金は日割りされて129円になる
- データ定額プランの仮面データパックSを2018-04-01に新規契約して2018-08-01に解約したとき、データ通信量が1000000001バイトの場合、2018-08-31を締め日とした月額料金は日割りされて129円になる
- データ定額プランの仮面データパックSを2018-04-01に新規契約して2018-08-02に解約したとき、データ通信量が1000000001バイトの場合、2018-08-31を締め日とした月額料金は日割りされて258円になる
- データ定額プランの仮面データパックSを2018-04-01に新規契約して2018-08-31に解約したとき、データ通信量が1000000001バイトの場合、2018-08-31を締め日とした月額料金は日割りされて4000円になる
- データ定額プランの仮面データパックSを2018-08-15に新規契約して2018-08-15に解約したとき、データ通信量が1000000001バイトの場合、2018-08-31を締め日とした月額料金は日割りされて129円になる
- データ定額プランの仮面データパックSを2018-08-15に新規契約して2018-08-16に解約したとき、データ通信量が1000000001バイトの場合、2018-08-31を締め日とした月額料金は日割りされて258円になる
- データ定額プランの仮面データパックSを2018-08-01に新規契約したとき、データ通信量が3000000001バイトの場合、2018-08-31を締め日とした月額料金は日割りされて5000円になる
- データ定額プランの仮面データパックSを2018-08-02に新規契約したとき、データ通信量が3000000001バイトの場合、2018-08-31を締め日とした月額料金は日割りされて4839円になる
- データ定額プランの仮面データパックSを2018-08-31に新規契約したとき、データ通信量が3000000001バイトの場合、2018-08-31を締め日とした月額料金は日割りされて161円になる
- データ定額プランの仮面データパックSを2018-04-01に新規契約して2018-08-01に解約したとき、データ通信量が3000000001バイトの場合、2018-08-31を締め日とした月額料金は日割りされて161円になる
- データ定額プランの仮面データパックSを2018-04-01に新規契約して2018-08-02に解約したとき、データ通信量が3000000001バイトの場合、2018-08-31を締め日とした月額料金は日割りされて323円になる
- データ定額プランの仮面データパックSを2018-04-01に新規契約して2018-08-31に解約したとき、データ通信量が3000000001バイトの場合、2018-08-31を締め日とした月額料金は日割りされて5000円になる
- データ定額プランの仮面データパックSを2018-08-15に新規契約して2018-08-15に解約したとき、データ通信量が3000000001バイトの場合、2018-08-31を締め日とした月額料金は日割りされて161円になる
- データ定額プランの仮面データパックSを2018-08-15に新規契約して2018-08-16に解約したとき、データ通信量が3000000001バイトの場合、2018-08-31を締め日とした月額料金は日割りされて323円になる
- データ定額プランの仮面データパックSを2018-08-01に新規契約したとき、データ通信量が5000000001バイトの場合、2018-08-31を締め日とした月額料金は日割りされて7000円になる
- データ定額プランの仮面データパックSを2018-08-02に新規契約したとき、データ通信量が5000000001バイトの場合、2018-08-31を締め日とした月額料金は日割りされて6774円になる
- データ定額プランの仮面データパックSを2018-08-31に新規契約したとき、データ通信量が5000000001バイトの場合、2018-08-31を締め日とした月額料金は日割りされて226円になる
- データ定額プランの仮面データパックSを2018-04-01に新規契約して2018-08-01に解約したとき、データ通信量が5000000001バイトの場合、2018-08-31を締め日とした月額料金は日割りされて226円になる
- データ定額プランの仮面データパックSを2018-04-01に新規契約して2018-08-02に解約したとき、データ通信量が5000000001バイトの場合、2018-08-31を締め日とした月額料金は日割りされて452円になる
- データ定額プランの仮面データパックSを2018-04-01に新規契約して2018-08-31に解約したとき、データ通信量が5000000001バイトの場合、2018-08-31を締め日とした月額料金は日割りされて7000円になる
- データ定額プランの仮面データパックSを2018-08-15に新規契約して2018-08-15に解約したとき、データ通信量が5000000001バイトの場合、2018-08-31を締め日とした月額料金は日割りされて226円になる
- データ定額プランの仮面データパックSを2018-08-15に新規契約して2018-08-16に解約したとき、データ通信量が5000000001バイトの場合、2018-08-31を締め日とした月額料金は日割りされて452円になる
- オプションとして何も契約していないとき、2018-08-31を締め日としたオプションの合計金額は0円になる
- オプションとしてあんしん補償サービスだけを2018-04-01に契約しているとき、2018-08-31を締め日としたオプションの合計金額は330円になる
- オプションとしてあんしん遠隔サポートだけを2018-04-01に契約しているとき、2018-08-31を締め日としたオプションの合計金額は400円になる
- オプションとしてあんしんネットセキュリティだけを2018-04-01に契約しているとき、2018-08-31を締め日としたオプションの合計金額は500円になる
- オプションとしてあんしん補償サービスとあんしん遠隔サポートとあんしんネットセキュリティを2018-04-01に契約しているとき、2018-08-31を締め日としたオプションの合計金額は1230円になる
- オプションとしてあんしん補償サービスを2018-08-01に新規契約したとき、2018-08-31を締め日としたこのオプションの個別の金額は初回加入月のため0円になる
- オプションとしてあんしん補償サービスを2018-07-01に新規契約したとき、2018-08-31を締め日としたこのオプションの個別の金額は初回加入月の翌月のため0円になる
- オプションとしてあんしん補償サービスを2018-06-30に新規契約したとき、2018-08-31を締め日としたこのオプションの個別の金額は330円になる
- オプションとしてあんしん補償サービスを1970-01-01に新規契約したとき、2018-08-31を締め日としたこのオプションの個別の金額は330円になる
- オプションとして以前に一度解約したあんしん補償サービスを2018-08-01に再度契約したとき、2018-08-31を締め日としたこのオプションの個別の金額は加入月ではあるが初回ではないため330円になる
- オプションとして以前に一度解約したあんしん補償サービスを2018-08-15に再度契約したとき、2018-08-31を締め日としたこのオプションの個別の金額は月途中であっても日割り計算せずに満額請求となり330円になる
- オプションとして以前に一度解約したあんしん補償サービスを2018-08-31に再度契約したとき、2018-08-31を締め日としたこのオプションの個別の金額は月途中であっても日割り計算せずに満額請求となり330円になる
- オプションとしてあんしん補償サービスを2018-04-01に新規契約して2018-08-01に解約したとき、2018-08-31を締め日としたこのオプションの個別の金額は月途中であっても日割り計算せずに満額請求となり330円になる
- オプションとしてあんしん補償サービスを2018-04-01に新規契約して2018-08-15に解約したとき、2018-08-31を締め日としたこのオプションの個別の金額は月途中であっても日割り計算せずに満額請求となり330円になる
- オプションとしてあんしん補償サービスを2018-04-01に新規契約して2018-08-31に解約したとき、2018-08-31を締め日としたこのオプションの個別の金額は330円になる
- オプションとしてあんしん遠隔サポートを2018-08-01に新規契約したとき、2018-08-31を締め日としたこのオプションの個別の金額は初回加入月のため0円になる
- オプションとしてあんしん遠隔サポートを2018-07-01に新規契約したとき、2018-08-31を締め日としたこのオプションの個別の金額は初回加入月の翌月のため0円になる
- オプションとしてあんしん遠隔サポートを2018-06-30に新規契約したとき、2018-08-31を締め日としたこのオプションの個別の金額は400円になる
- オプションとしてあんしん遠隔サポートを1970-01-01に新規契約したとき、2018-08-31を締め日としたこのオプションの個別の金額は400円になる
- オプションとして以前に一度解約したあんしん遠隔サポートを2018-08-01に再度契約したとき、2018-08-31を締め日としたこのオプションの個別の金額は加入月ではあるが初回ではないため400円になる
- オプションとして以前に一度解約したあんしん遠隔サポートを2018-08-15に再度契約したとき、2018-08-31を締め日としたこのオプションの個別の金額は月途中であっても日割り計算せずに満額請求となり400円になる
- オプションとして以前に一度解約したあんしん遠隔サポートを2018-08-31に再度契約したとき、2018-08-31を締め日としたこのオプションの個別の金額は月途中であっても日割り計算せずに満額請求となり400円になる
- オプションとしてあんしん遠隔サポートを2018-04-01に新規契約して2018-08-01に解約したとき、2018-08-31を締め日としたこのオプションの個別の金額は月途中であっても日割り計算せずに満額請求となり400円になる
- オプションとしてあんしん遠隔サポートを2018-04-01に新規契約して2018-08-15に解約したとき、2018-08-31を締め日としたこのオプションの個別の金額は月途中であっても日割り計算せずに満額請求となり400円になる
- オプションとしてあんしん遠隔サポートを2018-04-01に新規契約して2018-08-31に解約したとき、2018-08-31を締め日としたこのオプションの個別の金額は400円になる
- オプションとしてあんしんネットセキュリティを2018-08-01に新規契約したとき、2018-08-31を締め日としたこのオプションの個別の金額は初回加入月のため0円になる
- オプションとしてあんしんネットセキュリティを2018-07-01に新規契約したとき、2018-08-31を締め日としたこのオプションの個別の金額は初回加入月の翌月のため0円になる
- オプションとしてあんしんネットセキュリティを2018-06-30に新規契約したとき、2018-08-31を締め日としたこのオプションの個別の金額は500円になる
- オプションとしてあんしんネットセキュリティを1970-01-01に新規契約したとき、2018-08-31を締め日としたこのオプションの個別の金額は500円になる
- オプションとして以前に一度解約したあんしんネットセキュリティを2018-08-01に再度契約したとき、2018-08-31を締め日としたこのオプションの個別の金額は加入月ではあるが初回ではないため500円になる
- オプションとして以前に一度解約したあんしんネットセキュリティを2018-08-15に再度契約したとき、2018-08-31を締め日としたこのオプションの個別の金額は月途中であっても日割り計算せずに満額請求となり500円になる
- オプションとして以前に一度解約したあんしんネットセキュリティを2018-08-31に再度契約したとき、2018-08-31を締め日としたこのオプションの個別の金額は月途中であっても日割り計算せずに満額請求となり500円になる
- オプションとしてあんしんネットセキュリティを2018-04-01に新規契約して2018-08-01に解約したとき、2018-08-31を締め日としたこのオプションの個別の金額は月途中であっても日割り計算せずに満額請求となり500円になる
- オプションとしてあんしんネットセキュリティを2018-04-01に新規契約して2018-08-15に解約したとき、2018-08-31を締め日としたこのオプションの個別の金額は月途中であっても日割り計算せずに満額請求となり500円になる
- オプションとしてあんしんネットセキュリティを2018-04-01に新規契約して2018-08-31に解約したとき、2018-08-31を締め日としたこのオプションの個別の金額は500円になる
