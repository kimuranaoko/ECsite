set names utf8;
set foreign_key_checks=0;

drop database if exists kiyurumi;
create database if not exists kiyurumi;

use kiyurumi;

create table user_info(
id int primary key not null auto_increment comment "ID",
user_id varchar(16) unique not null comment "ユーザーID",
password varchar(16) not null comment "パスワード",
family_name varchar(32) not null comment "姓",
first_name varchar(32) not null comment "名",
family_name_kana varchar(32) not null comment "姓かな",
first_name_kana varchar(32) not null comment "名かな",
sex tinyint not null default 0 comment "姓別",
email varchar(32) not null comment "メールアドレス",
status tinyint not null default 0 comment "ステータス",
logined tinyint not null default 0 comment "ログインフラグ",
regist_date datetime not null comment "登録日",
update_date datetime comment "更新日"
)
default charset=utf8
comment="会員情報テーブル"
;

insert into user_info values
(1,"guest","guest","インターノウス","ゲストユーザー","いんたーのうす","げすとゆーざー",0,"guest@gmail.com",0,0,now(),null),
(2,"guest2","guest2","インターノウス","ゲストユーザー2","いんたーのうす","げすとゆーざー2",0,"guest2@gmail.com",0,0,now(),null),
(3,"guest3","guest3","インターノウス","ゲストユーザー3","いんたーのうす","げすとゆーざー3",0,"guest3@gmail.com",0,0,now(),null),
(4,"guest4","guest4","インターノウス","ゲストユーザー4","いんたーのうす","げすとゆーざー4",0,"guest4@gmail.com",0,0,now(),null),
(5,"guest5","guest5","インターノウス","ゲストユーザー5","いんたーのうす","げすとゆーざー5",0,"guest5@gmail.com",0,0,now(),null),
(6,"guest6","guest6","インターノウス","ゲストユーザー6","いんたーのうす","げすとゆーざー6",0,"guest6@gmail.com",0,0,now(),null),
(7,"guest7","guest7","インターノウス","ゲストユーザー7","いんたーのうす","げすとゆーざー7",0,"guest7@gmail.com",0,0,now(),null),
(8,"guest8","guest8","インターノウス","ゲストユーザー8","いんたーのうす","げすとゆーざー8",0,"guest8@gmail.com",0,0,now(),null),
(9,"guest9","guest9","インターノウス","ゲストユーザー9","いんたーのうす","げすとゆーざー9",0,"guest9@gmail.com",0,0,now(),null),
(10,"guest10","guest10","インターノウス","ゲストユーザー10","いんたーのうす","げすとゆーざー10",0,"guest10@gmail.com",0,0,now(),null),
(11,"guest11","guest11","インターノウス","ゲストユーザー11","いんたーのうす","げすとゆーざー11",0,"guest11@gmail.com",0,0,now(),null),
(12,"guest12","guest12","インターノウス","ゲストユーザー12","いんたーのうす","げすとゆーざー12",0,"guest12@gmail.com",0,0,now(),null),
(13,"admin","admin","インターノウス","管理者ユーザー","いんたーのうす","かんりしゃゆーざー",1,"admin@gmail.com",1,0,now(),null),
(14,"admin2","admin2","インターノウス","管理者ユーザー2","いんたーのうす","かんりしゃゆーざー2",1,"admin2@gmail.com",1,0,now(),null),
(15,"admin3","admin3","インターノウス","管理者ユーザー3","いんたーのうす","かんりしゃゆーざー3",1,"admin3@gmail.com",1,0,now(),null),
(16,"admin4","admin4","インターノウス","管理者ユーザー4","いんたーのうす","かんりしゃゆーざー4",1,"admin4@gmail.com",1,0,now(),null),
(17,"admin5","admin5","インターノウス","管理者ユーザー5","いんたーのうす","かんりしゃゆーざー5",1,"admin5@gmail.com",1,0,now(),null),
(18,"admin6","admin6","インターノウス","管理者ユーザー6","いんたーのうす","かんりしゃゆーざー6",1,"admin6@gmail.com",1,0,now(),null),
(19,"admin7","admin7","インターノウス","管理者ユーザー7","いんたーのうす","かんりしゃゆーざー7",1,"admin7@gmail.com",1,0,now(),null),
(20,"admin8","admin8","インターノウス","管理者ユーザー8","いんたーのうす","かんりしゃゆーざー8",1,"admin8@gmail.com",1,0,now(),null),
(21,"admin9","admin9","インターノウス","管理者ユーザー9","いんたーのうす","かんりしゃゆーざー9",1,"admin9@gmail.com",1,0,now(),null),
(22,"admin10","admin10","インターノウス","管理者ユーザー10","いんたーのうす","かんりしゃゆーざー10",1,"admin10@gmail.com",1,0,now(),null),
(23,"admin11","admin11","インターノウス","管理者ユーザー11","いんたーのうす","かんりしゃゆーざー11",1,"admin11@gmail.com",1,0,now(),null),
(24,"admin12","admin12","インターノウス","管理者ユーザー12","いんたーのうす","かんりしゃゆーざー12",1,"admin12@gmail.com",1,0,now(),null);


