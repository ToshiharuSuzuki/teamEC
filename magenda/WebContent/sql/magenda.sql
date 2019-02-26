set names utf8;
set foreign_key_checks=0;

drop database if exists magenda;
create database if not exists magenda;

use magenda;

create table user_info(
id int  primary key not null auto_increment comment "ID",
user_id varchar(16) unique not null comment "ユーザーID",
password varchar(16) not null comment "パスワード",
family_name varchar(32) not null comment "姓",
first_name varchar(32) not null comment "名",
family_name_kana varchar(32) not null comment "姓かな",
first_name_kana varchar(32) not null comment "名かな",
sex tinyint not null default 0 comment "性別",
email varchar(32) not null comment "メールアドレス",
status tinyint not null default 0 comment "ステータス",
logined tinyint not null default 0 comment "ログインフラグ",
regist_date datetime not null comment "登録日",
update_date datetime comment "更新日");

insert user_info values
(1,"guest","guest","インターノウス","ゲストユーザー","いんたーのうす","げすとゆーざー",0,"guest@gmail.com",0,0,now(),now()),
(2,"guest2","guest2","インターノウス","ゲストユーザー2","いんたーのうす","げすとゆーざー2",0,"guest2@gmail.com",0,0,now(),now()),
(3,"guest3","guest3","インターノウス","ゲストユーザー3","いんたーのうす","げすとゆーざー3",0,"guest3@gmail.com",0,0,now(),now()),
(4,"guest4","guest4","インターノウス","ゲストユーザー4","いんたーのうす","げすとゆーざー4",0,"guest4@gmail.com",0,0,now(),now()),
(5,"guest5","guest5","インターノウス","ゲストユーザー5","いんたーのうす","げすとゆーざー5",0,"guest5@gmail.com",0,0,now(),now()),
(6,"guest6","guest6","インターノウス","ゲストユーザー6","いんたーのうす","げすとゆーざー6",0,"guest6@gmail.com",0,0,now(),now()),
(7,"guest7","guest7","インターノウス","ゲストユーザー7","いんたーのうす","げすとゆーざー7",0,"guest7@gmail.com",0,0,now(),now()),
(8,"guest8","guest8","インターノウス","ゲストユーザー8","いんたーのうす","げすとゆーざー8",0,"guest8@gmail.com",0,0,now(),now()),
(9,"guest9","guest9","インターノウス","ゲストユーザー9","いんたーのうす","げすとゆーざー9",0,"guest9@gmail.com",0,0,now(),now()),
(10,"guest10","guest10","インターノウス","ゲストユーザー10","いんたーのうす","げすとゆーざー10",0,"guest10@gmail.com",0,0,now(),now()),
(11,"guest11","guest11","インターノウス","ゲストユーザー11","いんたーのうす","げすとゆーざー11",0,"guest11@gmail.com",0,0,now(),now()),
(12,"guest12","guest12","インターノウス","ゲストユーザー12","いんたーのうす","げすとゆーざー12",0,"guest12@gmail.com",0,0,now(),now());

create table product_info(
id int primary key not null auto_increment comment "ID",
product_id int unique not null comment "商品ID ",
product_name varchar(100) unique not null comment "商品名",
product_name_kana varchar(100) unique not null comment "商品名かな",
product_description varchar(255) not null comment "商品詳細",
category_id int not null comment "カテゴリID",
price int comment "価格",
image_file_path varchar(100) comment "画像ファイルパス",
image_file_name varchar(50) comment "画像ファイル名",
release_date datetime not null comment "発売年月",
release_company varchar(50) comment "発売会社",
status tinyint not null default 0 comment "ステータス",
regist_date datetime not null comment "登録日",
update_date datetime comment "更新日",
foreign key(category_id) references m_category(category_id))
default charset=utf8
comment="商品情報テーブル";

