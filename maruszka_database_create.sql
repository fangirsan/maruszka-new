create table additive (additive_name varchar(255), id bigint not null, primary key (id)) engine=InnoDB;
create table batch (id bigint not null auto_increment, rager_ibu decimal(19,2), tinseth_ibu decimal(19,2), alcohol_volume decimal(19,2), amount_of_priming_material integer not null, batch_number integer, blg1 decimal(19,2), blg2 decimal(19,2), bottling_date date, calculated_ibu decimal(19,2), creation_date date, designation varchar(255), efficiency decimal(19,2), fermentation_date date, maturation_date date, priming_material varchar(255), volume decimal(4,1), batch_comments_id bigint, beer_style_id bigint, primary key (id)) engine=InnoDB;
create table batch_comments (id bigint not null auto_increment, comment longtext, batch_id bigint, primary key (id)) engine=InnoDB;
create table batch_ingredient (amount integer not null, batch_id bigint not null, ingredient_id bigint not null, way_of_serving varchar(255) not null, primary key (amount, batch_id, ingredient_id, way_of_serving)) engine=InnoDB;
create table batch_malt_conversion (batch_id bigint not null, malt_conversion_id bigint not null, minutes integer not null, primary key (batch_id, malt_conversion_id, minutes)) engine=InnoDB;
create table beer_style (id bigint not null auto_increment, abv1 decimal(4,1), abv2 decimal(4,1), beer_style_name varchar(255), ebc1 integer, ebc2 integer, final_blg_1 decimal(4,1), final_blg_2 decimal(4,1), final_gravity_1 decimal(4,3), final_gravity_2 decimal(4,3), ibu1 integer, ibu2 integer, original_blg_1 decimal(4,1), original_blg_2 decimal(4,1), original_gravity_1 decimal(4,3), original_gravity_2 decimal(4,3), srm1 integer, srm2 integer, primary key (id)) engine=InnoDB;
create table country (id bigint not null auto_increment, country_code varchar(255), country_name varchar(255), primary key (id)) engine=InnoDB;
create table hop (alpha_acid_max decimal(3,1), alpha_acid_min decimal(3,1), aroma_hop bit, bitter_hop bit, hop_name varchar(255), id bigint not null, country_id bigint, primary key (id)) engine=InnoDB;
create table ingredient (in_type varchar(31) not null, id bigint not null auto_increment, in_name varchar(255), primary key (id)) engine=InnoDB;
create table malt (malt_ebc integer, malt_filling integer, malt_usage varchar(255), id bigint not null, country_id bigint, producer_id bigint not null, primary key (id)) engine=InnoDB;
create table malt_conversion_rest (id bigint not null auto_increment, description varchar(255), rest_name varchar(255), temp1 integer, temp2 integer, primary key (id)) engine=InnoDB;
create table producer (id bigint not null auto_increment, producer_name varchar(255), product varchar(255), primary key (id)) engine=InnoDB;
create table yeast (alcohol_tolerance decimal(2,0), fermentation_temp_max decimal(3,1), fermentation_temp_min decimal(3,1), flocculation integer, fermentation_type integer, yeast_name varchar(255), yeast_type varchar(255), id bigint not null, producer_id bigint, primary key (id)) engine=InnoDB;
alter table additive add constraint UK_61m5h7usysjj2j7jxtslhq83s unique (additive_name);
alter table beer_style add constraint UK_sowx1f440q3tqgxq5wc67l59e unique (beer_style_name);
alter table country add constraint UK_qrkn270121aljmucrdbnmd35p unique (country_name);
alter table hop add constraint UK_7f5uq8m0m1ey70mv84r2k6o2f unique (hop_name);
alter table ingredient add constraint UK_fl26nw3abg961kh0l8s5ojonv unique (in_name);
alter table yeast add constraint UK_4n8cuwy5vvgu40kon4suh120a unique (yeast_name);
alter table additive add constraint FKs71wv6q8broma753ungc4s8i foreign key (id) references ingredient (id);
alter table batch add constraint FKseg5h3yqh1uyfl46vut0o8fay foreign key (batch_comments_id) references batch_comments (id);
alter table batch add constraint FKkhjvygi25r299vx1dx7pjqvcy foreign key (beer_style_id) references beer_style (id);
alter table batch_comments add constraint FKs4tce7mavhst58nkvmwb7euek foreign key (batch_id) references batch (id);
alter table batch_ingredient add constraint FKlfko1nq7bp1tue31uijr8kdkw foreign key (batch_id) references batch (id);
alter table batch_ingredient add constraint FKfwsoy5y5sgvxecegg9a7n4xmj foreign key (ingredient_id) references ingredient (id);
alter table batch_malt_conversion add constraint FKedeug7o6inravkhp14ami6pux foreign key (batch_id) references batch (id);
alter table hop add constraint FKq9k4gqqviv7qiiu2kfe0x7cc foreign key (country_id) references country (id);
alter table hop add constraint FKnw8lbijgra4k1vvmutqcdqhbs foreign key (id) references ingredient (id);
alter table malt add constraint FKoic4jn99yvdjep9wlxy0fc6jg foreign key (country_id) references country (id);
alter table malt add constraint FKp4nne04jqsxhkmrmamvaof9ca foreign key (producer_id) references producer (id);
alter table malt add constraint FKp07pikubnyw581g9axvp3dld4 foreign key (id) references ingredient (id);
alter table yeast add constraint FK64ef7w304mr2wmahw1jypqfnh foreign key (producer_id) references producer (id);
alter table yeast add constraint FK2tixgnwt8iaj2j8bk6epkgb9f foreign key (id) references ingredient (id);
create table additive (additive_name varchar(255), id bigint not null, primary key (id)) engine=InnoDB;
create table batch (id bigint not null auto_increment, rager_ibu decimal(19,2), tinseth_ibu decimal(19,2), alcohol_volume decimal(19,2), amount_of_priming_material integer not null, batch_number integer, blg1 decimal(19,2), blg2 decimal(19,2), bottling_date date, calculated_ibu decimal(19,2), creation_date date, designation varchar(255), efficiency decimal(19,2), fermentation_date date, maturation_date date, priming_material varchar(255), volume decimal(4,1), batch_comments_id bigint, beer_style_id bigint, primary key (id)) engine=InnoDB;
create table batch_comments (id bigint not null auto_increment, comment longtext, batch_id bigint, primary key (id)) engine=InnoDB;
create table batch_ingredient (amount integer not null, batch_id bigint not null, ingredient_id bigint not null, way_of_serving varchar(255) not null, primary key (amount, batch_id, ingredient_id, way_of_serving)) engine=InnoDB;
create table batch_malt_conversion (batch_id bigint not null, malt_conversion_id bigint not null, minutes integer not null, primary key (batch_id, malt_conversion_id, minutes)) engine=InnoDB;
create table beer_style (id bigint not null auto_increment, abv1 decimal(4,1), abv2 decimal(4,1), beer_style_name varchar(255), ebc1 integer, ebc2 integer, final_blg_1 decimal(4,1), final_blg_2 decimal(4,1), final_gravity_1 decimal(4,3), final_gravity_2 decimal(4,3), ibu1 integer, ibu2 integer, original_blg_1 decimal(4,1), original_blg_2 decimal(4,1), original_gravity_1 decimal(4,3), original_gravity_2 decimal(4,3), srm1 integer, srm2 integer, primary key (id)) engine=InnoDB;
create table country (id bigint not null auto_increment, country_code varchar(255), country_name varchar(255), primary key (id)) engine=InnoDB;
create table hop (alpha_acid_max decimal(3,1), alpha_acid_min decimal(3,1), aroma_hop bit, bitter_hop bit, hop_name varchar(255), id bigint not null, country_id bigint, primary key (id)) engine=InnoDB;
create table ingredient (in_type varchar(31) not null, id bigint not null auto_increment, in_name varchar(255), primary key (id)) engine=InnoDB;
create table malt (malt_ebc integer, malt_filling integer, malt_usage varchar(255), id bigint not null, country_id bigint, producer_id bigint not null, primary key (id)) engine=InnoDB;
create table malt_conversion_rest (id bigint not null auto_increment, description varchar(255), rest_name varchar(255), temp1 integer, temp2 integer, primary key (id)) engine=InnoDB;
create table producer (id bigint not null auto_increment, producer_name varchar(255), product varchar(255), primary key (id)) engine=InnoDB;
create table yeast (alcohol_tolerance decimal(2,0), fermentation_temp_max decimal(3,1), fermentation_temp_min decimal(3,1), flocculation integer, fermentation_type integer, yeast_name varchar(255), yeast_type varchar(255), id bigint not null, producer_id bigint, primary key (id)) engine=InnoDB;
alter table additive add constraint UK_61m5h7usysjj2j7jxtslhq83s unique (additive_name);
alter table beer_style add constraint UK_sowx1f440q3tqgxq5wc67l59e unique (beer_style_name);
alter table country add constraint UK_qrkn270121aljmucrdbnmd35p unique (country_name);
alter table hop add constraint UK_7f5uq8m0m1ey70mv84r2k6o2f unique (hop_name);
alter table ingredient add constraint UK_fl26nw3abg961kh0l8s5ojonv unique (in_name);
alter table yeast add constraint UK_4n8cuwy5vvgu40kon4suh120a unique (yeast_name);
alter table additive add constraint FKs71wv6q8broma753ungc4s8i foreign key (id) references ingredient (id);
alter table batch add constraint FKseg5h3yqh1uyfl46vut0o8fay foreign key (batch_comments_id) references batch_comments (id);
alter table batch add constraint FKkhjvygi25r299vx1dx7pjqvcy foreign key (beer_style_id) references beer_style (id);
alter table batch_comments add constraint FKs4tce7mavhst58nkvmwb7euek foreign key (batch_id) references batch (id);
alter table batch_ingredient add constraint FKlfko1nq7bp1tue31uijr8kdkw foreign key (batch_id) references batch (id);
alter table batch_ingredient add constraint FKfwsoy5y5sgvxecegg9a7n4xmj foreign key (ingredient_id) references ingredient (id);
alter table batch_malt_conversion add constraint FKedeug7o6inravkhp14ami6pux foreign key (batch_id) references batch (id);
alter table hop add constraint FKq9k4gqqviv7qiiu2kfe0x7cc foreign key (country_id) references country (id);
alter table hop add constraint FKnw8lbijgra4k1vvmutqcdqhbs foreign key (id) references ingredient (id);
alter table malt add constraint FKoic4jn99yvdjep9wlxy0fc6jg foreign key (country_id) references country (id);
alter table malt add constraint FKp4nne04jqsxhkmrmamvaof9ca foreign key (producer_id) references producer (id);
alter table malt add constraint FKp07pikubnyw581g9axvp3dld4 foreign key (id) references ingredient (id);
alter table yeast add constraint FK64ef7w304mr2wmahw1jypqfnh foreign key (producer_id) references producer (id);
alter table yeast add constraint FK2tixgnwt8iaj2j8bk6epkgb9f foreign key (id) references ingredient (id);