create table product_info(
id int primary key not null auto_increment comment "ID",
product_id int unique not null comment "商品ID",
product_name varchar(100) unique not null comment "商品名",
product_name_kana varchar(100) not null comment "商品名かな",
product_description varchar(255) not null comment "商品詳細",
category_id int not null not null comment "カテゴリID",
price int comment "価格",
image_file_path varchar(100) comment "画像ファイルパス",
image_file_name varchar(50) comment "画像ファイル名",
birth_date varchar(32) not null comment "生年月日",
birth_place varchar(50) comment "出身地",
status tinyint not null default 0 comment "ステータス",
regist_date datetime not null comment "登録日",
update_date datetime comment "更新日",
foreign key(category_id) references m_category(category_id)
)
default charset=utf8
comment="ゆるキャラ情報テーブル"
;


insert into product_info values
( 1, 1,"きたひろまいピー","きたひろまいぴー",
"長い赤毛がチャームポイントの元気な女の子。寒さに強くなかなかお目にかかれない珍しい赤毛米から生まれた。ひとこと『赤毛米っていうのは、中山のおじいちゃんが作った北海道米の元祖なのよ！』☆サイズ：160-180cm☆重量：40kg",
2,20000,"./images","2hokkaido.jpg","2013-01-04","北海道北広島市島松",0,now(),null),

( 2, 2,"たけっこくん","たけっこくん","碇ヶ関特産の「たけのこ」と「かけっこ」がコラボして生まれた走るのが大好きなキャラクター。ひとこと『走ることがボクの使命。目標は、なんと言っても2020年東京五輪！』☆サイズ：140-180cm☆重量：35kg",
2,40000,"./images","2aomori.jpg","2013-01-04","青森県平川市碇ヶ関",0,now(),null),

( 3, 3,"こまちちゃん","こまちちゃん","平安時代の女流歌人で絶世の美女、小野小町ゆかりの秋田美人。ひとこと『好きな食べ物は、日本三銘うどんの一つと称される稲庭うどんと三関さくらんぼ！』☆サイズ：160-190cm☆重量：30kg",
2,100000,"./images","2akita.jpg","2004-11-11","秋田県湯沢市",0,now(),null),

( 4, 4,"ねじりほんにょ","ねじりほんにょ","米どころ栗原の残したい原風景のひとつ、刈り取った稲の束を少しずつずらしながら棒に掛け乾燥させる「ねじりほんにょ」から誕生。ひとこと『みんなでねじりほんにょダンスを踊ろう!』☆サイズ：140-160cm☆重量：40kg",
2,6000,"./images","2miyagi.jpg","2013-3-21","宮城県栗原市",0,now(),null),

( 5, 5,"んだべぇ","んだべぇ","南会津町の美味しい特産品が大好きになり、いつの間にか南会津町に住みついていた食いしん坊の妖精。ひとこと『笠と雪ぐつを着用しているのは、南会津の寒さを耐え忍ぶため先人の知恵を取り入れたんだ！』☆サイズ：140-160cm☆重量：40kg",
2,3000000,"./images","2fukushima.jpg","2017-03-20","福島県南会津町",0,now(),null),