insert into product_info values
(1,1,"ボーダーチェア","ぼーだーちぇあ","ボーダー柄のチェアです。",2,20000,"./images","chair1.jpg",now(),"megenda株式会社",0,now(),now()),
(2,2,"大きいチェア","おおきいちぇあ","おおきいチェアです。",2,32000,"./images","chair2.jpg",now(),"megenda株式会社",0,now(),now()),
(3,3,"ゴージャスチェア","ごーじゃすちぇあ","ゴージャスなチェアです。",2,40000,"./images","chair3.jpg",now(),"megenda株式会社",0,now(),now()),
(4,4,"木のチェア","きのちぇあ","木のチェアです。",2,8000,"./images","chair4.jpg",now(),"megenda株式会社",0,now(),now()),
(5,5,"アンティークチェア","あんてぃーくちぇあ","アンティークなチェアです。",2,25000,"./images","chair5.jpg",now(),"megenda株式会社",0,now(),now()),
(6,6,"収納デスク","しゅうのうですく","引き出しが多いデスクです。",3,155000,"./images","desk1.jpg",now(),"megenda株式会社",0,now(),now()),
(7,7,"おしゃれデスク","おしゃれですく","おしゃれなデスクです。",3,185000,"./images","desk2.jpg",now(),"megenda株式会社",0,now(),now()),
(8,8,"小物入れデスク","こものいれですく","小物入れデスクです。",3,55000,"./images","desk3.jpg",now(),"megenda株式会社",0,now(),now()),
(9,9,"珍しいデスク","めずらしいですく","珍しいデスクです。",3,200000,"./images","desk4.jpg",now(),"megenda株式会社",0,now(),now()),
(10,10,"多機能デスク","たきのうですく","多機能なデスクです。",3,225000,"./images","desk5.jpg",now(),"megenda株式会社",0,now(),now()),
(11,11,"ふんわりベッド","ふんわりべっど","ふんわりしたベッドです。",4,280000,"./images","bed1.jpg",now(),"megenda株式会社",0,now(),now()),
(12,12,"やわらかベッド","やわらかべっど","やわらかいベッドです。",4,1250000,"./images","bed2.jpg",now(),"megenda株式会社",0,now(),now()),
(13,13,"もふもふベッド","もふもふべっど","もふもふのベッドです。",4,500000,"./images","bed3.jpg",now(),"megenda株式会社",0,now(),now()),
(14,14,"ふかふかベッド","ふかふかべっど","ふかふかのベッドです。",4,620000,"./images","bed4.jpg",now(),"megenda株式会社",0,now(),now()),
(15,15,"もこもこベッド","もこもこべっど","もこもこのベッドです。",4,1000000,"./images","bed5.jpg",now(),"megenda株式会社",0,now(),now());

create table cart_info(
id int primary key not null auto_increment comment "ID",
user_id varchar(16) not null comment "ユーザーID",
temp_user_id varchar(16) comment "仮ユーザーID",
product_id int not null comment "商品ID",
product_count int not null comment "個数",
price int not null comment "金額",
regist_date datetime not null comment "登録日",
update_date datetime comment "更新日");

create table purchase_history_info(
id int primary key not null auto_increment comment "ID",
user_id varchar(16) not null comment "ユーザーID",
product_id int not null comment "商品ID",
product_count int not null comment "個数",
price int not null comment "金額",
destination_id int not null comment "宛先情報ID",
regist_date datetime not null comment "登録日",
update_date datetime comment "更新日",
foreign key(product_id) references product_info(product_id));

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
update_date datetime comment "更新日");

create table m_category(
id int primary key not null auto_increment comment "ID",
category_id int unique not null comment "カテゴリID",
category_name varchar(20) unique not null comment "カテゴリ名",
category_description varchar(100) comment "カテゴリ詳細",
regist_date datetime not null comment "登録日",
update_date datetime comment "更新日")
default charset=utf8
comment="カテゴリマスタテーブル";

insert into m_category values
(1,1,"全てのカテゴリー","チェア、デスク、ベッド全てのカテゴリーが対象となります",now(), now()),
(2,2,"チェア","チェアに関するカテゴリーが対象となります",now(),now()),
(3,3,"デスク","デスクに関するカテゴリーが対象となります",now(),now()),
(4,4,"ベッド","ベッドに関するカテゴリーが対象となります",now(),now());