( 6, 6,"かわべえ","かわべえ","川島町のことなら何でも知っているおじいさん。かわみんという可愛い孫娘がいる。川島町の特産品である「いちじく」がモチーフ。ひとこと『川島のことならなんでも知っとるんじゃ～。』☆サイズ：150-170cm☆重量：15kg",
3,30000,"./images","3kawabe.jpg","2018-05-18","埼玉県川島町",0,now(),null),

( 7, 7,"みどりん","みどりん","みどりの葉っぱをモチーフに、服は緑区の木「かえで」と「ミ」の文字。ほっぺは、緑区の花「シラン」のパープルの花びら。ひとこと『得意技は光合成！酸素を生み出すことで、地球温暖化防止に貢献していますよ。』☆サイズ：150-170cm☆重量：15kg",
3,30000,"./images","3midorin.jpg","2018-10-01","神奈川県三浦市",0,now(),null),

( 8, 8,"三浦ツナ之介","みうらつなのすけ","三浦一族の鎧を着ることで陸に上がれるようなったマグロ。最近は健康に気を使い、脇差の三浦大根をかじりつつ野菜中心生活に。ひとこと『疲れると横になって目を開けたまま寝ちゃうんだ～。』☆サイズ：150-170cm☆重量：15kg",
3,30000,"./images","3misakitunanosuke.jpg","2018-10-10","神奈川県三浦市",0,now(),null),

( 9, 9,"カシワニ","かしわに","柏市手賀沼在住の海がちょっと苦手なワニ。「日本一汚い沼」だった時はさすがに住めなくて近くの川に居候。最近ようやく住めるようになって帰ってきた。友だちと柏が大好き。ひとこと『おいでよ！カシワに！』☆サイズ：150-170cm☆重量：15kg",
3,30000,"./images","3kashiwani.jpg","2018-05-05","千葉県柏市",0,now(),null),

( 10, 10,"みらいくんとのぞみちゃん","みらいくんとのぞみちゃん","水のしずくをイメージしたみらいくんと、富の川越いもの帽子にほうれん草をイメージした羽をもつガールフレンドのぞみちゃん。ひとこと『三芳町のPRのため今日もあちこち飛び回っています！』☆サイズ：150-170cm☆重量：15kg",
3,50000,"./images","3miraikun_nozomichan.jpg","2018-11-03","埼玉県三芳町",0,now(),null),

( 11, 11,"リークル","りーくる","福井競輪場に舞い降りた自転車の妖精。特技は自転車に乗ること。1秒でも早くゴールへつくことを目標に日々特訓中。ひとこと『(現在レース特訓中のためコメントがいただけませんでした。)』☆サイズ：160-175cm☆重量：400g",
4,3000,"./images","4ri_kuru_hukui.jpg","1998-05-03","福井県福井市",0,now(),null),

( 12, 12,"こうにゅうどうくん","こうにゅうどうくん","いたずら好きな妖怪の男の子。好物はとんてき、そうめん、鍋料理。趣味は人をびっくりさせること。宝物はかぶせ茶、大入道、日永うちわ、コンビナート夜景。ひとこと『永遠の6歳だよん！』☆サイズ；160-175cm☆重量：4kg",
4,40000,"./images","4kounyuudoukun_mie.jpg","1997-08-01","三重県四日市市",0,now(),null),

( 13, 13,"はまぽん","はまぽん","八幡浜ちゃんぽんをモチーフに「ちゃんぽん」と「チャンピオン」をかけた王様っぽい風貌で、チャームポイントは八幡浜の八の字の赤いひげ。ひとこと『市民のソウルフード「八幡浜ちゃんぽん」のPRに日々努めているぽん』☆サイズ：165-175cm☆重量：1kg",
4,8500,"./images","4hamapon_ehime.jpg","2000-02-09","愛媛県八幡浜市",0,now(),null),

( 14, 14,"まっくん","まっくん","アカマツの妖精。特技は林の中でのウォーキングにEXILEのMAKIDAIさん直伝のまっくんターン(まだ練習中)。活動範囲を大芝高原から南箕輪村に拡大中。ひとこと『大芝高原味工房のジェラートは最高だよ！！』☆サイズ：170-185cm☆重量：3.5kg",
4,100000,"./images","4makkun_nagano.jpg","1994-11-21","長野県上伊那郡南箕輪村",0,now(),null),

( 15, 15,"マメリン","まめりん","豆年齢は23歳(収穫後2時間30分)。長岡市の肥沃な大地で枝豆の突然変異で生まれた、兄弟100男200女の300人兄弟のひとり。好きな言葉は桃栗3年枝豆100日。ひとこと『特徴は茹でた枝豆の甘いかおりがすることだわ！』☆サイズ：170-180cm☆重量：1.5kg",
4,25000,"./images","4mamerin_niigata.jpg","2018-10-13","新潟県長岡市",0,now(),null),

( 16, 16,"うどん脳","うどんのう","饂飩(うどん)が大好きで饂飩ばかり食べてたら、ある朝目覚めると「うどん脳」になってた元人間の妖怪ツル。ひとこと『饂飩一杯、幸せいっぱい！(う)(ど)(ん)』☆サイズ：170-190☆重量：80kg",
5,20000,"./images","5udonnou.jpg","2017-07-07","香川県高松市",0,now(),null),

( 17, 17,"なーしくん","なーしくん","特別天然記念物のニホンカワウソがモチーフ。特産品の愛南ゴールドの帽子、真珠のネックレス、ヒオウギ貝の形をした前掛けをいつもしている。尻尾には餌と間違えて喰いついた相棒のカツオのかっちゃん。ひとこと『なーしくんだよ！よろしくね。』☆サイズ：150-180cm☆重量：20kg",
5,20000,"./images","5na-shikun.jpg","2018-05-03","愛媛県愛南町",0,now(),null),

( 18, 18,"バリィさん","ばりぃさん","頭に来島海峡大橋をイメージしたクラウンをかぶりタオル生地のハラマキをし、手には特注の船の形の財布を持っている。ひとこと『今治をもっとしってほしいんよね！』☆サイズ：150-180cm☆重量：20kg",
5,20000,"./images","5barisan.jpg","2018-08-03","愛媛県今治市",0,now(),null),

( 19, 19,"い～にゃん","い～にゃん","島根県飯南町の緑豊かな森に住んでる食いしん坊なねこ。ヤマイモ、コシヒカリ、奥出雲和牛!!飯南町のことならなんでもしってる。ひとこと『飯南町にょい～にゃんだにゃ☆』☆サイズ：150-180cm☆重量：20kg",
5,20000,"./images","5i-nyan.jpg","2018-01-01","島根県飯南町",0,now(),null),

( 20, 20,"ゆずがっぱ","ゆずがっぱ","徳島県那賀町の木頭(きとう)というものお椀をひっくり返したような形の氷の小山を作る珍しい黒滝という滝がある山奥でうまれた、かっぱとゆずのキャラクターです。ひとこと『夢は木頭にコンビにをつくることだよ～☆』☆サイズ：150-180cm☆重量：20kg",
5,20000,"./images","5kappa.jpg","2018-07-31","徳島県那賀町",0,now(),null),

( 21, 21,"軍艦島のガンショーくん","ぐんかんじまのがんしょーくん","2015年に「明治日本の革命遺産」として世界遺産に登録された軍艦島をモチーフにしたキャラクター。ひとこと『今日も人間がやってくるのを待っているよ。』☆サイズ：160-180cm☆重量：5kg",
6,50000,"./images","6nagasaki_gunkanjimanoGanshokun.jpg","2017-07-12","長崎県軍艦島",0,now(),null),

( 22, 22,"とりまるくん＆とりまる子ちゃん","とりまるくんととりまるこちゃん","宮崎地鶏、炭火焼TORIMARUの公式キャラクターに任命。トサカに「0220」と刻印されている。ひとこと『彼女のとりまる子ちゃんと一緒にいろんなイベントを盛り上げに行きてぇなっち思っちょん。』☆サイズ：160-170cm☆重量：20kg",
6,100000,"./images","6miyazaki_torimarukun torimarukochan.jpg","2013-02-20","大分県大分市中央町",0,now(),null),

( 23, 23,"ゴーヤくん","ごーやくん","日本のはるか南の島から世界へ走り出すゴーヤ君！ゆるキャラ界No1の健脚！笑いと元気を世界に走っています。フルマラソンを5時間8分のゆるキャラ世界最高記録保持者。ひとこと『石垣島在住のアスリートだよ!』☆サイズ：120-190cm☆重量：15kg",
6,20000,"./images","6okinawa_goyakun.jpg","2018-05-18","沖縄県石垣島",0,now(),null),

( 24, 24,"千梅ちゃん","ちうめちゃん","今までのゆるキャラの枠を超えて歌手デビューも果たしているおてんば看板娘。ひとこと『独特な「ちうめ語」を堪能しに会いにきて。らぶりー!』☆サイズ：130-150cm☆重量：5kg",
6,50000,"./images","6fukuoka_chiume.jpg","2018-06-12","福岡県太宰府市",0,now(),null),

( 25, 25,"宙太くん","ちゅうたくん","宇宙とロケットをモチーフに誕生したキャラクターで、ポルトガルから種子島に伝わった火縄銃をイメージしたアンテナが付いている。ひとこと『アンテナを使って南種子町のPRをしているよ！』☆サイズ：150-185cm☆重量：20kg",
6,100000,"./images","6kagoshima_tchutakun.jpg","1997-04-12","鹿児島県南種子町役場",0,now(),null);


create table cart_info(
id int primary key not null auto_increment comment "ID",
user_id varchar(16) not null comment "ユーザーID",
temp_user_id varchar(16) comment "仮ユーザーID",
product_id int not null comment "商品ID",
product_count int not null comment "個数",
price int not null comment "金額",
regist_date datetime not null comment "登録日",
update_date datetime comment "更新日"
)
default charset=utf8
comment="カート情報テーブル"
;


create table purchase_history_info(
id int primary key not null auto_increment comment "ID",
user_id varchar(16) not null comment "ユーザーID",
product_id int not null comment "商品ID",
product_count int not null comment "個数",
price int not null comment "金額",
destination_id int not null comment "宛先情報ID",
regist_date datetime not null comment "登録日",
update_date datetime comment "更新日",
foreign key(user_id) references user_info(user_id) on delete cascade,
foreign key(product_id) references product_info(product_id) on delete cascade
)
default charset=utf8
comment="購入履歴情報テーブル"
;


create table destination_info(
id int primary key not null auto_increment comment "ID",
user_id varchar(16) not null comment "ユーザーID",
family_name varchar(32) not null comment "姓",
first_name varchar(32) not null comment "名",
family_name_kana varchar(32) not null comment "姓かな",
first_name_kana varchar(32) not null comment "名かな",
email varchar(32) not null comment "メールアドレス",
tel_number varchar(13) not null comment "電話番号",
user_address varchar(50) not null comment "住所",
regist_date datetime not null comment "登録日",
update_date datetime comment "更新日"
)
default charset=utf8
comment="宛先情報テーブル"
;
insert into destination_info values
(1,"guest","インターノウス","テストユーざー","いんたーのうす","てすとゆーざー","guest@internous.co.jp","080-1234-5678","東京都千代田区三番町１ー１ＫＹ三番町ビル１Ｆ",now(),null),
(2,"guest","インターノウス２","テストユーざー２","いんたーのうす２","てすとゆーざー２","aiueoaiueo@internous.co.jp","123-4568-7589","東京都千代田区三番町１ー１ＫＹ三番町ビル２Ｆ",now(),null);


create table m_category(
id int primary key not null comment "ID",
category_id int not null unique comment "カテゴリID",
category_name varchar(20) not null unique comment "カテゴリ名",
category_description varchar(100) comment "カテゴリ詳細",
insert_date datetime not null comment "登録日",
update_date datetime comment "更新日"
)
default charset=utf8
comment="カテゴリマスタテーブル"
;
insert into m_category values
(1,1,"全国","全国が対象となります",now(), null),
(2,2,"北海道_東北","北海道と東北地方が対象となります",now(),null),
(3,3,"関東","関東地方が対象となります",now(), null),
(4,4,"中部_近畿","中部地方と近畿地方が対象となります",now(),null),
(5,5,"中国_四国","中国地方と四国地方が対象となります",now(), null),
(6,6,"九州_沖縄","九州地方と沖縄が対象となります",now(),null);